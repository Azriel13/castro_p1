package dev.castro.p1.DAOs;

import dev.castro.p1.Entities.Employee;
import dev.castro.p1.Exceptions.ResourceNotFound;
import dev.castro.p1.Utilities.ConnectionsUtil;
import dev.castro.p1.Utilities.Logger;
import jdk.jfr.internal.LogLevel;
import org.postgresql.util.PSQLException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoPostgresImpl implements EmployeeDao{

    @Override
    public Employee createEmployeeAccount(Employee employee) {

        try {
            Connection conn = ConnectionsUtil.createConnection();
            String sql = "insert into employee values (?,?)";
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, employee.getEName());
            ps.setInt(2, employee.getEID());
            ps.execute();
            return employee;
        }  catch (SQLException e) {
            Logger.log("Error at creating Employee", LogLevel.DEBUG);
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Employee getEmployeeAccountByEid(int eid) {
        try{
        Connection conn = ConnectionsUtil.createConnection();
        String sql = "select * from employee where eid = ?";
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1,eid);

        ResultSet rs = ps.executeQuery();
        if(rs.next()){
        Employee employee = new Employee();
        employee.setEID(rs.getInt("eid"));
        employee.setEName(rs.getString("ename"));
        return employee;

    }else{
                throw new ResourceNotFound(eid);
            }
        }catch(SQLException e) {
            Logger.log("Error at getting an employee by EID", LogLevel.DEBUG);
        e.printStackTrace();
        return null;
    }

    }

    @Override
    public Employee updateEmployeeInformation(Employee employee) {

        try {
            Connection conn = ConnectionsUtil.createConnection();
            String sql = "update employee set ename = ? where eid = ?";
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, employee.getEName());
            ps.setInt(2, employee.getEID());
            ps.executeUpdate();
            return  employee;

        } catch (SQLException e) {
            Logger.log("Error at getting EID", LogLevel.DEBUG);
            e.printStackTrace();
            return  null;
        }
    }

    @Override
    public boolean deleteEmployeeByEid(int eid) {

        try {
            Connection conn = ConnectionsUtil.createConnection();
            String sql = "delete from employee  where eid= ?";
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, eid);
            ps.execute();
            return  true;
        } catch (SQLException e) {
            Logger.log("Error at deleting Employee", LogLevel.DEBUG);
            e.printStackTrace();
            return false;
        }
    }

    public List<Employee> getAllEmployee() {
        try {
            Connection conn = ConnectionsUtil.createConnection();
            String sql = "select * from employee";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            List<Employee> employees = new ArrayList();
            while (rs.next()){
                Employee employee = new Employee();
                employee.setEName(rs.getString("ename"));
                employee.setEID(rs.getInt("eid"));
                employees.add(employee);
            }

            return employees;

        } catch (SQLException e) {
            Logger.log("Error at getting all Employees", LogLevel.DEBUG);
            e.printStackTrace();
            return null;
        }
    }


}
