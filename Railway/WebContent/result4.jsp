<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Train Status</title>
<style type="text/css">
tr:nth-of-type(even) {
  background-color:white;
} 
th {
  background-color:white;
} 
 </style>
</head>
<body  bgcolor="#7fac47">
<center><h1  style="color:orange;font-size:35px">Train status</h1></center>

<br>
<%
	String str[] = request.getParameter("qwe4").split(" ");

	if (str[0].equals("motion")){

%>
<center>
<table>
<thead>
<th>Station name1</th><th>Departure Time</th><th>Station name2</th><th>Arrival Time</th>
</thead>
<tbody>
<tr>

<td><%=str[1] %></td><td><%=str[2] %></td><td><%=str[3] %></td><td><%=str[4] %></td>
</tr>
</tbody>
</table>
<% } 

	else if (str[0].equals("rest")){

%>
<table>
<thead>
<th>Station name</th><th>Departure Time</th><th>Arrival Time</th>
</thead>
<tbody>
<tr>

<td><%=str[1] %></td><td><%=str[2] %></td><td><%=str[3] %></td>
</tr>
</tbody>
</table>
<% } else if(str[0].equals("nothing")) { %>
<center><h1>Train not running</h1></center>
<%} %>
<br><br><a href="/Railway/query4.jsp">back</a></center>
</body>
</html>