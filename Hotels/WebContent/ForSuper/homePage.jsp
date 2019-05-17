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
<jsp:include page="/top.jsp"></jsp:include>
<body style="background-image: url(/image/login.jpg)" />
<center>
	<table align="right" width="500px" height="200px">
		<caption></caption>
		<tr>
			<td><a href="${ctx }/MainServlet?method=getHotel&role=super">
					<font size="5">管理酒店</font>
			</a></td>
		</tr>
		<p>
		<tr>
		<td><a href="${ctx }/MainServlet?method=getUser"> <font
				size="5">处理用户级别</font></a></td>
		</tr>
	</table>
	</body>
</html>