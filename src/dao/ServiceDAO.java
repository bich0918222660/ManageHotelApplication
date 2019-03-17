package dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entity.Service;

public class ServiceDAO extends DAOAbstract<Service> {

	@Override
	public List<Service> getAll() throws Exception {
		List<Service> list = new ArrayList<>();
		String sql = "select * from Services";
		try {
			ResultSet rs = conn.prepareStatement(sql).executeQuery();
			while(rs.next()) {
				list.add(new Service(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getString(4)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
