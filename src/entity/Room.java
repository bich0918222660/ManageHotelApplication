package entity;

public class Room {

	/*
	RoomID int identity(100,1) primary key,
	Position nvarchar(50),
	Status varchar(10),
	CategoryID int foreign key references Categories(CategoryID)
		on delete cascade on update cascade
	 */

	private int roomID;
	private String position;
	private String status;
	private int categoryID;

	public int getRoomID() {
		return roomID;
	}

	public void setRoomID(int roomID) {
		this.roomID = roomID;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}

	public Room(int roomID, String position, String status, int categoryID) {
		super();
		this.roomID = roomID;
		this.position = position;
		this.status = status;
		this.categoryID = categoryID;
	}

	public Room(String position, String status, int categoryID) {
		super();
		this.position = position;
		this.status = status;
		this.categoryID = categoryID;
	}

	public Room() {
		super();
	}

	@Override
	public String toString() {
		return this.roomID + "";
	}

}
