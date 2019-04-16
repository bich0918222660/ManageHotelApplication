package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entity.Employee;

public class EmployeeDAO extends DAOAbstract<Employee> {

	@Override
	public List<Employee> getAll() throws Exception {
		List<Employee> list = new ArrayList<>();
		String sql = "select * from Employees";
		try {
			ResultSet rs = conn.prepareStatement(sql).executeQuery();
			while(rs.next()) {
				list.add(new Employee(rs.getInt(1), rs.getString(2), 
						rs.getString(3), rs.getString(4), 
						rs.getString(5), rs.getDate(6), 
						rs.getString(7), rs.getString(8), 
						rs.getString(9), rs.getString(10), 
						rs.getString(11), rs.getString(12)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public boolean insert(Employee t) throws Exception {
		java.util.Date utilBirthDate = t.getDateOfBirth();
		java.sql.Date sqlBirthDate = new java.sql.Date(utilBirthDate.getTime());
		
		String sql = "insert into Employees(FirstName, MiddleName, "
				+ "LastName, Gender, DateOfBirth, Phone, "
				+ "Address, Email, Position, PersonCode) values (?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, t.getFirstName());
		ps.setString(2, t.getMiddleName());
		ps.setString(3, t.getLastName());
		ps.setString(4, t.getGender());
		ps.setDate(5, sqlBirthDate);
		ps.setString(6, t.getPhone());
		ps.setString(7, t.getAddress());
		ps.setString(8, t.getEmail());
		ps.setString(9, t.getPosition());
		ps.setString(10, t.getPersonCode());
		return ps.executeUpdate() > 0;
	}
	
	@Override
	public boolean update(Employee t) throws Exception {
		java.util.Date utilBirthDate = t.getDateOfBirth();
		java.sql.Date sqlBirthDate = new java.sql.Date(utilBirthDate.getTime());
		
		String sql = "update Employees set FirstName = ?, MiddleName = ?, "
				+ "LastName = ?, Gender = ?, DateOfBirth = ?, "
				+ "Phone = ?, Address = ?, Email = ?, "
				+ "PersonCode = ?, Position = ? where EmployeeID = ?";
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
		ps.setString(10, t.getPosition());
		ps.setInt(11, t.getEmployeeID());
		return ps.executeUpdate() > 0;
	}
	
	@Override
	public boolean delete(int id) throws Exception {
		String sql = "delete Employees where EmployeeID = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, id);
		return ps.executeUpdate() > 0;
	}

}
