package jdbc1;

import java.sql.Connection;
import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.Properties;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.PrintWriter;
import java.security.Principal;

import org.postgresql.Driver;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class JdbcPostgresqlConnection
 */
public class JdbcPostgresqlConnection extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	Connection conn1 =null;
	Statement st =null,st2=null;
public void init() throws ServletException {
      //Open the connection here
	
	String dbURL2 = "jdbc:postgresql://localhost/railway";
    String user = "user2";
    String pass = "123";

    try {
		Class.forName("org.postgresql.Driver");
		conn1 = DriverManager.getConnection(dbURL2, user, pass);
		st = conn1.createStatement();
		st2 =conn1.createStatement();
		System.out.println("init"+conn1);
    	} catch (Exception e) {
		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    }

    public void destroy() {
     //Close the connection here
    	try{
    		conn1.close();
    		System.out.println("close");
    	}catch(Exception e)
    	{
    		System.out.println(e);
    	}
    }
	
    public JdbcPostgresqlConnection() {
        super();
        // TODO Auto-generated constructor stubnt
    }
    public boolean lessthan(String time1,String time2) {
    	int hours1=Integer.parseInt(time1.substring(0,2));
    	int minutes1=Integer.parseInt(time1.substring(3,5));
    	
    	
    	int seconds1=Integer.parseInt(time1.substring(6));
    	int hours2=Integer.parseInt(time2.substring(0,2));
    	int minutes2=Integer.parseInt(time2.substring(3,5));
    	int seconds2=Integer.parseInt(time2.substring(6));
    	if(hours1<hours2) return true;
    	 if(hours1>hours2) return false;
    	 if(minutes1<minutes2) return true;
    	if(minutes1>minutes2) return false;
    	 if(seconds1<seconds2) return true;
    	 if(seconds1>seconds2) return false;
    	return false;
    	
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		
		String stat= request.getParameter("stat");
		String tdate= request.getParameter("tdate");
		String stationname1= request.getParameter("stationname1");
		String stationname2= request.getParameter("stationname2");
		String trainname= request.getParameter("trainname");
		String trainname1= request.getParameter("trainname1");
		String stationname11= request.getParameter("stationname11");
		String stationname21= request.getParameter("stationname21");
		String trainname11= request.getParameter("trainname11");
		String time1= request.getParameter("time1");
		String date1= request.getParameter("date1");
		String trainname12= request.getParameter("trainname12");
		String loginid= request.getParameter("loginid");
		String loginpass= request.getParameter("loginpass");
		String booktrainname= request.getParameter("booktrainname");
		String bookstationname1= request.getParameter("bookstationname1");
		String bookstationname2= request.getParameter("bookstationname2");
		String booktdate= request.getParameter("booktdate");
		String bookclass= request.getParameter("bookclass");
		String sdf= request.getParameter("sdf");
		String cpnr= request.getParameter("cpnr");
		String sloginid= request.getParameter("sloginid");
		String sloginname= request.getParameter("sloginname");
		String sloginpass= request.getParameter("sloginpass");
	
		
		
		try{
			if (stat!="abc"){
				if (!tdate.equals("NULL") && tdate!=null){
				{ResultSet rs=null;
	rs=st.executeQuery("select x.trainid,x.departuretime,y.arrivaltime "
			+ "from route x, route y, calendar z where x.trainid=y.trainid "
			+ "and x.stationid='"+stationname1+"' and y.stationid='"+stationname2+"' and "
					+ "z.trainid=y.trainid and  ((x.arrivaltime<y.arrivaltime and z.departuretime::date='"+tdate+"')"
							+ " or (x.arrivaltime>y.arrivaltime and z.departuretime::date>'"+tdate+"'))"
									+ " and  ((z.arrivaltime::date='"+tdate+"' and x.arrivaltime>=z.arrivaltime::time) "
											+ "or (z.departuretime::date='"+tdate+"' and "
													+ "x.arrivaltime<z.arrivaltime::time));");
				
				String res="";
				while (rs.next()){
					ResultSet rs3=null;
					rs3=st2.executeQuery("with a as (select x.* from route x,calendar y where "
							+ "x.trainid='"+rs.getString(1)+"' and y.trainid=x.trainid and "
									+ "x.arrivaltime<='23:59:59' and "
									+ "x.arrivaltime >= y.arrivaltime::time "
									+ "order by x.arrivaltime) select distinct "
									+ "trainid,stationid,arrivaltime,departuretime from a "
									+ "order by arrivaltime");
					Vector<Integer> vx = new Vector<>();
					while (rs3.next()){
						 vx.add(rs3.getInt(2));
					}

					rs3.close();
					rs3=st2.executeQuery("with b as (select x.* from route x,calendar y "
							+ "where x.trainid='"+rs.getString(1)+"' and y.trainid=x.trainid and "
							+ "x.arrivaltime<='23:59:59' and x.arrivaltime>=y.arrivaltime::time "
							+ "order by x.arrivaltime) select distinct trainid,stationid,arrivaltime,departuretime"
							+ " from route z "
							+ "where z.trainid='"+rs.getString(1)+"' and not exists (select * from b where "
							+ "arrivaltime=z.arrivaltime) order by z.arrivaltime");
					
					while (rs3.next()){
						 vx.add(rs3.getInt(2));
					}
					rs3.close();
					
					int ind1=vx.indexOf(Integer.parseInt(stationname1));
					int ind2=vx.indexOf(Integer.parseInt(stationname2));
					Vector<Integer> vx2 = new Vector<>();
					for (int j=ind1;j<ind2;j++){
						vx2.add(vx.get(j));
					}
					int m1=0,m2=0;
					for (int j=0;j<vx2.size();j++){
						rs3=st2.executeQuery("select accap,slcap from seatcap where stationid='"+vx2.get(j)+"' and trainid='"+rs.getString(1)+"'"
								+ "and tdate='"+tdate+"'");
						while (rs3.next()){
							 if (m1<=rs3.getInt(1)) m1=rs3.getInt(1);
							 if (m2<=rs3.getInt(2)) m2=rs3.getInt(2);
						}
					}
					
					rs3.close();
					rs3=st2.executeQuery("select accap,slcap from train where trainid='"+rs.getString(1)+"'");
					String ms1="",ms2="";
					while (rs3.next()){
						if(m1<rs3.getInt(1)) ms1="AV"+(rs3.getInt(1)-m1);
						else ms1="WL"+(m1-rs3.getInt(1));
						if(m2<rs3.getInt(2)) ms2="AV"+(rs3.getInt(2)-m2);
						else ms2="WL"+(m2-rs3.getInt(2));
					}
					rs3.close();
					res+=rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+ms1+" "+ms2+" ";
					
				}
				rs.close();
				
				response.sendRedirect("/Railway/result1.jsp?qwe1="+res);
			}
				}
	
				else if (!trainname.equals("NULL") && trainname!=null){
					ResultSet rs=null;
					ResultSet rs2=null;
					
					rs2=st.executeQuery("with a as (select x.* from route x,calendar y where "
							+ "x.trainid='"+trainname+"' and y.trainid=x.trainid and "
									+ "x.arrivaltime<='23:59:59' and "
									+ "x.arrivaltime >= y.arrivaltime::time "
									+ "order by x.arrivaltime) select distinct "
									+ "trainid,stationid,arrivaltime,departuretime from a "
									+ "order by arrivaltime");
					
					String res="";
					System.out.println("rs");
					while (rs2.next()){
						res+=rs2.getString(1)+" "+rs2.getString(2)+" "+
								rs2.getString(3)+" "+rs2.getString(4)+" ";
					}
					rs2.close();
					rs=st.executeQuery("with b as (select x.* from route x,calendar y "
							+ "where x.trainid='"+trainname+"' and y.trainid=x.trainid and "
							+ "x.arrivaltime<='23:59:59' and x.arrivaltime>=y.arrivaltime::time "
							+ "order by x.arrivaltime) select distinct trainid,stationid,arrivaltime,departuretime"
							+ " from route z "
							+ "where z.trainid='"+trainname+"' and not exists (select * from b where "
							+ "arrivaltime=z.arrivaltime) order by z.arrivaltime");
					
					System.out.println("rs2");
				
					while (rs.next()){
						res+=rs.getString(1)+" "+rs.getString(2)+" "+
								rs.getString(3)+" "+rs.getString(4)+" ";
					}
					System.out.println(res);
					rs.close();
					
					response.sendRedirect("/Railway/result2.jsp?qwe2="+res);
				}
				else if (!trainname1.equals("NULL") && trainname!=null)
					{ResultSet rs=null;
					rs=st.executeQuery("select fareac,faresl,faregn from route where trainid='"+trainname1+"' and "
							+ "(stationid='"+stationname11+"' "
							+ "or stationid='"+stationname21+"') order by arrivaltime");
								
								int a[]={0,0},b[]={0,0},c[]={0,0};
							      int i=0;
								  while (rs.next()){
									 
								  a[i]=rs.getInt(1);
								  b[i]=rs.getInt(2);
								  c[i]=rs.getInt(3);
								  i++;
								  }
								  int d=0,e=0,f=0;
								  if (a[0]>=a[1]) d=a[0]-a[1];
								  else d=a[1]-a[0];
								  if (b[0]>=b[1]) e=b[0]-b[1];
								  else e=b[1]-b[0];
								  if (c[0]>=c[1]) f=c[0]-c[1];
								  else f=c[1]-c[0];
								 
								String res=d+" "+ e +" "+f;
								rs.close();
								
								response.sendRedirect("/Railway/result3.jsp?qwe3="+res);
							}
								
				
				else if (!trainname11.equals("NULL") && trainname11!=null){
					ResultSet rs=null;
					rs=st.executeQuery("select * from calendar where trainid='"+trainname11+"' and "
							+ "((arrivaltime::date='"+date1+"' and arrivaltime::time<='"+time1+"'"
							+ "and arrivaltime::date!=departuretime::date) or (departuretime::date"
							+ "='"+date1+"' and departuretime::time>='"+time1+"' and "
							+ "arrivaltime::date!=departuretime::date) or(departuretime::date"
							+ "='"+date1+"' and arrivaltime::date='"+date1+"' and "
							+ "departuretime::time>'"+time1+"' and arrivaltime::time<='"+time1+"' "
							+ "and departuretime::date>='"+date1+"'))");
					
					String res="";
					
					if (!rs.next() ) {
					    res="nothing";
					    rs.close();   
					}
					 
					else{rs.close();
						String s3[]={""},s4[]={""};
						rs=st.executeQuery("select * from calendar where trainid='"+trainname11+"' and "
								+ "((arrivaltime::date='"+date1+"' and arrivaltime::time<='"+time1+"'"
								+ "and arrivaltime::date!=departuretime::date) or (departuretime::date"
								+ "='"+date1+"' and departuretime::time>='"+time1+"' and "
								+ "arrivaltime::date!=departuretime::date) or(departuretime::date"
								+ "='"+date1+"' and arrivaltime::date='"+date1+"' and "
								+ "departuretime::time>'"+time1+"' and arrivaltime::time<='"+time1+"' "
								+ "and departuretime::date>='"+date1+"'))");
				    while (rs.next()){
				    	String s1[]=rs.getString(2).split(" ");
				    	System.out.println(rs.getString(2) + "a3");
				    	String s2[]=rs.getString(3).split(" ");
				    	s3=s1;
				    	s4=s2;}
							String temp="";
								rs.close();
								rs=st.executeQuery("with a as (select x.* from route x,calendar y where x.trainid='"
										+trainname11+ "'and y.trainid=x.trainid and x.arrivaltime<='23:59:59' and"
										+ " x.arrivaltime>=y.arrivaltime::time order by x.arrivaltime) "
										+ "select distinct * from a order by arrivaltime;");
								String laststation="";
								String lastarrival="";
								String lastdept="";
								while (rs.next()){temp+=rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+" ";
								laststation=rs.getString(2);
								lastarrival=rs.getString(3);
								lastdept=rs.getString(4);
								}
					
						
				
				rs.close();
				
				rs=st.executeQuery("with a as (select x.* from route x,calendar y "
						+ "where x.trainid='"+trainname11+"' and y.trainid=x.trainid and "
						+ "x.arrivaltime<='23:59:59' and x.arrivaltime>="
						+ "y.arrivaltime::time order by x.arrivaltime) select "
						+ "distinct * from route z where z.trainid='"+trainname11+"' and not "
						+ "exists (select * from a where arrivaltime=z.arrivaltime)"
						+ " order by z.arrivaltime;");
			String temp1="";
			
				while (rs.next()){
				temp1+=rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+" ";
				}
				rs.close();
				String previousstation="";
				
				String temp3[]=temp1.split(" ");
				String firsttrain=temp3[0];
						String firsttrainarr=temp3[1];
				String prevtime="";
				System.out.println(temp);
				String temp2[]=temp.split(" ");
				System.out.println((s3[0]+ " arrival "+s4[0]+" dept"));
			if((!(s4[0].equals(date1)) && (!(s4[0].equals(s3[0]))))){
	 			for(int i=0;i<temp2.length/3;i++)	{
	 				System.out.println("a2");
			if((lessthan(temp2[3*i+1],time1) || temp2[3*i+1].equals(time1)) && (lessthan(time1,temp2[3*i+2])|| temp2[3*i+1].equals(time1))){ res="rest "+
				temp2[3*i]+" "+temp2[3*i+1]+" "+temp2[3*i+2];
				System.out.println(res);
			break;}
					else if(lessthan(time1,temp2[3*i+1])) {
						
			res="motion "+previousstation+" "+prevtime+" "+temp2[3*i]+" "+temp2[3*i+1];
			System.out.println("asdsfsf");
			break;	}
					else if((lessthan(time1,"23:59:59") || time1.equals("23:59:59")) &&lessthan(temp2[3*i+2],time1)&& i==temp2.length/3-1)
					{System.out.println("a1");
						res="motion "+temp2[3*i]+" "+temp2[3*i+2]+" "+firsttrain+" "+firsttrainarr;
						System.out.println(temp2[3*i]);
					break;}
					previousstation=temp2[3*i];
					prevtime=temp2[3*i+2];
				}}
			else {previousstation=laststation;
			prevtime=lastdept;
				
				for(int i=0;i<temp3.length/3;i++)	{
					if((lessthan(temp3[3*i+1],time1) || temp3[3*i+1].equals(time1)) && (lessthan(time1,temp3[3*i+2])|| temp2[3*i+1].equals(time1))){ res="rest "+
						temp3[3*i]+" "+temp3[3*i+1]+" "+temp3[3*i+2];
						
					break;}
							else if(lessthan(time1,temp3[3*i+1])) {
								
					res="motion "+previousstation+" "+prevtime+" "+temp3[3*i]+" "+temp3[3*i+1];
					
					break;	}
	
							previousstation=temp3[3*i];
							prevtime=temp3[3*i+2];
						}
			}
				
				}
				
								  
					
					
					System.out.println(res); 
					response.sendRedirect("/Railway/result4.jsp?qwe4="+res);
				}
				
				else if (!trainname12.equals("NULL") && trainname12!=null){
					{ResultSet rs=null;
					ResultSet rs2=null;
					rs=st.executeQuery("with a as (select x.* from route x,calendar y where "
							+ "x.trainid='"+ trainname12+"' and y.trainid=x.trainid and x.arrivaltime<='23:59:59' "
							+ "and x.arrivaltime>=y.arrivaltime::time order by x.arrivaltime) "
							+ "select distinct stationid,arrivaltime from "
							+ "a order by arrivaltime");
							
					Vector<Integer> v = new Vector<>();
					//Vector<Integer> dist = new Vector<>();
								  while (rs.next()){
									 v.add(rs.getInt(1));
								  
								 }
									rs.close();
								  String res="";
								  int i=1;
								  int j=v.get(0);
								  while(i<v.size()){
									  int k=v.get(i);
									  int r,s;
									  if (j>=k){
										  r=k;
										  s=j;
									  }
									  else{
										  r=j;
										  s=k;
									  }
									
									  rs2=st.executeQuery("select sum(distance) from track where stationid1>='"+ r+"' and stationid1<'"+ s+"'");
									 while (rs2.next()){
									  res+=j+" "+k+" "+rs2.getInt(1)+" ";
									 }
									  j=k;
									  i++;
									  rs2.close();
								  }
								
								  rs=st.executeQuery("with a as (select x.* from route x,calendar y"
								  		+ " where x.trainid='"+ trainname12+"' and y.trainid=x.trainid and"
								  		+ " x.arrivaltime<='23:59:59' and x.arrivaltime"
								  		+ ">=y.arrivaltime::time order by x.arrivaltime) select "
								  		+ "distinct * from route z where z.trainid='"+ trainname12+"' and not "
								  		+ "exists (select * from a where arrivaltime=z.arrivaltime)"
								  		+ " order by z.arrivaltime");
											
									Vector<Integer> v2 = new Vector<>();
									 while (rs.next()){
										 v2.add(rs.getInt(2));
									  
									 }
									//Vector<Integer> dist = new Vector<>();
								if(!v2.isEmpty())	{int a=v.get(v.size()-1);
									int b=v2.get(0);
									int p,q;
									if(a>b) {p=b;
									q=a;
									}
									else {p=a; q=b;}
									rs2=st.executeQuery("select sum(distance) from track where stationid1>='"+ p+"' and stationid1<'"+ q+"'");
									 while (rs2.next()){
									  res+=a+" "+b+" "+rs2.getInt(1)+" ";
									 }
												  
													rs.close();
											
												  i=1;
												 j=v2.get(0);
												  while(i<v2.size()){
													  int k=v2.get(i);
													  int r,s;
													  if (j>=k){
														  r=k;
														  s=j;
													  }
													  else{
														  r=j;
														  s=k;
													  }
													
													  rs2=st.executeQuery("select sum(distance) from track where stationid1>='"+ r+"' and stationid1<'"+ s+"'");
													 while (rs2.next()){
													  res+=j+" "+k+" "+rs2.getInt(1)+" ";
													 }
													  j=k;
													  i++;
													  rs2.close();
												  }
								}
												
							
							
								
								response.sendRedirect("/Railway/result5.jsp?qwe5="+res);
							}
								}
				
				else if (!loginid.equals("NULL") && loginid!=null){
					
					ResultSet rs=null;
					rs=st.executeQuery("select * from customer where cid='"+ loginid+"' and cpass=md5('"+ loginpass+"');");
					if (rs.next() ){
						String res=rs.getString(1)+" "+rs.getString(2);
						
						session = request.getSession();
						session.setAttribute("sid1",res );
						
						rs.close();
						response.sendRedirect("/Railway/home.jsp?login="+res);
					}
					else{
						rs.close();
						response.sendRedirect("/Railway/login.jsp");
					}
					
				
				}
				else if (!sloginid.equals("NULL") && sloginid!=null){
					
					ResultSet rs=null;
					rs=st.executeQuery("select * from customer where cid='"+ sloginid+"'");
					if (!rs.next() ){
						String res=sloginid+" "+sloginname;
						session = request.getSession();
						session.setAttribute("sid1",res );
						rs.close();
						st.executeUpdate("insert into customer values('"+ sloginid+"','"+ sloginname+"',md5('"+ sloginpass+"'))");
						response.sendRedirect("/Railway/home.jsp?login="+res);
					}
					else{
						rs.close();
						response.sendRedirect("/Railway/fail3.jsp");
					}
					
				
				}
				else if (!booktdate.equals("NULL") && booktdate!=null){
				
					ResultSet rs4=null,rs5=null;
					
				    rs5=st.executeQuery("select * from seatcap where trainid='"+booktrainname+"' and stationid='"+bookstationname1+"' and tdate='"+booktdate+"'");
				    if (!rs5.next() ) {
				    	response.sendRedirect("/Railway/fail.jsp");
				    	rs5.close();
				    }
				    else{
				    	
				    rs4=st.executeQuery("with a as (select x.* from route x,calendar y where "
							+ "x.trainid='"+booktrainname+"' and y.trainid=x.trainid and "
									+ "x.arrivaltime<='23:59:59' and "
									+ "x.arrivaltime >= y.arrivaltime::time "
									+ "order by x.arrivaltime) select distinct "
									+ "trainid,stationid,arrivaltime,departuretime from a "
									+ "order by arrivaltime");
					Vector<Integer> vy = new Vector<>();
					while (rs4.next()){
						 vy.add(rs4.getInt(2));
					}

					rs4.close();
					rs4=st.executeQuery("with b as (select x.* from route x,calendar y "
							+ "where x.trainid='"+booktrainname+"' and y.trainid=x.trainid and "
							+ "x.arrivaltime<='23:59:59' and x.arrivaltime>=y.arrivaltime::time "
							+ "order by x.arrivaltime) select distinct trainid,stationid,arrivaltime,departuretime"
							+ " from route z "
							+ "where z.trainid='"+booktrainname+"' and not exists (select * from b where "
							+ "arrivaltime=z.arrivaltime) order by z.arrivaltime");
					
					while (rs4.next()){
						 vy.add(rs4.getInt(2));
					}
					rs4.close();
					System.out.println("qqwq");
					int ind1=vy.indexOf(Integer.parseInt(bookstationname1));
					int ind2=vy.indexOf(Integer.parseInt(bookstationname2));
					Vector<Integer> vy2 = new Vector<>();
					for (int j=ind1;j<ind2;j++){
						vy2.add(vy.get(j));
					}
				
					for (int j=0;j<vy2.size();j++){
						if (bookclass.equals("AC")){
						st.executeUpdate("update seatcap set accap=accap+1 where"
								+ " trainid='"+booktrainname+"' and stationid='"+vy2.get(j)+"' "
										+ "and tdate='"+booktdate+"'");
						}
						if (bookclass.equals("SL")){
							st.executeUpdate("update seatcap set slcap=slcap+1 where"
									+ " trainid='"+booktrainname+"' and stationid='"+vy2.get(j)+"' "
											+ "and tdate='"+booktdate+"'");}
						
						
					}
					String res=(String)session.getAttribute("sid1");
					String res2[]=res.split(" ");
					st.executeUpdate("insert into trans(loginid,trainid,tdate,stationid1,stationid2,"
							+ "class)values('"+res2[0]+"','"+booktrainname+"','"+booktdate+"','"+bookstationname1+"','"+bookstationname2+"','"+bookclass+"')");
					response.sendRedirect("/Railway/done.jsp");
				    }
					
				}
				else if  (request.getParameter("sdf").equals("abc")){
				 ResultSet rs=null;
				 String res=(String)session.getAttribute("sid1");
				 String res2[]=res.split(" ");
				 rs=st.executeQuery("select * from trans where loginid='"+res2[0]+"' order by tdate desc");
				 String s1="";
				 while (rs.next()){
					 s1+=rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+" "+rs.getString(5)+
							 " "+rs.getString(6)+" "+rs.getString(7)+" ";
				 }
				 response.sendRedirect("/Railway/trans.jsp?sdf="+s1+"");
				}
				else if(!cpnr.equals("NULL") && cpnr!=null){
					ResultSet rs4=null,rs5=null;
					System.out.println(cpnr);
				    rs5=st.executeQuery("select * from trans where trind='"+cpnr+"'");
				    if (!rs5.next()) {
				    	response.sendRedirect("/Railway/fail2.jsp");
				    	rs5.close();
				    }
				    else{
				    String a2="",a4="",a7="";
				    int a1=0,a3=0,a5=0,a6=0;
				   
				    
				    	a1=rs5.getInt(1);
				    	a2=rs5.getString(2);
				    	a3=rs5.getInt(3);
				    	a4=rs5.getString(4);
				    	a5=rs5.getInt(5);
				    	a6=rs5.getInt(6);
				    	a7=rs5.getString(7);
				    
				    rs5.close();
				  
				    rs4=st.executeQuery("with a as (select x.* from route x,calendar y where "
							+ "x.trainid='"+a3+"' and y.trainid=x.trainid and "
									+ "x.arrivaltime<='23:59:59' and "
									+ "x.arrivaltime >= y.arrivaltime::time "
									+ "order by x.arrivaltime) select distinct "
									+ "trainid,stationid,arrivaltime,departuretime from a "
									+ "order by arrivaltime");
					Vector<Integer> vy = new Vector<>();
					while (rs4.next()){
						 vy.add(rs4.getInt(2));
					}
					
					rs4.close();
					rs4=st.executeQuery("with b as (select x.* from route x,calendar y "
							+ "where x.trainid='"+a3+"' and y.trainid=x.trainid and "
							+ "x.arrivaltime<='23:59:59' and x.arrivaltime>=y.arrivaltime::time "
							+ "order by x.arrivaltime) select distinct trainid,stationid,arrivaltime,departuretime"
							+ " from route z "
							+ "where z.trainid='"+a3+"' and not exists (select * from b where "
							+ "arrivaltime=z.arrivaltime) order by z.arrivaltime");
					
					while (rs4.next()){
						 vy.add(rs4.getInt(2));
					}
					rs4.close();
					System.out.println("qqwq");
					int ind1=vy.indexOf(a5);
					int ind2=vy.indexOf(a6);
					Vector<Integer> vy2 = new Vector<>();
					for (int j=ind1;j<ind2;j++){
						vy2.add(vy.get(j));
					}
				
					for (int j=0;j<vy2.size();j++){
						if (a7.equals("AC")){
						st.executeUpdate("update seatcap set accap=accap-1 where"
								+ " trainid='"+a3+"' and stationid='"+vy2.get(j)+"' "
										+ "and tdate='"+a4+"'");
						}
						if (a7.equals("SL")){
							st.executeUpdate("update seatcap set slcap=slcap-1 where"
									+ " trainid='"+a3+"' and stationid='"+vy2.get(j)+"' "
											+ "and tdate='"+a4+"'");}
						
						
					}
					System.out.println("asd");
					String res=(String)session.getAttribute("sid1");
					String res2[]=res.split(" ");
					st.executeUpdate("delete from trans where trind='"+a1+"'");
					response.sendRedirect("/Railway/done2.jsp");
				    }
				}
				
				}
				}
				catch (SQLException ex) {
		            ex.printStackTrace();
				}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
