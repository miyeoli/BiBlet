<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/resources/css/appraisalbootstrap.min.css">
</head>
<body>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
		crossorigin="anonymous">
</script>
	<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
		<div class="container-fluid">
			<a class="navbar-brand" href="/">BiBlet</a>
		</div>
	</nav>

	<div class="container">
		<div class="row d-flex justify-content-center mt-5">
			<div class="col-12 col-md-8 col-lg-6 col-xl-5">
				<div class="card py-3 px-2">
					<h6 class="text-center text-secondary">회원탈퇴</h6>
					
					<div class="input-group flex-nowrap">
						<span class="input-group-text" id="addon-wrapping">기존 비밀번호</span>
						<div id="u${myInfo.mem_num}">
							<input type="password" class="form-control"
								placeholder="Password"
								onKeyUp="javascript:fnChkByte(this,'50','byteInfo')"
								aria-label="Userpassword" limitbyte="100"
								aria-describedby="addon-wrapping" name="passCheck"
								id="passCheck">
						</div>
						<input type="hidden" name="mem_pass" id="mem_pass"
							value="${myInfo.mem_pass}" /> <input type="button"
							class="btn btn-outline-primary" value="비밀번호 확인"
							onClick="passCheck(${myInfo.mem_num})">
					</div>
				</div>
			</div>
		</div>
	</div>

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
					window.location.href = "/Main";	
				}, error: function(data){
 					window.location.href = "/Main";	
				}
			});
		}
				
			
		
	</script>
</body>
</html>