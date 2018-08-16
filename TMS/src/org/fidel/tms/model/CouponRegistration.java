package org.fidel.tms.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class CouponRegistration {
	
	@Id
	private int c_id;
	private String oil_company;
	private String c_serial_no;
	private String amount;
	private String c_status;
	
	@Transient
	private String create_date;
	
	@Transient
	private String no_of_coupon;
	@Transient
	private String c_serial_no_from;
	@Transient
	private String c_serial_no_to;
	@Transient
	private String form_type;
	@Transient
	private String coup_category_label;
	@Transient
	private String date_from;
	@Transient
	private String date_to;
	
		
	public CouponRegistration(){}
	
	public CouponRegistration(int c_id, String oil_company, String c_serial_no, String amount, String c_status) {
		super();
		this.c_id = c_id;
		this.oil_company = oil_company;
		this.c_serial_no = c_serial_no;
		this.amount = amount;
		this.c_status = c_status;
	}
	public int getC_id() {
		return c_id;
	}
	public void setC_id(int c_id) {
		this.c_id = c_id;
	}
	public String getOil_company() {
		return oil_company;
	}
	public void setOil_company(String oil_company) {
		this.oil_company = oil_company;
	}
	public String getC_serial_no() {
		return c_serial_no;
	}
	public void setC_serial_no(String c_serial_no) {
		this.c_serial_no = c_serial_no;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getC_status() {
		return c_status;
	}
	public void setC_status(String c_status) {
		this.c_status = c_status;
	}

	public String getC_serial_no_from() {
		return c_serial_no_from;
	}

	public void setC_serial_no_from(String c_serial_no_from) {
		this.c_serial_no_from = c_serial_no_from;
	}

	public String getC_serial_no_to() {
		return c_serial_no_to;
	}

	public void setC_serial_no_to(String c_serial_no_to) {
		this.c_serial_no_to = c_serial_no_to;
	}

	public String getForm_type() {
		return form_type;
	}

	public void setForm_type(String form_type) {
		this.form_type = form_type;
	}

	public String getNo_of_coupon() {
		return no_of_coupon;
	}

	public void setNo_of_coupon(String no_of_coupon) {
		this.no_of_coupon = no_of_coupon;
	}

	public String getCreate_date() {
		return create_date;
	}

	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}

	public String getCoup_category_label() {
		return coup_category_label;
	}

	public void setCoup_category_label(String coup_category_label) {
		this.coup_category_label = coup_category_label;
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

}
