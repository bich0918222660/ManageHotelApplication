package entity;

public class Category {

	/*
	CategoryID int identity(1,1) primary key,
	CategoryName nvarchar(100) not null,
	Description nvarchar(500),
	Price float not null,
	Discount float not null,
	Image varchar(100),
	Type nvarchar(60)
	 */

	private int categoryID;
	private String categoryName;
	private String description;
	private double price;
	private float discount;
	private String image;
	private String type;

	public Category(int categoryID, String categoryName, String description, double price, float discount, String image,
			String type) {
		super();
		this.categoryID = categoryID;
		this.categoryName = categoryName;
		this.description = description;
		this.price = price;
		this.discount = discount;
		this.image = image;
		this.type = type;
	}

	public Category(String categoryName, String description, double price, float discount, String image, String type) {
		super();
		this.categoryName = categoryName;
		this.description = description;
		this.price = price;
		this.discount = discount;
		this.image = image;
		this.type = type;
	}

	public Category() {
		super();
	}

	public int getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return categoryName;
	}

}
