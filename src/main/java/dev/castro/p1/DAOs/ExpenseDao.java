package dev.castro.p1.DAOs;

import dev.castro.p1.Entities.Expense;

import java.util.List;

public interface ExpenseDao {

    Expense createExpense(Expense expense);

    Expense getExpenseByExpId(int expid);

    List<Expense> getAllExpense();

    Expense updateExpenseStatus(Expense expense);

    boolean deleteExpenseByExpId(int expid);

}
