package com.pms;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailTemplate {

	String username, password;
	String mailTo, candidate , emp_name,subject;
	String sql;
	String[][] paramSql;
	DBConnection db;
	String[][] rs;
	int apprempid,phaseid;
	public MailTemplate() {
		
		this.username = "the3iappraisal";
		this.password =  "Jan@2018";
		db=new DBConnection();
	}


	public String getSubject() {
		return subject;
	}


	public void setSubject(String subject) {
		this.subject = subject;
	}


	public String getMailTo() {
		return mailTo;
	}


	public void setMailTo(String mailTo) {
		this.mailTo = mailTo;
	}


	public String getCandidate() {
		return candidate;
	}


	public void setCandidate(String candidate) {
		this.candidate = candidate;
	}


	public String getEmp_name() {
		return emp_name;
	}


	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}


	public void setMail()
	{	
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new Authenticator() {
		  //new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(mailTo));
			message.setSubject(subject);
			message.setText("Dear "+emp_name+","
				+ "\n\n Appraisal form is submitted by "+candidate+" and now available for your review.");
			System.out.println("Sending Email");
			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
	public void setQuery()
	{
		sql="select ApprEmpId,phaseid,emp_work_email,emp_firstname+' '+emp_lastname emp_name \r\n" + 
				" from hs_hr_employee inner join appr_empl_flow on hs_hr_employee.emp_number=appr_empl_flow.hs_hr_employee_id\r\n" + 
				" where ApprEmpId=? order by phaseid";
	}
	public String[][] getData( int apprempid)
	{
		paramSql=new String[1][2];
		paramSql[0][0]="int";
		paramSql[0][1]=Integer.toString(apprempid);
		rs=db.getConnection(sql, paramSql);
		return rs;
	}
	public void setParam()
	{
		int len=rs.length;
		
		for(int i=0;i<len;i++)
		{
			if(phaseid-1==Integer.parseInt(rs[i][1]))
			{
				candidate=rs[i][3];
				emp_name=rs[i+1][3];
				mailTo=rs[i+1][2];
			}
				
		}
	}
	public void sendMail(int apprempid,int phaseid)
	{
		String vsubject="";
		vsubject=phaseid==2?"Appraiser":"Reviewer";
		System.out.println("phaseid "+phaseid);
		System.out.println("phaseid "+this.phaseid);
		
		this.phaseid=phaseid;
		this.setQuery();
		this.getData(apprempid);
		this.setParam();
		this.setSubject( candidate + " has submitted the appraisal form for " + vsubject +" phase!");
		this.setMail();
	}
}
