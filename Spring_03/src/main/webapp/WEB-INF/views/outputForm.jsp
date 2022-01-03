<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>outputForm</title>
</head>
<body>
	<table border=1 align=center>
		<tr>
			<th colspan=4 align=center>메세지 리스트
		</tr>
		<tr>
			<td>No
			<td>작성자
			<td>메세지
			<td>날짜
		</tr>
		<c:forEach var="i" items="${list }">
			<tr>
				<td>${i.seq }
				<td>${i.writer }
				<td>${i.message }
				<td>${i.write_date }
			</tr>
		</c:forEach>
		<tr>
			<form action="search">
				<td colspan=4>
					<input type=text placeholder="search by Seq" name="seq">
					<button>찾기</button>
				</td>
			</form>
		</tr>
		<tr>
			<form action="deleteProc" method="get">
				<td colspan=4><input type=text placeholder="delete by id"name="seq">
					<button>삭제하기</button>
				</td>
			</form>
		</tr>
		<tr>
			<form action="updateProc" method="get">
				<td colspan=4>
					<input type=text placeholder="update target seq" name="seq"><br>
					<input type=text placeholder="update column" name="column"><br>
					<input type=text placeholder="update value" name="value">
					<button>수정</button>
				</td>
			</form>
		</tr>
		<tr>
			<td colspan=4 align=center><a href="/"><input type=button value="뒤로가기"></a>
		</tr>

	</table>
</body>
</html>