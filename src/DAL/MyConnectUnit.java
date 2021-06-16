package DAL;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;

import java.sql.Connection;

public class MyConnectUnit {
	private DatabaseConnect connect;
	
	public MyConnectUnit(String host, String username, String password, String database){
		connect = new DatabaseConnect(host, username, password, database);
	}
	
	public Connection getConnect() throws Exception {
		return connect.getConnect();
	}
	
	/* SELECT */
		public ResultSet Select(String table, String condition, String orderBy) throws Exception {
			StringBuilder query = new StringBuilder("SELECT * FROM " + table);
			this.AddCondition(query, condition);
			this.AddOrderBy(query, orderBy);
			query.append(";");
			return this.connect.executeQuery(query.toString());
		}
		//HÀM OVER LOAD Select GIẢM oderBy parameter
		public ResultSet Select(String table, String condition) throws Exception {
			return this.Select(table, condition, null);
		}
		//HÀM OVER LOAD Select GIẢM condition parameterQ
		public ResultSet Select(String table) throws Exception {
			return this.Select(table, null);
		}
		
		public ResultSet select(String str) throws Exception {
			StringBuilder query = new StringBuilder(str);
			return this.connect.executeQuery(query.toString());
		}
	/*---------------------------------------------------------------------------------------------------*/
		
		//HÀM THÊM ĐIỀU KIỆN VÀO Query
		private void AddCondition(StringBuilder query, String condition) {
			if(condition != null) {
				query.append(" WHERE " + condition);
			}
		}
		
		//HÀM THÊM ORDER BY VÀO Query
		private void AddOrderBy(StringBuilder query, String orderBy) {
			if(orderBy != null) {
				query.append(" ORDER BY " + orderBy);
			}
		}
	/*----------------------------------------------------------------------------------------------------*/
		
	//HÀM HỖ TRỢ Insert XUỐNG CSDL
	//INSERT INTO table (columnName...) VALUES (column Values) 
	public boolean Insert(String table, String valueInsert) throws Exception {
		StringBuilder query = new StringBuilder("INSERT INTO " + table);
		query.append(" VALUES (" + valueInsert + ")");
		
		return this.connect.executeUpdate(query.toString()) > 0;
	}
	
	public boolean Update(String table, String valueInsert, String condition) throws Exception {
		StringBuilder query = new StringBuilder("UPDATE " + table + " SET ");
		query.append(valueInsert);
		//CẮT BỚT KÍ TỰ , Ở CUỐI CÂU
		//ĐƯA CÂU LỆNH ĐIỀU KIỆN VÀO TRONG QUERY
		this.AddCondition(query, condition);
		return this.connect.executeUpdate(query.toString()) > 0;
	}
	
	public boolean Delete(String table, String condition) throws Exception {
		StringBuilder query = new StringBuilder("DELETE FROM " + table);
		this.AddCondition(query, condition);
		return this.connect.executeUpdate(query.toString()) > 0;
	}
	
	//ĐẾM SỐ CỘT TRONG table
	public int getColumnCount(ResultSet rs) throws SQLException {
		return rs.getMetaData().getColumnCount();
	}
	
	public void Close() throws SQLException {
		connect.Close();
	}
}
