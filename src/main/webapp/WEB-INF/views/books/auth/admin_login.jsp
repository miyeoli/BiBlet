<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/adminbootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/registPage.css" type="text/css">
<title>숨겨진 관리자 페이지</title>
</head>
<body>
<div class="container">
<div class="row d-flex justify-content-center mt-5">
<div class="col-12 col-md-8 col-lg-6 col-xl-5">
<div class="card py-3 px-2">
		<p class="text-center mb-3 mt-2">관리자 로그인</p>
			<div class="division">
				<div class="row">
					<div class="col-3"><div class="line l"></div></div>
					<div class="col-6"><span>ADMINLOGIN</span></div>
					<div class="col-3"><div class="line r"></div></div>
				</div>
			</div>
			
		<form:form commandName="commandAdminLogin" class="myform">	
	
			<div class="form-group">
				<form:input path="adm_id" type="text" class="form-control" placeholder="아이디"/> 
				<form:errors path="adm_id" />
			</div>
 					
 			<div class="form-group">
				<form:input path="adm_pass" type="password" class="form-control" placeholder="비밀번호" />
				<form:errors path="adm_pass" />
			</div>
 					
			<div class="row">
				<div class="col-md-6 col-12">
					<div class="form-group form-check">
							<input type="checkbox" class="form-check-input" id="rememberId" name="rememberId">
							<label class="form-check-label" for="rememberId">REMMBER ME</label>
					</div>
				</div>
			</div>
		
	
		<div class="form-group mt-3">
			<button type="submit" class="btn btn-block btn-primary btn-lg" >
			<small><i class="far fa-user pr-2"></i>LOGIN</small>
			</button>
		</div>
	
	</form:form>	
			
			
</div>
	</div>
	</div>
	</div>			
<script type="text/javascript">
 if(${!empty sessionScope.adminauthInfo}){
	 window.history.forward();
 }
</script>
</body>
</html>