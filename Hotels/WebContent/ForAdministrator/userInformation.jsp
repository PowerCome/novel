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
<body style="background-image: url(/image/login.jpg)" />
<jsp:include page="/top.jsp"></jsp:include>
<a href="${ctx}/ForAdministrator/homePage.jsp">回到首页</a>
<table width=300 height=200 align="center">
	<caption>
		<h1>用户信息</h1>
	</caption>
	<tr>
		<td width=80>用户名: ${user.name }</td>
	</tr>
	<tr>
		<td width=80>用户id: ${user.id }</td>
	</tr>
	<tr>
		<td width=80>手机号: ${user.phone }</td>
	</tr>
	<tr>
		<td width=80>身份证: ${user.idcard }</td>
	</tr>
	<tr>
		<td width=50><a href="${ctx}/ForAdministrator/resetPwd.jsp">修改密码</a></td>
	</tr>
	
</table>
</body>
</html>