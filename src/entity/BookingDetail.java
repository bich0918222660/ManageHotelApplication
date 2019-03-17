package entity;

import java.util.Date;

public class BookingDetail {
	
	/*
	BookingDetailID int identity(1,1) primary key,
	CheckinDate date,
	CheckoutDate date,
	Price float,
	Discount float,
	BookingID int foreign key references Bookings(BookingID) on delete cascade,
	RoomID int foreign key references Rooms(RoomID)
		on delete cascade on update cascade,
	CategoryID int foreign key references Categories(CategoryID)
	 */
	
	private int bookingDetailID;
	private Date checkinDate;
	private Date checkoutDate;
	private double price;
	private float discount;
	private int bookingID;
	private int roomID;
	private int categoryID;
	
	public BookingDetail(int bookingDetailID, Date checkinDate, Date checkoutDate, double price, float discount,
			int bookingID, int roomID, int categoryID) {
		super();
		this.bookingDetailID = bookingDetailID;
		this.checkinDate = checkinDate;
		this.checkoutDate = checkoutDate;
		this.price = price;
		this.discount = discount;
		this.bookingID = bookingID;
		this.roomID = roomID;
		this.categoryID = categoryID;
	}

	public BookingDetail(Date checkinDate, Date checkoutDate, double price, float discount, int bookingID, int roomID,
			int categoryID) {
		super();
		this.checkinDate = checkinDate;
		this.checkoutDate = checkoutDate;
		this.price = price;
		this.discount = discount;
		this.bookingID = bookingID;
		this.roomID = roomID;
		this.categoryID = categoryID;
	}

	public BookingDetail() {
		super();
	}

	public int getBookingDetailID() {
		return bookingDetailID;
	}

	public void setBookingDetailID(int bookingDetailID) {
		this.bookingDetailID = bookingDetailID;
	}

	public Date getCheckinDate() {
		return checkinDate;
	}

	public void setCheckinDate(Date checkinDate) {
		this.checkinDate = checkinDate;
	}

	public Date getCheckoutDate() {
		return checkoutDate;
	}

	public void setCheckoutDate(Date checkoutDate) {
		this.checkoutDate = checkoutDate;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public float getDiscount() {
		return discount;
	}

	public void setDiscount(float discount) {
		this.discount = discount;
	}

	public int getBookingID() {
		return bookingID;
	}

	public void setBookingID(int bookingID) {
		this.bookingID = bookingID;
	}

	public int getRoomID() {
		return roomID;
	}

	public void setRoomID(int roomID) {
		this.roomID = roomID;
	}

	public int getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}
	
}
