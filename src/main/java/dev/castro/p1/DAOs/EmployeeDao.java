package dev.castro.p1.DAOs;

import dev.castro.p1.Entities.Employee;

public interface EmployeeDao {

    Employee createEmployeeAccount(Employee employee);

    Employee getEmployeeAccountByEid(int eid);

    Employee updateEmployeeInformation(Employee employee);

    boolean deleteEmployeeByEid(int eid);

}
