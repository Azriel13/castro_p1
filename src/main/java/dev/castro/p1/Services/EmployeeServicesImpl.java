package dev.castro.p1.Services;

import dev.castro.p1.DAOs.EmployeeDao;
import dev.castro.p1.DAOs.EmployeeDaoPostgresImpl;
import dev.castro.p1.Entities.Employee;

import java.util.ArrayList;
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
        return this.employeeDao.getAllEmployee();
    }

    @Override
    public List<Employee> getEmployeeByEid(int eid) {

        List<Employee> allemployees = this.employeeDao.getAllEmployee();

        List<Employee> filteredEmployee = new ArrayList();

        for(int i =0; i< allemployees.size(); i++){
            if(allemployees.get(i).getEID() == eid){
                filteredEmployee.add(allemployees.get(i));
            }
        }

        return filteredEmployee;
    }

    }
