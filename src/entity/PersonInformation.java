package entity;

import java.util.Date;

public class PersonInformation {
	
	/*
	FirstName nvarchar(30) not null,
	MiddleName nvarchar(30) not null,
	LastName nvarchar(30) not null,
	Gender varchar(10),
	dateOfBirth date,
	Phone int not null,
	Address nvarchar(100) not null,
	Email varchar(60) not null,
	AccountID varchar(40) foreign key references Accounts(Username)
		on delete cascade on update cascade
	PersonCode varchar(12)
	 */
	
	private String firstName;
	private String middleName;
	private String lastName;
	private String gender;
	private Date dateOfBirth;
	private String phone;
	private String address;
	private String email;
	private String accountID;
	private String personCode;
	
	public PersonInformation(String firstName, String middleName, String lastName, String gender, Date dateOfBirth,
			String phone, String address, String email, String accountID, String personCode) {
		super();
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.phone = phone;
		this.address = address;
		this.email = email;
		this.accountID = accountID;
		this.personCode = personCode;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAccountID() {
		return accountID;
	}

	public void setAccountID(String accountID) {
		this.accountID = accountID;
	}

	public String getPersonCode() {
		return personCode;
	}

	public void setPersonCode(String personCode) {
		this.personCode = personCode;
	}
	
}
