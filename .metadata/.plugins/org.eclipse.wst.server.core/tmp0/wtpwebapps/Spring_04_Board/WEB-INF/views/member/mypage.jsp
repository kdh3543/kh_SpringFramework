<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mypage</title>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
 <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
</head>
<body>
	<form action="/member/update" method="post">
		<table border="1" align=center>
			<tr>
				<th colspan=2 align=center>회원 정보 수정
			</tr>
			<tr>
				<td class=title>아이디 :
				<td>${dto.id }<br> 가입일 : ${dto.signup_date }
			</tr>
			<tr>
				<td class=title>이름 :
				<td><input type=text id=name name=name value="${dto.name }">
			</tr>
			<tr>
				<td class=title>전화번호 :
				<td><input type=text id=num name=phone value="${dto.phone }">
				</td>
			</tr>
			<tr>
				<td class=title>이메일 :
				<td><input type=text id=email name=email value="${dto.email }">
			</tr>
			<tr>
				<td class=title>우편번호 :
				<td><input type=text id=postcode name=zipcode
					value="${dto.zipcode }"> <input type=button value=찾기
					id=search>
			</tr>
			<tr>
				<td class=title>주소1 :
				<td><input type=text id=adrs1 name=address1
					value="${dto.address1}">
			</tr>
			<tr>
				<td class=title>주소2 :
				<td><input type=text id=adrs2 name=address2
					value="${dto.address2}">
			</tr>

			<tr>
				<td colspan=2 align=center>
					<button>수정하기</button>
					<button type="button" id="back">돌아가기</button>
			</tr>
		</table>
	</form>
	<script>
	$("#back").on("click",function(){
		location.href="/";
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