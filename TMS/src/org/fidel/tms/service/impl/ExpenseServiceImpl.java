package org.fidel.tms.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.fidel.tms.dao.ExpenseDao;
import org.fidel.tms.model.Expense;
import org.fidel.tms.model.FrightOrder;
import org.fidel.tms.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExpenseServiceImpl implements ExpenseService {
	
	@Autowired
	ExpenseDao expenseDao;

	@Override
	public List<FrightOrder> getFrightOrder(String fon) {
		
		return expenseDao.getFrightOrder(fon);
	}

	@Override
	public boolean saveFrightOrderExpenseAmount(Expense expense) {
		
		return expenseDao.saveFrightOrderExpenseAmount(expense);
	}

	@Override
	public String getTotalExpenseByFoid(Expense expense) {
		
		return expenseDao.getTotalExpenseByFoid(expense);
	}

	@Override
	public List<Expense> getExpenseListByFoid(Expense expense) {
		
		return expenseDao.getExpenseListByFoid(expense);
	}

	@Override
	public List<Expense> rpt_generateRevenueExpensePerTruckReport(Expense expense) {
		
		List<Expense> filteredList = new ArrayList<Expense>();
		
		List<Expense> rpt_list = expenseDao.rpt_generateRevenueExpensePerTruckReport(expense);
		
		for(int i = 0; i < rpt_list.size(); i++){
			
			for(int j = i+1; j < rpt_list.size(); j++){
				
				if(rpt_list.get(i).getFrightOrder().getTri_id() == rpt_list.get(j).getFrightOrder().getTri_id()){
					
					rpt_list.get(i).setTotal_revenue(rpt_list.get(i).getTotal_revenue() + rpt_list.get(j).getTotal_revenue());
					rpt_list.get(i).setTotal_expense(rpt_list.get(i).getTotal_expense() + rpt_list.get(j).getTotal_expense());					
				}
			}
			
			int counter = 0;
			
			for(int j = 0; j < filteredList.size(); j++){
				
				if(filteredList.get(j).getFrightOrder().getTri_id() == rpt_list.get(i).getFrightOrder().getTri_id()){
					
					counter++;
				}				
			}
			
			if(counter == 0){
				filteredList.add(rpt_list.get(i));
			}
		}
		
		return filteredList;
	}

	@Override
	public List<Expense> rpt_generateRevenueExpensePerFonReport(Expense expense) {
		
		return expenseDao.rpt_generateRevenueExpensePerTruckReport(expense);
	}

	@Override
	public boolean updateFOExpenseAmount(Expense expense) {
		
		return expenseDao.updateFOExpenseAmount(expense);
	}

}
