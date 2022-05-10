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
		<title>MainPage 로그인</title>
	</head>
	<body>
		<div>
			<a href="/MyPage">마이페이지</a>
			<a href="/member/logout">로그아웃</a>
			<a href="/member/login">로그인</a>
			<a href="/member/findId">아이디 찾기</a>
			<a href="/admin/login">관리자 로그인</a>
			<a href="/member/signup">회원가입</a>
			<a href="/admin/signup">관리자 회원가입</a>
		</div>

		<form action="/search">
			<span>검색 </span>
			<input type="text" name="query" id="query" value="${query}" placeholder="제목, 저자 또는 출판사 검색" size=30>
			<button type="submit">검색</button>
		</form>
		
		<c:if test="${!empty myID}">
			<p>	
				<span>${myID}님 안녕하세요</span>
				<br>
				<c:if test="${!empty myCommentCount}">
					<span>지금까지 ${myCommentCount}개의 평가를 작성하였어요!</span>
				</c:if>
			</p>		
			<span>${myID}이 찜한 도서</span>
		</c:if>
	
		<div id="myLike"></div>

		<br>
	
		<h2>최근 코멘트</h2>
	
		<table border=1>
			<c:if test="${!empty latestList}">
				<tr>
					<th>제목</th>
					<th>회원</th>
					<th>별점</th>
					<th>평가</th>
				</tr>
				<c:forEach var="list" items="${latestList}">
					<tr>
						<td id="bookName${list.isbn}"></td>
						<td>
							<c:if test="${list.star==1 }">★☆☆☆☆</c:if> 
							<c:if test="${list.star==2 }">★★☆☆☆</c:if> 
							<c:if test="${list.star==3 }">★★★☆☆</c:if> 
							<c:if test="${list.star==4 }">★★★★☆</c:if> 
							<c:if test="${list.star==5 }">★★★★★</c:if>	
						</td>
						<td>${list.book_comment}</td>
						<td>${list.mem_id}</td>
					</tr>
				</c:forEach>
			</c:if>
		</table>
		
		<br>
	
		<h2>인기 도서</h2>
		
		<div id="popularList"></div>
		
		<br>
		
		<c:if test="${!empty allCommentCount}">
			<h2>지금 까지 총  ${allCommentCount}개의 평가가 쌓였어요!</h2>
		</c:if>
	
		<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
		<script>
			$(document).ready(function(){
				    
				 // 최근평가 isbn for문으로 담기
				<c:if test="${!empty latestList}">
					<c:forEach var="latestList" items="${latestList}">
						latestList(${latestList.isbn})
					</c:forEach>
				</c:if>
				
				// 인기 도서 불러오기
				<c:if test="${!empty popularList}">
					<c:forEach var="popularList" items="${popularList}">
						popularList(${popularList})
					</c:forEach>
				</c:if>	
				
				// 로그인한 회원의 도서 정보
				<c:if test="${!empty myBookInfo}">
					<c:forEach var="myBookInfo" items="${myBookInfo}">
						myBookInfo(${myBookInfo.isbn})
					</c:forEach>
				</c:if>
			});
			
			// 인기도서 불러오기(1개)
			function popularList(isbn){
				 
				$.ajax({	//카카오 검색요청 / [요청]
			        method: "GET",
			        traditional: true,
			        async: false,	//앞의 요청의 대한 응답이 올 때 까지 기다리기(false: 순서대로, true: 코드 중에 실행)
			        url: "https://dapi.kakao.com/v3/search/book?target=isbn",
			        data: { query: isbn},
			        headers: {Authorization: "KakaoAK 6f9ab74953bbcacc4423564a74af264e"} 
			    })
			    .done(function (msg) {	//검색 결과 담기 / [응답]
			    	console.log(msg);
			        $("#popularList").append("<img src='" + msg.documents[0].thumbnail + "'/>");	//표지
			    });   
			}
			
			
			// '찜' 도서 목록 불러오기
			function myBookInfo(isbn){
					
				$.ajax({	//카카오 검색요청 / [요청]
			        method: "GET",
			        traditional: true,
			        async: false,	//앞의 요청의 대한 응답이 올 때 까지 기다리기(false: 순서대로, true: 코드 중에 실행)
			        url: "https://dapi.kakao.com/v3/search/book?target=isbn",
			        data: { query: isbn },
			        headers: { Authorization: "KakaoAK 6f9ab74953bbcacc4423564a74af264e" } 
			    })
			    .done(function (msg) {	//검색 결과 담기 / [응답]
			    	console.log(msg);
		            $("#myLike").append("<img src='" + msg.documents[0].thumbnail + "'/>");	//표지
		            $("#myLike").append(msg.documents[0].title);	//제목
			    });   
			}
			
			// 최근 평가 도서 불러오기(1개)
			function latestList(isbn){
				console.log(isbn);
				$.ajax({	//카카오 검색요청 / [요청]
			        method: "GET",
			        traditional: true,
			        async: false,	//앞의 요청의 대한 응답이 올 때 까지 기다리기(false: 순서대로, true: 코드 중에 실행)
			        url: "https://dapi.kakao.com/v3/search/book?target=isbn",
			        data: { query: isbn },
			        headers: {Authorization: "KakaoAK 6f9ab74953bbcacc4423564a74af264e"} 
			    })
			    .done(function (msg) {	//검색 결과 담기 / [응답]
			    	console.log(msg);
			        $("#bookName" + isbn).append(msg.documents[0].title);	//표지
			    });   
			}	
		</script>
	</body>
</html>

