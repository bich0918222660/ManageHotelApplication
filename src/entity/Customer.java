package entity;

import java.util.Date;

public class Customer extends PersonInformation {
	
	/*
	CustomerID int identity(1000,1) primary key,
	------- Person Info --------
	FirstName nvarchar(30) not null,
	MiddleName nvarchar(30) not null,
	LastName nvarchar(30) not null,
	Gender varchar(10),
	dateOfBirth date,
	Phone varchar(10) not null,
	Address nvarchar(100) not null,
	Email varchar(60) not null,
	AccountID varchar(40) foreign key references Accounts(Username)
		on delete cascade on update cascade
	 */
	
	private int customerID;

	public Customer(int customerID, String firstName, String middleName, String lastName, String gender, Date dateOfBirth, String phone,
			String address, String email, String accountID, String personCode) {
		super(firstName, middleName, lastName, gender, dateOfBirth, phone, address, email, accountID, personCode);
		this.customerID = customerID;
	}

	public Customer(String firstName, String middleName, String lastName, String gender, Date dateOfBirth, String phone,
			String address, String email, String accountID, String personCode) {
		super(firstName, middleName, lastName, gender, dateOfBirth, phone, address, email, accountID, personCode);
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	
}
