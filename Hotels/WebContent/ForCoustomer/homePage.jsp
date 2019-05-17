<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/Hotels/css/information.css" />
<title>Insert title here</title>
</head>
<body style="background-image: url(/image/login.jpg) " />
<jsp:include page="/top.jsp"></jsp:include>
<div id="information">
	<table width=700 height=500 align="center" border="1">
		<caption><h1>酒店信息</h1></caption>
		<tr>
		    <td width=100 >酒店id</td> 
		    <td width=100 >名称</td>
		    <td width=100 >星级</td> 
		    <td width=100 >评分</td> 
		    <td width=100 >类型</td> 
		    <td width=100 >基本操作</td>
		</tr>
		<c:forEach items="${pb.beanList }" var="hotel">
		<tr>
		    <td >${hotel.id }</td> 
		    <td >${hotel.name }</td> 
		    <td >${hotel.level }</td> 
		    <td >${hotel.score }</td> 
		    <td >${hotel.type }</td> 
		    <td ><a href="/Hotels/MainServlet?hotelId=${hotel.id }&method=getRoom&role=coustomer">进入房间</a></td>
		</tr>
		</c:forEach>
		</table>
	<center>
	<a href="<c:url value='/MainServlet?pc=1&method=getHotel&role=coustomer'/>" >首页</a>
	<c:if test="${pb.pageCode > 1 }">
	<a href="<c:url value='/MainServlet?pc=${pb.pageCode-1 }&method=getHotel&role=coustomer'/>" >上一页</a>
	</c:if>
	
	<c:choose>
		<c:when test="${pb.totalPage <= 10 }">
			<c:set var="begin" value = "1"/>
			<c:set var="end" value = "${pb.totalPage }"/>
		</c:when>
		<c:otherwise>
			<c:set var="begin" value ="${pd.pageCode-5 }"/>
			<c:set var="end" value = "${pb.pageCode+4 }"/>
			<c:if test="${begin < 1 }">
				<c:set var="begin" value ="1" />
				<c:set var="end" value ="10" />
			</c:if>
			<c:if test="${end >pb.totalPage}">
				<c:set var="begin" value ="${pb.totalPage-9 }"/>
				<c:set var="end" value ="${pb.totalPage }"/>
			</c:if>
		</c:otherwise>
	</c:choose>
	<c:forEach var="i" begin="${begin }" end="${end }">
	   <a href="<c:url value='/MainServlet?pc=${i }&method=getHotel&role=coustomer'/>" >${i }</a>
	</c:forEach>
	
	
	<c:if test="${pb.pageCode < pb.totalPage }">
	<a href="<c:url value='/MainServlet?pc=${pb.pageCode+1 }&method=getHotel&role=coustomer'/>" >下一页</a>
	</c:if>
	<a href="<c:url value='/MainServlet?pc=${pb.totalPage }&method=getHotel&role=coustomer'/>" >尾页</a>
	</center>
</div>
</body>
</html>