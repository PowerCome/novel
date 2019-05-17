<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${ctx}/css/information.css" />
<title>Insert title here</title>
</head>
<body style="background-image: url(/image/login.jpg) " />
<jsp:include page="/top.jsp"></jsp:include>
<a href="${ctx}/ForSuper/homePage.jsp"><font size="3" >返回</font></a>
		
<div id="information">
<table width=900 height=400 align="center" border="1">
	<caption><h1>用户信息</h1></caption>
	<tr>
	    <td width=100 >用户id</td> 
	    <td width=100 >用户名</td> 
	    <td width=200 >身份证</td> 
	    <td width=200 >手机号</td> 
	    <td width=100 >用户级别</td>
	    <td width=400 colspan="2">级别处理</td>
	</tr>
	<c:forEach items="${pb.beanList }" var="user">
	<tr>
	    <td >${user.id }</td> 
	    <td >${user.name }</td> 
	    <td >${user.idcard }</td> 
	    <td >${user.phone }</td> 
		<c:if  test="${user.roleId == 1 }"><td >顾客</td></c:if> 
		<c:if  test="${user.roleId == 2 }"><td >管理员</td></c:if> 
		<c:if  test="${user.roleId == 3 }"><td >超级管理员</td></c:if> 
		
	    <c:if  test="${user.roleId < 3 }">
		    <td>
			    <a href="${ctx}/MainServlet?id=${user.id}
			    &wdo=upLevel&roleId=${user.roleId}&method=userLevel">提升</a>
			</td>
		</c:if>
		<c:if  test="${user.roleId == 3 }"><td>提升</td></c:if>
		
		<c:if  test="${user.roleId == 2 }">
		    <td>
			    <a href="${ctx}/MainServlet?id=${user.id}
			    &wdo=downLevel&roleId=${user.roleId}&method=userLevel">下降</a>
			</td>
		</c:if>
		<c:if  test="${user.roleId == 3 || user.roleId == 1}"><td>下降</td></c:if>
	</tr>
	</c:forEach>
	</table>
<center>
<a href="<c:url value='/MainServlet?pc=1&method=getUser'/>" >首页</a>
<c:if test="${pb.pageCode > 1 }">
<a href="<c:url value='/MainServlet?pc=${pb.pageCode-1 }&method=getUser'/>" >上一页</a>
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
   <a href="<c:url value='/MainServlet?pc=${i }&method=getUser'/>" >${i }</a>
</c:forEach>


<c:if test="${pb.pageCode < pb.totalPage }">
<a href="<c:url value='/MainServlet?pc=${pb.pageCode+1 }&method=getUser'/>" >下一页</a>
</c:if>
<a href="<c:url value='/MainServlet?pc=${pb.totalPage }&method=getUser'/>" >尾页</a>

</center>
</div>
</body>
</html>