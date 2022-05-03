<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>보관함</title>
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
</head>
<body>
	
	<button id="mypage" onClick="location.href = '/MyPage'">마이페이지</button>
	<button id="mypage" onClick="location.href = '/MainLogin'">메인페이지</button>
	
	<p>
	<h2>찜</h2>
	<c:if test="${!empty MyLikeCount}">
	내 찜 수 : ${MyLikeCount}
	</c:if>
	</p>
	
	
	<p>
	<div id="like"></div>
	</p>
		
	<p>
	<h2>보는 중</h2>
	<c:if test="${!empty MyLeadingCount}">
	내 보는 중 수 : ${MyLeadingCount}
	</c:if>
	</p>
	
	<p>
	<div id="leading"></div>
	</p>
	
	<h2>독서 완료</h2>
	<c:if test="${!empty MyCommentCount}">
	내 평가 수 : ${MyCommentCount}
	</c:if>
	
	
	
	<c:if test="${!empty MyComment}">
			<c:forEach var="myComment" items="${MyComment}">
			
					별점 : ${myComment.star}
					평가 : ${myComment.book_comment}
					시작 날짜 : ${myComment.start_date}
					다 읽은 날짜 : ${myComment.end_date}
					<input type="hidden" id="appraisal_num" name="appraisal_num" value="${myComment.appraisal_num}">
					<div id="img${myComment.appraisal_num}"></div>
				
			</c:forEach>
	</c:if>


<script>

//		'찜' 페이지 실행 되면 isbn for문으로 담기
		$(document).ready(function(){
			<c:forEach var="likeIsbn" items="${likeIsbn}">
				likeIsbn("${likeIsbn}")
			</c:forEach>
		});
		
		
// 		'찜' 도서 불러오기(1개)
		function likeIsbn(isbn){
			$.ajax({	//카카오 검색요청 / [요청]
		        method: "GET",
		        traditional: true,
		        url: "https://dapi.kakao.com/v3/search/book",
		        data: { query: isbn},
		        headers: {Authorization: "KakaoAK 6f9ab74953bbcacc4423564a74af264e"} 
		    })
		   
		    .done(function (msg) {	//검색 결과 담기 / [응답]
		    	console.log(msg);
		            $("#like").append("<img src='" + msg.documents[0].thumbnail + "'/>");	//표지
		    });   
		}
		
		
//		'보는 중' 페이지 실행 되면 isbn for문으로 담기
		$(document).ready(function(){
			<c:forEach var="leadingIsbn" items="${leadingIsbn}">
				leadingIsbn("${leadingIsbn}")
			</c:forEach>
		});
		
// 		'보는 중' 도서 불러오기(1개)
		function leadingIsbn(isbn){
			$.ajax({	//카카오 검색요청 / [요청]
		        method: "GET",
		        traditional: true,
		        url: "https://dapi.kakao.com/v3/search/book?target=isbn",
		        data: { query: isbn},
		        headers: {Authorization: "KakaoAK 6f9ab74953bbcacc4423564a74af264e"} 
		    })
		   
		    .done(function (msg) {	//검색 결과 담기 / [응답]
		    	console.log(msg);
		            $("#leading").append("<img src='" + msg.documents[0].thumbnail + "'/>");	//표지
		    });   
		}		
		
		
//		'독서 완료' 페이지 실행 되면 isbn for문으로 담기
		$(document).ready(function(){
			<c:forEach var="completeIsbn" items="${completeIsbn}">
				completeIsbn("${completeIsbn.isbn}", "${completeIsbn.appraisal_num}")
			</c:forEach>
		});
		
		
// 		'독서 완료' 도서 불러오기(1개)
		function completeIsbn(isbn, appraisal_num){
// 			let appraisal_num = $("#appraisal_num").val();
			
			$.ajax({	//카카오 검색요청 / [요청]
		        method: "GET",
		        traditional: true,
		        async: false,
		        url: "https://dapi.kakao.com/v3/search/book?target=isbn",
		        data: { query: isbn},
		        headers: {Authorization: "KakaoAK 6f9ab74953bbcacc4423564a74af264e"} 
		    })
		   
		    .done(function (msg) {	//검색 결과 담기 / [응답]
		    	console.log(msg);
		           $("#img" + appraisal_num).append("<img src='" + msg.documents[0].thumbnail + "'/>");	//표지
		    });   
		}	
</script>	
</body>
</html>