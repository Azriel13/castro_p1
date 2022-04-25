package dev.castro.p1.Services;

import dev.castro.p1.Entities.Expense;
import dev.castro.p1.Enums.Status;

import java.util.List;

public interface ExpenseServices {

    Expense createExpense(Expense expense);

    Expense updateExpenseStatus(Expense expense);

    Expense getExpenseByExpID(int expid);


    List<Expense> getExpenseByApproval(Status approval);

    boolean deleteExpenseByExpID(int expid);

    List<Expense> getAllExpense();

}
