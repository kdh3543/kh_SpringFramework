<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Free Board</title>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="//cdn.datatables.net/1.11.3/js/jquery.dataTables.min.js"></script>
<link rel="stylesheet"
	href="//cdn.datatables.net/1.11.3/css/jquery.dataTables.min.css">
<style>
.paginate_button {
	background-color: white !important;
	border: none !important;
}
#writeList{
	text-align: center;
}
</style>
</head>
<body>
	<table id="" class="display" style="width: 100%">
		<thead>
			<tr>
				<th>No.
				<th>제목
				<th>작성자
				<th>작성일자
				<th>조회수
			</tr>
		</thead>
		<tbody>
			<c:forEach var="i" items="${list }">
				<tr id=writeList>
					<td>${i.seq}
					<td><a href="/board/detail?seq=${i.seq }">${i.title }</a>
					<td>${i.writer }
					<td>${i.detailDate}
					<td>${i.view_count }
				</tr>
			</c:forEach>
		</tbody>
		<tfoot>
			<tr>

				<td colspan=5 align=right>
					<form action="searchByMulti" method="post">
						<input type=text placeholder="제목" name=title> 
						<input type=text placeholder="작성자" name=writer>
						<button>검색</button>
						<button type=button id=write>글쓰기</button>
					</form>
				</td>
			</tr>
		</tfoot>
	</table>


	<script>
		$('table.display').DataTable({
			order : [ [ 0, 'desc' ] ],
			ordering : true,
			bStateSave: true
			
		});
		$(".display").DataTable();
		$("#write").on("click", function() {
			location.href = "/board/writeForm";
		})
	</script>
</body>
</html>