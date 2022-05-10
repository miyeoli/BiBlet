<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/adminbootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/registPage.css" type="text/css">
<title>아이디 찾기</title>
</head>
<body>
<div class="container">
<div class="row d-flex justify-content-center mt-5">
<div class="col-12 col-md-8 col-lg-6 col-xl-5">
<div class="card py-3 px-2">
	<p class="text-center mb-3 mt-2">FIND ID</p>
			
			
			<form class="myform">
					<div class="form-group">
						<p>
							<label>ID : </label>
							${findedId}
						</p>
 					</div>
 	
 					<div class="form-group mt-3" style = "text-align:center;">
 						<button type="button" id=loginBtn class="btn btn-block btn-primary" onclick="location.href='login'">Login</button>
						<button type="button" class="btn btn-block btn-primary" onclick="history.go(-1);">Cancel</button>
 					</div>
 						
			</form>
			
		
</div>
</div>
</div>
</div>
</body>
</html>