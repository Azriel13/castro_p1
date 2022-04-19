package dev.castro.p1.Services;

import dev.castro.p1.DAOs.EmployeeDao;
import dev.castro.p1.DAOs.EmployeeDaoPostgresImpl;
import dev.castro.p1.Entities.Employee;

public class EmployeeServicesImpl implements EmployeeServices{

    private EmployeeDao employeeDao;

    public EmployeeServicesImpl (EmployeeDaoPostgresImpl employeeDao){
        this.employeeDao = employeeDao;
    }
    @Override
    public Employee createEmployee(Employee employee) {
        return null;
    }

    @Override
    public Employee uppdateEmployeeInformation(Employee employee) {
        return null;
    }

    @Override
    public Employee deleteEmployeeByEid(int eid) {
        return null;
    }

    @Override
    public Employee getEmployeeByEid(int eid) {
        return null;
    }
}
