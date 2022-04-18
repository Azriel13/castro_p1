package dev.castro.p1.DAOs;

import dev.castro.p1.Entities.Employee;

public interface EmployeeDao {

    Employee createEmployeeAccount(Employee employee);

    Employee getEmployeeAccountByEid(int eid);

    Employee updateEmployeePassword(Employee employee);

    Employee updateEmployeeEname(Employee employee);

    Employee updateEmployeeEusername(Employee employee);

    boolean deleteEmployeeByEid(int eid);

}
