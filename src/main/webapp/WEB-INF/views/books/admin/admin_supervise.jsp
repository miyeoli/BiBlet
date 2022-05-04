<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<style type="text/css">
dl {
	position: relative;
	width: 1000px;
	height: 100px;
}

dt {
	height: 30px;
	float: left;
	width: 250px;
	z-index: 9;
	position: relative;
	text-align: center;
	background: #ddd;
}

dd {
	position: absolute;
	padding-top: 30px;
	background-color: #f3f3f3;
	width: 1000px;
	height: 100px;
	margin: 0;
}

dd.hidden {
	display: none;
}
</style>


<title>관리자 페이지</title>
</head>
<body>

[${sessionScope.adm_id}]님 안녕하세요 

		<span>
			<button id="logout" onClick="location.href = '/member/logout'">로그아웃</button>
		</span>

	<dl>
		<dt>가입 회원 현황</dt>
		<dd>
			<form name="member" action="<c:url value='/search'/>" method="post">
				가입 회원 검색 &nbsp:&nbsp <select name="option">
					<option value="mem_num">회원 번호</option>
					<option value="mem_name">회원 이름</option>
					<option value="mem_email">회원 이메일</option>
				</select> <input type="text" name="keyword" placeholder="회원 번호, 이름, 이메일로 검색"
					style="width: 30%;"> <input type="submit" value="검색">
			</form>

			가입 회원 수 : ${memcount}
			<table border="1">
				<tr>
					<th>회원 번호</th>
					<th>이름</th>
					<th>아이디</th>
					<th>비밀번호</th>
					<th>가입일</th>
					<th>이메일</th>
					<th>이메일 인증 완료 여부</th>
					<th>회원 강제 탈퇴 버튼</th>
				</tr>
				<c:forEach var="member" items="${memberList}">
					<tr>
						<td>${member.mem_num }</td>
						<td>${member.mem_name }</td>
						<td>${member.mem_id }</td>
						<td id="hide_pass">${member.mem_pass }</td>
						<!-- 로그인과 합쳤을 때 session정보가지고 관리자 비번확인해서 값 보이도록 하기(https://esckey.tistory.com/66) -->
						<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm"
								value="${member.mem_regdate }" /></td>
						<td>${member.mem_email }</td>
						<td><c:if test="${member.authstatus==1 }">완료</c:if> <c:if
								test="${member.authstatus==0 }">미완료</c:if></td>
						<td><a
							href="<c:url value="/adminPage/deleteMember/${member.mem_num }"/>"><button>강제
									탈퇴</button></a></td>
					</tr>
				</c:forEach>

				<c:if test="${! empty searchList }">
					<c:forEach var="member" items="${searchList}">
						<tr>
							<td>${member.mem_num }</td>
							<td>${member.mem_name }</td>
							<td>${member.mem_id }</td>
							<td id="hide_pass">${member.mem_pass }</td>
							<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm"
									value="${member.mem_regdate }" /></td>
							<td>${member.mem_email }</td>
							<td><c:if test="${member.authstatus==1 }">완료</c:if> <c:if
									test="${member.authstatus==0 }">미완료</c:if></td>
							<td><a
								href="<c:url value="/adminPage/deleteMember/${member.mem_num }"/>"><button>강제
										탈퇴</button></a></td>
						</tr>
					</c:forEach>
				</c:if>

			</table>
		</dd>

		<dt>총 평가</dt>
		<dd class="hidden">
			총 평가 수 :
			<div id="starcount" name="starcount" value="${starcount}">${starcount}</div>
			<table border="1">
				<tr>
					<th>회원 아이디</th>
					<th>책 제목</th>
					<th>작가</th>
					<th>출판사</th>
					<th>별점</th>

				</tr>
				<tbody id="dynamicTbody">

				</tbody>

				<c:if test="${! empty searchApprList }">

					<tbody id="dynamicTbody">

					</tbody>
				</c:if>


			</table>
		</dd>

		<dt>총 코멘트</dt>
		<dd class="hidden">
			
				코멘트 검색 &nbsp:&nbsp <select name="coOption" id="coOption">
					<option value="mem_id">회원 아이디</option>
					<option value="comment">코멘트</option>
				</select> <input type="text" name="coKeyword" id="coKeyword"
					placeholder="회원 아이디, 코멘트 내용으로 검색" style="width: 30%;"> <input
					type="button" value="검색" onClick="javascript:CommentStopPage()"/>

			총 코멘트 수 : ${commentcount}
			<table border="1">
				<tr>
					<th>회원 아이디</th>
					<th>책 제목</th>
					<th>작가</th>
					<th>출판사</th>
					<th>코멘트</th>
					<th>코멘트 강제 삭제</th>
				</tr>

				<tbody id="dynamicTbody2">

				</tbody>
				<tbody id="test">

				</tbody>
				<c:if test="${! empty searchApprList }">
					<tbody id="dynamicTbody2">

					</tbody>
				</c:if>
			</table>
		</dd>

		<dt>관리자 정보</dt>
		<dd class="hidden">
			
				가입 관리자 검색 &nbsp:&nbsp <select name="admOption" id="admOption">
					<option value="adm_num">관리자 번호</option>
					<option value="adm_name">관리자 이름</option>
					<option value="adm_email">관리자 이메일</option>
				</select> <input type="text" name="admKeyword" id="admKeyword"
					placeholder="관리자 번호, 이름, 이메일로 검색" style="width: 30%;"> <input
					type="button" value="검색" onClick="javascript:AdmStopPage()">
			

			가입 관리자 수 : ${admcount} <br> 관리자 코드 : ABC
			<table border="1">
				<tr>
					<th>관리자 번호</th>
					<th>이름</th>
					<th>아이디</th>
					<th>가입일</th>
					<th>이메일</th>
					<th>이메일 인증 완료 여부</th>
				</tr>
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
		</dd>
	</dl>

	<script src="http://code.jquery.com/jquery-3.5.1.js"
		integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
		crossorigin="anonymous"></script>

	<script>
		var $menuEle = $('dt'); // 탭메뉴를 변수에 지정
		$menuEle.click(function() { // 탭메뉴 클릭 이벤트
			$('dd').addClass('hidden');
			$(this).next().removeClass('hidden');
		});
	
		
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