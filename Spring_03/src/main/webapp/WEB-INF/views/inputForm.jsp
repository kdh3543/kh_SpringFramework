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
				<th>메세지 작성 페이지
			</tr>
			<tr>
				<td>
					<input type=text placeholder="writer" name="writer">
					
				</td>
			</tr>
			<tr>
				<td>
					<input type=text placeholder="message" name="message">
					<button>제출</button>
					<a href="/"><input type=button value="뒤로가기"></a>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>