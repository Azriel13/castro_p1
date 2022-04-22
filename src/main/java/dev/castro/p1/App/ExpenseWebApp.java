package dev.castro.p1.App;
import com.google.gson.Gson;
import dev.castro.p1.DAOs.EmployeeDaoPostgresImpl;
import dev.castro.p1.DAOs.ExpenseDaoPostgresImpl;
import dev.castro.p1.Entities.Employee;
import dev.castro.p1.Entities.Expense;
import dev.castro.p1.Exceptions.ResourceNotFound;
import dev.castro.p1.Exceptions.ResourceNotFound2;
import dev.castro.p1.Services.EmployeeServices;
import dev.castro.p1.Services.EmployeeServicesImpl;
import dev.castro.p1.Services.ExpenseServices;
import dev.castro.p1.Services.ExpenseServicesImpl;
import io.javalin.Javalin;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ExpenseWebApp {

    public static Gson gson = new Gson();
    public static EmployeeServices employeeServices = new EmployeeServicesImpl(new EmployeeDaoPostgresImpl());
    public static ExpenseServices expenseServices = new ExpenseServicesImpl(new ExpenseDaoPostgresImpl());


    Javalin app = Javalin.create();

    public static void main(String[] args) {
        Javalin app = Javalin.create();

        app.post("/employee", context -> {
                String body = context.body();
                Employee employee = gson.fromJson(body, Employee.class);
                employeeServices.createEmployee(employee);
                context.status(201);
                String eJson = gson.toJson(employee);
                context.result(eJson);
        });

        app.get("/employee", context -> {
                List<Employee> employees = employeeServices.getAllEmployee();
                String employeeJSON = gson.toJson(employees);
                context.result(employeeJSON);

        });
        app.get("/employee/{eid}", context -> {

            int eid = Integer.parseInt(context.pathParam("eid"));

            try {
                String employeeJSON =  gson.toJson(employeeServices.getEmployeeByEid(eid));
                context.result(employeeJSON);
            }catch (ResourceNotFound e){
                context.status(404);
                context.result("Employee with EID: "+eid+" not found.");
            }


        });
        app.put("/employee/{eid}", context -> {
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

        app.delete("/employee/{eid}",context -> {
            int eid = Integer.parseInt(context.pathParam("eid"));
            try{
                String employeeJSON = gson.toJson(employeeServices.deleteEmployeeByEid(eid));
                context.result(employeeJSON);
            }catch(ResourceNotFound e){
                context.status(404);
                context.result("Employee with EID: "+eid+" not found");
            }
        });

        app.post("/employee/{eid}/expense", context -> {
            int eid = Integer.parseInt(context.pathParam("eid"));

                String body = context.body();
                Expense expense = gson.fromJson(body, Expense.class);
                expense.setEid(eid);
                expenseServices.createExpense(expense);
                context.status(201);
                String eJson = gson.toJson(expense);
                context.result(eJson);
        });

        app.get("/employee/{eid}/expense", context -> {
            List<Expense> expenses = expenseServices.getAllExpense();
            String expenseJSON = gson.toJson(expenses);
            context.result(expenseJSON);
        });

        app.get("/employee/{eid}/expense/{expid}", context -> {

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

            app.get("/employee/{eid}/expense?status={approval}", context -> {
                int eid = Integer.parseInt(context.pathParam("eid"));
                String approval = context.pathParam("approval");

                try{
                    String expenseJSON = gson.toJson(expenseServices.getExpenseByApproval(approval));
                    context.result(expenseJSON);
                }catch (ResourceNotFound2 e){
                    context.result("Employee with EID:" +eid+" not found, or Expenses with Approval:"+approval+" were not found");
                }
            });

        app.put("/employee/{eid}/expense/{expid}", context -> {
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

        app.patch("/employee/{eid}/expense/{expid}/{approval}",context -> {
            int eid = Integer.parseInt(context.pathParam("eid"));
            int expid = Integer.parseInt(context.pathParam("expid"));
            String approval = context.pathParam("approval");
            String body = context.body();
            Expense expense = gson.fromJson(body, Expense.class);
            expense.setApproval(approval);
            try{
            if(expense.getApproval() == "Pending"){
                expenseServices.updateExpenseStatus(expense);
                context.result("Status has been changed");
                context.status(200);
            }else{
                System.out.println("Expense can no longer be changed.");
            }
            }catch (ResourceNotFound e) {
                context.status(404);
                context.result("Employee with EID: " + eid + " not found, or expense with ExpID: "+expid+" not found.");
            }

        });


        app.delete("/employee/{eid}/expense/{expid}",context -> {
            int eid = Integer.parseInt(context.pathParam("eid"));
            int expid = Integer.parseInt(context.pathParam("expid"));

            try{
                String expenseJSON = gson.toJson(expenseServices.deleteExpenseByExpID(expid));
                context.result(expenseJSON);
            }catch(ResourceNotFound e){
                context.status(404);
                context.result("Employee with EID: "+eid+" not found or Expense with ExpID: "+expid+" not found.");
            }
        });


        app.start(5000);
    }

    }
