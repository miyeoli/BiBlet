<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/adminbootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/adminPage.css" type="text/css">

<title>관리자 페이지</title>
</head>
<body>
<div class="title">
	[${sessionScope.adm_id}]님 안녕하세요 
	<br>
	<span><a href="logout">로그아웃</a></span>
</div>

<div class="codepen-container">

	<div id="icetab-container">
    	<div class="icetab current-tab">가입 회원 현황</div>
    	<div class="icetab">도서 평가 관리</div>
    	<div class="icetab">도서 코멘트 관리</div>    
    	<div class="icetab">관리자 정보</div>      
    </div>
    
    <div id="icetab-content">
    	<div class="tabcontent tab-active">
			
				<div class="row justify-content-center">
                        <div class="col-12 col-md-10 col-lg-8">
                            <form name="member" action="<c:url value='/search'/>" method="post" class="card card-sm">
                                <div class="card-body row no-gutters align-items-center">
                                <select name="option">
					<option value="mem_num">회원 번호</option>
					<option value="mem_name">회원 이름</option>
					<option value="mem_email">회원 이메일</option>
				</select> 
                                    <div style="display:inline;" class="col-auto">
                                        <i class="fas fa-search h4 text-body"></i>
                                    </div>
                                    <!--end of col-->
                                    <div style="display:inline;" class="col">
                                        <input class="form-control form-control-sm form-control-borderless" type="search" name="keyword" placeholder="회원 번호, 이름, 이메일로 검색">
                                    </div>
                                    <!--end of col-->
                                    <div class="col-auto">
                                        <button class="btn btn-sm" style="background-color: #8080ff;" type="submit">검색</button>
                                    </div>
                                    <!--end of col-->
                                </div>
                            </form>
                        </div>
                        <!--end of col-->
                    </div>
		

			가입 회원 수 : ${memcount}
			<table class="table table-fixed">
			<thead>
				<tr>
					<th class="col-xs-3">회원 번호</th>
					<th class="col-xs-3">이름</th>
					<th class="col-xs-3">아이디</th>
					<th class="col-xs-3">비밀번호</th>
					<th class="col-xs-3">가입일</th>
					<th class="col-xs-3">이메일</th>
					<th class="col-xs-3">이메일 인증 완료 여부</th>
					<th class="col-xs-3">회원 강제 탈퇴 버튼</th>
				</tr>
				</thead>
				 <tbody>
				<c:forEach var="member" items="${memberList}">
				
					<tr>
						<td class="col-xs-3">${member.mem_num }</td>
						<td class="col-xs-3">${member.mem_name }</td>
						<td class="col-xs-3">${member.mem_id }</td>
						<td class="col-xs-3" id="hide_pass">${member.mem_pass }</td>
						<!-- 로그인과 합쳤을 때 session정보가지고 관리자 비번확인해서 값 보이도록 하기(https://esckey.tistory.com/66) -->
						<td class="col-xs-3"><fmt:formatDate pattern="yyyy-MM-dd HH:mm"
								value="${member.mem_regdate }" /></td>
						<td class="col-xs-3">${member.mem_email }</td>
						<td class="col-xs-3"><c:if test="${member.authstatus==1 }">완료</c:if> <c:if
								test="${member.authstatus==0 }">미완료</c:if></td>
						<td class="col-xs-3"><a
							href="<c:url value="/adminPage/deleteMember/${member.mem_num }"/>"><button>강제
									탈퇴</button></a></td>
					
					</tr>
					
				</c:forEach>
				</tbody>
				<c:if test="${! empty searchList }">
				<tbody>
					<c:forEach var="member" items="${searchList}">
					
						<tr>
							<td class="col-xs-3">${member.mem_num }</td>
							<td class="col-xs-3">${member.mem_name }</td>
							<td class="col-xs-3">${member.mem_id }</td>
							<td class="col-xs-3" id="hide_pass">${member.mem_pass }</td>
							<td class="col-xs-3"><fmt:formatDate pattern="yyyy-MM-dd HH:mm"
									value="${member.mem_regdate }" /></td>
							<td class="col-xs-3">${member.mem_email }</td>
							<td class="col-xs-3"><c:if test="${member.authstatus==1 }">완료</c:if> <c:if
									test="${member.authstatus==0 }">미완료</c:if></td>
							<td class="col-xs-3">
							<a href="<c:url value="/adminPage/deleteMember/${member.mem_num }"/>"><button>강제
										탈퇴</button></a>
							</td>
						</tr>
						
					</c:forEach>
					</tbody>
				</c:if>

			</table>
			
		</div>
		 
        <div class="tabcontent">
			총 평가 수 :
			<div style="display:inline;" id="starcount" name="starcount" value="${starcount}">${starcount}</div>
			<table class="table table-fixed">
			<thead>
				<tr>
					<th>회원 아이디</th>
					<th>책 제목</th>
					<th>작가</th>
					<th>출판사</th>
					<th>별점</th>

				</tr>
				</thead>
				<tbody id="dynamicTbody">

				</tbody>

				<c:if test="${! empty searchApprList }">

					<tbody id="dynamicTbody">

					</tbody>
				</c:if>


			</table>
		</div>
		
        <div class="tabcontent">
		
		<div class="row justify-content-center">
                        <div class="col-12 col-md-10 col-lg-8">
                            <form class="card card-sm">
                                <div class="card-body row no-gutters align-items-center">
                                <select name="coOption" id="coOption">
					<option value="mem_id">회원 아이디</option>
					<option value="comment">코멘트</option>
				</select> 
                                    <div style="display:inline;" class="col-auto">
                                        <i class="fas fa-search h4 text-body"></i>
                                    </div>
                                    <!--end of col-->
                                    <div style="display:inline;" class="col">
                                        <input class="form-control form-control-sm form-control-borderless" type="search" name="coKeyword" id="coKeyword" placeholder="회원 아이디, 코멘트 내용으로 검색">
                                    </div>
                                    <!--end of col-->
                                    <div class="col-auto">
                                        <button class="btn btn-sm" style="background-color: #8080ff;" type="button" onClick="javascript:CommentStopPage()">검색</button>
                                    </div>
                                    <!--end of col-->
                                </div>
                            </form>
                        </div>
                        <!--end of col-->
                    </div>


			<br>총 코멘트 수 : ${commentcount}
			<table class="table table-fixed">
			 <thead>
				<tr>
					<th>회원 아이디</th>
					<th>책 제목</th>
					<th>작가</th>
					<th>출판사</th>
					<th>코멘트</th>
					<th>코멘트 강제 삭제</th>
				</tr>
				</thead>
				<tbody id="dynamicTbody2">

				</tbody>
				
				<c:if test="${! empty searchApprList }">
					<tbody id="dynamicTbody2">

					</tbody>
				</c:if>
			</table>
		</div>
		
		<div class="tabcontent">			
			<div class="row justify-content-center">
                        <div class="col-12 col-md-10 col-lg-8">
                            <form class="card card-sm">
                                <div class="card-body row no-gutters align-items-center">
                                <select name="admOption" id="admOption">
					<option value="adm_num">관리자 번호</option>
					<option value="adm_name">관리자 이름</option>
					<option value="adm_email">관리자 이메일</option>
				</select>
                                    <div style="display:inline;" class="col-auto">
                                        <i class="fas fa-search h4 text-body"></i>
                                    </div>
                                    <!--end of col-->
                                    <div style="display:inline;" class="col">
                                        <input class="form-control form-control-sm form-control-borderless" type="search" name="admKeyword" id="admKeyword"	placeholder="관리자 번호, 이름, 이메일로 검색">
                                    </div>
                                    <!--end of col-->
                                    <div class="col-auto">
                                        <button class="btn btn-sm" style="background-color: #8080ff;" type="button" onClick="javascript:AdmStopPage()">검색</button>
                                    </div>
                                    <!--end of col-->
                                </div>
                            </form>
                        </div>
                        <!--end of col-->
                    </div>

			<br>가입 관리자 수 : ${admcount} 
			<br> 관리자 코드 : ABC
			<table class="table table-fixed">
			 <thead>
				<tr>
					<th>관리자 번호</th>
					<th>이름</th>
					<th>아이디</th>
					<th>가입일</th>
					<th>이메일</th>
					<th>이메일 인증 완료 여부</th>
				</tr>
				</thead>
				<tbody id="Tbody3">

					
				<c:forEach var="admin" items="${adminList}">
					<tr>
						<td>${admin.adm_num }</td>
						<td>${admin.adm_name }</td>
						<td>${admin.adm_id }</td>
						<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm"
								value="${admin.adm_regdate }" /></td>
						<td>${admin.adm_email }</td>
						<td><c:if test="${admin.adm_authstatus==1 }">완료</c:if> <c:if
								test="${admin.adm_authstatus==0 }">미완료</c:if></td>
					</tr>
				</c:forEach>

				<c:if test="${! empty searchAdmList }">
					<c:forEach var="admin" items="${searchAdmList}">
						<tr>
							<td>${admin.adm_num }</td>
							<td>${admin.adm_name }</td>
							<td>${admin.adm_id }</td>
							<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm"
									value="${admin.adm_regdate }" /></td>
							<td>${admin.adm_email }</td>
							<td><c:if test="${admin.adm_authstatus==1 }">완료</c:if> <c:if
									test="${admin.adm_authstatus==0 }">미완료</c:if></td>
						</tr>
					</c:forEach>
				</c:if>
				</tbody>
			</table>
		</div>
    </div> 
</div>

	

	<script src="http://code.jquery.com/jquery-3.5.1.js"
		integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
		crossorigin="anonymous"></script>

	<script>
	var tabs = document.getElementById('icetab-container').children;
	var tabcontents = document.getElementById('icetab-content').children;
	var myFunction = function() {
		var tabchange = this.mynum;
		for(var int=0;int<tabcontents.length;int++){
			tabcontents[int].className = ' tabcontent';
			tabs[int].className = ' icetab';
		}
		tabcontents[tabchange].classList.add('tab-active');
		this.classList.add('current-tab');
	}	
	for(var index=0;index<tabs.length;index++){
		tabs[index].mynum=index;
		tabs[index].addEventListener('click', myFunction, false);
	}
	</script>
	<script>
	
		
		function bookinfo(isbn, mem_id, star) {
			var pageNum = 1;
			$.ajax({ //카카오 검색요청 / [요청]
				method : "GET",
				url : "https://dapi.kakao.com/v3/search/book",
				data : {
					query : isbn,
					page : pageNum
				},
				headers : {
					Authorization : "KakaoAK 6f9ab74953bbcacc4423564a74af264e"
				}
			})
			.done(function(msg) { //검색 결과 담기 / [응답]
				console.log(msg);
				var html='';
				html +='<tr>';
				html +='<td>'+mem_id+'</td>';
				html +='<td>'+msg.documents[0].title+'</td>';
				html +='<td>'+msg.documents[0].authors+'</td>';
				html +='<td>'+msg.documents[0].publisher+'</td>';
				html +='<td>'+star+'</td>';
				html +='<tr>';
				$("#dynamicTbody").append(html);
			});
		};
		
		function bookinfo2(isbn, mem_id, comment,appr_num) {
			$("#dynamicTbody2").empty();
			var pageNum = 1;
			$.ajax({ //카카오 검색요청 / [요청]
				method : "GET",
				url : "https://dapi.kakao.com/v3/search/book",
				data : {
					query : isbn,
					page : pageNum
				},
				headers : {
					Authorization : "KakaoAK 6f9ab74953bbcacc4423564a74af264e"
				}
			})
			.done(function(msg) { //검색 결과 담기 / [응답]
				console.log(msg);
				var html='';
				html +='<tr>';
				html +='<td>'+mem_id+'</td>';
				html +='<td>'+msg.documents[0].title+'</td>';
				html +='<td>'+msg.documents[0].authors+'</td>';
				html +='<td>'+msg.documents[0].publisher+'</td>';
				html +='<td>'+comment+'</td>';
				html +='<td><a href="<c:url value="/adminPage/deleteComment/'+appr_num+'"/>"><button>강제 삭제</button></a></td>';
				html +='<tr>';
				$("#dynamicTbody2").append(html);
			});
		};
		
	$(document).ready(function(){
		<c:forEach var="appr" items="${apprList}">
			<c:if test="${0 ne appr.star}">
				<c:if test="${appr.star==1 }">var star='★☆☆☆☆';</c:if> 
				<c:if test="${appr.star==2 }">var star='★★☆☆☆';</c:if> 
				<c:if test="${appr.star==3 }">var star='★★★☆☆';</c:if> 
				<c:if test="${appr.star==4 }">var star='★★★★☆';</c:if> 
				<c:if test="${appr.star==5 }">var star='★★★★★';</c:if>
				bookinfo("${appr.isbn}", "${appr.mem_id }", star);
			</c:if>
		</c:forEach>
		
		
	
		<c:forEach var="comm" items="${commentList}">
			<c:if test="${! empty comm.book_comment}">
				bookinfo2("${comm.isbn}","${comm.mem_id}","${comm.book_comment}","${comm.appraisal_num}")		
			</c:if>
		</c:forEach>
		
		<c:forEach var="comm" items="${searchApprList}">
			<c:if test="${! empty comm.book_comment}">
				bookinfo2("${comm.isbn}","${comm.mem_id}","${comm.book_comment}","${comm.appraisal_num}")		
			</c:if>
		</c:forEach>
		
	});
	</script>
	<script>
function CommentStopPage(){
	
	let coOption=$("#coOption").val();
	let coKeyword=$("#coKeyword").val();
	console.log("확인" + coOption + coKeyword)
	$.ajax({
		url:'<c:url value="/commentPage"/>',
		type:'POST',
		dataType:'json',
		contentType : "application/json; charset=UTF-8",
		data:JSON.stringify({
			"coOption" : coOption,
			"coKeyword" : coKeyword
		}),
		success:function(data){
			$("#dynamicTbody2").empty();
			for(var i = 0; i < data.item.length; i++){
				if(data.item[i].bookcomment!=null){
					console.log(data.item[i].isbn,data.item[i].mem_id,data.item[i].bookcomment,data.item[i].appraisal_num);
						bookinfo2(data.item[i].isbn,
								data.item[i].mem_id,data.item[i].bookcomment,data.item[i].appraisal_num);		
				}
			}
	}
	   ,error:function(request,status,error){
		    alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);}
});
};
function AdmStopPage(){
	
	let admOption=$("#admOption").val();
	let admKeyword=$("#admKeyword").val();
	$.ajax({
		url:'<c:url value="/admInfoPage"/>',
		type:'POST',
		dataType:'json',
		contentType : "application/json; charset=UTF-8",
		data:JSON.stringify({
			"admOption" : admOption,
			"admKeyword" : admKeyword
		}),
		success:function(data){
			$("#Tbody3").empty();
			for(var i = 0; i < data.item.length; i++){
				var html='';
				html +='<tr>';
				html +='<td>'+data.item[i].adm_num+'</td>';
				html +='<td>'+data.item[i].adm_name+'</td>';
				html +='<td>'+data.item[i].adm_id+'</td>';
				html +='<td>'+data.item[i].adm_regdate+'</td>';
				html +='<td>'+data.item[i].adm_email+'</td>';
				if(data.item[i].adm_authstatus==1){html +='<td>완료</td>';}
				else if(data.item[i].adm_authstatus==0){html +='<td>미완료</td>';}
				html +='<tr>';
				$("#Tbody3").append(html);
			}
	}
	   ,error:function(request,status,error){
		    alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);}
});
};
	</script>
</body>
</html>