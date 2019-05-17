<%@page import="javax.xml.ws.Response"%>
<%@page
	import="javax.security.auth.message.callback.PrivateKeyCallback.Request"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/Hotels/css/information.css" />
</head>
<body style="background-image: url(/image/login.jpg)" />
<a href="${ctx }/MainServlet?method=getHotel&role=super">返回</a>
<form action="${ctx }/AddHotelServlet" method="post">
	<jsp:include page="/top.jsp"></jsp:include>
	<table width=300 height=100 align="center">
		<caption>
			<h1>添加酒店</h1>
		</caption>
		<tr>
			<td colspan="2" align="center"><font size="-3" color="red">${tag }</font>
			<td>
		</tr>
		<tr>
			<td width=70 align="right">名称</td>
			<td width=20><input name="name" value="${name }" type="text"></td>

		</tr>
		<tr>
			<td width=70 align="right">星级</td>
			<td width=20><input name="level" value="${level } " type="text"></td>

		</tr>
		<tr>
			<td width=70 align="right">评分</td>
			<td width=20><input name="score" value="${score } " type="text"></td>

		</tr>
		<tr>
			<td width=70 align="right">类型</td>
			<td width=20><select name="type" size="1">
					<option value="长住型">长住型</option>
					<option value="度假型">度假型</option>
					<option value="个性化">个性化</option>
					<option value="商务型">商务型</option>
					<option value="会议型">会议型</option>
			</select></td>
		</tr>
		<tr>
			<td colspan="2" align="right"><button class="button2" type="submit">提交</button>

		</tr>
	</table>
</form>

</body>
</html>