<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Trains available between two stations</title>
<style type="text/css">
tr:nth-of-type(even) {
  background-color:white;
} 
th {
  background-color:white;
} 
 </style>
</head>
<body bgcolor="#7fac47">
<center><h1   style="color:orange;font-size:35px">Trains available between two stations</h1>

<br>
<%
	String str[] = request.getParameter("qwe1").split(" ");
	 int l= str.length;

%>
<table style="text-align:center">
<thead>
<th>Train Id</th><th>Departure Time</th><th>Arrival Time</th><th>AC availability</th><th>Sleeper availability</th>
</thead>
<tbody>
<% for (int i=0;i<l/5;i+=1){ %>
<tr>

<td ><%=str[5*i] %></td><td><%=str[5*i+1] %></td><td><%=str[5*i+2] %></td>
<td><%=str[5*i+3] %></td><td><%=str[5*i+4] %></td>
</tr>
<% } %>
</tbody>
</table>
<p>
<a href="/Railway/query1.jsp">back</a></center>
</body>
</html>