package dev.castro.p1.App;
import com.google.gson.Gson;
import dev.castro.p1.DAOs.EmployeeDaoPostgresImpl;
import dev.castro.p1.Entities.Employee;
import dev.castro.p1.Exceptions.ResourceNotFound;
import dev.castro.p1.Services.EmployeeServices;
import dev.castro.p1.Services.EmployeeServicesImpl;
import io.javalin.Javalin;
import java.util.ArrayList;
import java.util.List;

public class ExpenseWebApp {

    public static Gson gson = new Gson();
    public static EmployeeServices employeeServices = new EmployeeServicesImpl(new EmployeeDaoPostgresImpl());


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
            String eid = context.queryParam("eid");
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
            String body = context.body();
            Employee employee = gson.fromJson(body, Employee.class);
            employee.setEID(eid);
            employeeServices.updateEmployeeInformation(employee);
            context.result("Name Changed");
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

        app.start(5000);
    }

    }
