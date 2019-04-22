package entity;

import java.util.Date;

public class Payment {
	
	/*
	paymentID	int	Unchecked
	ServiceQuantity	int	Checked
	ServicePrice	float	Checked
	RentalPrice	float	Checked
	SubTotal	float	Checked
	bookingID	int	Checked
	 */
	
	private int paymentID;
	private int serviceQuantity;
	private double servicePrice;
	private double rentalPrice;
	private double subTotal;
	private int bookingID;
	private Date paymentDate;
	
	public Payment() {
		super();
	}

	public Payment(int serviceQuantity, double servicePrice, double rentalPrice, double subTotal, int bookingID, Date paymentDate) {
		super();
		this.serviceQuantity = serviceQuantity;
		this.servicePrice = servicePrice;
		this.rentalPrice = rentalPrice;
		this.subTotal = subTotal;
		this.bookingID = bookingID;
		this.paymentDate = paymentDate;
	}
	
	public Payment(int serviceQuantity, double servicePrice, double rentalPrice, double subTotal, int bookingID) {
		super();
		this.serviceQuantity = serviceQuantity;
		this.servicePrice = servicePrice;
		this.rentalPrice = rentalPrice;
		this.subTotal = subTotal;
		this.bookingID = bookingID;
	}
	
	public Payment(int paymentID, int serviceQuantity, double servicePrice, double rentalPrice, double subTotal,
			int bookingID, Date paymentDate) {
		super();
		this.paymentID = paymentID;
		this.serviceQuantity = serviceQuantity;
		this.servicePrice = servicePrice;
		this.rentalPrice = rentalPrice;
		this.subTotal = subTotal;
		this.bookingID = bookingID;
		this.paymentDate = paymentDate;
	}
	
	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public int getPaymentID() {
		return paymentID;
	}

	public void setPaymentID(int paymentID) {
		this.paymentID = paymentID;
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

	public double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}

	public int getBookingID() {
		return bookingID;
	}

	public void setBookingID(int bookingID) {
		this.bookingID = bookingID;
	}

}
