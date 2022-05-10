<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<<<<<<< HEAD
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/adminbootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/registPage.css" type="text/css">
=======
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/loginPage.css" type="text/css">
>>>>>>> 6b08b15aff3dbcafdd8640d49d96bc287813c7c8
<title>아이디 찾기</title>
</head>
<body>
<div class="container">
<div class="row d-flex justify-content-center mt-5">
<div class="col-12 col-md-8 col-lg-6 col-xl-5">
<div class="card py-3 px-2">
	<p class="text-center mb-3 mt-2">FIND ID</p>
			
			
			<form class="myform" method="post">
					<div class="form-group">
						<p>
							<label>ID 검색을 위한 이메일 입력 : </label>
							<input type="text" id="mem_email" name="mem_email">
						</p>
 					</div>
 	
 					<div class="form-group mt-3" style = "text-align:center;">
 						<button type="submit" id=findBtn class="btn btn-block btn-primary">FIND</button>
<<<<<<< HEAD
 						<button type="button" class="btn btn-block btn-primary" onClick="location.href = '/'">Main</button>
=======
 						<button type="button" class="btn btn-block btn-primary" onclick="history.go(-1);">login</button>
>>>>>>> 6b08b15aff3dbcafdd8640d49d96bc287813c7c8
 					</div>
 						
			</form>
			
		
</div>
</div>
</div>
</div>
<<<<<<< HEAD
=======

<%-- 	<form method="post"> --%>
	
<!-- 	<h2>아이디 찾기</h2> -->
<!-- 		<div> -->
<!-- 			<p> -->
<!-- 				<label>ID 검색을 위한 이메일 입력 : </label> -->
<!-- 				<input type="text" id="mem_email" name="mem_email"> -->
<!-- 			</p> -->
<!-- 			<p> -->
<!-- 				<button type="submit" id=findBtn>확인</button> -->
<!-- 				<button type="button" onclick="history.go(-1);">로그인</button> -->
<!-- 			</p> -->
<!-- 		</div> -->
<%-- 	</form> --%>
>>>>>>> 6b08b15aff3dbcafdd8640d49d96bc287813c7c8

</body>
</html>