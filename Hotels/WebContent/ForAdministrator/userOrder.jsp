<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="/Hotels/css/information.css" />
<title>Insert title here</title>
</head>
<body style="background-image: url(/image/login.jpg)" />
<jsp:include page="/top.jsp"></jsp:include>
<a href="<c:url value='/ForAdministrator/homePage.jsp'/>"><font
	size="3">返回</font></a>
<a href="<c:url value='/OrderServlet?method=getCancelOrder'/>"><font
	size="3">处理取消申请</font></a>
<div id="information">
	<table width=700 height=500 align="center" border="1">
		<caption>
			<h1>用户预定信息</h1>
		</caption>
		<tr>
			<td width=100>入住日期</td>
			<td width=100>退房日期</td>
			<td width=100>入住时间</td>
			<td width=100>退房时间</td>
			<td width=100>房间类型</td>
			<td width=100>酒店id</td>
			<td width=100>用户id</td>
		</tr>
		<c:forEach items="${pb.beanList }" var="order">
			<tr>
				<td>${order.startDate }</td>
				<td>${order.endDate }</td>
				<td>${order.startTime }</td>
				<td>${order.endTime }</td>
				<td>${order.type }</td>
				<td>${order.hotelId }</td>
				<td>${order.userId }</td>
			</tr>
		</c:forEach>
	</table>
	<center>
		<a href="<c:url value='/OrderServlet?pc=1&method=getSomeOrder'/>">首页</a>
		<c:if test="${pb.pageCode > 1 }">
			<a
				href="<c:url value='/OrderServlet?pc=${pb.pageCode-1 }&method=getSomeOrder'/>">上一页</a>
		</c:if>

		<c:choose>
			<c:when test="${pb.totalPage <= 10 }">
				<c:set var="begin" value="1" />
				<c:set var="end" value="${pb.totalPage }" />
			</c:when>
			<c:otherwise>
				<c:set var="begin" value="${pd.pageCode-5 }" />
				<c:set var="end" value="${pb.pageCode+4 }" />
				<c:if test="${begin < 1 }">
					<c:set var="begin" value="1" />
					<c:set var="end" value="10" />
				</c:if>
				<c:if test="${end >pb.totalPage}">
					<c:set var="begin" value="${pb.totalPage-9 }" />
					<c:set var="end" value="${pb.totalPage }" />
				</c:if>
			</c:otherwise>
		</c:choose>
		<c:forEach var="i" begin="${begin }" end="${end }">
			<a href="<c:url value='/OrderServlet?pc=${i }&method=getSomeOrder'/>">${i }</a>
		</c:forEach>


		<c:if test="${pb.pageCode < pb.totalPage }">
			<a
				href="<c:url value='/OrderServlet?pc=${pb.pageCode+1 }&method=getSomeOrder'/>">下一页</a>
		</c:if>
		<a
			href="<c:url value='/OrderServlet?pc=${pb.totalPage }&method=getSomeOrder'/>">尾页</a>
	</center>
</div>
</body>
</html>