package com.pms;

public class SubordinatesBean extends BaseForReview{
	String[][] subordinates_list;
	String[][] paramSql;
	String sql;
	DBConnection db;
	

	public SubordinatesBean() {
		db=new DBConnection();
	}
	
	public void setParam(int empid)
	{
		sql="select * from view_apprreviewer where emp_number=? and phaseid=2";
		paramSql=new String[1][2];
		paramSql[0][0]="int";
		paramSql[0][1]=Integer.toString(empid);
		
	}
	

	public String[][] getSubordinates_list() {
		return subordinates_list;
	}

	public void setSubordinates_list() {
		subordinates_list=db.getConnection(sql, paramSql);
	}
	

}
