package com.pms;

public class AppraiseBean {
	String emp_name;
	DBConnection db;
	String[][] paramSql;
	String[] result;
	String sql1=null,sql2=null;
	int resultCount=0,row=0,col=0,flag=0;
	String[][] rs;
	
	String[][] section_form;
	int apprempid,phaseid;
	public AppraiseBean()
	{
		db=new DBConnection();
		
	}
	public void setQuery()
	{
		sql1="select * from [view2_getappraisalrecords] where ApprEmpId=?";
	}
	public String[] getSections()
	{
		if(flag==0)
		this.getAllForms();
		row=rs.length;
		col=rs[0].length;
		String temp="";
		int count=0;
		for(int i=0;i<row;i++)
		{
			if(!temp.equals(rs[i][1]))
			{
				count++;	
				temp=rs[i][1];
			}
			
		}
		result=new String[count];
		temp="";int indx=0;
		for(int i=0;i<row;i++)
			{
				if(!temp.equals(rs[i][1]))
				{
					result[indx]=rs[i][1];
					temp=rs[i][1];
					indx++;
				}
				
			}
		return result;
	}
	public String[][] getForm(int section)
	{
		row=rs.length;
		col=rs[0].length;
		int size=0;
		for(int i=0;i<row;i++)
		{
			if(rs[i][1].equals(result[section]))
			{
				size++;
			}
		}
		section_form=new String[size][col];
		int indx=0;
		for(int i=0;i<row;i++)
		{
			if(rs[i][1].equals(result[section]))
			{
				for(int j=0;j<col;j++)
				{
					section_form[indx][j]=rs[i][j];
					
				}
				indx++;
			}
		}
		return section_form;
		
	}
	public String[][] getAllForms()
	{
		paramSql=new String[1][2];
		paramSql[0][0]="int";
		paramSql[0][1]=Integer.toString(apprempid);
		rs=db.getConnection(sql1, paramSql);
		return rs;
	}
	public void setUpdatedForms(String[][] forms)
	{
		rs=forms;
		flag=1;
	}
	public void setApprempid(int apprempid)
	{
		this.apprempid=apprempid;
	}
	public void setPhaseid(int phaseid)
	{
		this.phaseid=phaseid;
	}
	public int getApprempid() {
		return apprempid;
	}
	public int getPhaseid() {
		return phaseid;
	}
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	
}

