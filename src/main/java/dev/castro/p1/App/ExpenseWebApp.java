package dev.castro.p1.App;
import com.google.gson.Gson;
import dev.castro.p1.DAOs.EmployeeDaoPostgresImpl;
import dev.castro.p1.DAOs.ExpenseDaoPostgresImpl;
import dev.castro.p1.Entities.Employee;
import dev.castro.p1.Entities.Expense;
import dev.castro.p1.Enums.Status;
import dev.castro.p1.Exceptions.DuplicateResource;
import dev.castro.p1.Exceptions.ResourceNotFound;
import dev.castro.p1.Services.EmployeeServices;
import dev.castro.p1.Services.EmployeeServicesImpl;
import dev.castro.p1.Services.ExpenseServices;
import dev.castro.p1.Services.ExpenseServicesImpl;
import io.javalin.Javalin;

import java.util.List;

public class ExpenseWebApp {

    public static Gson gson = new Gson();
    public static EmployeeServices employeeServices = new EmployeeServicesImpl(new EmployeeDaoPostgresImpl());
    public static ExpenseServices expenseServices = new ExpenseServicesImpl(new ExpenseDaoPostgresImpl());


    public static void main(String[] args) {
        Javalin app = Javalin.create();



        // Start of Exmployee Routes



        app.post("/employees", context -> {
            try {
                String body = context.body();
                Employee employee = gson.fromJson(body, Employee.class);
                employeeServices.createEmployee(employee);
                context.status(201);
                String eJson = gson.toJson(employee);
                context.result(eJson);
            }catch (DuplicateResource e){
                context.status(409);
                context.result("Exmployee already exists.");
            }
        });

        app.get("/employees", context -> {
            try{
                List<Employee> employees = employeeServices.getAllEmployee();
                String employeeJSON = gson.toJson(employees);
                context.result(employeeJSON);
            }catch (ResourceNotFound e){
                context.status(404);
                context.result("Currently no employees exist.");
            }

        });
        app.get("/employees/{eid}", context -> {

            int eid = Integer.parseInt(context.pathParam("eid"));
            try {
                String employeeJSON =  gson.toJson(employeeServices.getEmployeeByEid(eid));
                context.result(employeeJSON);
            }catch (ResourceNotFound e){
                context.status(404);
                context.result("Employee with EID: "+eid+" not found.");
            }


        });
        app.put("/employees/{eid}", context -> {
            int eid = Integer.parseInt(context.pathParam("eid"));
            try {
                String body = context.body();
                Employee employee = gson.fromJson(body, Employee.class);
                employee.setEID(eid);
                employeeServices.updateEmployeeInformation(employee);
                context.result("Name Changed");
            }catch (ResourceNotFound e) {
                context.status(404);
                context.result("Employee with EID: " + eid + " not found.");
            }
        });

        app.delete("/employees/{eid}",context -> {
            int eid = Integer.parseInt(context.pathParam("eid"));
            try{
                String employeeJSON = gson.toJson(employeeServices.deleteEmployeeByEid(eid));
                context.result(employeeJSON);
            }catch(ResourceNotFound e){
                context.status(404);
                context.result("Employee with EID: "+eid+" not found");
            }
        });



        // START OF EXPENSE ROUTES



        app.post("/employees/{eid}/expenses", context -> {
            int eid = Integer.parseInt(context.pathParam("eid"));
            try {
                String body = context.body();
                Expense expense = gson.fromJson(body, Expense.class);
                expense.setEid(eid);
                expenseServices.createExpense(expense);
                context.status(201);
                String eJson = gson.toJson(expense);
                context.result(eJson);
            }catch (DuplicateResource e){
                context.status(409);
                context.result("The expense already exists.");
            }
        });

        app.get("/employees/{eid}/expenses", context -> {
            try {
                List<Expense> expenses = expenseServices.getAllExpense();
                String expenseJSON = gson.toJson(expenses);
                context.result(expenseJSON);
            }catch (ResourceNotFound e){
                context.status(404);
                context.result("There are currently no expenses.");
            }
        });

        app.get("/employees/{eid}/expenses/{expid}", context -> {

            int eid = Integer.parseInt(context.pathParam("eid"));
            int expid = Integer.parseInt(context.pathParam("expid"));

            try {
                String expenseJSON = gson.toJson(expenseServices.getExpenseByExpID(expid));
                context.result(expenseJSON);
            } catch (ResourceNotFound e) {
                context.status(404);
                context.result("Employee with EID:" + eid + " not found, or " + "Expense with ExpID: " + expid + " not found.");
            }
        });

        app.get("/employees/{eid}/expense?status=Pending", context -> {
            int eid = Integer.parseInt(context.pathParam("eid"));
            try{
                String expenseJSON =  gson.toJson(expenseServices.getExpenseByApproval(Status.Pending));
                context.result(expenseJSON);
            }catch (ResourceNotFound e){
                context.result("Employee with EID:" +eid+" Or no expense with that approval were not found");
            }
        });

        app.get("/employees/{eid}/expense?status=Denied", context -> {
            int eid = Integer.parseInt(context.pathParam("eid"));
            try{
                String expenseJSON = gson.toJson(expenseServices.getExpenseByApproval(Status.Denied));
                context.result(expenseJSON);
            }catch (ResourceNotFound e){
                context.result("Employee with EID:" +eid+" Or no expense with that approval were not found");
            }
        });

        app.get("/employees/{eid}/expense?status=Approved", context -> {
            int eid = Integer.parseInt(context.pathParam("eid"));
            try{
                String expenseJSON = gson.toJson(expenseServices.getExpenseByApproval(Status.Approved));
                context.result(expenseJSON);
            }catch (ResourceNotFound e){
                context.result("Employee with EID:" +eid+" Or no expense with that approval were not found");
            }
        });

        app.put("/employees/{eid}/expenses/{expid}", context -> {
            int eid = Integer.parseInt(context.pathParam("eid"));
            int expid = Integer.parseInt(context.pathParam("expid"));

            try {
                String body = context.body();
                Expense expense = gson.fromJson(body, Expense.class);
                expense.setExpid(expid);
                expenseServices.updateExpenseStatus(expense);
                context.result("Expense changed.");
            }catch (ResourceNotFound e) {
                context.status(404);
                context.result("Employee with EID: " + eid + " not found, or expense with ExpID: "+expid+" not found.");
            }
        });

        app.patch("/employees/{eid}/expenses/{expid}/Denied",context -> {
            int eid = Integer.parseInt(context.pathParam("eid"));
            int expid = Integer.parseInt(context.pathParam("expid"));
            String body = context.body();
            Expense expense = gson.fromJson(body, Expense.class);
            expense.setEid(eid);
            expense.setExpid(expid);
                try {
                    if (expense.getApproval() == Status.Pending) {
                        expense.setApproval(Status.Denied);
                        expenseServices.updateExpenseStatus(expense);
                        context.result("Status has been changed");
                        context.status(200);
                    } else {
                        System.out.println("Expense can no longer be changed.");
                    }
                } catch (ResourceNotFound e) {
                    context.status(404);
                    context.result("Employee with EID: " + eid + " not found, or expense with ExpID: " + expid + " not found.");
                }

        });

        app.patch("/employees/{eid}/expenses/{expid}/Approved",context -> {
            int eid = Integer.parseInt(context.pathParam("eid"));
            int expid = Integer.parseInt(context.pathParam("expid"));
            String body = context.body();
            Expense expense = gson.fromJson(body, Expense.class);
            expense.setEid(eid);
            expense.setExpid(expid);
                try {
                    if (expense.getApproval() == Status.Pending) {
                        expense.setApproval(Status.Approved);
                        expenseServices.updateExpenseStatus(expense);
                        context.result("Status has been changed");
                        context.status(200);
                    } else {
                        System.out.println("Expense can no longer be changed.");
                    }
                }catch (ResourceNotFound e) {
                    context.status(404);
                    context.result("Employee with EID: " + eid + " not found, or expense with ExpID: " + expid + " not found.");
                }

        });


        app.delete("/employees/{eid}/expenses/{expid}",context -> {
            int eid = Integer.parseInt(context.pathParam("eid"));
            int expid = Integer.parseInt(context.pathParam("expid"));
            String body = context.body();
            Expense expense = gson.fromJson(body, Expense.class);
            expense.setExpid(expid);
            if(expense.getApproval() == Status.Pending) {
                try {
                    String expenseJSON = gson.toJson(expenseServices.deleteExpenseByExpID(expid));
                    context.result(expenseJSON);
                } catch (ResourceNotFound e) {
                    context.status(404);
                    context.result("Employee with EID: " + eid + " not found or Expense with ExpID: " + expid + " not found.");
                }
            }else{
                System.out.println("This expense cannot be deleted.");
            }
        });


        app.start(5000);
    }

    }
