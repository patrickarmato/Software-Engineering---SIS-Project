package project;
import java.sql.*;


public class SISConnection {
	static Connection con;
	static Statement st;
	
	public SISConnection () {
		connect();
	}
	
	public static void connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver Loaded Successfully");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Student Information System?user=patrick","patrick","Stackers28");
			System.out.println("Successful Connection");
			st=con.createStatement();
			System.out.println("Statement created Successfully");
			System.out.println("Now, You can Access the Database");
		}
		catch (ClassNotFoundException cnfe){
			System.err.println(cnfe);
		}
		catch (SQLException sqle) {
			System.err.println(sqle);
		}
	}
}
