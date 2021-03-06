<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>join</title>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
</head>

<body>
	<form action="/member/signupProc" method=post id=frm>
		<table border="1" align=center>
			<tr>
				<th colspan=2 align=center>회원 가입 정보
			</tr>
			<tr>
				<td class=title>아이디 :
				<td><input type=text id=id name=id><span id=checkResult></span>
			</tr>
			<tr>
				<td class=title>비밀번호 :
				<td><input type=password id=pwd name=pw>
			</tr>
			<tr>
				<td class=title>비밀번호 확인 :
				<td><input type=password id=repwd> <span id=result></span>
			</tr>
			<tr>
				<td class=title>이름 :
				<td><input type=text id=name name=name>
			</tr>
			<tr>
				<td class=title>전화번호 :
				<td><select id=p1 name=select>
						<option>010
						<option>016
						<option>017
						<option>080
						<option>031
						<option>02
						<option>051
						<option>052
				</select> - <input type=text id=p2> - <input type=text id=p3>
					<input type=hidden name=phone id=phone></td>
			</tr>
			<tr>
				<td class=title>이메일 :
				<td><input type=text id=email name=email>
			</tr>
			<tr>
				<td class=title>우편번호 :
				<td><input type=text id=postcode name=zipcode><input
					type=button value=찾기 id=search>
			</tr>
			<tr>
				<td class=title>주소1 :
				<td><input type=text id=adrs1 name=address1>
			</tr>
			<tr>
				<td class=title>주소2 :
				<td><input type=text id=adrs2 name=address2>
			</tr>

			<tr>
				<td colspan=2 align=center>
					<button type=submit>회원가입</button>
					<button type="reset">다시 입력</button> <a href="/"><button
							type=button>뒤로가기</button></a>
			</tr>
		</table>
	</form>
	<script>
        let frm = $("#frm");
        let id = $("#id");
        let pwd = $("#pwd");
		let repwd = $("#repwd");
		let result = $("#result");
		let name = $("#name");
		let num1 = $("#num1");
		let num2 = $("#num2");
		let email = $("#email");
		
		repwd.on("keyup",function(){
			if(pwd.val()!=repwd.val()){
				result.html("비밀번호가 일치하지 않습니다.");
				result.css("color","red");
			}else{
				result.html("비밀번호가 일치합니다.");
				result.css("color","blue");
			}
		})
		
		pwd.on("keyup",function(){
			if(pwd.val()!=repwd.val()){
				result.html("비밀번호가 일치하지 않습니다.");
				result.css("color","red");
			}else{
				result.html("비밀번호가 일치합니다.");
				result.css("color","blue");
			}
		})
		
        $(function () {
            $("#id").on("blur", function () {
              $.ajax({
                url: "/member/idDuplCheck",
                data: { id: $("#id").val() }
              }).done(function (resp) {
                if (resp == "1") {
                  $("#checkResult").css("color", "pink");
                  $("#checkResult").text($("#id").val() + "이미 사용중인 ID 입니다.");
                  $("#id").val("");
                  $("#id").focus();
                } else {
                  $("#checkResult").css("color", "dodgerblue");
                  $("#checkResult").text("사용 가능한 ID 입니다.");
                }
              });
            });
        })

        frm.on("submit", function () {
        	let p1 = $("#p1").val();
        	let p2 = $("#p2").val();
        	let p3 = $("#p3").val();
        	$("#phone").val(p1+p2+p3);
          let idRegex = /[a-z][a-z0-9]{3,10}/g;
          let idResult = idRegex.test(id.val());
          if (!idResult) {
            alert("아이디를 제대로 입력해주세요.\n아이디는 4자리 이상 11자리 이하로 입력하셔야 하며\n첫자리는 숫자 입력을 하시거나 공백이 올 수 없습니다.");

            id.val("");
            id.focus();
            return false;
          }

          let pwRegex = /[a-z0-9]{4}/;
          let pwResult = pwRegex.test(pwd.val());
          if (!pwResult) {
            alert("패스워드는 4자리 이상 입력해주세요.");
            pwd.val("");
            repwd.val("");
            pwd.focus();
            return false;
          }

          let nameRegex = /[가-힣a-z]{2,5}/g;
          let nameResult = nameRegex.test(name.val());
          if (!nameResult) {
            alert("이름을 제대로 입력해주세요.");
            name.val("");
            name.focus();
            return false;
          }
        })
        
        document.getElementById("search").onclick = function(){
            new daum.Postcode({
                oncomplete: function(data){
                    document.getElementById('postcode').value = data.zonecode;
                    document.getElementById("adrs1").value = data.roadAddress;
                    document.getElementById("adrs2").value = data.jibunAddress;
                }
            }).open();
        }

      </script>
</body>

</html>