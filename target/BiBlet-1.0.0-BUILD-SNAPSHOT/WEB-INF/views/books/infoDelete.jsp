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

<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
	crossorigin="anonymous"></script>
</head>
<body>

	<c:if test="${!empty myInfo}">
			<table border="1">
				<tr>
					<td>  기존 비밀번호 : 
						<input type="password" name="passCheck" id="passCheck" placeholder="한글 6자, 영문20자 이내">
						<input type="hidden" name="mem_pass" id="mem_pass" value="${myInfo.mem_pass}" />
						<input type="button" value="비밀번호 확인" onClick="passCheck(${myInfo.mem_num})">				
						
					</td>
				</tr>
			</table>
	</c:if>		
		
<script>
	//	# 비밀번호 확인
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
					 	
					 	// 확인 팝업창 
						if (confirm("탈퇴하시겠습니까?")) {
					 	 	infoDelete(mem_num);							
						}
					 
					}else if(data == 0){
						alert("비밀번호가 일치하지 않습니다.");
						
					}
				}
			});
		}



// 		# 회원 삭제
		function infoDelete(mem_num){
	
			$.ajax({
				url: '<c:url value="/infoDelete"/>',
				type: 'POST',
				data: JSON.stringify({
					"mem_num": mem_num
				}),
				dataType: "json",
				contentType: 'application/json',
				success: function(data) {
					window.location.href = "Main";	
				}, error: function(data){
 					window.location.href = "Main";	
				}
			});
		}
				
			
		
	</script>		
</body>
</html>