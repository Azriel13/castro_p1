package dev.castro.p1.DAOs;

import dev.castro.p1.Entities.Employee;

import java.util.List;

public interface EmployeeDao {

    Employee createEmployeeAccount(Employee employee);

    Employee getEmployeeAccountByEid(int eid);

    List<Employee> getAllEmployee();

    Employee updateEmployeeInformation(Employee employee);

    boolean deleteEmployeeByEid(int eid);

}
