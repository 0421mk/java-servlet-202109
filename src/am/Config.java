package am;

public class Config {
	public static String getDBUrl() {
		return "jdbc:mysql://localhost:3306/am?ServerTimeZone=UTC";
	}
	
	public static String getDBId() {
		return "root";
	}
	
	public static String getDBPw() {
		return "";
	}

	public static String getDriverClassName() {
		return "com.mysql.cj.jdbc.Driver";
	}

}
