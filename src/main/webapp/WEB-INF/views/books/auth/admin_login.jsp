<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><spring:message code="admin.title"/></title>
</head>
<body>
	<h2>관리자로그인</h2>
	
	<form:form commandName="commandAdminLogin">
		<form:errors />
		<p>
			<label> 
				<spring:message code="adm_id" /> 
				<form:input path="adm_id" /> 
				<form:errors path="adm_id" />
			</label>
		</p>
		<p>
			<label> 
				<spring:message code="adm_pass" /> 
				<form:input type="password" path="adm_pass" /> 
				<form:errors path="adm_pass" />
			</label>
		</p>
		<p>
			<label> 
				<spring:message code="rememberAdmId" /> 
				<form:checkbox path="rememberAdmId" />
			</label>
		</p>

		<input type="submit" value="<spring:message code="admin.btn"/>">
	</form:form>


<script type="text/javascript">
 if(${!empty sessionScope.adminauthInfo}){
	 window.history.forward();
 }
</script>
</body>
</html>