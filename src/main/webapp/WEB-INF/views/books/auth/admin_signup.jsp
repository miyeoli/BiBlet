<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page import="java.net.URLEncoder"%>
<%@ page import="java.security.SecureRandom"%>
<%@ page import="java.math.BigInteger"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BiBlet 관리자 회원가입</title>
<script type="text/javascript">
	function fnChkByte(obj, maxByte, id) {
		var str = obj.value;
		var str_len = str.length;
		var input = document.getElementById(id);
		var rbyte = 0;
		var rlen = 0;
		var one_char = "";
		var str2 = "";
		for (var i = 0; i < str_len; i++) {
			one_char = str.charAt(i);
			if (escape(one_char).length > 4) {
				rbyte += 3; //한글3Byte
			} else {
				rbyte++; //영문 등 나머지 1Byte
			}
			if (rbyte <= maxByte) {
				rlen = i + 1; //return할 문자열 갯수
			}
		}
		if (rbyte > maxByte) {
			// alert("한글 "+(maxByte/3)+"자 / 영문 "+maxByte+"자를 초과 입력할 수 없습니다.");
			alert("메세지는 최대 " + maxByte + "byte를 초과할 수 없습니다.")
			str2 = str.substr(0, rlen); //문자열 자르기
			obj.value = str2;
			fnChkByte(obj, maxByte);
		} else {
			input.innerText = rbyte;
		}
	}
</script>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/adminbootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/registPage.css" type="text/css">
</head>
<body>
<div class="container">
<div class="row d-flex justify-content-center mt-5">
<div class="col-12 col-md-8 col-lg-6 col-xl-5">
<div class="card py-3 px-2">
	<p class="text-center mb-3 mt-2">관리자 회원 가입</p>
	<div class="division">
		<div class="row">
			<div class="col-3"><div class="line l"></div></div>
			<div class="col-6"><span>BiBlet 관리자 가입</span></div>
			<div class="col-3"><div class="line r"></div></div>
		</div>
	</div>
	
	<form:form commandName="admin" class="myform">
		<div class="form-group">
			<label>이름</label>
			<form:input path="adm_name" limitbyte="50"
				placeholder="한글 6자, 영문50자 이내"
				onKeyUp="javascript:fnChkByte(this,'50','byteInfo')" 
				type="text" class="form-control"/>
			<span><span id="byteInfo">0</span> / 50bytes</span>
			<form:errors path="adm_name" />
		</div>
		
		<div class="form-group">
			<label>아이디</label>
			<form:input path="adm_id" limitbyte="100"
				placeholder="한글 33자, 영문100자 이내"
				onKeyUp="javascript:fnChkByte(this,'100','byteInfo2')" type="text" class="form-control"/>
			<span><span id="byteInfo2">0</span> / 100bytes</span>
			<form:errors path="adm_id" />
		</div>
		
		<div class="form-group">
			<label>비밀번호</label>
			<form:input type="password" path="adm_pass" limitbyte="200"
				placeholder="한글66자, 영문200자 이내"
				onKeyUp="javascript:fnChkByte(this,'200','byteInfo3')" class="form-control"/>
			<span><span id="byteInfo3">0</span> / 200bytes</span>
			<form:errors path="adm_pass" />
		</div>
		
		<div class="form-group">
			<label>이메일</label>
			<form:input path="adm_email" type="text" class="form-control"/>
			<form:errors path="adm_email" />
		</div>

		<div class="form-group">
		<label>관리자 인증 코드</label>
		<form:input type="password" path="securitycode" class="form-control"/>
		<form:errors path="securitycode" />
		</div>
		
		<div class="form-group mt-3">
			<button type="submit" class="btn btn-block btn-primary btn-lg">
			<small><i class="far fa-user pr-2"></i>관리자 가입</small>
			</button>
		</div>

		<div class="text-center mb-3 mt-2">
			<a href="registPage">회원 가입</a>
		</div>
		
	</form:form>
	
	
	</div>
	</div>
	</div>
	</div>
</body>
</html>