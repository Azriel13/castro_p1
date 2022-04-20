package dev.castro.p1.Services;

import dev.castro.p1.DAOs.EmployeeDao;
import dev.castro.p1.DAOs.EmployeeDaoPostgresImpl;
import dev.castro.p1.Entities.Employee;

import java.util.List;

public class EmployeeServicesImpl implements EmployeeServices{

    private EmployeeDao employeeDao;

    public EmployeeServicesImpl (EmployeeDaoPostgresImpl employeeDao){

        this.employeeDao = employeeDao;
    }
    @Override
    public Employee createEmployee(Employee employee) {

        return this.employeeDao.createEmployeeAccount(employee);
    }

    @Override
    public Employee updateEmployeeInformation(Employee employee) {
        return this.employeeDao.updateEmployeeInformation(employee);
    }

    @Override
    public boolean deleteEmployeeByEid(int eid) {

        return this.employeeDao.deleteEmployeeByEid(eid);
    }

    @Override
    public List<Employee> getAllEmployee() {
        return this.getAllEmployee();
    }

    @Override
    public Employee getEmployeeByEid(int eid) {

        return this.employeeDao.getEmployeeAccountByEid(eid);
    }
}
