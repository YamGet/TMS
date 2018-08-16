package org.fidel.tms.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.fidel.tms.model.Expense;
import org.fidel.tms.model.FrightOrder;
import org.fidel.tms.model.Payment;
import org.fidel.tms.model.TruckInformation;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

public class ReportExpenseExtrator implements ResultSetExtractor<Expense> {

	@Override
	public Expense extractData(ResultSet result) throws SQLException, DataAccessException {
		
		Expense ex = new Expense();
		
		FrightOrder fo = new FrightOrder();
		fo.setTri_id(result.getInt("tri_id"));
		fo.setFo_id(result.getInt("fo_id"));
		fo.setFon(result.getString("fon"));
		
		TruckInformation ti = new TruckInformation();
		ti.setTruck_plate_no(result.getString("truck_plate_no"));
		fo.setTruckInformation(ti);
		
		ex.setFrightOrder(fo);
		
//		Payment py = new Payment();
//		py.setPayment_date(result.getString("payment_date"));
//		ex.setPayment(py);
		
		ex.setTotal_expense(result.getDouble("expense"));
		ex.setTotal_revenue(result.getDouble("revenue"));		
		
		return ex;
	}

}
