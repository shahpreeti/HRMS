package com.pms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Types;
import java.util.*;

import org.mindrot.jbcrypt.BCrypt;

public class DBConnection {  
private String name,password,connectionUrl,stored_pass;;
Map<String,String> menu_item; 
Connection con;  
PreparedStatement stmt;  
ResultSet rs;  
String[][] result;
int[][] resultCol;
public DBConnection()
{
	con = null;  stmt = null;	rs = null;	stored_pass=null;
	connectionUrl="jdbc:sqlserver://192.168.1.67:1433;" +  
		        "databaseName=HRMS;user=sa;password=Admin123";
	 
}
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
       String SQL = "select * from ohrm_user where user_name=?";  

       stmt=con.prepareStatement(SQL); 
       stmt.setString(1,name);  
       rs=stmt.executeQuery();
       while (rs.next()) {   
	            stored_pass=rs.getString(5);
	            System.out.println(stored_pass);
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

	if(null == stored_hash || !stored_hash.startsWith("$2a$"))
		//throw new java.lang.IllegalArgumentException("Invalid hash provided for comparison");
		return false;
	password_verified = BCrypt.checkpw(password_plaintext, stored_hash);

	return(password_verified);
}
public String[][] getConnection(String sql,String[][] paramSql)
{
	 try {  
	       // Establish the connection.  
	       Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
	       con = DriverManager.getConnection(connectionUrl);  

	       // Create and execute an SQL statement that returns some data.  
	       String SQL = sql;  
	       stmt=con.prepareStatement(SQL,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY); 
	       for (int i=0;i<paramSql.length;i++)
	       {
	    	   String caseParam = paramSql[i][0];
	    	   String valueParam = paramSql[i][1];
	    	   switch (caseParam)
	    	   {
	    	   case "String":stmt.setString(i+1,valueParam);break;
	    	   case "int":stmt.setInt(i+1,Integer.parseInt(valueParam));break;
	    	   default: stmt.setInt(i+1,Integer.parseInt(valueParam));break;
	    	   }
	       }
	       rs=stmt.executeQuery(); 
	       ResultSetMetaData rsmd = rs.getMetaData();
	       int col=rsmd.getColumnCount()-1;
	       
	       resultCol=new int[col+1][2];
	       for(int i=0;i<=col;i++)
	       {
	    	   resultCol[i][0]=i+1;
	    	   int type=rsmd.getColumnType(i+1);
	    	   if(type==Types.VARCHAR || type==Types.CHAR )
	    		   resultCol[i][1]=1;
	    	   else if(type==Types.DATE)
	    		   resultCol[i][1]=3;
	    	   else
	    		   resultCol[i][1]=2;
	    		   
	       }
	       rs.last();
	       int row=rs.getRow();
	       rs.beforeFirst();
	       int i=0,j=0;
	       result=new String[row][col+1];
	       while(rs.next())
	    	   {
	    	   	   j=0;
	    		   while(j<col+1)
	   				{
	   					if(resultCol[j][1]==1)
	   						result[i][j]=rs.getString(j+1);
	   					else if(resultCol[j][1]==3)
	   							result[i][j]=""+rs.getDate(j+1);
	   					else	
	   						result[i][j]=""+rs.getLong(j+1);
	   					j++;
	   				}
	   				i++;
	    	   }
	 }
	 catch (Exception e) {  
         e.printStackTrace();  
      }  
      finally {   
    	 if (rs != null) try { rs.close(); } catch(Exception e) {}  
         if (stmt != null) try { stmt.close(); } catch(Exception e) {}  
         if (con != null) try { con.close(); } catch(Exception e) {}  
      }
	 return result;  	
	 }
}  