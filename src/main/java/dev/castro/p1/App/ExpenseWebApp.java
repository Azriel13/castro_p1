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
                if(expense.getExpammount()>0) {
                    expenseServices.createExpense(expense);
                    context.status(201);
                    String eJson = gson.toJson(expense);
                    context.result(eJson);
                }
                else{
                    String mJson = "Expense cannot be negative";
                    context.result(mJson);
                }
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

        app.get("/employees/{eid}/expense?status={approval}", context -> {
            int eid = Integer.parseInt(context.pathParam("eid"));
            Expense expense = expenseServices.getExpenseByEID(eid);
            Status approval = Status.valueOf(context.pathParam("approval"));
            expense.setEid(eid);
            expense.setApproval(approval);
            try{
                String expenseJSON = gson.toJson(expenseServices.getExpenseByEID(eid));
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
        app.patch("/expenses/{expid}/{approval}",context -> {
            int expid = Integer.parseInt(context.pathParam("expid"));
            Expense expense = expenseServices.getExpenseByExpID(expid);
            Status approval = Status.valueOf(context.pathParam("approval"));
            expense.setExpid(expid);
            try {
                if(expense.getApproval()==Status.Pending) {
                    expense.setApproval(approval);
                    expenseServices.updateExpenseStatus(expense);
                    context.result("Status has been changed to: " + expense.getApproval());
                    context.status(200);
                }else{
                    context.result("Status cannot be changed.");
                }

            } catch (ResourceNotFound e) {
                context.status(404);
                context.result(" not found, or expense with ExpID: " + expid + " not found.");
            }


        });



        app.delete("/expenses/{expid}/{approval}",context -> {
            int eid = Integer.parseInt(context.pathParam("eid"));
            int expid = Integer.parseInt(context.pathParam("expid"));
            Status approval = Status.valueOf(context.pathParam("approval"));
            Expense expense = new Expense();
            expense.setExpid(expid);
            expense.setApproval(approval);
                try {
                    if(expense.getApproval() == Status.Pending) {
                    String expenseJSON = gson.toJson(expenseServices.deleteExpenseByExpID(expid));
                    context.result(expenseJSON);
                    }else{
                        String mJson = "This expense cannot be deleted.";
                        context.result(mJson);
                    }
                } catch (ResourceNotFound e) {
                    context.status(404);
                    context.result("Employee with EID: " + eid + " not found or Expense with ExpID: " + expid + " not found.");
                }

        });


        app.start(5000);
    }

    }
