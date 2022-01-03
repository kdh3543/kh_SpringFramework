<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>WriteForm</title>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>

<style>
#text {
	width: 300px;
}
</style>

</head>
<body>
	<form action="/board/writeProc" method="post" enctype="multipart/form-data">
		<table border=1 align=center>
			<tr>
				<th colspan=2>글쓰기
			</tr>
			<tr>
				<td align=center>제목
				<td><input type=text placeholder="제목을 입력해주세요." id=text
					name=title><input type=file name=file multiple>
			</tr>
			<tr>
				<td colspan=2><textarea id=summernote cols=80 rows=30
						name=contents></textarea>
			</tr>
			<tr>
				<td colspan=2 align=right>
					<button type=button id=back>목록으로</button>
					<button>작성완료</button> <input type=reset value="다시작성">
			</tr>
		</table>
	</form>

	<script>
		$("#back").on("click",function(){
			location.href="/board/list";
		})
		
		
			
		
	</script>
</body>
</html>