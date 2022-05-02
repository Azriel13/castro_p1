package dev.castro.p1.DAOs;

import dev.castro.p1.Entities.Expense;
import dev.castro.p1.Enums.Status;

import java.util.List;

public interface ExpenseDao {

    Expense createExpense(Expense expense);

    Expense getExpenseByExpId(int expid);

    List<Expense> getExpenseByApproval (Status approval);

    List<Expense> getAllExpense();

    Expense updateExpenseStatus(Expense expense);

    boolean deleteExpenseByExpId(int expid);

}
