package dev.castro.p1.Services;

import dev.castro.p1.DAOs.ExpenseDao;
import dev.castro.p1.Entities.Expense;

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

        for(int i =0; i< allexpense.size(); i++){
            if(allexpense.get(i).getExpid() == expid){
                filteredExpense.add(allexpense.get(i));
            }
        }

        return this.expenseDao.getExpenseByExpId(expid);
    }

    @Override
    public List<Expense> getExpenseByEID(int eid) {
        List<Expense> allexpense = this.expenseDao.getAllExpense();

        List<Expense> filteredExpense = new ArrayList();

        for (int i = 0; i < allexpense.size(); i++) {
            if (allexpense.get(i).getExpid() == eid) {
                filteredExpense.add(allexpense.get(i));
            }
        }
        return filteredExpense;
    }

    @Override
    public List<Expense> getExpenseByApproval(String approval) {
        List<Expense> allexpense = this.expenseDao.getAllExpense();

        List<Expense> filteredExpense = new ArrayList();

        for(int i =0; i< allexpense.size(); i++){
            if(allexpense.get(i).getApproval() == approval){
                filteredExpense.add(allexpense.get(i));
            }
        }

        return filteredExpense;
    }

    @Override
    public boolean deleteExpenseByExpID(int expid) {
        Expense expense = this.expenseDao.getExpenseByExpId(expid);

            return this.expenseDao.deleteExpenseByExpId(expid);
    }

    @Override
    public List<Expense> getAllExpense() {
        return this.expenseDao.getAllExpense();
    }
}
