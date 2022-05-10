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
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/resources/css/appraisalbootstrap.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
	crossorigin="anonymous">
</script>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
		<div class="container-fluid">
			<a class="navbar-brand" href="/">BiBlet</a>
		</div>
	</nav>


	<div class="container">
		<div class="row d-flex justify-content-center mt-5">
			<div class="col-12 col-md-8 col-lg-6 col-xl-5">
				<div class="card py-3 px-2">
					<h6 class="text-center text-secondary">회원정보 수정</h6>
					<form:form method="post" action="/infoUpdate"
						CommandName="memInfoCmd" enctype="multipart/form-data">
						<c:if test="${!empty myInfo}"></c:if>
						<div>
							<p>
								<input type="file" name="file" id="file">
							</p>
						</div>

						<div class="input-group flex-nowrap">
							<span class="input-group-text" id="addon-wrapping">이름</span> 
							<input
								type="text" id="mem_name" name="mem_name" class="form-control"
								placeholder="${myInfo.mem_name}"
								onKeyUp="javascript:fnChkByte(this,'50','byteInfo')"
								aria-label="Username" limitbyte="50"
								aria-describedby="addon-wrapping" required>
						</div>

						<div class="input-group flex-nowrap">
							<span class="input-group-text" id="addon-wrapping">아이디</span> 
							<input
								type="text"  id="mem_id" name="mem_id" class="form-control" placeholder="${myInfo.mem_id}"
								onKeyUp="javascript:fnChkByte(this,'50','byteInfo')"
								aria-label="Userid" limitbyte="50"
								aria-describedby="addon-wrapping" required>
						</div>

						<div class="input-group flex-nowrap">
							<span class="input-group-text" id="addon-wrapping">비밀번호</span>
							<div id="u${myInfo.mem_num}">
								<input type="password" class="form-control"
									placeholder="Password"
									onKeyUp="javascript:fnChkByte(this,'50','byteInfo')"
									aria-label="Userpassword" limitbyte="100"
									aria-describedby="addon-wrapping" name="passCheck"
									id="passCheck" required>
							</div>
							<input type="hidden" name="mem_pass" id="mem_pass"
								value="${myInfo.mem_pass}" /> <input type="button"
								class="btn btn-outline-primary" value="비밀번호 확인"
								onClick="passCheckBtn(${myInfo.mem_num})">
						</div>

						<input type="hidden" name="mem_num" id="mem_num"
							value="${myInfo.mem_num}" />
						<button type="submit" class="btn btn-primary"
							style="margin-top: 20px;">회원정보수정</button>
					</form:form>
				</div>
			</div>
		</div>
	</div>

	<script>


// 		# 비밀번호 확인
		function passCheckBtn(mem_num){
				
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
						 
					'새로운 비밀번호 입력 : <input type="password" id="mem_passU" name="mem_passU" />'
				);		
			}
			
//			# 글자 수 제한
			function fnChkByte(obj, maxByte, id) {
				var str = obj.value;
				var str_len = str.length;
				var input = document.getElementById(id);
				var rbyte = 0;
				var rlen = 0;
				var one_char = "";
				var str2 = "";
				for (var i = 0; i < str_len; i++) {
					one_char = str.charAt(i);
					if (escape(one_char).length > 4) {
						rbyte += 3; //한글3Byte
					} else {
						rbyte++; //영문 등 나머지 1Byte
					}
					if (rbyte <= maxByte) {
						rlen = i + 1; //return할 문자열 갯수
					}
				}
				if (rbyte > maxByte) {
					// alert("한글 "+(maxByte/3)+"자 / 영문 "+maxByte+"자를 초과 입력할 수 없습니다.");
					alert("메세지는 최대 " + maxByte + "byte를 초과할 수 없습니다.")
					str2 = str.substr(0, rlen); //문자열 자르기
					obj.value = str2;
					fnChkByte(obj, maxByte);
				} else {
					input.innerText = rbyte;
				}
			}
</script>

</body>
</html>