<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Input Form</title>
</head>
<body>
	<form action="inputProc" method="post">
		<table border=1 align=center>
			<tr>
				<th>Contact
			</tr>
			<tr>
				<td><input type=text name="name" placeholder="input your name" required>
			</tr>
			<tr>
				<td><input type=text name="contact" placeholder="input your contact" required>
			</tr>
			<tr>
				<td align=center><input type=submit>
			</tr>
			<tr>
				<td align=center><a href="/">back</a>
			</tr>
		</table>
	</form>
</body>
</html>