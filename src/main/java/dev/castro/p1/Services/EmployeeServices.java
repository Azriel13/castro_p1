package dev.castro.p1.Services;

import dev.castro.p1.Entities.Employee;

public interface EmployeeServices {

    Employee createEmployee (Employee employee);

    Employee uppdateEmployeeInformation (Employee employee);

    Employee deleteEmployeeByEid ( int eid);

    Employee getEmployeeByEid (int eid);

}
