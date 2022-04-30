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
        Expense azr = new Expense(12, 121, 1200.00, Status.Pending);
        Expense expense = expenseDao.createExpense(azr);
        ExpenseDAOTests.testexpense = expense;
        Assertions.assertEquals(Status.Pending, expense.getApproval());
    }

    @Test
    @Order(2)
    void getExpenseByExpenseID() {
        Expense retrievedExpense = expenseDao.getExpenseByExpId(testexpense.getExpid());
        System.out.println(retrievedExpense);
        Assertions.assertEquals(1200.00, testexpense.getExpammount());
    }

    @Test
    @Order(3)
    void getExpenseByApproval(){
        Expense retrievedExpense = expenseDao.getExpenseByApproval(testexpense.getApproval());
        System.out.println(retrievedExpense);
        Assertions.assertEquals(Status.Pending, testexpense.getApproval());

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
    @Disabled
    void deleteExpenseByExpenseId() {
        boolean result = expenseDao.deleteExpenseByExpId(testexpense.getExpid());
        System.out.println(result);
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
    }
}

