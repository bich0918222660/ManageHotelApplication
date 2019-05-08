package entity;

public class ReportCategory {

	private String categoryName;
	private String room;
	private int roomQuantity;
	private int bookingQuantity;
	private double price;

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public int getRoomQuantity() {
		return roomQuantity;
	}

	public void setRoomQuantity(int roomQuantity) {
		this.roomQuantity = roomQuantity;
	}

	public int getBookingQuantity() {
		return bookingQuantity;
	}

	public void setBookingQuantity(int bookingQuantity) {
		this.bookingQuantity = bookingQuantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public ReportCategory(String categoryName, String room, double price, int roomQuantity, int bookingQuantity) {
		super();
		this.categoryName = categoryName;
		this.room = room;
		this.roomQuantity = roomQuantity;
		this.bookingQuantity = bookingQuantity;
		this.price = price;
	}

	@Override
	public String toString() {
		return "ReportCategory [categoryName=" + categoryName + ", room=" + room + ", roomQuantity="
				+ roomQuantity + ", bookingQuantity=" + bookingQuantity + ", price=" + price + "]";
	}
	
}
