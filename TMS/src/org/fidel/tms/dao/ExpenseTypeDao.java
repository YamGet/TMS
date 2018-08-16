package org.fidel.tms.dao;

import java.util.List;

import org.fidel.tms.model.ExpenseType;

public interface ExpenseTypeDao {
	
	public List<ExpenseType> getActiveExpenseType();
	
	public List<ExpenseType> getAllExpenseType();
	
	public List<ExpenseType> getUnrelatedExpenseType(String fon);
	
	public boolean saveExpenseType(ExpenseType expenseType);
	
	public boolean updateExpenseType(ExpenseType expenseType);
	
	public boolean checkExpenseType(String expenseTypeName);
	
	public boolean checkExpenseType(String expenseTypeName, int ex_id);

}
