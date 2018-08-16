package org.fidel.tms.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class FrightOrder {
	
	@Id
	private int fo_id;
	private int a_id;
	private String fon;
	private int tri_id;
	private int tli_id;
	private int dr_id;
	private String trip;
	private double total_coupon_amount;
	private double commission;
	private String date_from;
	private String date_to;
	private String fo_status;
	
	@Transient
	private int old_tri_id;
	@Transient
	private int old_tli_id;
	@Transient
	private long no_of_days;
	@Transient
	private String directory_path;
	@Transient
	private String search_value;
	
	@Transient
	private Associations associations;
	@Transient
	private TruckInformation truckInformation;
	@Transient
	private TrailInformation trailInformation;
	@Transient
	private Drivers drivers;
	@Transient
	private FrightOrderTripDetail frightOrderTripDetail;
	@Transient
	private AdvancePayment advancePayment;
	@Transient
	private Payment payment;
	@Transient
	private CouponDissemination couponDissemination;
	
	
	public FrightOrder(){}
	
	public FrightOrder(int fo_id, int a_id, String fon, int tri_id, int tli_id, int dr_id, String trip,
			int total_coupon_amount, String date_from, String date_to, String fo_status) {
		super();
		this.fo_id = fo_id;
		this.a_id = a_id;
		this.fon = fon;
		this.tri_id = tri_id;
		this.tli_id = tli_id;
		this.dr_id = dr_id;
		this.trip = trip;
		this.total_coupon_amount = total_coupon_amount;
		this.date_from = date_from;
		this.date_to = date_to;
		this.fo_status = fo_status;
	}
	public int getFo_id() {
		return fo_id;
	}
	public void setFo_id(int fo_id) {
		this.fo_id = fo_id;
	}
	public int getA_id() {
		return a_id;
	}
	public void setA_id(int a_id) {
		this.a_id = a_id;
	}
	public String getFon() {
		return fon;
	}
	public void setFon(String fon) {
		this.fon = fon;
	}
	public int getTri_id() {
		return tri_id;
	}
	public void setTri_id(int tri_id) {
		this.tri_id = tri_id;
	}
	public int getTli_id() {
		return tli_id;
	}
	public void setTli_id(int tli_id) {
		this.tli_id = tli_id;
	}
	public int getDr_id() {
		return dr_id;
	}
	public void setDr_id(int dr_id) {
		this.dr_id = dr_id;
	}
	public String getTrip() {
		return trip;
	}
	public void setTrip(String trip) {
		this.trip = trip;
	}
	public double getTotal_coupon_amount() {
		return total_coupon_amount;
	}
	public void setTotal_coupon_amount(double total_coupon_amount) {
		this.total_coupon_amount = total_coupon_amount;
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
	public String getFo_status() {
		return fo_status;
	}
	public void setFo_status(String fo_status) {
		this.fo_status = fo_status;
	}	

	public TruckInformation getTruckInformation() {
		return truckInformation;
	}

	public void setTruckInformation(TruckInformation truckInformation) {
		this.truckInformation = truckInformation;
	}

	public TrailInformation getTrailInformation() {
		return trailInformation;
	}

	public void setTrailInformation(TrailInformation trailInformation) {
		this.trailInformation = trailInformation;
	}

	public Drivers getDrivers() {
		return drivers;
	}

	public void setDrivers(Drivers drivers) {
		this.drivers = drivers;
	}

	public Associations getAssociations() {
		return associations;
	}

	public void setAssociations(Associations associations) {
		this.associations = associations;
	}
	
	public double getCommission() {
		return commission;
	}
	
	public void setCommission(double commission) {
		this.commission = commission;
	}
	
	public FrightOrderTripDetail getFrightOrderTripDetail() {
		return frightOrderTripDetail;
	}
	
	public void setFrightOrderTripDetail(FrightOrderTripDetail frightOrderTripDetail) {
		this.frightOrderTripDetail = frightOrderTripDetail;
	}

	public AdvancePayment getAdvancePayment() {
		return advancePayment;
	}

	public void setAdvancePayment(AdvancePayment advancePayment) {
		this.advancePayment = advancePayment;
	}

	public int getOld_tri_id() {
		return old_tri_id;
	}

	public void setOld_tri_id(int old_tri_id) {
		this.old_tri_id = old_tri_id;
	}

	public int getOld_tli_id() {
		return old_tli_id;
	}

	public void setOld_tli_id(int old_tli_id) {
		this.old_tli_id = old_tli_id;
	}

	public long getNo_of_days() {
		return no_of_days;
	}

	public void setNo_of_days(long no_of_days) {
		this.no_of_days = no_of_days;
	}

	public String getDirectory_path() {
		return directory_path;
	}

	public void setDirectory_path(String directory_path) {
		this.directory_path = directory_path;
	}

	public String getSearch_value() {
		return search_value;
	}

	public void setSearch_value(String search_value) {
		this.search_value = search_value;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public CouponDissemination getCouponDissemination() {
		return couponDissemination;
	}

	public void setCouponDissemination(CouponDissemination couponDissemination) {
		this.couponDissemination = couponDissemination;
	}	
}
