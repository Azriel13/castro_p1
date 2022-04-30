package dev.castro.p1.Services;

import dev.castro.p1.Entities.Expense;
import dev.castro.p1.Enums.Status;

import java.util.List;
import java.util.stream.Stream;

public interface ExpenseServices {

    Expense createExpense(Expense expense);

    Expense updateExpenseStatus(Expense expense);

    Expense getExpenseByExpID(int expid);

    Expense getExpenseByEID(int eid);

    boolean deleteExpenseByExpID(int expid);

    List<Expense> getAllExpense();

}
