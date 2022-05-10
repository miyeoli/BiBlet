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
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/appraisalbootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/star.css">
		<title>search</title>
	</head>
	<body>
		<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
  <div class="container-fluid">
    <a class="navbar-brand" href="/">BiBlet</a>
    <div class="collapse navbar-collapse" id="navbarColor01">
    	<div class="form-group">
       		<form action="/search" class="d-flex flex-row">
				<input class="form-control me-sm-2 flex-grow-1" type="text" name="query" id="query" value="${query}" placeholder="제목, 저자 또는 출판사 검색">
				<button class="btn btn-secondary my-2 my-sm-0" type="submit">Search</button>
			</form>
		</div>
    </div>
    <div class="col-25 col-md-20 col-lg-2 col-xl-2">
		<div class="navbar-collapse collapse show" id="navbarColor01"
			 style="float:right;">
			<ul class="navbar-nav me-auto">
				<li class="nav-item"><a class="nav-link" onClick="location.href = '/MyPage'">MyPage</a></li>
			</ul>
			<ul class="navbar-nav me-auto">
				<li class="nav-item"><a class="nav-link" onClick="location.href = '/'">MainPage</a></li>
			</ul>
		</div>
	</div>
  </div>	
</nav>	
		<br>
		
		<div id="searchBook"></div>
	
		<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
		<script>
			$(document).ready(function(){

				var pageNum = 1;
				$.ajax({	//카카오 검색요청 / [요청]
			           method: "GET",
			           url: "https://dapi.kakao.com/v3/search/book",
			           data: { query: "${query}", page: pageNum},
			           headers: {Authorization: "KakaoAK 6f9ab74953bbcacc4423564a74af264e"} 
			    })
		        .done(function (msg) {	//검색 결과 담기 / [응답]
		        	console.log(msg);
		        	if (msg.documents.length == 0) {
		        		$("#searchBook").append("<div>검색에 일치하는 정보를 찾지 못하였습니다.</div>");
		        		return;
		        	}
		        	$('#main_add_info').empty();
		        	for (var i = 0; i < 10; i++){
		                $("#searchBook").append("<img src='" + msg.documents[i].thumbnail + "'/><br>");		//표지
		                $("#searchBook").append("<h2><a href='/read/"+ msg.documents[i].isbn.slice(-13)+"?query="+$("#query").val()+ "'>" + msg.documents[i].title + "</a></h2>");	//제목
		                $("#searchBook").append("<strong>저자:</strong> " + msg.documents[i].authors + "<br>");		//저자	
		                $("#searchBook").append("<strong>출판사:</strong> " + msg.documents[i].publisher + "<br>");		//출판사
		                $("#searchBook").append("<strong>줄거리:</strong> " + msg.documents[i].contents + "...<br>");		//줄거리
		            	$("#searchBook").append("<strong>ISBN:</strong>" + msg.documents[i].isbn + "<br>");	//일련번호
		            }
		        })
		        .fail(function () {
		        	$("#searchBook").append("<div>검색에 일치하는 정보를 찾지 못하였습니다.</div>");
		        }); 
			});
		</script>
	</body>
</html>