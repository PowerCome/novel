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
<div id="information">
	<table width=700 height=500 align="center" border="1">
		<caption>
			<h1>用户信息</h1>
		</caption>
		<tr>
			<td width=100>用户id</td>
			<td width=200>用户名</td>
			<td width=300>身份证</td>
			<td width=200>手机号</td>
		</tr>
		<c:forEach items="${pb.beanList }" var="user">
			<tr>
				<td>${user.id }</td>
				<td>${user.name }</td>
				<td>${user.idcard }</td>
				<td>${user.phone }</td>
			</tr>
		</c:forEach>
	</table>
	<center>
		<a href="<c:url value='/MainServlet?pc=1&method=getCoustomer'/>">首页</a>
		<c:if test="${pb.pageCode > 1 }">
			<a
				href="<c:url value='/MainServlet?pc=${pb.pageCode-1 }&method=getCoustomer'/>">上一页</a>
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
			<a href="<c:url value='/MainServlet?pc=${i }&method=getCoustomer'/>">${i }</a>
		</c:forEach>


		<c:if test="${pb.pageCode < pb.totalPage }">
			<a
				href="<c:url value='/MainServlet?pc=${pb.pageCode+1 }&method=getCoustomer'/>">下一页</a>
		</c:if>
		<a
			href="<c:url value='/MainServlet?pc=${pb.totalPage }&method=getCoustomer'/>">尾页</a>

	</center>
</div>
</body>
</html>