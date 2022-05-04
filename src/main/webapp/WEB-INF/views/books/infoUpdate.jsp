<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원정보 관리페이지</title>
</head>

<script src="https://code.jquery.com/jquery-3.6.0.js"
	integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
	crossorigin="anonymous"></script>
</head>
<body>
   <h2>회원정보관리-회원정보수정</h2>
	<c:if test="${!empty myInfo}">


	<table border="1">
		<tr>
<!-- 			<td rowspan="6"> -->
			<td>${myInfo.mem_pic}</td>
<%-- 				<img src='<c:url value="/resources"/>'+"/upload/images/profile.png" width="100" height="150"/> --%>
			
		</tr>	
		<tr>
			<td>이름</td>
			<td><input type="text" id="mem_name" name="mem_name" limitbyte="50" placeholder="한글 6자, 영문50자 이내"></td>
		</tr>
		<tr>
			<td>아이디</td>
			<td><input type="text" id="mem_id" name="mem_id" limitbyte="50" placeholder="한글 6자, 영문50자 이내"></td>
		</tr>
		<tr>
				<td>비밀번호</td>
				<td>
				   기존 비밀번호 : 
					<input type="password" name="passCheck" id="passCheck" limitbyte="200" placeholder="한글 6자, 영문20자 이내">
					<input type="hidden" name="mem_pass" id="mem_pass" value="${myInfo.mem_pass}" />
					<input type="button" value="비밀번호 확인" onClick="passCheck(${myInfo.mem_num})">				
					
					<div id="u${myInfo.mem_num}"></div>
				</td>
		</tr>
		<tr>
			<td>이메일</td>
			<td><input type="text" id="mem_email" name="mem_email"></td>
		</tr>
		<tr>
			<td>가입날짜</td>
			<td>${myInfo.mem_regdate}</td>
		</tr>
		
	</table>
	<input type="button" value="회원정보 수정" onClick="infoUpdate(${myInfo.mem_num})">		
 	<input type="button" value="회원정보 수정" onClick="infoUpdate(${myInfo.mem_num})">
	
</c:if>

<script>
// 		# 비밀전호 확인
		function passCheck(mem_num){
				
				let passCheck = $("#passCheck").val();
				let mem_pass = $("#mem_pass").val();
				
				$.ajax({
					url: '<c:url value="/infoUpdatePassCheck"/>',
					type: 'POST',
					data: JSON.stringify({
						"mem_num": mem_num,
						"passCheck": passCheck,
						"mem_pass": mem_pass
					}),
					dataType: "json",
					contentType: 'application/json',
					success: function(data) {
						if(data == 1){
						 	alert("비밀번호가 확인되었습니다.");
						 	
						 	passUpdateForm(mem_num);
						}else if(data == 0){
							alert("비밀번호가 일치하지 않습니다.");
							
						}
					}
				});
			}
			
// 			비밀번호 일치 시 비밀번호 변경 폼 보여주기
			function passUpdateForm(mem_num){
				 $("#u"+mem_num).html(
						 
					'새로운 비밀번호 입력 : <input type="password" id="mem_passU" name="mem_pass" />'
				);		
			}
			
// 			# 회원 정보 수정
			function infoUpdate(mem_num){
				let mem_name = $("#mem_name").val();
				let mem_id = $("#mem_id").val();
				let mem_pass = $("#mem_passU").val();
				let mem_email = $("#mem_email").val();
				
				$.ajax({
					url: '<c:url value="/infoUpdate"/>',
					type: 'POST',
					data: JSON.stringify({
						"mem_name": mem_name,
						"mem_id": mem_id,	
						"mem_pass": mem_pass,
						"mem_email": mem_email,
						"mem_num": mem_num
					}),
					dataType: "json",
					contentType: 'application/json',
					success: function(data) {
						window.location.href = "/MyPage";
					}, error: function(data){
						window.location.href = "/MyPage";
					}
				});
			}
</script>
 
</body>
</html>