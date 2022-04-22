package dev.castro.p1.DAOTests;

import dev.castro.p1.DAOs.EmployeeDao;
import dev.castro.p1.DAOs.EmployeeDaoPostgresImpl;
import dev.castro.p1.Entities.Employee;
import dev.castro.p1.Exceptions.ResourceNotFound;
import org.junit.jupiter.api.*;

import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeDaoTests {

    static EmployeeDao employeeDao = new EmployeeDaoPostgresImpl();
    static Employee testemployee;

    @Test
    @Order(1)
    void createEmployeeAccountTest(){
        Employee azr = new Employee("Michael Castro",13);
        Employee employee = employeeDao.createEmployeeAccount(azr);
        EmployeeDaoTests.testemployee = employee;
        Assertions.assertNotEquals(null, employee.getEName());
    }
    @Test
    @Order(2)
    void getEmployeeByEID(){
        Employee retrievedEmployee = employeeDao.getEmployeeAccountByEid(testemployee.getEID());
        System.out.println(retrievedEmployee);
        Assertions.assertEquals("Michael Castro", retrievedEmployee.getEName());

    }

    @Test
    @Order(3)
    void updateEmployeeInformationTest(){
        EmployeeDaoTests.testemployee.setEName("Misty Castro");
        employeeDao.updateEmployeeInformation(testemployee);
        Employee retrievedEmployee = employeeDao.getEmployeeAccountByEid(testemployee.getEID());
        Assertions.assertEquals("Misty Castro", retrievedEmployee.getEName());
    }

    @Test
    @Order(4)
    void deleteEmployeeAccountTest(){
        boolean result = employeeDao.deleteEmployeeByEid(testemployee.getEID());
        Assertions.assertTrue(result);
    }
    @Test
    @Order(5)
    void getAllEmployee(){
        Employee b = new Employee("Michelle", 8);
        Employee c = new Employee("Misty", 14);
        employeeDao.createEmployeeAccount(b);
        employeeDao.createEmployeeAccount(c);
        List<Employee> employees = employeeDao.getAllEmployee();
        int totalEmployees = employees.size();
        Assertions.assertTrue(totalEmployees >= 2);
        employeeDao.deleteEmployeeByEid(b.getEID());
        employeeDao.deleteEmployeeByEid(c.getEID());
    }

    @Test
    void get_non_existent_employee(){
        Assertions.assertThrows(ResourceNotFound.class, ()->{
            employeeDao.getEmployeeAccountByEid(10000);
        });
    }

}
