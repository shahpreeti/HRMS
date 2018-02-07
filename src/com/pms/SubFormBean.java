package com.pms;



public class SubFormBean {
	DBConnection db;
	String[][] paramSql;
	String[] result;
	String sql1=null,sql2=null;
	int resultCount=0,row=0,col=0,flag=0;
	String[][] rs;
	String[][] section_form;
	int userid,phaseid;
	public SubFormBean()
	{
		db=new DBConnection();
		
	}
	public void setQuery()
	{
		sql1="select * from view_getappraisalrecords where ApprEmpId=? and phaseid=? order by SectionColOrder, QuestionColOrder";
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
		paramSql=new String[2][2];
		paramSql[0][0]="int";
		paramSql[0][1]=Integer.toString(userid);
		paramSql[1][0]="int";
		paramSql[1][1]=Integer.toString(phaseid);
		rs=db.getConnection(sql1, paramSql);
		return rs;
	}
	public void setUpdatedForms(String[][] forms)
	{
		rs=forms;
		flag=1;
	}
	public void setUserid(int userid)
	{
		this.userid=userid;
	}
	public void setPhaseid(int phaseid)
	{
		this.phaseid=phaseid;
	}
}

