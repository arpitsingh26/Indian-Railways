<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Fare Inquiry</title>
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

<center><h1  style="color:orange;font-size:35px">Fare inquiry for a train</h1></center>

<br>
<%
	String str[] = request.getParameter("qwe3").split(" ");
	

%>



  <div>
        <ul id="Menu" style="text-align:center">
            <li>AC: Rs.<%=str[0] %></li>
            <li>Sleeper: Rs.<%=str[1] %></li>
            <li>General: Rs.<%=str[2] %></li>
           
           
        </ul><br><br><center><a href="/Railway/intro.html">back</a></center>
    </div>



</body>
</html>
