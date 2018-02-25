<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%  if (session == null || session.getAttribute("sid1") == null) {
    response.sendRedirect("login.jsp");
} 
	String str[] = request.getParameter("sdf").split(" ");
	 int l= str.length;
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>My previous booking</title>
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
<center><h1   style="color:orange;font-size:35px">My previous booking</h1>
<table style="text-align:center">
<thead>
<th>PNR</th><th>Loginid</th><th>Trainid</th><th>Date</th>
<th>Source station</th><th>Destination</th>
<th>Class</th>
</thead>
<tbody>
<% for (int i=0;i<l/7;i++){ %>
<tr>

<td ><%=str[7*i] %></td><td><%=str[7*i+1] %></td><td><%=str[7*i+2] %></td>
<td><%=str[7*i+3] %></td><td><%=str[7*i+4] %></td><td><%=str[7*i+5] %></td>
<td><%=str[7*i+6] %>
</tr>
<% } %>
</tbody>
</table>
<p>
<a href="/Railway/home.jsp?login=<%=session.getAttribute("sid1")%>">back</a>
<a href="/Railway/logout.jsp">logout</a>
</body>
</html>