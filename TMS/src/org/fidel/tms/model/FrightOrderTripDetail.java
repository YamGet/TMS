package org.fidel.tms.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class FrightOrderTripDetail {
	@Id
	private int fotd_id;
	private int fo_id;
	private String client_organization;
	private String loading_type;
	private String origin_place;
	private String destination_place;
	private String initial_km;
	private String loading_quantity;
	private String distance;
	private double price;
	private String crv_number;
	private String trip_type;
	private String dispatch_doc_ref_no;
	private String delivery_doc_ref_no;
	private double delivered_quantity;
	private String delivery_date;
	private String fright_note;
	private Date payment_request_date;
	private String payment_appointment_date;
	private String fotd_status;
	private Date close_date;
	
	public FrightOrderTripDetail(){}
	
	public FrightOrderTripDetail(int fotd_id, int fo_id, String client_organization, String loading_type,
			String origin_place, String destination_place, String loading_quantity, String distance, double price,
			String crv_number, String trip_type, String dispatch_doc_ref_no, String delivery_doc_ref_no, String delivery_date,
			String fright_note, String fotd_status, Date close_date) {
		super();
		this.fotd_id = fotd_id;
		this.fo_id = fo_id;
		this.client_organization = client_organization;
		this.loading_type = loading_type;
		this.origin_place = origin_place;
		this.destination_place = destination_place;
		this.loading_quantity = loading_quantity;
		this.distance = distance;
		this.price = price;
		this.crv_number = crv_number;
		this.trip_type = trip_type;
		this.dispatch_doc_ref_no = dispatch_doc_ref_no;
		this.delivery_doc_ref_no = delivery_doc_ref_no;
		this.delivery_date = delivery_date;
		this.fright_note = fright_note;
		this.fotd_status = fotd_status;
		this.close_date = close_date;
	}
	public int getFotd_id() {
		return fotd_id;
	}
	public void setFotd_id(int fotd_id) {
		this.fotd_id = fotd_id;
	}
	public int getFo_id() {
		return fo_id;
	}
	public void setFo_id(int fo_id) {
		this.fo_id = fo_id;
	}
	public String getClient_organization() {
		return client_organization;
	}
	public void setClient_organization(String client_organization) {
		this.client_organization = client_organization;
	}
	public String getLoading_type() {
		return loading_type;
	}
	public void setLoading_type(String loading_type) {
		this.loading_type = loading_type;
	}
	public String getOrigin_place() {
		return origin_place;
	}
	public void setOrigin_place(String origin_place) {
		this.origin_place = origin_place;
	}
	public String getDestination_place() {
		return destination_place;
	}
	public void setDestination_place(String destination_place) {
		this.destination_place = destination_place;
	}
	public String getLoading_quantity() {
		return loading_quantity;
	}
	public void setLoading_quantity(String loading_quantity) {
		this.loading_quantity = loading_quantity;
	}
	public String getDistance() {
		return distance;
	}
	public void setDistance(String distance) {
		this.distance = distance;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getTrip_type() {
		return trip_type;
	}
	public void setTrip_type(String trip_type) {
		this.trip_type = trip_type;
	}
	public String getDispatch_doc_ref_no() {
		return dispatch_doc_ref_no;
	}
	public void setDispatch_doc_ref_no(String dispatch_doc_ref_no) {
		this.dispatch_doc_ref_no = dispatch_doc_ref_no;
	}
	public String getDelivery_doc_ref_no() {
		return delivery_doc_ref_no;
	}
	public void setDelivery_doc_ref_no(String delivery_doc_ref_no) {
		this.delivery_doc_ref_no = delivery_doc_ref_no;
	}	
	public String getFright_note() {
		return fright_note;
	}
	public void setFright_note(String fright_note) {
		this.fright_note = fright_note;
	}
	public String getFotd_status() {
		return fotd_status;
	}
	public void setFotd_status(String fotd_status) {
		this.fotd_status = fotd_status;
	}

	public Date getPayment_request_date() {
		return payment_request_date;
	}

	public void setPayment_request_date(Date payment_request_date) {
		this.payment_request_date = payment_request_date;
	}

	public String getPayment_appointment_date() {
		return payment_appointment_date;
	}

	public void setPayment_appointment_date(String payment_appointment_date) {
		this.payment_appointment_date = payment_appointment_date;
	}

	public String getDelivery_date() {
		return delivery_date;
	}

	public void setDelivery_date(String delivery_date) {
		this.delivery_date = delivery_date;
	}

	public double getDelivered_quantity() {
		return delivered_quantity;
	}

	public void setDelivered_quantity(double delivered_quantity) {
		this.delivered_quantity = delivered_quantity;
	}

	public String getCrv_number() {
		return crv_number;
	}

	public void setCrv_number(String crv_number) {
		this.crv_number = crv_number;
	}

	public Date getClose_date() {
		return close_date;
	}

	public void setClose_date(Date close_date) {
		this.close_date = close_date;
	}

	public String getInitial_km() {
		return initial_km;
	}

	public void setInitial_km(String initial_km) {
		this.initial_km = initial_km;
	}
}
