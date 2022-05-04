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
</head>
<body>
<h2>회원정보관리-회원정보</h2>
<c:if test="${!empty myInfo}">


	<table border="1">
		<tr>
<!-- 			<td rowspan="6"> -->
			<td>${myInfo.mem_pic}</td>
<%-- 				<img src='<c:url value="/resources"/>'+"/upload/images/profile.png" width="100" height="150"/> --%>
			
		</tr>	
		<tr>
			<td>이름</td>
			<td>${myInfo.mem_name}</td>
		</tr>
		<tr>
			<td>아이디</td>
			<td>${myInfo.mem_id}</td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td>${myInfo.mem_pass}</td>
		</tr>
		<tr>
			<td>이메일</td>
			<td>${myInfo.mem_email}</td>
		</tr>
		<tr>
			<td>가입날짜</td>
			<td>${myInfo.mem_regdate}</td>
		</tr>

	</table>
		<input type="button" value="회원정보 수정" onClick="infoUpdate(${myInfo.mem_num})">		
		<input type="button" value="회원 탈퇴" onClick="infoDelete(${myInfo.mem_num})">
	
</c:if>

	<button id="mypage" onClick="location.href = '/bookShelf'">보관함</button>


<script>
	
	
	
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
