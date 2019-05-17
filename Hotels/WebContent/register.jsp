<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${ctx }/css/Register.css" />
<script type="text/javascript" src="${ctx }/js/register.js"></script>
</head>
<body>
	<img src="/image/login.jpg">
	<div id="reg">
		<h1>用户注册</h1>
		<form name="form1"
			action="${ctx }/RegisterServlet" method="post"
			onsubmit="return check()">
			<font size="3" color="red">${tag }</font>
			<p>
				<span>用户名&nbsp;&nbsp;&nbsp;&nbsp;:</span><input name="name"
					type="text">
			</p>
			<p>
				<span>手机号&nbsp;&nbsp;&nbsp;&nbsp;:</span><input name="phone"
					type="text" maxlength="11">
			</p>
			<p>
				<span>身份证&nbsp;&nbsp;&nbsp;&nbsp;:</span><input name="idcard"
					type="text" maxlength="18">
			</p>
			<p>
				<span>&nbsp;&nbsp;密&nbsp;码&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:</span><input
					name="pwd1" type="password" maxlength="25">
			</p>
			<p>
				<span>确认密码:</span><input name="pwd2" type="password" maxlength="25">
			</p>
			<p>
				<button class="button" type="submit">注册</button>
			</p>
			<table align="center">
				<tr>
					<a href="${ctx }/login.jsp"><font size="4"
						color="white">返回登陆界面</font></a>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>