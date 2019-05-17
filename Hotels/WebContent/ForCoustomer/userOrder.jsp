<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/Hotels/css/information.css" />
</head>
<body>
<jsp:include page="/top.jsp"></jsp:include>
<a href="/Hotels/MainServlet?method=getRoom&hotelId=${hotelId}&role=coustomer"><font
		size="3" color="black">返回</font></a>
	<h1>订单详情</h1><br/>
	<font color="red">申请取消预约请在入住前3小时内申请，否则不予处理</font>
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
		<td><a href="/Hotels/OrderServlet?method=addCancel&orderId=${order.id}&startTime=${order.startTime}&startDate=${order.startDate}"><button class="button2">申请取消</button></a><td>
		</tr>
		</table>
		<br/>
		<br/>
	</c:forEach>
</body>
</html>