package org.fidel.tms.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.fidel.tms.model.Expense;
import org.fidel.tms.model.ExpenseType;
import org.fidel.tms.model.FrightOrder;
import org.fidel.tms.model.TruckInformation;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

public class ExpenseExtractor implements ResultSetExtractor<Expense> {

	@Override
	public Expense extractData(ResultSet result) throws SQLException, DataAccessException {
		
		Expense exp = new Expense();
		
		exp.setE_id(result.getInt("e_id"));
		exp.setFo_id(result.getInt("fo_id"));
		exp.setEt_id(result.getInt("et_id"));
		exp.setExpense_amount(result.getDouble("expense_amount"));
		
		ExpenseType et = new ExpenseType();
		et.setEt_id(result.getInt("et_id"));
		et.setExpense_type_name(result.getString("expense_type_name"));
		et.setAccount_number(result.getString("account_number"));
		exp.setExpenseType(et);
		
		FrightOrder fo = new FrightOrder();
		fo.setFon(result.getString("fon"));
		
		TruckInformation tri = new TruckInformation();
		tri.setTruck_plate_no(result.getString("truck_plate_no"));
		
		fo.setTruckInformation(tri);
		exp.setFrightOrder(fo);
				
		return exp;
	}

}
