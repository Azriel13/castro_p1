package dev.castro.p1.App;
import com.google.gson.Gson;
import dev.castro.p1.DAOs.EmployeeDaoPostgresImpl;
import dev.castro.p1.Entities.Employee;
import dev.castro.p1.Services.EmployeeServices;
import dev.castro.p1.Services.EmployeeServicesImpl;
import io.javalin.Javalin;
import java.util.ArrayList;
import java.util.List;

public class ExpenseWebApp {

    static List<Employee> employeeList = new ArrayList<>();
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



        app.start(4500);
    }

    }
