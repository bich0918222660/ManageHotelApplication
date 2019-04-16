package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entity.Category;
import entity.StatisticalCategory;

public class CategoryDAO extends DAOAbstract<Category> {
	
	@Override
	public List<Category> getAll(){
		List<Category> list = new ArrayList<>();
		String sql = "select * from Categories";
		try {
			ResultSet rs = conn.prepareStatement(sql).executeQuery();
			while(rs.next()) {
				list.add(new Category(rs.getInt(1), rs.getString(2), 
						rs.getString(3), rs.getDouble(4), 
						rs.getFloat(5), rs.getString(6), rs.getString(7)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public boolean insert(Category t) throws Exception {
		String sql = "insert into Categories(CategoryName, Description, "
				+ "Price, Discount, Image, Type) values(?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, t.getCategoryName());
		ps.setString(2, t.getDescription());
		ps.setDouble(3, t.getPrice());
		ps.setFloat(4, t.getDiscount());
		ps.setString(5, t.getImage());
		ps.setString(6, t.getType());
		return ps.executeUpdate() > 0;
	}
	
	@Override
	public boolean update(Category t) throws Exception {
		String sql = "update Categories set CategoryName = ?, Description = ?,"
				+ "Price = ?, Discount = ?, Image = ?, Type = ? where CategoryID = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, t.getCategoryName());
		ps.setString(2, t.getDescription());
		ps.setDouble(3, t.getPrice());
		ps.setFloat(4, t.getDiscount());
		ps.setString(5, t.getImage());
		ps.setString(6, t.getType());
		ps.setInt(7, t.getCategoryID());
		return ps.executeUpdate() > 0;
	}
	
	public boolean updateNoImage(Category t) throws Exception {
		String sql = "update Categories set CategoryName = ?, Description = ?,"
				+ "Price = ?, Discount = ?, Type = ? where CategoryID = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, t.getCategoryName());
		ps.setString(2, t.getDescription());
		ps.setDouble(3, t.getPrice());
		ps.setFloat(4, t.getDiscount());
		ps.setString(5, t.getType());
		ps.setInt(6, t.getCategoryID());
		return ps.executeUpdate() > 0;
	}
	
	@Override
	public boolean delete(int id) throws Exception {
		String sql = "delete Categories where CategoryID = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, id);
		return ps.executeUpdate() > 0;
	}
	
	public List<StatisticalCategory> statistical() {
		List<StatisticalCategory> list = new ArrayList<>();
		String sql = "select c.CategoryName, count(c.CategoryID) "
				+ "from Categories c join BookingDetails bd "
				+ "on c.CategoryID = bd.CategoryID "
				+ "group by c.CategoryName";
		try {
			ResultSet rs = conn.prepareStatement(sql).executeQuery();
			while(rs.next()) {
				list.add(new StatisticalCategory(rs.getString(1), rs.getInt(2)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
}
