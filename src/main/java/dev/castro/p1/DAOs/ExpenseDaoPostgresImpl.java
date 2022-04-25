package dev.castro.p1.DAOs;

import dev.castro.p1.Entities.Expense;
import dev.castro.p1.Enums.Status;
import dev.castro.p1.Exceptions.ResourceNotFound;
import dev.castro.p1.Utilities.ConnectionsUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExpenseDaoPostgresImpl implements ExpenseDao {


    @Override
    public Expense createExpense(Expense expense) {
        try {
            Connection conn = ConnectionsUtil.createConnection();
            String sql = "insert into expense values (?,?,?,?)";
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, expense.getEid());
            ps.setInt(2, expense.getExpid());
            ps.setDouble(3, expense.getExpammount());
            ps.setObject(4, expense.getApproval(), Types.OTHER);
            ps.execute();
            return expense;
        }  catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Expense getExpenseByExpId(int expid) {
        try{
            Connection conn = ConnectionsUtil.createConnection();
            String sql = "select * from expense where expid = ?";
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,expid);

            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                Expense expense= new Expense();
                expense.setEid(rs.getInt("eid"));
                expense.setExpid(rs.getInt("expid"));
                expense.setExpammount(rs.getDouble("expammount"));
                return expense;

            }else{
                throw new ResourceNotFound(expid);
            }
        }catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Expense> getAllExpense() {
        try {
            Connection conn = ConnectionsUtil.createConnection();
            String sql = "select * from expense";
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            List<Expense> expenses = new ArrayList();
            while (rs.next()){
                Expense expense = new Expense();
                expense.setEid(rs.getInt("eid"));
                expense.setExpid(rs.getInt("expid"));
                expense.setExpammount(rs.getDouble("expammount"));
                expenses.add(expense);
            }

            return expenses;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Expense updateExpenseStatus(Expense expense) {
        try {
            Connection conn = ConnectionsUtil.createConnection();
            String sql = "update expense set approval = ? where expid = ?";
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setObject(1,expense.getApproval(), Types.OTHER);
            ps.setInt(2, expense.getExpid());
            ps.executeUpdate();
            return  expense;

        } catch (SQLException e) {
            System.out.println("ExpID not valid, or invalid approval status\n Please try a new ExpID" +
                    "\n or input one of two options: \nApproved\nDenied");
            e.printStackTrace();
            return  null;
        }
    }

    @Override
    public boolean deleteExpenseByExpId(int expid) {
        try {
            Connection conn = ConnectionsUtil.createConnection();
            String sql = "delete from expense where expid= ?";
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, expid);
            ps.execute();
            return  true;
        } catch (SQLException e) {
            System.out.println("ExpID not valid.");
            e.printStackTrace();
            return false;
        }
    }
}
