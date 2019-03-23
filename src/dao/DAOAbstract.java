package dao;

import java.sql.Connection;
import java.util.List;

import factory.ConnectionFactory;

public abstract class DAOAbstract<T> {
	
	protected Connection conn;

	public DAOAbstract() {
		try {
			conn = ConnectionFactory.getInstance().getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public abstract List<T> getAll() throws Exception;

	public boolean insert(T t) throws Exception {
		return false;
	}
	
	public boolean update(T t) throws Exception {
		return false;
	}
	
	public boolean delete(String t) throws Exception {
		return false;
	}
	
	public boolean delete(int t) throws Exception {
		return false;
	}
	
}
