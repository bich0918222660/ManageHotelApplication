package factory;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

	private static ConnectionFactory instance;
	private Connection conn;
	private final String databaseName = "BHHotelDB";
	private final String url = "jdbc:sqlserver://localhost;databaseName=" + databaseName;
	private final String user = "sa";
	private final String password = "123456";

	public ConnectionFactory() throws Exception {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		conn = DriverManager.getConnection(url, user, password);
	}

	public synchronized static ConnectionFactory getInstance() throws Exception {
		if (instance == null)
			instance = new ConnectionFactory();
		return instance;
	}

	public Connection getConnection() {
		return conn;
	}

}
