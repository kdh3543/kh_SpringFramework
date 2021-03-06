<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Home</title>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script
	src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
	crossorigin="anonymous"></script>
<script
	src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
</head>
<link rel="style" href="/css/style.css">

<body>
	<c:choose>
		<c:when test="${loginID !=null }">
			<table border=1 align=center>
				<tr>
					<th align=center colspan=4>${loginID }님환영합니다.
				</tr>
				<tr>
					<td><button id="toBoard">게시판</button>
					<td><button id="mypage">마이페이지</button>
					<td><button id="logout">로그아웃</button>
					<td><button id="resign">회원탈퇴</button>
				</tr>
			</table>
		</c:when>
		<c:otherwise>
			<form action="/member/login" method="post">
				<table border=1 align=center>
					<tr>
						<th>Login
					</tr>
					<tr>
						<td><img src="/imgs/이미지3.gif">
					</tr>
					<tr>
						<td align=center><input type=text name=id
							placeholder="input your id"> <input type=password name=pw
							placeholder="input your pw">
					</tr>
					<tr>
						<td align=center>
							<button>로그인</button>
							<button type=button id=join>회원가입</button>
					</tr>
				</table>
			</form>
		</c:otherwise>
	</c:choose>


	<script>
		$("#toBoard").on("click",function(){
			location.href = "/board/list";
		})
	
		$("#mypage").on("click", function() {
			location.href = "/member/mypage";
		})
		$("#logout").on("click", function() {
			location.href = "/member/logout";
		})
		$("#resign").on("click", function() {
			if (confirm("정말 탈퇴하시겠습니까?")) {
				location.href = "/member/resign";
			}
		})
		$("#join").on("click", function() {
			location.href = "/member/join";
		})

		document.getElementById("search").onclick = function() {
			new daum.Postcode(
					{
						oncomplete : function(data) {
							document.getElementById('zipcode').value = data.zonecode;
							document.getElementById("address1").value = data.roadAddress;
							document.getElementById("address2").value = data.jibunAddress;
						}
					}).open();
		}
	</script>
</body>

</html>