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
<title>관리자 회원가입</title>

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

<!-- 네이버 api -->
<!-- <script type="text/javascript"
	src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js"
	charset="utf-8"></script>
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.11.3.min.js"></script> -->

</head>
<body>
	<h1>관리자 회원 가입</h1>
	<form:form commandName="admin">
		<div>
			<label>이름</label>
			<form:input path="adm_name" limitbyte="50"
				placeholder="한글 6자, 영문50자 이내"
				onKeyUp="javascript:fnChkByte(this,'50','byteInfo')" />
			<span id="byteInfo">0</span> / 50bytes
			<form:errors path="adm_name" />
		</div>
		<div>
			<label>아이디</label>
			<form:input path="adm_id" limitbyte="100"
				placeholder="한글 33자, 영문100자 이내"
				onKeyUp="javascript:fnChkByte(this,'100','byteInfo2')" />
			<span id="byteInfo2">0</span> / 100bytes
			<form:errors path="adm_id" />
		</div>
		<div>
			<label>비밀번호</label>
			<form:input type="password" path="adm_pass" limitbyte="200"
				placeholder="한글66자, 영문200자 이내"
				onKeyUp="javascript:fnChkByte(this,'200','byteInfo3')" />
			<span id="byteInfo3">0</span> / 200bytes
			<form:errors path="adm_pass" />
		</div>
		<div>
			<label>이메일</label>
			<form:input path="adm_email" />
			<form:errors path="adm_email" />
		</div>

		<label>관리자 인증 코드</label>
		<form:input type="password" path="securitycode" />
		<form:errors path="securitycode" />

		<div>
			<input type="submit" value="관리자 회원가입" />
		</div>

		<%-- <!-- 네이버 로그인 버튼 노출 영역 -->
		<%
			String clientId = "d49IDn_y2lOLJI_iR5pL";//애플리케이션 클라이언트 아이디값";
		String redirectURI = URLEncoder.encode("http://localhost:8080/www/home", "UTF-8");
		SecureRandom random = new SecureRandom();
		String state = new BigInteger(130, random).toString();
		String apiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code";
		apiURL += "&client_id=" + clientId;
		apiURL += "&redirect_uri=" + redirectURI;
		apiURL += "&state=" + state;
		session.setAttribute("state", state);
		%>
		<a href="<%=apiURL%>"><img height="50"
			src="http://static.nid.naver.com/oauth/small_g_in.PNG" /></a>
 --%>
	</form:form>
</body>
</html>