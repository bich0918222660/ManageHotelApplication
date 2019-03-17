package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entity.Customer;
import entity.Room;
import factory.ConnectionFactory;

public class CustomerDAO extends DAOAbstract<Customer> {
	
	@Override
	public List<Customer> getAll() {
		List<Customer> list = new ArrayList<>();
		String sql = "select * from Customers";
		try {
			ResultSet rs = conn.prepareStatement(sql).executeQuery();
			while(rs.next()) {
				list.add(new Customer(rs.getInt(1), rs.getString(2), 
						rs.getString(3), rs.getString(4), 
						rs.getString(5), rs.getDate(6), 
						rs.getString(7), rs.getString(8), 
						rs.getString(9), rs.getString(10), 
						rs.getString(11)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public boolean insert(Customer t) throws Exception {
		java.util.Date utilBirthDate = t.getDateOfBirth();
		java.sql.Date sqlBirthDate = new java.sql.Date(utilBirthDate.getTime());
		
		String sql = "insert into Customers(FirstName, MiddleName, "
				+ "LastName, Gender, dateOfBirth, Phone, "
				+ "Address, Email, personCode) values (?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, t.getFirstName());
		ps.setString(2, t.getMiddleName());
		ps.setString(3, t.getLastName());
		ps.setString(4, t.getGender());
		ps.setDate(5, sqlBirthDate);
		ps.setString(6, t.getPhone());
		ps.setString(7, t.getAddress());
		ps.setString(8, t.getEmail());
		ps.setString(9, t.getPersonCode());
		return ps.executeUpdate() > 0;
	}

}
