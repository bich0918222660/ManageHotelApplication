package entity;

public class Booking {
	
	/*
	BookingID int identity(1,1) primary key,
	QuantityRoom int,
	QuantityCategory int,
	SubTotal float,
	personCode varchar(10),
	CustomerID int foreign key references Customers(CustomerID)
		on delete cascade on update cascade
	 */
	
	private int bookingID;
	private int quantityRoom;
	private int quantityCategory;
	private double subTotal;
	private String personCode;
	private int customerID;
	private String status;
	
	public Booking(int quantityRoom, int quantityCategory, double subTotal, String personCode, int customerID,
			String status) {
		super();
		this.quantityRoom = quantityRoom;
		this.quantityCategory = quantityCategory;
		this.subTotal = subTotal;
		this.personCode = personCode;
		this.customerID = customerID;
		this.status = status;
	}

	public Booking(int bookingID, int quantityRoom, int quantityCategory, double subTotal, String personCode,
			int customerID, String status) {
		super();
		this.bookingID = bookingID;
		this.quantityRoom = quantityRoom;
		this.quantityCategory = quantityCategory;
		this.subTotal = subTotal;
		this.personCode = personCode;
		this.customerID = customerID;
		this.status = status;
	}

	public Booking() {
		super();
	}

	public int getBookingID() {
		return bookingID;
	}

	public void setBookingID(int bookingID) {
		this.bookingID = bookingID;
	}

	public int getQuantityRoom() {
		return quantityRoom;
	}

	public void setQuantityRoom(int quantityRoom) {
		this.quantityRoom = quantityRoom;
	}

	public int getQuantityCategory() {
		return quantityCategory;
	}

	public void setQuantityCategory(int quantityCategory) {
		this.quantityCategory = quantityCategory;
	}

	public double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}

	public String getPersonCode() {
		return personCode;
	}

	public void setPersonCode(String personCode) {
		this.personCode = personCode;
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
