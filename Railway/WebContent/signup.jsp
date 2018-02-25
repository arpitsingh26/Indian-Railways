<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Signup</title>
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
<form action="JdbcPostgresqlConnection">
<center><h1   style="color:orange;font-size:35px">Signup</h1></center>
<p>
<center>
  <div>
        <ul id="Menu" style="text-align:center">
<li>Loginid
<input type="text" name="sloginid" size="20px"></li>

<li>Name<input type="text" name="sloginname" size="20px"></li>
<li>Password
<input type="password" name="sloginpass" size="20px"></li>

<input id="submit1" type="submit" value="submit" onclick="al()"> 
<input type="reset" VALUE="clear">

<input type="hidden" name="stat" VALUE="abcd">

<input type="hidden" name="trainname" VALUE="NULL">
<input type="hidden" name="trainname1" VALUE="NULL">
<input type="hidden" name="stationname11" VALUE="NULL">
<input type="hidden" name="stationname21" VALUE="NULL">
<input type="hidden" name="trainname11" VALUE="NULL">
<input type="hidden" name="time1" VALUE="NULL">
<input type="hidden" name="date1" VALUE="NULL">
<input type="hidden" name="trainname12" VALUE="NULL">
<input type="hidden" name="tdate" VALUE="NULL">
<input type="hidden" name="stationname1" VALUE="NULL">
<input type="hidden" name="stationname2" VALUE="NULL">
<input type="hidden" name="booktrainname" VALUE="NULL">
<input type="hidden" name="booktdate" VALUE="NULL">
<input type="hidden" name="bookstationname1" VALUE="NULL">
<input type="hidden" name="bookstationname2" VALUE="NULL">
<input type="hidden" name="bookclass" VALUE="NULL">
<input type="hidden" name="sdf" VALUE="NULL">
<input type="hidden" name="cpnr" VALUE="NULL">
<input type="hidden" name="loginid" VALUE="NULL">
<input type="hidden" name="loginpass" VALUE="NULL">
</form> 
</ul><br><br>
    </div>
</body>
<center>Already a member?
<a href="/Railway/login.jsp">Login</a><br><br>
<a href="/Railway/intro.html">back</a></center>
</html>
