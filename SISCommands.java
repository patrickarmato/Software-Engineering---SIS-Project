package project;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SISCommands {
	public static void main(String[] args) {
		SISConnection con = new SISConnection();
		try {
			String sql="INSERT INTO ;";
			int i=SISConnection.st.executeUpdate(sql);
			if (i>0) {
				System.out.println("Successful Insert");
			}
			sql="select * from ";
			ResultSet rs2=SISConnection.st.executeQuery(sql);
			while(rs2.next()) {
				String pass=rs2.getString("pass");
				String fname=rs2.getString("fname");
				String lname=rs2.getString("lname");
				System.out.println(pass + "\t\t" + fname + "\t" + lname);
			}
		}
		catch(SQLException sqle) {
			System.err.println("sqle");
		}
	}
}
