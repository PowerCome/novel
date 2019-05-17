<%@page import="javax.xml.ws.Response"%>
<%@page import="javax.security.auth.message.callback.PrivateKeyCallback.Request"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${ctx}/css/information.css" />
</head>
<body style="background-image: url(/image/login.jpg) " />
<form action="${ctx }/ResetPwdServlet"  method="post">
<jsp:include page="/top.jsp"></jsp:include>
<a href="${ctx }/ForSuper/userInformation.jsp">返回</a>
	<table width=300 height=200 align="center">
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
		<td colspan="2" align="center"><button class="button2" type="submit">提交</button></td> 
		
	</tr>		
	</table>
</form>

</body>
</html>