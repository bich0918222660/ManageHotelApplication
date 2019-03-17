package entity;

public class PaymentDetail {
	
	/*
	PaymentDetailID	int	Unchecked
	serviceID	int	Checked
	PaymentID	int	Checked
	Quantity	int	Checked
	Price	float	Checked
	SubTotal	float	Checked
	 */
	
	private int paymentDetailID;
	private int serviceID;
	private int paymentID;
	private int quantity;
	private double price;
	private double subtotal;
	
	public PaymentDetail(int serviceID, int paymentID, int quantity, double price, double subtotal) {
		super();
		this.serviceID = serviceID;
		this.paymentID = paymentID;
		this.quantity = quantity;
		this.price = price;
		this.subtotal = subtotal;
	}

	public PaymentDetail(int paymentDetailID, int serviceID, int paymentID, int quantity, double price,
			double subtotal) {
		super();
		this.paymentDetailID = paymentDetailID;
		this.serviceID = serviceID;
		this.paymentID = paymentID;
		this.quantity = quantity;
		this.price = price;
		this.subtotal = subtotal;
	}
	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	public int getPaymentDetailID() {
		return paymentDetailID;
	}

	public void setPaymentDetailID(int paymentDetailID) {
		this.paymentDetailID = paymentDetailID;
	}

	public int getServiceID() {
		return serviceID;
	}

	public void setServiceID(int serviceID) {
		this.serviceID = serviceID;
	}

	public int getPaymentID() {
		return paymentID;
	}

	public void setPaymentID(int paymentID) {
		this.paymentID = paymentID;
	}
	

}
