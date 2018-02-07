package com.pms;

public class SaveAppraiseBean {
	String[][] selfappr;
	String[] sec;
	String name;
	String sql;
	int flag=0;int count=0;

	public void setVal(String s)
	{
		selfappr[0][0]=s;
	}
	public String getVal()
	{
		System.out.println(selfappr[0][0]);
		return selfappr[0][0];
	}
	public void setSec(String[] sec)
	{
		this.sec=sec;
	}
	public String[] getSec()
	{
		return sec;
	}
	public void setSelfappr(String[][] selfappr)
	{
		this.selfappr=selfappr;
	}
	public String[][] getSelfappr()
	{
		return selfappr;
	}
	public void setAppraiseStatus(int flag)
	{
		this.flag=flag;
	}
	public String getAppraiseStatus()
	{ 
		switch(flag)
		{
		case 1: return "Form saved successfully";
		case 2: return "Can not submit incomplete form";
		case 3: return "Form submitted successfully and mailed for further process";
		default: return "";
		}
	}
	public void setQuery()
	{
		sql="update appr_empl_rating	set remarks=?,rating=? where ApprEmpRatingId=?";
	}
	public int saveForm_self()
	{
		int rowupdated=0;
		this.setQuery();
		UpdateDB ob=new UpdateDB();
		String[][] paramSql=new String[3][2];
		int row=selfappr.length;
		for(int i=0;i<row;i++)
		{
			paramSql[0][0]="String";
			paramSql[0][1]=selfappr[i][3];
			paramSql[1][0]="int";
			paramSql[1][1]=selfappr[i][4];
			paramSql[2][0]="int";
			paramSql[2][1]=selfappr[i][10];
			rowupdated+=ob.getConnection(sql, paramSql);
			System.out.println(rowupdated+" row");
		}
		System.out.println(rowupdated+" total");
		return rowupdated;
	}
	public int saveForm_super()
	{
		int rowupdated=0;
		this.setQuery();
		UpdateDB ob=new UpdateDB();
		String[][] paramSql=new String[3][2];
		int row=selfappr.length;
		for(int i=0;i<row;i++)
		{
			paramSql[0][0]="String";
			paramSql[0][1]=selfappr[i][16];
			paramSql[1][0]="int";
			paramSql[1][1]=selfappr[i][17];
			paramSql[2][0]="int";
			paramSql[2][1]=selfappr[i][23];
			rowupdated+=ob.getConnection(sql, paramSql);
			System.out.println(rowupdated+" row");
		}
		System.out.println(rowupdated+" total");
		return rowupdated;
	}
	public int saveForm_rev()
	{
		int rowupdated=0;
		this.setQuery();
		UpdateDB ob=new UpdateDB();
		String[][] paramSql=new String[3][2];
		int row=selfappr.length;
		for(int i=0;i<row;i++)
		{
			paramSql[0][0]="String";
			paramSql[0][1]=selfappr[i][18];
			paramSql[1][0]="int";
			paramSql[1][1]=selfappr[i][19];
			paramSql[2][0]="int";
			paramSql[2][1]=selfappr[i][24];
			rowupdated+=ob.getConnection(sql, paramSql);
			System.out.println(rowupdated+" row");
		}
		System.out.println(rowupdated+" total");
		return rowupdated;
	}
	
	public void submitForm( int source)
	{
		String sql="update appr_empl_flow set Status = 2 where apprEmpId= ? and phaseid=?";
		UpdateDB ob=new UpdateDB();
		String[][] paramSql=new String[2][2];
		paramSql[0][0]="int";
		paramSql[0][1]=selfappr[0][0];
		paramSql[1][0]="int";
		paramSql[1][1]=selfappr[0][11];
			
		int len=selfappr.length;
		
			int rowupdated=0;
			if(source==1)
			{
				for(int i=0;i<len;i++)
				{
					if(selfappr[i][3].equals("null")||selfappr[i][3].equals(null)||selfappr[i][3].equals("") || selfappr[i][4].equals("")|| selfappr[i][4].equals("0"))
						count++;
					
					System.out.println(i + " - " + count);
				}
				
				if(count==0)
				{
					this.saveForm_self();
				}
			}
				
			else if(source==2)
			{
				for(int i=0;i<len;i++)
				{
					if(selfappr[i][16].equals("null")||selfappr[i][16].equals(null)||selfappr[i][16].equals("") || selfappr[i][17].equals("")|| selfappr[i][17].equals("0"))
						count++;
					
					System.out.println(i + " - " + count);
				}
				
				if(count==0)
				{
					this.saveForm_super();
				}
			}
				
			else
			{
				for(int i=0;i<len;i++)
				{
					if(selfappr[i][18].equals("null")||selfappr[i][18].equals(null)||selfappr[i][18].equals("") || selfappr[i][19].equals("")|| selfappr[i][19].equals("0"))
						count++;
					
					System.out.println(i + " - " + count);
				}
				
				if(count==0)
				{
					this.saveForm_rev();
				}
			}		
			rowupdated=ob.getConnection(sql, paramSql);
			System.out.println(rowupdated+" row");
			sql="update appr_empl set curr_phase_id= curr_phase_id + 1 where ApprEmpId=?";
			String[][] paramSql1=new String[1][2];
			paramSql1[0][0]="int";
			paramSql1[0][1]=selfappr[0][0];
			rowupdated=ob.getConnection(sql, paramSql1);
			System.out.println(rowupdated+" phase updated");
			int phase=Integer.parseInt(paramSql[1][1]);
			phase+=1;
			paramSql[1][1]=Integer.toString(phase);
			MailTemplate sm=new MailTemplate();
			sm.sendMail(Integer.parseInt(paramSql[0][1]),Integer.parseInt(paramSql[1][1]));
			
	
	}
	public int getCount()
	{
		return count;
	}
	public int updatePhaseid(int apprempid,UpdateDB ob)
	{
		int rowupdated=0;
		String sql="update appr_empl set curr_phase_id= curr_phase_id + 1 where ApprEmpId=?";
		String[][] paramsql1=new String[1][2];
		paramsql1[0][1]="int";
		paramsql1[0][1]=Integer.toString(apprempid);
		rowupdated=ob.getConnection(sql, paramsql1);
		return rowupdated;
	}
	
}
