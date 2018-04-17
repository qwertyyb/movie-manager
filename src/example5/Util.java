package example5;

import java.sql.*;
import java.util.HashMap;
import java.util.LinkedList;

public class Util {
	private static Connection conn = null;
	
	
	private static Statement getStatement() {
		try {
			if (conn != null) {
				return conn.createStatement();
			}
			String driver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/javaexample?characterEncoding=utf8";
			Class.forName(driver);
			conn = DriverManager.getConnection(url, "root", "123456");
			return conn.createStatement();
		} catch(ClassNotFoundException e) {
			System.out.println("can't find the driver");
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static boolean validateAdmin(String user, String pwd) {
		Statement state = getStatement();
		String sql = "select * from admin where admin='" + user +"' and password='" + pwd + "'";
		try {
			ResultSet result = state.executeQuery(sql);
			if(result.next()) {
				return true;
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		return false;
	}
	public static boolean resetPwd(String user, String originPwd, String newPwd) {
		Statement state = getStatement();
		String sql = "select * from admin where admin = '" + user + "' and password = '" + originPwd + "'";
		try {
			ResultSet result = state.executeQuery(sql);
			if(!result.next()) {
				return false;
			}
			sql = "update admin set password = '" + newPwd + "' where admin = '" + user + "'";
			int n = state.executeUpdate(sql);
			if (n > 0) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return false;
	}
	public static LinkedList<HashMap<String, String>> getAllMovie() {
		Statement state = getStatement();
		String sql = "select * from movies";
		try {
			ResultSet result = state.executeQuery(sql);
			ResultSetMetaData rsmd = result.getMetaData();
			LinkedList<HashMap<String, String>> movies = new LinkedList<HashMap<String, String>>();
			while(result.next()) {
				HashMap<String, String> movie = new HashMap<String, String>();
				for(int i = 1; i <= rsmd.getColumnCount(); i++) {
					movie.put(rsmd.getColumnName(i), result.getString(i));
				}
				movies.push(movie);
			}
			return movies;
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return null;
	}
	public static boolean addMovie(HashMap<String, String> movie) {
		Statement state = getStatement();
		String sql = "insert into movies(title, thumb, actors, director, showDate, type, time, country, detail)"
				+ "values('" + movie.get("title") + "', '" + movie.get("thumb") + "', '" + movie.get("actors")
				+ "', '" + movie.get("director") + "', '" + movie.get("showDate") + "', '" + movie.get("type")
				+ "', '" + movie.get("time") + "', '" + movie.get("country") + "', '" + movie.get("detail") + "')";
		
		try {
			int result = state.executeUpdate(sql);
			if (result > 0) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		return false;
	}
	public static boolean updateMovie(int id, HashMap<String, String> movie) {
		Statement state = getStatement();
		String sql = "update movies set title='" + movie.get("title") + "',";
		sql += ("actors='" + movie.get("actors") + "',");
		sql += ("director='" + movie.get("director") + "',");
		sql += ("thumb='" + movie.get("thumb") + "',");
		sql += ("showDate='" + movie.get("showDate") + "',");
		sql += ("time='" + movie.get("time") + "',");
		sql += ("type='" + movie.get("type") + "', ");
		sql += ("country='" + movie.get("country") + "',");
		sql += ("detail='" + movie.get("detail") + "' ");
		sql += ("where id = " + id);
		
		try {
			int result = state.executeUpdate(sql);
			if (result > 0) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return false;
	}
	public static boolean deleteMovie(int id) {
		Statement state = getStatement();
		String sql = "delete from movies where id = " + id;
		try {
			int result = state.executeUpdate(sql);
			if (result > 0) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return false;
	}
}
