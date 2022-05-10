<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>코멘트 강제 삭제 페이지</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/adminbootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/registPage.css" type="text/css">

</head>
<body>
<div class="container">
<div class="row d-flex justify-content-center mt-5">
<div class="col-12 col-md-8 col-lg-6 col-xl-5">
<div class="card py-3 px-2">
		<p class="text-center mb-3 mt-2">
		평가번호가 <input name="appraisal_num" value="${appraisal_num }"/>인 코멘트를 삭제시키시겠습니까?<br>
		</p>
<form name="deleteCommentForm"
	action="<c:url value="/adminpage/deleteComment"/>" method="POST" class="myform">
		<div class="form-group">
			<span >관리자 이메일</span>
			<input name="adm_email"/><br>
		</div>
		<div class="form-group">
			<span >관리자 비밀번호</span>
			<input name="adm_pass"/><br>
		</div>
		
		<div class="form-group mt-3">
			<button type="submit" class="btn btn-block btn-primary btn-lg">
			<small><i class="far fa-user pr-2"></i>강제 삭제</small>
			</button>
		</div>
		<div class="form-group mt-3">
		<a href="<c:url value="/adminPage"/>">
			<button type="submit" class="btn btn-block btn-primary btn-lg">
			<small><i class="far fa-user pr-2"></i>취소</small>
			</button>
			</a>
		</div>
</form>
	<div>${msg}</div>
	</div>
	</div>
	</div>
	</div>
</body>
</html>