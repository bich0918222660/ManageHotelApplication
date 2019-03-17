package entity;

public class Service {
	
	/*
	serviceID int identity(1,1) primary key,
	serviceName nvarchar(100),
	Price float,
	Description nvarchar(200)
	 */
	
	private int serviceID;
	private String serviceName;
	private double price;
	private String description;
	
	public Service(int serviceID, String serviceName, double price, String description) {
		super();
		this.serviceID = serviceID;
		this.serviceName = serviceName;
		this.price = price;
		this.description = description;
	}

	public Service(String serviceName, double price, String description) {
		super();
		this.serviceName = serviceName;
		this.price = price;
		this.description = description;
	}

	public Service() {
		super();
	}

	public int getServiceID() {
		return serviceID;
	}

	public void setServiceID(int serviceID) {
		this.serviceID = serviceID;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return serviceName;
	}

}
