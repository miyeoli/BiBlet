<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>코멘트 강제 삭제 페이지</title>
</head>
<body>
<form name="deleteCommentForm"
	action="<c:url value="/adminpage/deleteComment"/>" method="POST">
		평가번호가 <input name="appraisal_num" value="${appraisal_num }"/>인 코멘트를 삭제시키시겠습니까?<br>
		관리자 이메일 : <input name="adm_email"/><!-- 추후에 session에 있는 adm_email 값 가져오기 --><br>
		관리자 비밀번호 : <input name="adm_pass"/><br>
		<input type="submit" value="강제 삭제">
		<a href="<c:url value="/adminPage"/>"><button>취소</button></a>
	</form>
	<div>${msg}</div>
</body>
</html>