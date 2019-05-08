package entity;

import java.util.Date;

public class ReportRevenue {

	private int paymentID;
	private String categoryName;
	private int CategoryQuantity;
	private String roomID;
	private int roomQuantity;
	private String serviceName;
	private int serviceQuantity;
	private double servicePrice;
	private double rentalPrice;
	private Date paymentDate;
	private double subtotal;

	public ReportRevenue(int paymentID, String categoryName, int categoryQuantity, String roomID, int roomQuantity,
			String serviceName, int serviceQuantity, double servicePrice, double rentalPrice, Date paymentDate,
			double subtotal) {
		super();
		this.paymentID = paymentID;
		this.categoryName = categoryName;
		CategoryQuantity = categoryQuantity;
		this.roomID = roomID;
		this.roomQuantity = roomQuantity;
		this.serviceName = serviceName;
		this.serviceQuantity = serviceQuantity;
		this.servicePrice = servicePrice;
		this.rentalPrice = rentalPrice;
		this.paymentDate = paymentDate;
		this.subtotal = subtotal;
	}

	public int getPaymentID() {
		return paymentID;
	}

	public void setPaymentID(int paymentID) {
		this.paymentID = paymentID;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public int getCategoryQuantity() {
		return CategoryQuantity;
	}

	public void setCategoryQuantity(int categoryQuantity) {
		CategoryQuantity = categoryQuantity;
	}

	public String getRoomID() {
		return roomID;
	}

	public void setRoomID(String roomID) {
		this.roomID = roomID;
	}

	public int getRoomQuantity() {
		return roomQuantity;
	}

	public void setRoomQuantity(int roomQuantity) {
		this.roomQuantity = roomQuantity;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public int getServiceQuantity() {
		return serviceQuantity;
	}

	public void setServiceQuantity(int serviceQuantity) {
		this.serviceQuantity = serviceQuantity;
	}

	public double getServicePrice() {
		return servicePrice;
	}

	public void setServicePrice(double servicePrice) {
		this.servicePrice = servicePrice;
	}

	public double getRentalPrice() {
		return rentalPrice;
	}

	public void setRentalPrice(double rentalPrice) {
		this.rentalPrice = rentalPrice;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	@Override
	public String toString() {
		return "ReportRevenue [paymentID=" + paymentID + ", categoryName=" + categoryName + ", CategoryQuantity="
				+ CategoryQuantity + ", roomID=" + roomID + ", roomQuantity=" + roomQuantity + ", serviceName="
				+ serviceName + ", serviceQuantity=" + serviceQuantity + ", servicePrice=" + servicePrice
				+ ", rentalPrice=" + rentalPrice + ", paymentDate=" + paymentDate + ", subtotal=" + subtotal + "]";
	}

}
