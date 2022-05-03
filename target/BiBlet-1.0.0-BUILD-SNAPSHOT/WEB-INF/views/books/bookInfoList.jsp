<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>BiBlet 테스트용 메인 페이지</title>
 <script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script> 
    
 	 
</head>
<body>
	<p>
		검색 키워드 입력 : 
		<select name="option">
			<option value="title">제목</option>
			<option value="author">저자</option>
			<option value="publisher">출판사</option>
		</select> 
			
		<input type="text" name="query" id="query" value="${query}" placeholder="제목, 저자 또는 출판사 검색" size=30> 
		<button id="search">검색</button>
 	</p>
 		
    <div></div>
    
 	
 	<script>
 	  <!-- 도서 검색 -->
        $(document).ready(function () {
            var pageNum = 1;
            $("#search").click(function () {	//검색 버튼 클릭시 ajax실행
            	$.ajax({	//카카오 검색요청 / [요청]
                    method: "GET",
                    url: "https://dapi.kakao.com/v3/search/book",
                    data: { query: $("#query").val(), page: pageNum},
                    headers: {Authorization: "KakaoAK 6f9ab74953bbcacc4423564a74af264e"} 
                })
                
                .done(function (msg) {	//검색 결과 담기 / [응답]
                /* 	console.log(msg); */
                    for (var i = 0; i < 10; i++){
                        $("div").append("<img src='" + msg.documents[i].thumbnail + "'/><br>");		//표지
                        $("div").append("<h2><a href='./read/"+ msg.documents[i].isbn.slice(0, 10)+"?query="+$("#query").val()+ "'>" + msg.documents[i].title + "</a></h2>");	//제목
                        $("div").append("<strong>저자:</strong> " + msg.documents[i].authors + "<br>");		//저자	
                        $("div").append("<strong>출판사:</strong> " + msg.documents[i].publisher + "<br>");		//출판사
                        $("div").append("<strong>줄거리:</strong> " + msg.documents[i].contents + "...<br>");		//줄거리
                    	$("div").append("<strong>일련번호:</strong>" + msg.documents[i].isbn + "<br>");	//일련번호
                    }
                });
            });
            var query = "${query}";
            if (query.length > 0) {
            	$.ajax({	//카카오 검색요청 / [요청]
                    method: "GET",
                    url: "https://dapi.kakao.com/v3/search/book",
                    data: { query: query, page: pageNum},
                    headers: {Authorization: "KakaoAK 6f9ab74953bbcacc4423564a74af264e"} 
                })
               
                .done(function (msg) {
                	//검색 결과 담기 / [응답]
                	console.log("hi");
                	console.log(msg);
                	for (var i = 0; i < 10; i++){
                        $("div").append("<img src='" + msg.documents[i].thumbnail + "'/><br>");		//표지
                        $("div").append("<h2><a href='./read/"+ msg.documents[i].isbn.slice(0, 10)+"?query="+$("#query").val()+ "'>" + msg.documents[i].title + "</a></h2>");	//제목
                        $("div").append("<strong>저자:</strong> " + msg.documents[i].authors + "<br>");		//저자	
                        $("div").append("<strong>출판사:</strong> " + msg.documents[i].publisher + "<br>");		//출판사
                        $("div").append("<strong>줄거리:</strong> " + msg.documents[i].contents + "...<br>");		//줄거리
                    	$("div").append("<strong>일련번호:</strong>" + msg.documents[i].isbn + "<br>");	//일련번호
                    }
                });
            }
        	   
        	
        	$("#query").off(); 
            
//             무한스크롤
            $(window).scroll(function(){  
                if ( Math.ceil($(window).scrollTop()) + $(window).height() >= $(document).height() ){
                    pageNum++;
                    $.ajax({
                        method: "GET",
                        url: "https://dapi.kakao.com/v3/search/book",
                        data: { query: $("#query").val(),  page: pageNum},
                        headers: {Authorization: "KakaoAK 6f9ab74953bbcacc4423564a74af264e"} 
                    })
                    .done(function (msg) {
                        console.log(msg);
                        for (var i = 0; i < 10; i++){
                            $("div").append("<img src='" + msg.documents[i].thumbnail + "'/><br>");
                            $("div").append("<h2>" + msg.documents[i].title + "</h2>");
                            $("div").append("<strong>저자:</strong> " + msg.documents[i].authors + "<br>");
                            $("div").append("<strong>출판사:</strong> " + msg.documents[i].publisher + "<br>");
                            $("div").append("<strong>줄거리:</strong> " + msg.documents[i].contents + "...<br>");
                            $("div").append("<strong>일련번호:</strong> " + msg.documents[i].isbn + "<br>");	//일련번호
                        }
                    });
                }   
            });

        });    
 	 </script>
 
</body>
</html>