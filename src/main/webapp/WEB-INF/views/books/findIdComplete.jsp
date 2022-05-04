<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>아이디 찾기</title>
</head>
<body>

	<h3>아이디 찾기 검색결과</h3>
			
	<div>
		<h5>
			${findedId}
		</h5>
		<p>
			<button type="button" id=loginBtn onclick="location.href='loginForm'">Login</button>
			<button type="button" onclick="history.go(-1);">Cancel</button>
		</p>
	</div>
	
</body>
</html>