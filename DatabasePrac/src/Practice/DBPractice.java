package Practice;

import java.sql.*;

public class DBPractice {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection conn= null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			Class.forName("net.ucanacces.jdbc.UcanaccessDriver");
		}catch(ClassNotFoundException ex) {
			System.out.println("problem in loading the driver");
			ex.printStackTrace();
		}
		
		try {
			String dbName = "Employee.accdb";
			String dbUrl = "jdbc:ucanaccess://" + dbName;
			conn = DriverManager.getConnection(dbUrl);
			String n = "John";
			double sa = 66000;
			stat = conn.createStatement();
			String query = "INSERT INTO Emp (EName, Salary)" + 
							"values ('"+n+"',"+sa+")";
			query = "UPDATE EMP SET salary=120000 where EName = 'ABC'";
		    //stat.executeUpdate(query);
			
			query = "DELETE FROM EMP where EName = 'John'";
		    stat.executeUpdate(query);
			
			rs = stat.executeQuery("SELECT * FROM Emp");
			int id;
			String name;
			double sal;
			while(rs.next()) {
				id = rs.getInt(1);
				name = rs.getString(2);
				sal = rs.getInt(3);
				System.out.println("ID:" + id 
						+ ", Name:"+ name 
						+ ", Salary:" + sal);
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally { //close connection
			try {
				if(conn != null) {
					rs.close();
					stat.close();
					conn.close();
				}
			}catch(SQLException ex) {
				ex.printStackTrace();
			}
		}	
	}
}
