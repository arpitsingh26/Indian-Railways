<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<% if (session == null || session.getAttribute("sid1") == null) {
    response.sendRedirect("login.jsp");
} 
	String str[] = request.getParameter("login").split(" ");

%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><%= str[1] %></title>
  <style type="text/css">    

             #Menu
        {
            padding:0;
            margin:0;
            list-style-type:none;
            font-size:16px;
            color:white;
            width:100%;
        }

        #Menu li
        {
            border-bottom:1px solid #eeeeee;
            padding:7px 25px 7px 25px;
        }


        #Menu a:link
        {
            color:#F2F2F2;
            text-decoration:none;
        }

        #Menu a:hover
        {
            color:#ffcc00;
        }
        
    </style>
</head>

<body bgcolor="#7fac47">
<center><h1 style="color:orange;font-size:35px">Welcome <%= str[1] %></h1></center>
<center> 
<form action="JdbcPostgresqlConnection">
<center><h1 style="color:orange;font-size:35px"> Cancel Ticket</h1></center>

<center><div>
        <ul id="Menu" style="text-align:center">
<li>
PNR
<input type="text" name="cpnr" size="20px"></li>
<li><input id="submit1" type="submit" value="Cancel ticket" onclick="al()"> 
 
<input type="reset" VALUE="clear">

<input type="hidden" name="stat" VALUE="abcd">
<input type="hidden" name="tdate" VALUE="NULL">
<input type="hidden" name="trainname" VALUE="NULL">
<input type="hidden" name="trainname1" VALUE="NULL">
<input type="hidden" name="stationname11" VALUE="NULL">
<input type="hidden" name="stationname21" VALUE="NULL">
<input type="hidden" name="trainname11" VALUE="NULL">
<input type="hidden" name="time1" VALUE="NULL">
<input type="hidden" name="date1" VALUE="NULL">
<input type="hidden" name="trainname12" VALUE="NULL">
<input type="hidden" name="loginid" VALUE="NULL">
<input type="hidden" name="loginpass" VALUE="NULL">
<input type="hidden" name="booktrainname" VALUE="NULL">
<input type="hidden" name="booktdate" VALUE="NULL">
<input type="hidden" name="bookstationname1" VALUE="NULL">
<input type="hidden" name="bookstationname2" VALUE="NULL">
<input type="hidden" name="sdf" VALUE="NULL">
<input type="hidden" name="sloginid" VALUE="NULL">
<input type="hidden" name="sloginname" VALUE="NULL">
<input type="hidden" name="sloginpass" VALUE="NULL"></li></ul><br><br></div></center></form>
</center>
<center>
<a href="/Railway/home.jsp?login=<%=request.getParameter("login")%>">back</a>
<a href="/Railway/logout.jsp">logout</a></center>
</body>
</html>
