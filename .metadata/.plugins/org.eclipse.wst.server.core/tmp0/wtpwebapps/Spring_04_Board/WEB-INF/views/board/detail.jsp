<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${dto.title }</title>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<style>
#title {
	width: 300px;
}
</style>

</head>
<body>
	<form action="/board/update" id=frm>
		<table border=1 align=center>
			<tr>
				<th colspan=2>글쓰기
			</tr>
			<tr>
				<td align=center>제목
				<td><input type=hidden value="${dto.seq }" name=seq> <input
					type=text placeholder="제목을 입력해주세요." id=title name=title
					value="${dto.title }" readonly>
			</tr>
			<c:if test="${!empty fList }">
				<tr>
					<td colspan=2>
					<c:forEach var="i" items="${fList }">
							<a href="/file/download?oriName=${i.oriName}&sysName=${i.sysName }">${i.oriName}</a><br>		
					</c:forEach>
				</tr>
			</c:if>
			<tr>
				<td colspan=2><textarea cols=80 rows=30 name=contents readonly
						id=contents>${dto.contents }</textarea>
			</tr>
			<tr>
				<td colspan=2 align=right>
					<button type=button id=back>목록으로</button> <c:if
						test="${dto.writer == loginID }">
						<button type=button id=mod>수정하기</button>
						<button type=button id=del>삭제하기</button>
						<button type=button id=modOk style="display: none">수정완료</button>
						<button type=button id=modCancel style="display: none">취소</button>
					</c:if>
			</tr>
			<tr>
				<td>
				<td>
			</tr>
		</table>
	</form>
	<script>
		$("#back").on("click", function() {
			location.href = "/board/list";
		})
		let bkTitle = "";
		let bkContents = "";
		$("#mod").on("click", function() {

			bkTitle = $("#title").val();
			bkContents = $("#contents").val();

			$("#title").removeAttr("readonly");
			$("#contents").removeAttr("readonly");
			$("#modOk").css("display", "inline");
			$("#modCancel").css("display", "inline");
			$("#mod").css("display", "none");
			$("#del").css("display", "none");

		})
		$("#modOk").on("click", function() {
			if (confirm("이대로 수정하시겠습니까?")) {
				$("#frm").submit();
			}
		})
		$("#modCancel").on("click", function() {
			if (confirm("정말 취소하시겠습니까?")) {
				location.reload();
			}
		})
		$("#del").on("click", function() {
			if (confirm("정말 삭제하시겠습니까?")) {
				location.href = "/board/delete?seq=${dto.seq}";
			}
		})
	</script>
</body>
</html>