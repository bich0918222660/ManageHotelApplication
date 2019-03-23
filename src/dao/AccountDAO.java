package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entity.Account;

public class AccountDAO extends DAOAbstract<Account> {

	@Override
	public List<Account> getAll() throws Exception {
		List<Account> list = new ArrayList<>();
		String sql = "select * from Accounts";
		try {
			ResultSet rs = conn.prepareStatement(sql).executeQuery();
			while(rs.next()) {
				list.add(new Account(rs.getString(1), rs.getString(2), rs.getString(3)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public boolean insert(Account t) throws Exception {
		String sql = "insert into Accounts(Username, Password, Role) values(?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, t.getUsername());
		ps.setString(2, t.getPassword());
		ps.setString(3, t.getRole());
		return ps.executeUpdate() > 0;
	}
	
	@Override
	public boolean update(Account t) throws Exception {
		String sql = "update Accounts set Password = ?, Role = ? where Username = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, t.getPassword());
		ps.setString(2, t.getRole());
		ps.setString(3, t.getUsername());
		return ps.executeUpdate() > 0;
	}

	@Override
	public boolean delete(String username) throws Exception {
		String sql = "delete Accounts where Username = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, username);
		return ps.executeUpdate() > 0;
	}
	
}
