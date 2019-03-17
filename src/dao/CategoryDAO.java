package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entity.Category;
import factory.ConnectionFactory;

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
	
	
}
