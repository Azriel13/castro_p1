package dev.castro.p1.Services;

import dev.castro.p1.Entities.Employee;

import java.util.List;

public interface EmployeeServices {

    Employee createEmployee (Employee employee);

    Employee updateEmployeeInformation (Employee employee);

    boolean deleteEmployeeByEid (int eid);

    List<Employee> getAllEmployee();

    List<Employee> getEmployeeByEid (int eid);

}
