<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/Hotels/css/simple.css" />
<title>修改房间信息</title>
</head>
<body style="background-image: url(/image/login.jpg)" />
<jsp:include page="/top.jsp"></jsp:include>
<a href="/Hotels/MainServlet?method=getRoom&hotelId=${hotelId}&role=adm">返回</a>
<form action="/Hotels/ResetRoomServlet" enctype="multipart/form-data"
	method="post">
	<table width="450px" height=100 align="center">
		<caption>
			<h1>修改房间信息</h1>
		</caption>
		<tr>
			<td width=70><input name="id" value="${id } " type="text"
				style="display: none"></td>
				<td></td>
		</tr>
		<tr>
			<td width=70><input name="hotelId" value="${hotelId } " type="text"
				style="display: none"></td>
				<td></td>
		</tr>
		<tr>
			<td colspan="2" align="center"><font size="-3" color="red">${tag }</font>
			<td>
		</tr>
		<tr>
			<td width=70 align="right">类型</td>
			<td width=20><select name="type" size="1">
					<option value="${type }" selected="selected">${type }</option>
					<option value="双套间">双套间</option>
					<option value="单人间">单人间</option>
					<option value="标准间">标准间</option>
					<option value="豪华间">豪华间</option>
			</select></td>
		</tr>
		<tr>
			<td width=70 align="right">占地面积</td>
			<td width=20><input name="area" value="${area }" type="text"></td>

		</tr>
		<tr>
			<td width=70 align="right">床宽</td>
			<td width=20><input name="bedWidth" value="${bedWidth } "
				type="text"></td>

		</tr>
		<tr>
			<td width=70 align="right">价格</td>
			<td width=20><input name="price" value="${price } " type="text"></td>

		</tr>
		<tr>
			<td width=70 align="right">上传房间图片:</td>
			<td width=20><input name="picture" type="file" ></td>
		</tr>
		<tr>
			<td colspan="2" align="right"><button class="button2" type="submit"
				>提交</td>

		</tr>
		</form>
		</body>
		</html>