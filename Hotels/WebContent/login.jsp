<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/Hotels/css/Login.css" />
<script type="text/javascript" src="/Hotels/js/login.js"></script>
</head>
<body>
<img src="/image/login.jpg">
<div  id="login">
	<h1>用户登陆</h1>
 	<form action="/Hotels/LoginServlet" method="post" ">
			  <font size="3" color="red">${tag }</font>
		<p>
				<span>用户名</span> 
				<input name="uname" type="text" value="${user.name }"></input>
		</p>
		<p>
				<span>密&nbsp;&nbsp;码</span> 
				<input name="upwd" type="password" maxlength="20" value="${pwd }"></input>
		</p>
		<table align="center">
			<tr>
				<td><input name="code"  type="text" class="code" siza="20"></input></td>
				<td></td>
				<td>
					<img id="img"  width="85" height="40" src="/Hotels/MainServlet?method=getVerifyCode" />
					<a href="javascript:_change()"><font size="3">看不清？换一张</font></a>
				</td>
			</tr>
		</table>
			<p>
					<button class="button2" type="submit">立即登陆</button>
			</p>
			<table align="center">
				<tr>
				<P>
					<a href="/Hotels/register.jsp"><font size="4">注册账户</font></a>
				</P>
					&nbsp;&nbsp;&nbsp;&nbsp;<a href="/Hotels/forgetPwd.jsp"><font size="4">忘记密码？</font></a>
				</tr>
			</table>	
		</form>	
	</div>
</body>
</html>

<script type="text/javascript">
function _change(){
	var imgEle = document.getElementById("img");
	imgEle.src = "/Hotels/MainServlet?method=getVerifyCode&a="+ new Date().getTime();
}
</script>