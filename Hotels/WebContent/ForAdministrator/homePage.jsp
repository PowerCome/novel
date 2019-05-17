<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="Hotels/css/information.css" />
</head>
<body style="background-image: url(/image/login.jpg)" />
<jsp:include page="/top.jsp"></jsp:include>
<center>
	<table align="right" width="500px" height="200px"  >
	<caption></caption>
		<tr><td>
			<a href="/Hotels/MainServlet?method=getCoustomer"><font size="5">查看用户信息</font></a></td>
		</tr>
		<p>
		<tr><td><a href="/Hotels/MainServlet?method=getHotel&role=adm"> <font
				size="5">管理房间信息</font></a></td>
				</tr>
		<tr><td>
			<a href="/Hotels/OrderServlet?method=getSomeOrder"> <font
				size="5">管理订单</font></a></td>
		</tr>
	</table>
</center>
</body>
</html>