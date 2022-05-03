package dev.castro.p1.DAOTests;

import dev.castro.p1.DAOs.ExpenseDao;
import dev.castro.p1.DAOs.ExpenseDaoPostgresImpl;
import dev.castro.p1.Entities.Expense;
import dev.castro.p1.Enums.Status;
import org.junit.jupiter.api.*;

import java.util.List;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ExpenseDAOTests {

    static ExpenseDao expenseDao = new ExpenseDaoPostgresImpl();
    static Expense testexpense;


    @Test
    @Order(1)
    void createExpenseTest() {
        Expense azr = new Expense(12, 1231, 1200.00, Status.Pending);
        Expense expense = expenseDao.createExpense(azr);
        ExpenseDAOTests.testexpense = expense;
        Assertions.assertEquals(Status.Pending, expense.getApproval());
    }

    @Test
    @Order(2)
    void getExpenseByExpenseID() {

        Assertions.assertEquals(1231, testexpense.getExpid());
    }

    @Test
    @Order(3)
    void getExpenseByApproval(){
        Expense b = new Expense(12, 1311, 140, Status.Approved);
        Expense c = new Expense(12, 1411, 256.99, Status.Denied);
        Expense d = new Expense(12, 1511, 2.99, Status.Pending);
        expenseDao.createExpense(b);
        expenseDao.createExpense(c);
        expenseDao.createExpense(d);
        List<Expense> expenses = expenseDao.getExpenseByApproval(Status.Pending);
        System.out.println(expenses);
        int totalExpenses = expenses.size();
        Assertions.assertTrue(totalExpenses ==2);

    }

    @Test
    @Order(4)
    void updateExpenseStatustest() {
        ExpenseDAOTests.testexpense.setApproval(Status.Denied);
        expenseDao.updateExpenseStatus(testexpense);
        Assertions.assertEquals(Status.Denied, testexpense.getApproval());
    }

    @Test
    @Order(5)
    void deleteExpenseByExpenseId() {
        Assertions.assertEquals(true, expenseDao.deleteExpenseByExpId(testexpense.getExpid()));
    }

    @Test
    @Order(6)
    void getAllExpense() {
        Expense b = new Expense(12, 131, 140, Status.Pending);
        Expense c = new Expense(12, 141, 256.99, Status.Pending);
        expenseDao.createExpense(b);
        expenseDao.createExpense(c);
        List<Expense> expenses = expenseDao.getAllExpense();
        int totalExpenses = expenses.size();
        Assertions.assertTrue(totalExpenses >= 2);
        expenseDao.deleteExpenseByExpId(b.getExpid());
        expenseDao.deleteExpenseByExpId(c.getExpid());
        expenseDao.deleteExpenseByExpId(1311);
        expenseDao.deleteExpenseByExpId(1411);
        expenseDao.deleteExpenseByExpId(1511);
    }
}

