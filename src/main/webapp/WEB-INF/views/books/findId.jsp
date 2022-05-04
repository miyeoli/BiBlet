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

<script src="https://code.jquery.com/jquery-3.6.0.js"
	integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
	crossorigin="anonymous"></script>
</head>
<body>

	<form method="post">
	
	<h2>아이디 찾기</h2>
		<div>
			<p>
				<label>ID 검색을 위한 이메일 입력 : </label>
				<input type="text" id="mem_email" name="mem_email">
			</p>
			<p>
				<button type="submit" id=findBtn>확인</button>
				<button type="button" onclick="history.go(-1);">메인 페이지</button>
			</p>
		</div>
	</form>

</body>
</html>