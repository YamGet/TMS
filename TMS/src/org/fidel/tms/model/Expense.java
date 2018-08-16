package org.fidel.tms.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Expense {
	
	@Id
	private int e_id;
	private int fo_id;
	private int et_id;
	private Double expense_amount;
	
	@Transient
	private FrightOrder frightOrder;
	@Transient
	private ExpenseType expenseType;
	@Transient
	private String date_from;
	@Transient
	private String date_to;
	@Transient
	private Double total_expense;
	@Transient
	private Double total_revenue;
	@Transient
	private Payment payment;
	@Transient
	private String report_type;
	@Transient
	private String directory_path;
	@Transient
	private String is_coupon_on_credit;
	
	public Expense(){}
	
	public Expense(int e_id, int fo_id, int et_id, Double expense_amount, String is_coupon_on_credit) {
		super();
		this.e_id = e_id;
		this.fo_id = fo_id;
		this.et_id = et_id;
		this.expense_amount = expense_amount;
		this.is_coupon_on_credit = is_coupon_on_credit;
	}
	public int getE_id() {
		return e_id;
	}
	public void setE_id(int e_id) {
		this.e_id = e_id;
	}
	public int getFo_id() {
		return fo_id;
	}
	public void setFo_id(int fo_id) {
		this.fo_id = fo_id;
	}
	public int getEt_id() {
		return et_id;
	}
	public void setEt_id(int et_id) {
		this.et_id = et_id;
	}
	public Double getExpense_amount() {
		return expense_amount;
	}
	public void setExpense_amount(Double expense_amount) {
		this.expense_amount = expense_amount;
	}

	public FrightOrder getFrightOrder() {
		return frightOrder;
	}

	public void setFrightOrder(FrightOrder frightOrder) {
		this.frightOrder = frightOrder;
	}

	public ExpenseType getExpenseType() {
		return expenseType;
	}

	public void setExpenseType(ExpenseType expenseType) {
		this.expenseType = expenseType;
	}

	public String getDate_from() {
		return date_from;
	}

	public void setDate_from(String date_from) {
		this.date_from = date_from;
	}

	public String getDate_to() {
		return date_to;
	}

	public void setDate_to(String date_to) {
		this.date_to = date_to;
	}

	public Double getTotal_expense() {
		return total_expense;
	}

	public void setTotal_expense(Double total_expense) {
		this.total_expense = total_expense;
	}

	public Double getTotal_revenue() {
		return total_revenue;
	}

	public void setTotal_revenue(Double total_revenue) {
		this.total_revenue = total_revenue;
	}
	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public String getReport_type() {
		return report_type;
	}

	public void setReport_type(String report_type) {
		this.report_type = report_type;
	}

	public String getDirectory_path() {
		return directory_path;
	}

	public void setDirectory_path(String directory_path) {
		this.directory_path = directory_path;
	}

	public String getIs_coupon_on_credit() {
		return is_coupon_on_credit;
	}

	public void setIs_coupon_on_credit(String is_coupon_on_credit) {
		this.is_coupon_on_credit = is_coupon_on_credit;
	}

}
