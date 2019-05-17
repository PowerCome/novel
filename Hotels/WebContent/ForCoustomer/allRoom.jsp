<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="/Hotels/css/information.css" />
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="/top.jsp"></jsp:include>
	<a href="<c:url value='/MainServlet?method=getHotel&role=coustomer'/>"><font
		size="3">返回</font></a>
		<h1></h1>
		<c:forEach items="${pb.beanList }" var="room">
				<table width=650 height=200 align="left" border="0">
						<td rowspan="6" aligen="left"><img width="300" height="200"
							src="/image/${room.picture}"></td>
						<td>房间id:${room.id }</td>
					</tr>
					<tr>
						<td>房间类型:${room.type }</td>
					</tr>
					<tr>
						<td>占地面积:${room.area }</td>
					</tr>
					<tr>
						<td>床宽:${room.bedWidth }</td>
					</tr>
					<tr>
						<td>价格:${room.price }</td>
					</tr>
					<tr>
						<td><a
							href="javascript:location.href=encodeURI('http://localhost:8080/Hotels/MainServlet?roomId=${room.id }
						&type=${room.type }&method=getRoomInformation')"><button
									class="button2">预定</button></a></td>
					</tr>
				</table>
			<br />
			<br />
			<br />
		</c:forEach>
		<div id="foot">
			<a
				href="<c:url value='/MainServlet?pc=1&hotelId=${hotelId }&method=getRoom&role=coustomer'/>">首页</a>
			<c:if test="${pb.pageCode > 1 }">
				<td><a
					href="<c:url value='/MainServlet?pc=${pb.pageCode-1 }&hotelId=${hotelId }&method=getRoomrole=coustomer'/>">上一页</a></td>
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
				<a
					href="<c:url value='/MainServlet?pc=${i }&hotelId=${hotelId }&method=getRoom&role=coustomer'/>">${i }</a>
			</c:forEach>


			<c:if test="${pb.pageCode < pb.totalPage }">
				<a
					href="<c:url value='/MainServlet?pc=${pb.pageCode+1 }&hotelId=${hotelId }&method=getRoom&role=coustomer'/>">下一页</a>
			</c:if>
			<a
				href="<c:url value='/MainServlet?pc=${pb.totalPage }&hotelId=${hotelId }&method=getRoom&role=coustomer'/>">尾页</a>
			</div>
</body>
</html>