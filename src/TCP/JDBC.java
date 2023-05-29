package TCP;

import java.sql.Connection;
import java.sql.DriverManager;

import com.microsoft.sqlserver.jdbc.SQLServerDriver;

public class JDBC {
	public static Connection getConnection() {
		Connection connect = null;
		try {
			DriverManager.registerDriver(new SQLServerDriver());
			
			String url = "jdbc:sqlserver://haint\\MSSQLSERVER01;databaseName=LoginForm;encrypt=true;trustServerCertificate=true";
			String use = "sa";
			String password = "123456789";
			connect = DriverManager.getConnection(url, use, password);
//			System.out.println("Connected");
		} catch (Exception e) {
			System.out.println("Error");
		}
		
		return connect;
	}
	
	public static void close(Connection c) {
		try {
			if (c != null) {
				c.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}