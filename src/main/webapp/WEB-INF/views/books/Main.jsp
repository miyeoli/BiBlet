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
<title>MainPage 비로그인</title>
</head>
<body>
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script> 

	<!-- 로그인 -->
	<div id="main_add_info">
		<c:if test="${!empty myBookInfo}">
				<div class="mypage_button">
				<button id="mypage" onClick="location.href = '/MyPage'">마이페이지</button>
			</div>
			<span>
				<button id="logout" onClick="location.href = '/member/logout'">로그아웃</button>
			</span>
			
			<p>	
				${myBookInfo.mem_id}님 안녕하세요 <br>
				<c:if test="${!empty myCommentCount}">
					지금까지 ${myCommentCount}개의 평가를 작성하였어요!
				</c:if>
			</p>		
		
			${myBookInfo.mem_id}이 찜한 도서
			<div id="myLike"></div>
		
		</c:if>
	</div>	
	
	
	
	
	<!-- 비로그인 -->
	<div class="login_button">
		<button id="login" onClick="location.href = '/member/login'">로그인</button>
	</div>
	
	<div>
	<button id="findId" onClick="location.href = '/member/findId'">아이디 찾기</button>
	</div>
	
	<div>
		<button id="adminlogin" onClick="location.href = '/admin/login'">관리자 로그인</button>
	</div>
	<span>
		<button id="signup" onClick="location.href = '/member/signup'">회원가입</button>
		<button id="signup" onClick="location.href = '/admin/signup'">관리자 회원가입</button>
	</span>
		
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
						<td id="bookName${list.book_comment}"></td>
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

	<!-- 도서 검색 -->
	
	






		<p>
			검색 키워드 입력 : <select name="keyword">
				<option value="title">제목</option>
				<option value="author">저자</option>
				<option value="publisher">출판사</option>
			</select> 
			<input type="text" name="query" id="query" value="${query}" placeholder="제목, 저자 또는 출판사 검색" size=30>
			<button id="search">검색</button>
			
		</p>

	
	<div id="searchBook"></div>
	
	<div id="main_add_info">
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
						<td id="bookName${list.book_comment}"></td>
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
	</div>
	
	
<script>

		
		$(document).ready(function(){
			
		//	도서 검색
			 var pageNum = 1;
			    
			    $("#search").click(function () {	//검색 버튼 클릭시 ajax실행
			    	
			    	$.ajax({	//카카오 검색요청 / [요청]
			            method: "GET",
			            url: "https://dapi.kakao.com/v3/search/book",
			            data: { query: $("#query").val(), page: pageNum},
			            headers: {Authorization: "KakaoAK 6f9ab74953bbcacc4423564a74af264e"} 
			        })
			       
			        .done(function (msg) {	//검색 결과 담기 / [응답]
			        	console.log(msg);
			        	$('#main_add_info').empty();
			        	for (var i = 0; i < 10; i++){
			                $("#searchBook").append("<img src='" + msg.documents[i].thumbnail + "'/><br>");		//표지
			                $("#searchBook").append("<h2><a href='/read/"+ msg.documents[i].isbn.slice(-13)+"?query="+$("#query").val()+ "'>" + msg.documents[i].title + "</a></h2>");	//제목
			                $("#searchBook").append("<strong>저자:</strong> " + msg.documents[i].authors + "<br>");		//저자	
			                $("#searchBook").append("<strong>출판사:</strong> " + msg.documents[i].publisher + "<br>");		//출판사
			                $("#searchBook").append("<strong>줄거리:</strong> " + msg.documents[i].contents + "...<br>");		//줄거리
			            	$("#searchBook").append("<strong>ISBN:</strong>" + msg.documents[i].isbn + "<br>");	//일련번호
			            }
			        });
			    
			    	$('#searchBook').empty();
			    }); 
			
			// 인기 도서 불러오기
			<c:if test="${!empty popularList}">
				<c:forEach var="popularList" items="${popularList}">
					popularList("${popularList}")
				</c:forEach>
			</c:if>	
			
			// 로그인한 회원의 도서 정보
			<c:if test="${!empty myBookInfo}">
				<c:forEach var="myBookInfo" items="${myBookInfo}">
					myBookInfo("${myBookInfo.isbn}")
				</c:forEach>
			</c:if>
			
			// 최근평가 isbn for문으로 담기
			<c:if test="${!empty latestList}">
				<c:forEach var="latestList" items="${latestList}">
					latestList("${latestList.isbn}", "${latestList.book_comment}")
				</c:forEach>
			</c:if>
			
		});
		
		//	인기도서 불러오기(1개)
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
		
		
		//'찜' 도서 목록 불러오기
		function myBookInfo(isbn){
				
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
		            $("#myLike").append("<img src='" + msg.documents[0].thumbnail + "'/>");	//표지
		            $("#myLike").append(msg.documents[0].title);	//제목
		    });   
		}
		
//		최근 평가 도서 불러오기(1개)
		 function latestList(isbn, book_comment){
				
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
		            $("#bookName" + book_comment).append(msg.documents[0].title);	//표지
		    });   
		}
		
</script>
</body>
</html>

