<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원정보 관리페이지</title>
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
					<h6 class="text-center text-secondary">회원정보</h6>
					<div>
						<p>
							<c:if test="${myInfo.mem_pic == 'profile.png'}">
								<img id="defaultProfile"
									src="<c:url value='/resources/image/profile.png'/>" width="220"
									height="170" />
							</c:if>
							
							<c:if test="${!empty myInfo.mem_storedpic}">
								<img id="memProfile"
									src="<c:url value='/images/${myInfo.mem_storedpic}'/>" width="220"
									height="170" />
							</c:if>
						</p>
					</div>
					<div class="container">
						<div class="row" style="margin-top: 20px;">
							<p class="text-muted">이름 <strong>${myInfo.mem_name}</strong></p>
						</div>
						<div class="row">
							<p class="text-muted">아이디 <strong>${myInfo.mem_id}</strong></p>
						</div>
						<div class="row">
							<p class="text-muted">이메일 <strong>${myInfo.mem_email}</strong></p>
						</div>
						<div class="row">
							<p class="text-muted">가입날짜 <strong>${myInfo.mem_regdate}</strong></p>
						</div>
					</div>

					<div>
						<button id="mypage" onClick="location.href = '/bookShelf'"
							class="btn btn-primary" style="margin-top: 20px;">보관함</button>
					</div>
					<div>
						<button type="button" onClick="infoUpdate(${myInfo.mem_num})"
							class="btn btn-outline-primary" style="margin-top: 10px;">회원정보 수정</button>
					</div>
					<div>
						<button type="button" onClick="infoDelete(${myInfo.mem_num})"
							class="btn btn-outline-secondary" style="margin-top: 2px;">회원탈퇴</button>
					</div>
				</div>
			</div>
		</div>
	</div>


	<script>
	$(document).ready(function(){
		<c:if test="${!empty myInfo.mem_storedpic}">
			$("#defaultProfile").hide();
		</c:if>
	});
	
	function infoUpdate(mem_num){
		// 확인 팝업 창
		if(confirm("수정하시겠습니까?")){
			 location.href = "/edit";
		}
	}
	
	function infoDelete(mem_num){
		// 확인 팝업 창
		if(confirm("탈퇴하시겠습니까?")){
			 location.href = "/delete";
		}
	}
</script>
</body>
</html>
