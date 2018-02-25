<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Route for a train</title>
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
<center><h1   style="color:orange;font-size:35px">Route for a train</h1></center>

<br>
<%
	String str[] = request.getParameter("qwe2").split(" ");
	 int l= str.length;
	System.out.println(str);

%>
<center>
<table style="text-align:center">
<thead>
<th>Train Id</th><th>Station Id</th><th>Arrival Time</th><th>Departure Time</th>
</thead>
<tbody>
<% for (int i=0;i<l/4;i++){ %>
<tr>

<td><%=str[4*i] %></td><td><%=str[4*i+1] %></td><td><%=str[4*i+2] %></td><td><%=str[4*i+3] %></td>
</tr>
<% } %>
</tbody>
</table>
<p>
<a href="/Railway/query2.jsp">back</a></center>
</body>
</html>