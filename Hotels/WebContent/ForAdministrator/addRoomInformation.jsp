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
<link rel="stylesheet" type="text/css" href="/Hotels/css/simple.css" />
</head>
<body style="background-image: url(/image/login.jpg)" />
<a href="${ctx }/MainServlet?method=getRoom&hotelId=${hotelId}&role=adm">返回</a>
<jsp:include page="/top.jsp"></jsp:include>
<form action="${ctx }/AddRoomServlet" enctype="multipart/form-data"
	method="post">
	<table width="450" height="100" align="center">
		<caption>
			<h1>添加房间</h1>
		</caption>
		<tr>
			<td width=70><input name="hotelId" type="text"
				value="${ hotelId}" style="display: none"></td>
			<td></td>
		</tr>
		<tr>
			<td colspan="2" align="center"><font size="-3" color="red">${tag }</font>
				<td>
		
		</tr>
		<tr>
			<td width=70 align="right">类型</td>
			<td width=20><select name="type" size="1">
					<option value="双套间">双套间</option>
					<option value="单人间">单人间</option>
					<option value="标准间">标准间</option>
					<option value="豪华间">豪华间</option>
			</select></td>

		</tr>
		<tr>
			<td width=70 align="right">占地面积</td>
			<td width=20><input name="area" type="text" value="${ area}"></td>

		</tr>
		<tr>
			<td width=70 align="right">床宽</td>
			<td width=20><input name="bedWidth" type="text"
				value="${ bedWidth}"></td>

		</tr>
		<tr>
			<td width=70 align="right">价格</td>
			<td width=20><input name="price" type="text" value="${ price}"></td>
		</tr>
		<tr>
			<td width=70 align="right">上传房间图片:</td>
			<td width=20><input name="picture" type="file"
				value="${ picture}"></td>
		</tr>
		<tr>
			<td colspan="2" align="right"><button class="button2"
					type="submit">提交</td>

		</tr>
	</table>
</form>

</body></
			html>