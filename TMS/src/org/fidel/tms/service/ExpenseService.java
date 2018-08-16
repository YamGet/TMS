package org.fidel.tms.service;

import java.util.List;

import org.fidel.tms.model.Expense;
import org.fidel.tms.model.FrightOrder;

public interface ExpenseService {

	public List<FrightOrder> getFrightOrder(String fon);
	
	public boolean saveFrightOrderExpenseAmount(Expense expense);
	
	public String getTotalExpenseByFoid(Expense expense);
	
	public List<Expense> getExpenseListByFoid(Expense expense);
	
	public List<Expense> rpt_generateRevenueExpensePerTruckReport(Expense expense);
	
	public List<Expense> rpt_generateRevenueExpensePerFonReport(Expense expense);
	
	public boolean updateFOExpenseAmount(Expense expense);
}
