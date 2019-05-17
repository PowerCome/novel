<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body style="background-image: url(/image/login.jpg)" />
<form action="${ctx }/ForgetPwdServlet"
	method="post">
	<table width=300 height=100 align="center">
		<caption>
			<h1>忘记密码</h1>
		</caption>
		<tr>
			<td colspan="2" align="center"><font size="-3" color="red">${tag }</font>
			<td>
		</tr>
		<tr>
			<td width=70 align="right">手机号</td>
			<td width=20><input name="phone" type="text" maxlength="11"></td>

		</tr>
		<tr>
			<td width=70 align="right">身份证</td>
			<td width=20><input name="idcard" type="text" maxlength="18"></td>

		</tr>
		<tr>
			<td width=70 align="right">新密码</td>
			<td width=20><input name="newPwd" type="password" maxlength="25"></td>
		</tr>
		<tr>
			<td width=70 align="right">确认密码</td>
			<td width=20><input name="newPwd2" type="password"
				maxlength="25"></td>
		</tr>
		<tr>
			<td colspan="2" align="right"><input name="botton" type="submit"
				value="提交"></td>
		</tr>
		<tr>
			<td colspan="2" align="right"><a
				href="${ctx }/login.jsp"><font size="2"
					color="black">返回</font></a>
		</tr>

	</table>
</form>
</body>
</html>