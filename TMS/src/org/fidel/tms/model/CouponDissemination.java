package org.fidel.tms.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class CouponDissemination {
	
	@Id
	private int cd_id;
	private int fo_id;
	private int c_id;
	private String c_onhand;
	private String cd_status;
	
	@Transient
	private CouponRegistration couponRegistration;
	@Transient
	private FrightOrderTripDetail frightOrderTripDetail;
	@Transient
	private TruckInformation truckInformation;
	
	public CouponDissemination(){}
	
	public CouponDissemination(int cd_id, int fo_id, int c_id, String c_onhand, String cd_status) {
		super();
		this.cd_id = cd_id;
		this.fo_id = fo_id;
		this.c_id = c_id;
		this.c_onhand = c_onhand;
		this.cd_status = cd_status;
	}
	public int getCd_id() {
		return cd_id;
	}
	public void setCd_id(int cd_id) {
		this.cd_id = cd_id;
	}
	public int getFo_id() {
		return fo_id;
	}
	public void setFo_id(int fo_id) {
		this.fo_id = fo_id;
	}
	public int getC_id() {
		return c_id;
	}
	public void setC_id(int c_id) {
		this.c_id = c_id;
	}
	public String getCd_status() {
		return cd_status;
	}
	public void setCd_status(String cd_status) {
		this.cd_status = cd_status;
	}
	public String getC_onhand() {
		return c_onhand;
	}
	public void setC_onhand(String c_onhand) {
		this.c_onhand = c_onhand;
	}

	public CouponRegistration getCouponRegistration() {
		return couponRegistration;
	}

	public void setCouponRegistration(CouponRegistration couponRegistration) {
		this.couponRegistration = couponRegistration;
	}

	public FrightOrderTripDetail getFrightOrderTripDetail() {
		return frightOrderTripDetail;
	}

	public void setFrightOrderTripDetail(FrightOrderTripDetail frightOrderTripDetail) {
		this.frightOrderTripDetail = frightOrderTripDetail;
	}

	public TruckInformation getTruckInformation() {
		return truckInformation;
	}

	public void setTruckInformation(TruckInformation truckInformation) {
		this.truckInformation = truckInformation;
	}
}
