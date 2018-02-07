package com.pms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Types;
import java.util.*;

import org.mindrot.jbcrypt.BCrypt;

public class UpdateDB {  
private String connectionUrl,stored_pass;;
Map<String,String> menu_item; 
Connection con;  
PreparedStatement stmt;  
ResultSet rs;  
String[][] result;
int[][] resultCol;
public UpdateDB()
{
	con = null;  stmt = null;	rs = null;	stored_pass=null;
	connectionUrl="jdbc:sqlserver://192.168.1.67:1433;" +  
	"databaseName=HRMS;user=sa;password=Admin123";
	 
}
public int getConnection(String sql,String[][] paramSql)
{
	int rowupdated=0;
	 try {  
	       // Establish the connection.  
	       Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
	       con = DriverManager.getConnection(connectionUrl);    
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
	       rowupdated=stmt.executeUpdate(); 
	       return rowupdated;
	 }
	 catch (Exception e) {  
         e.printStackTrace();  
      }  
      finally {   
    	 if (rs != null) try { rs.close(); } catch(Exception e) {}  
         if (stmt != null) try { stmt.close(); } catch(Exception e) {}  
         if (con != null) try { con.close(); } catch(Exception e) {}  
      }
	 return rowupdated;  	
	 }
}  