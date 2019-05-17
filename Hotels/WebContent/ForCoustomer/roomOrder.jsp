<%@page import="javax.xml.ws.Response"%>
<%@page import="javax.security.auth.message.callback.PrivateKeyCallback.Request"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/Hotels/css/simple.css" />
</head>
<body>
	<a
		href="/Hotels/MainServlet?method=getRoom&hotelId=${hotelId}&role=coustomer">返回</a>
	<h1>预定信息</h1>
	${tag }
	<br />
	<hr color="gray" />
	<form action="/Hotels/ReservaionServlet" method="post">
		<table>
			<tr>
				<td><font size="5">入住时间</font>&nbsp;&nbsp;&nbsp;</td>
				<td><select name="startYear" size="1">
						<option value="${year }">${year }</option>
						<c:if test="${month == 12 }">
							<option value="${year + 1 }">${year + 1 }</option>
						</c:if>
				</select>&nbsp;年&nbsp;</td>
				<td><select name="startMonth" size="1">
						<option value="${month }">${month }</option>
						<c:if test="${month == 12 }">
							<option value="01">01</option>
						</c:if>
						<c:if test="${month != 12 }">
							<option value="${month + 1 }">${month + 1 }</option>
						</c:if>
				</select>&nbsp;月&nbsp;</td>
				<td><select name="startDay" size="1">
						<c:forEach var="i" begin="01" end="31">
							<option value="${i }">${i }</option>
						</c:forEach>
				</select> &nbsp;日&nbsp;</td>
				<td><select name="startTime" size="1">
						<c:forEach var="i" begin="01" end="12">
							<option value="${i }">${i }</option>
						</c:forEach>
				</select> &nbsp;时&nbsp;</td>
			</tr>
		</table>
		<br />
		<table>
			<tr>
				<td><font size="5">退房时间</font>&nbsp;&nbsp;&nbsp;</td>
				<td><select name="endYear" size="1">
						<option value="${year }">${year }</option>
						<c:if test="${month == 12 }">
							<option value="${year + 1 }">${year + 1 }</option>
						</c:if>
				</select>&nbsp;年&nbsp;</td>
				<td><select name="endMonth" size="1">
						<option value="${month }">${month }</option>
						<c:if test="${month == 12 }">
							<option value="1">1</option>
						</c:if>
						<c:if test="${month != 12 }">
							<option value="${month + 1 }">${month + 1 }</option>
						</c:if>
				</select>&nbsp;月&nbsp;</td>
				<td><select name="endDay" size="1">
						<c:forEach var="i" begin="01" end="31">
							<option value="${i }">${i }</option>
						</c:forEach>
				</select> &nbsp;日&nbsp;</td>
				<td><select name="endTime" size="1">
						<c:forEach var="i" begin="01" end="12">
							<option value="${i }">${i }</option>
						</c:forEach>
				</select> &nbsp;时&nbsp;</td>
			</tr>
		</table>
	<button class="button2" type="submit" align="center">提交</button>
	</form>
</body>
</html>