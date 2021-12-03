package application;

import java.sql.*;

public class testSQL{
	static final String url = "jdbc:mysql://localhost:3306/cs136finaldb";
	static final String user = "ShadyBarrios";
	static final String pass = "ShadySagb@SQL62196";
	public static void main(String[] args) throws Exception{
		Connection myConn = DriverManager.getConnection(url, user, pass);
		Statement myStmt = myConn.createStatement();
		//ResultSet myRs = myStmt.executeQuery("select * from students");
	}
}