package DAL;

import java.sql.*;

public class DatabaseConnect {
	String host = "";
	String username = "";
	String password = "";
	String database = "";
	
	Connection connect = null;
	Statement statement = null;
	ResultSet rs = null;
	
	public DatabaseConnect(String host, String username, String password, String database) {
		this.database = database;
		this.host = host;
		this.password = password;
		this.username = username;
	}
	
	protected void driverTest() throws Exception {
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
		}catch(ClassNotFoundException e) {
			throw new Exception("MySQL JDBC Driver not found.");
		}
	}
	
	protected Connection getConnect() throws Exception {
		if(this.connect == null) {
			driverTest();
			
			String url = "jdbc:mysql://" + this.host + ":3306/" + this.database;
			try{
				this.connect = DriverManager.getConnection(url, this.username, this.password);
			}catch(SQLException e) {
				throw new Exception("Không thể kết nối đến Database Server: " + url + e.getMessage());
			}
		}
		
		return this.connect;
	}
	
	protected Statement getStatement() throws Exception {
		if(this.statement == null ? true : this.statement.isClosed()) {
			this.statement = this.getConnect().createStatement();
		}
		return this.statement;
	}
	
	public ResultSet executeQuery(String query) throws Exception {
		try {
			this.rs = getStatement().executeQuery(query);
		}catch(Exception e) {
			throw new Exception("Error: " + e.getMessage() + " - " + query);
		}
		return this.rs;
	}
	
	public int executeUpdate(String query) throws Exception {
		int res = Integer.MIN_VALUE;
		try {
			res = getStatement().executeUpdate(query);
		}catch(Exception e) {
			throw new Exception("Error: " + e.getMessage() + " - " + query);
		}finally {
			this.Close();
		}
		
		return res;
	}
	
	public void Close() throws SQLException {
		if(this.rs != null && !this.rs.isClosed()) {
			this.rs.close();
			this.rs = null;
		}
		
		if(this.statement != null && !this.statement.isClosed()) {
			this.statement.close();
			this.statement = null;
		}
		
		if(this.connect != null && !this.connect.isClosed()) {
			this.connect.close();
			this.connect = null;
		}
	}
}
