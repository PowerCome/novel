<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table align="right">
		<tr>
			<c:if test="${user.roleId == 1}">
				<td><font size="3">你好顾客</font></td>
				<td><a href="/Hotels/ForCoustomer/userInformation.jsp"> 
						<font size="3" color="#800000">查看信息</font></a>&nbsp;&nbsp;|&nbsp;&nbsp;</td>
				<td><a href="/Hotels/MainServlet?method=getOrder"> 
						<font size="3" color="#800000">查看订单</font></a>&nbsp;&nbsp;|&nbsp;&nbsp;</td>
			</c:if>
			<c:if test="${user.roleId == 2}">
				<td><font size="3">你好管理员</font></td>
				<td><a href="/Hotels/ForAdministrator/userInformation.jsp">
						<font size="3" color="#800000">查看信息</font></a>&nbsp;&nbsp;|&nbsp;&nbsp;
				</td>
			</c:if>
			<c:if test="${user.roleId == 3}">
				<td><font size="3">你好超管</font></td>
				<td><a href="${ctx }/ForSuper/userInformation.jsp"><font size="3" color="#800000">查看信息</font></a>&nbsp;&nbsp;|&nbsp;&nbsp;</td>
			</c:if>
			</td>
			<td><a href="<c:url value='/login.jsp'/>"><font size="3" color="#800000">退出登陆</font></a>
			</td>
		</tr>


	</table>
</body>
</html>