<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>InputForm</title>
</head>
<body>
	<form action="inputProc" method="get">
		<table border=1 align=center>
			<tr>
				<th colspan=2>입력
			</tr>
			<tr>
				<td><input type="text" placeholder="아이디" name="id">
				<td><input type="text" placeholder="비밀번호" name="pw">
			</tr>
			<tr>
				<td colspan=2><input type="submit" value="제출">
			</tr>
		</table>
	</form>
</body>
</html>