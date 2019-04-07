package entity;

import java.util.Date;

public class Employee extends PersonInformation {
	
	/*
	EmployeeID	int	Unchecked
	Position	nvarchar(50)	Checked
	------- Person Info --------
	FirstName	nvarchar(30)	Unchecked
	MiddleName	nvarchar(30)	Unchecked
	LastName	nvarchar(30)	Unchecked
	Gender	varchar(10)	Checked
	dateOfBirth	date	Checked
	Phone	int	Unchecked
	Address	nvarchar(100)	Unchecked
	Email	varchar(60)	Unchecked
	AccountID	varchar(40)	Checked
	PersonCode varchar(12)
	 */
	
	private int employeeID;
	private String position;

	public Employee(int employeeID, String firstName, String middleName, String lastName, String gender, Date dateOfBirth, String phone,
			String address, String email, String accountID, String position, String personCode) {
		super(firstName, middleName, lastName, gender, dateOfBirth, phone, address, email, accountID, personCode);
		this.employeeID = employeeID;
		this.position = position;
	}
	
	public Employee(String firstName, String middleName, String lastName, String gender, Date dateOfBirth, String phone,
			String address, String email, String accountID, String position, String personCode) {
		super(firstName, middleName, lastName, gender, dateOfBirth, phone, address, email, accountID, personCode);
		this.position = position;
	}

	public int getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

}
