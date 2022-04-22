package dev.castro.p1.Services;

import dev.castro.p1.Entities.Expense;

import java.util.List;

public interface ExpenseServices {

    Expense createExpense(Expense expense);

    Expense updateExpenseStatus(Expense expense);

    Expense getExpenseByExpID(int expid);

    List<Expense> getExpenseByEID(int eid);

    List<Expense> getExpenseByApproval(String approval);

    boolean deleteExpenseByExpID(int expid);

    List<Expense> getAllExpense();

}
