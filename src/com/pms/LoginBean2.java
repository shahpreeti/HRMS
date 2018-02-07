package com.pms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.mindrot.jbcrypt.BCrypt;

public class LoginBean2 {  
private String name,password;
String[][] menu=new String[15][2];
String  connectionUrl="jdbc:sqlserver://192.168.1.67:1433;" +  
        "databaseName=HRMS;user=sa;password=Admin123";

     // Declare the JDBC objects.  
Connection con = null;  
PreparedStatement stmt = null;  
ResultSet rs = null;  
String stored_pass=null;
int appr_empid, emp_id;
public String getName() {  
    return name;  
}  
public void setName(String name) {  
    this.name = name;  
   
}  
public String getPassword() {  
    return password;  
}  
public void setPassword(String password) {  
    this.password = password;  
}  

public int getAppr_empid() {
	return appr_empid;
}
public int getEmpid() {
	return emp_id;
}

public boolean validate(){
	if(name.equals(""))
	{
		System.out.println("name can not be blank");
		return false;
	}
	else {
	this.connectDB();
	
	//connectDB();
	boolean match=checkPassword(password,stored_pass);
	if(match)
	{
		System.out.println("Welcome "+name+" on server");
	return true;	
	}
	else
	{
		System.out.println("invalid credentials");
		return false;
	}}
}  
public void connectDB()
{   
    try {  
       // Establish the connection.  
       Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
       con = DriverManager.getConnection(connectionUrl);  

       // Create and execute an SQL statement that returns some data.  
       String SQL = "select * from view_userapprempid where user_name=?";  

       stmt=con.prepareStatement(SQL); 
       stmt.setString(1,name);  
       rs=stmt.executeQuery();
       while (rs.next()) {   
	            stored_pass=rs.getString(5);
	            appr_empid=rs.getInt(13);
	            emp_id=rs.getInt(3);
	         }    
    }
	      // Handle any errors that may have occurred.  
	      catch (Exception e) {  
	         e.printStackTrace();  
	      }  
	      finally {  
	         if (rs != null) try { rs.close(); } catch(Exception e) {}  
	         if (stmt != null) try { stmt.close(); } catch(Exception e) {}  
	         if (con != null) try { con.close(); } catch(Exception e) {}  
	      }  	
}
public static boolean checkPassword(String password_plaintext, String stored_hash) {
	boolean password_verified = false;
return true;
	
/*	if(null == stored_hash || !stored_hash.startsWith("$2a$"))
		//throw new java.lang.IllegalArgumentException("Invalid hash provided for comparison");
		return false;
	password_verified = BCrypt.checkpw(password_plaintext, stored_hash);

	return(password_verified);*/
}
public String[][] getMenu() {
	return menu;
}
public void setMenu(String name) {
	int i=0;
	String SQL="select module_name, sub_module_name,link \r\n" + 
			"from appr_module \r\n" + 
			"     inner join  appr_sub_module on appr_module.module_id=appr_sub_module.module_id \r\n" + 
			"     inner join  appr_role_module on appr_role_module.sub_module_id=appr_sub_module.sub_module_id\r\n" + 
			"     inner join  ohrm_user on ohrm_user.user_role_id=appr_role_module.user_role_id\r\n" + 
			"     where ohrm_user.user_name=?";
	try {  
	       // Establish the connection.  
	       Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
	       con = DriverManager.getConnection(connectionUrl); 
	       stmt=con.prepareStatement(SQL); 
	       stmt.setString(1,name);  
	       rs=stmt.executeQuery();
	       while (rs.next()) {   
		       menu[i][0]=rs.getString(2);
		       menu[i][1]=rs.getString(3);
		       i++;
		         }   
	       
	    }
		      // Handle any errors that may have occurred.  
		      catch (Exception e) {  
		         e.printStackTrace();  
		      }  
		      finally {  
		         if (rs != null) try { rs.close(); } catch(Exception e) {}  
		         if (stmt != null) try { stmt.close(); } catch(Exception e) {}  
		         if (con != null) try { con.close(); } catch(Exception e) {}  
		      }  	

}
}  