package dev.castro.p1.DAOs;

import dev.castro.p1.Entities.Employee;
import dev.castro.p1.Utilities.ConnectionsUtil;

import java.sql.*;

public class EmployeeDaoPostgresImpl implements EmployeeDao{

    @Override
    public Employee createEmployeeAccount(Employee employee) {

        try {
            Connection conn = ConnectionsUtil.createConnection();
            String sql = "insert into employee values (?,?,?,?,?)";
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, employee.getEusername() );
            ps.setString(2, employee.getEname());
            ps.setString(3, employee.getEpassword());
            ps.setInt(4, employee.getEid());
            ps.setInt(5, employee.getAdminkey());

            ps.execute();

            return employee;
        }  catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Employee getEmployeeAccountByEid(int eid) {

        try {
            Connection conn = ConnectionsUtil.createConnection();
            String sql = "select * from emplyee where eid = ?";
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, eid);
            ResultSet rs = ps.executeQuery();

            rs.next();
            Employee employee = new Employee();
            employee.setEusername(rs.getString("eusername"));
            employee.setEname(rs.getString("ename"));
            employee.setEpassword(rs.getString("epassword"));
            employee.setEid(rs.getInt("eid"));
            employee.setAdminkey(rs.getInt("adminkey"));
            return employee;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public Employee updateEmployeePassword(Employee employee) {

        try {
            Connection conn = ConnectionsUtil.createConnection();
            String sql = "update employee set password = ? where ueid = ?";
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, employee.getEpassword());
            ps.setInt(2, employee.getEid());
            ps.executeUpdate();
            return  employee;

        } catch (SQLException e) {
            e.printStackTrace();
            return  null;
        }
    }

    @Override
    public Employee updateEmployeeEname(Employee employee) {

        try {
            Connection conn = ConnectionsUtil.createConnection();
            String sql = "update employee set ename = ? where ueid = ?";
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, employee.getEname());
            ps.setInt(2, employee.getEid());
            ps.executeUpdate();
            return  employee;

        } catch (SQLException e) {
            e.printStackTrace();
            return  null;
        }
    }

    @Override
    public Employee updateEmployeeEusername(Employee employee) {

        try {
            Connection conn = ConnectionsUtil.createConnection();
            String sql = "update employee set eusername = ? where ueid = ?";
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, employee.getEusername());
            ps.setInt(2, employee.getEid());
            ps.executeUpdate();
            return  employee;

        } catch (SQLException e) {
            e.printStackTrace();
            return  null;
        }
    }

    @Override
    public boolean deleteEmployeeByEid(int eid) {

        try {
            Connection conn = ConnectionsUtil.createConnection();
            String sql = "delete from employee where eid= ?";
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, eid);
            ps.execute();
            return  true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
