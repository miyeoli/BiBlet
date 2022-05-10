<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript">
 if(${!empty sessionScope.authInfo}){
	 window.history.forward();
 }
</script>

<title><spring:message code="login.title" /></title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/adminbootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/registPage.css" type="text/css">

</head>
<body>
<div class="container">
<div class="row d-flex justify-content-center mt-5">
<div class="col-12 col-md-8 col-lg-6 col-xl-5">
<div class="card py-3 px-2">
	<p class="text-center mb-3 mt-2">LOGIN</p>
	<form:form commandName="commandLogin" class="myform">
		<form:errors />
		
			<div class="form-group">
			<form:input
					path="mem_id" type="text" class="form-control" placeholder="아이디"/> 
					<form:errors path="mem_id" />
			</div>
		
		<div class="form-group">
		<form:input
					type="password" path="mem_pass" class="form-control" placeholder="비밀번호"/> 
					<form:errors path="mem_pass" />
			</div>
		<div class="row">
  						<div class="col-md-6 col-12">
  							<div class="form-group form-check">
    							<input type="checkbox" class="form-check-input" id="rememberId" name="rememberId">
    							<label class="form-check-label" for="rememberId">REMMBER ME</label>
  							</div>
  						</div>
  						<div class="col-md-6 col-12 bn" id=findBtn onclick="location.href='findId'">FORGET</div>
  					</div>

		<div class="form-group mt-3" style = "text-align:center;">
 						<button type="submit" class="btn btn-block btn-primary btn-lg" >
 						<small><i class="far fa-user pr-2"></i>LOGIN</small></button>
 					</div>
	</form:form>
</div>
</div>
</div>
</div>
</body>
</html>