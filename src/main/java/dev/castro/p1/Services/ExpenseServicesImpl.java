package dev.castro.p1.Services;

import dev.castro.p1.DAOs.ExpenseDao;
import dev.castro.p1.Entities.Expense;
import dev.castro.p1.Enums.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ExpenseServicesImpl implements ExpenseServices{

    private ExpenseDao expenseDao;

    public ExpenseServicesImpl(ExpenseDao expenseDao){
        this.expenseDao = expenseDao;
    }
    @Override
    public Expense createExpense(Expense expense) {
            return this.expenseDao.createExpense(expense);
    }

    @Override
    public Expense updateExpenseStatus(Expense expense) {
            return this.expenseDao.updateExpenseStatus(expense);

    }

    @Override
    public Expense getExpenseByExpID(int expid) {
        List<Expense> allexpense = this.expenseDao.getAllExpense();

        List<Expense> filteredExpense = new ArrayList();

        for (Expense expense : allexpense) {
            if (expense.getExpid() == expid) {
                filteredExpense.add(expense);
            }
        }

        return this.expenseDao.getExpenseByExpId(expid);
    }

    @Override
    public Expense getExpenseByApproval(Status approval) {
        List<Expense> allexpense = this.expenseDao.getAllExpense();

        List<Expense> filteredExpense = new ArrayList();

        for (Expense expense : allexpense) {
            if (expense.getApproval() == approval) {
                filteredExpense.add(expense);
            }
        }

        return this.expenseDao.getExpenseByApproval(approval);
    }

    @Override
    public boolean deleteExpenseByExpID(int expid) {
            return this.expenseDao.deleteExpenseByExpId(expid);
    }

    @Override
    public List<Expense> getAllExpense() {
        return this.expenseDao.getAllExpense();
    }
}
