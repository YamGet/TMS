package org.fidel.tms.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class ExpenseType {
	
	@Id
	private int et_id;
	private String expense_type_name;
	private String account_number;
	private String expense_type_usage;
	private String expense_type_status;
	
	@Transient
	private FrightOrder frightOrder;
	
	public ExpenseType(){}
	
	public ExpenseType(int et_id, String expense_type_name, String expense_type_status) {
		super();
		this.et_id = et_id;
		this.expense_type_name = expense_type_name;
		this.expense_type_status = expense_type_status;
	}
	public int getEt_id() {
		return et_id;
	}
	public void setEt_id(int et_id) {
		this.et_id = et_id;
	}
	public String getExpense_type_name() {
		return expense_type_name;
	}
	public void setExpense_type_name(String expense_type_name) {
		this.expense_type_name = expense_type_name;
	}
	public String getExpense_type_status() {
		return expense_type_status;
	}
	public void setExpense_type_status(String expense_type_status) {
		this.expense_type_status = expense_type_status;
	}
	public FrightOrder getFrightOrder() {
		return frightOrder;
	}
	public void setFrightOrder(FrightOrder frightOrder) {
		this.frightOrder = frightOrder;
	}

	public String getAccount_number() {
		return account_number;
	}

	public void setAccount_number(String account_number) {
		this.account_number = account_number;
	}

	public String getExpense_type_usage() {
		return expense_type_usage;
	}

	public void setExpense_type_usage(String expense_type_usage) {
		this.expense_type_usage = expense_type_usage;
	}
}
