<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Distance between two stations</title>
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
<center><h1 style="color:orange;font-size:35px">Distance between two stations for a train</h1></center>

<br>
<%
	String str[] = request.getParameter("qwe5").split(" ");
	 int l= str.length;

%>
<center>
<table>
<thead>
<th>Station Id1</th><th>Station Id2</th><th>Distance</th>
</thead>
<tbody>
<% for (int i=0;i<l/3;i++){ %>
<tr>

<td><%=str[3*i] %></td><td><%=str[3*i+1] %></td><td><%=str[3*i+2] %></td>
</tr>
<% } %>
</tbody>
</table>
<p>
<a href="/Railway/query5.jsp">back</a></</p></center>
</body>
</html>