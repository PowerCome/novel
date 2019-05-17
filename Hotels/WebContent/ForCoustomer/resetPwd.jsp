<%@page import="javax.xml.ws.Response"%>
<%@page import="javax.security.auth.message.callback.PrivateKeyCallback.Request"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/Hotels/css/information.css" />
</head>
<body style="background-image: url(/image/login.jpg) " />
<jsp:include page="/top.jsp"></jsp:include>
<a href="/Hotels/ForCoustomer/userInformation.jsp" >返回</a>
<form action="/Hotels/ResetPwdServlet"  method="post">
	<table width=300 height=100 align="center">
	<caption><h1>修改密码</h1></caption>
	<tr>
	    <td colspan="2" align="center" ><font size="-3" color="red">${tag }</font><td>
	</tr>
	<tr>
		<td width=70 align="right">旧密码</td> 
		<td width=20><input name="oldPwd" type="password" maxlength="25"></td>
	
	</tr>
	<tr>
		<td width=70 align="right">新密码</td> 
		<td width=20><input name="newPwd" type="password" maxlength="25"></td>
	
	</tr>
	<tr>
		<td width=70  align="right">确认密码</td> 
		<td width=20><input name="newPwd2" type="password" maxlength="25"></td>
	</tr>	
	<tr>
		<td colspan="2" align="right"><button class="button2" type="submit">提交</button></td>
	</table>
</form>

</body>
</html>