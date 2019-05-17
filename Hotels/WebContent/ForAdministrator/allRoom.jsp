<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/Hotels/css/information.css" />
</head>
<body>
<jsp:include page="/top.jsp"></jsp:include>
<a href="<c:url value='/MainServlet?method=getHotel&role=adm'/>">返回</a>
	<a href="<c:url value='/ForAdministrator/addRoomInformation.jsp?'/>">增加房间</a>
	<h1></h1>
	<c:forEach items="${pb.beanList }" var="room">
		<table width=650 height=250 align="left" border="0">
			<tr>
				<td rowspan="15" aligen="left"><img width="300" height="250"
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
			<tr><td></td></tr>
			<tr><td></td></tr>
			<tr><td></td></tr>
			<tr><td>
				<a
					href="/Hotels/MainServlet?roomId=${room.id }
						&hotelId=${hotelId }&method=delRoom"><button class="button2">删除</button></a>
			</td></tr>
			<tr>
				<td><a
					href="javascript:location.href=encodeURI('http://localhost:8080/Hotels/MainServlet?method=getRoomInitialValue
					&id=${room.id }&type=${room.type }&area=${room.area }&picture=${room.picture }
				    &bedWidth=${room.bedWidth }&price=${room.price }&hotelId=${hotelId }')"><button class="button2">修改</button></a>
			</td></tr>
			<tr><td></td></tr>
			<tr><td></td></tr>
			<tr><td></td></tr>
			<tr><td></td></tr>
			<tr><td></td></tr>
		</table>
	</c:forEach>
	<div id="foot">
		<a
			href="<c:url value='/MainServlet?pc=1&hotelId=${hotelId }&method=getRoom&role=adm'/>">首页</a>
		<c:if test="${pb.pageCode > 1 }">
			<a
				href="<c:url value='/MainServlet?pc=${pb.pageCode-1 }&hotelId=${hotelId }&method=getRoom&role=adm'/>">上一页</a>
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
				href="<c:url value='/MainServlet?pc=${i }&hotelId=${hotelId }&method=getRoom&role=adm'/>">${i }</a>
		</c:forEach>


		<c:if test="${pb.pageCode < pb.totalPage }">
			<a
				href="<c:url value='/MainServlet?pc=${pb.pageCode+1 }&hotelId=${hotelId }&method=getRoom&role=adm'/>">下一页</a>
		</c:if>
		<a
			href="<c:url value='/MainServlet?pc=${pb.totalPage }&hotelId=${hotelId }&method=getRoom&role=adm'/>">尾页</a>
	</div>
</body>
</html>