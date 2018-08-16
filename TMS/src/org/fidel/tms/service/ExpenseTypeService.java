package org.fidel.tms.service;

import java.util.List;

import org.fidel.tms.model.ExpenseType;

public interface ExpenseTypeService {
	
	public List<ExpenseType> getActiveExpenseType();
	
	public List<ExpenseType> getAllExpenseType();
	
	public List<ExpenseType> getUnrelatedExpenseType(String fon);
	
	public boolean saveExpenseType(ExpenseType expenseType);
	
	public boolean updateExpenseType(ExpenseType expenseType);

}
