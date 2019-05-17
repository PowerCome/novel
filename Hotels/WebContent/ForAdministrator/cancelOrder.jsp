<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="/top.jsp"></jsp:include>
<a href="/Hotels/OrderServlet?method=getSomeOrder"><font
		size="3" color="black">返回</font></a>
	<h1>处理用户订单取消申请</h1><br/>
	${tag }
	<br />
	<hr color="gray" />
	<c:forEach items="${orders }" var="order">
		<table>
		<tr>
		<td>房间类型&nbsp;:${order.type }<td>
		</tr>
		<tr>
		<td>预约时间&nbsp;:${order.startDate }&nbsp;&nbsp;${order.startTime}时&nbsp;到&nbsp;${order.endDate }&nbsp;&nbsp;${order.endTime}时<td>
		</tr>
		<tr>
		<td><a href="/Hotels/OrderServlet?method=delCancel&orderId=${order.id}&startTime=${order.startTime}&startDate=${order.startDate}">取消</a><td>
		</tr>
		</table>
		<br/>
		<br/>
	</c:forEach>
</body>
</html>