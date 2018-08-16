package org.fidel.tms.service.impl;

import java.util.List;

import org.fidel.tms.dao.ExpenseTypeDao;
import org.fidel.tms.model.ExpenseType;
import org.fidel.tms.service.ExpenseTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExpenseTypeServiceImpl implements ExpenseTypeService {
	
	@Autowired
	ExpenseTypeDao expenseTypeDao;

	@Override
	public List<ExpenseType> getActiveExpenseType() {
		
		return expenseTypeDao.getActiveExpenseType();
	}

	@Override
	public List<ExpenseType> getAllExpenseType() {
		
		return expenseTypeDao.getAllExpenseType();
	}

	@Override
	public boolean saveExpenseType(ExpenseType expenseType) {
		
		return expenseTypeDao.saveExpenseType(expenseType);
	}

	@Override
	public boolean updateExpenseType(ExpenseType expenseType) {
		
		return expenseTypeDao.updateExpenseType(expenseType);
	}

	@Override
	public List<ExpenseType> getUnrelatedExpenseType(String fon) {
		
		return expenseTypeDao.getUnrelatedExpenseType(fon);
	}

}
