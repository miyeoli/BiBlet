<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>보관함</title>
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/resources/css/appraisalbootstrap.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
	crossorigin="anonymous">
	
</script>
</head>
<body>

	<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
		<div class="container-fluid">
			<a class="navbar-brand" href="/">BiBlet</a>
			<div class="collapse navbar-collapse" id="navbarColor01"></div>
			<div class="col-25 col-md-20 col-lg-2 col-xl-2">

				<div class="navbar-collapse collapse show" id="navbarColor01"
					 style="float:right;">
					<ul class="navbar-nav me-auto">
						<li class="nav-item"><a class="nav-link" id="mypage" onClick="location.href = '/MyPage'">마이페이지</a></li>
					</ul>
				</div>
			</div>
		</div>
	</nav>

	<div class="container">
		<div class="row d-flex justify-content-center mt-5">
			<div class="col-30 col-md-20 col-lg-10 col-xl-8">
				<div class="card py-3 px-2">
					<h6 class="text-center text-secondary">보관함</h6>


					<div class="container">
						<div class="row">
							<h3 style="margin-top: 20px;" class="text-secondary">
								<strong>찜</strong>
							</h3>
							<p class="text-muted">
								내 찜 수 : <strong>${MyLikeCount}</strong>
							</p>
						</div>

						<div class="row">
							<h3 style="margin-top: 20px;" class="text-secondary">
								<strong>보는 중</strong>
							</h3>
							<p class="text-muted">
								내 보는 중 수 : <strong>${MyLeadingCount}</strong>
							</p>
						</div>

						<div class="row">
							<h3 style="margin-top: 20px;" class="text-secondary">
								<strong>독서 완료</strong>
							</h3>

							<p class="text-muted">
								내 평가 수 : <strong>${MyCommentCount}</strong>
							</p>
						</div>

						<div class="container">
							<div class="row">
								<div class="col">
									<div class="col-20">
										<div class="card text-white bg-secondary mb-3"
											style="max-width: 100rem;">
											<div class="card-header">
												<c:if test="${!empty MyComment}">
													<c:forEach var="myComment" items="${MyComment}">
						별점 : ${myComment.star}
						평가 : ${myComment.book_comment}
						<div class="row"></div>
						시작 날짜 : ${myComment.start_date}
						다 읽은 날짜 : ${myComment.end_date}
						<input type="hidden" id="appraisal_num" name="appraisal_num"
															value="${myComment.appraisal_num}">
														<div id="img${myComment.appraisal_num}"></div>
													</c:forEach>
												</c:if>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>

					</div>
				</div>
			</div>
		</div>
	</div>



	<script>
		//		'찜' 페이지 실행 되면 isbn for문으로 담기
		$(document).ready(function() {
			<c:forEach var="likeIsbn" items="${likeIsbn}">
			likeIsbn("${likeIsbn}")
			</c:forEach>
		});

		// 		'찜' 도서 불러오기(1개)
		function likeIsbn(isbn) {
			$
					.ajax(
							{ //카카오 검색요청 / [요청]
								method : "GET",
								traditional : true,
								url : "https://dapi.kakao.com/v3/search/book",
								data : {
									query : isbn
								},
								headers : {
									Authorization : "KakaoAK 6f9ab74953bbcacc4423564a74af264e"
								}
							})

					.done(
							function(msg) { //검색 결과 담기 / [응답]
								console.log(msg);
								$("#like")
										.append(
												"<img src='" + msg.documents[0].thumbnail + "'/>"); //표지
							});
		}

		//		'보는 중' 페이지 실행 되면 isbn for문으로 담기
		$(document).ready(function() {
			<c:forEach var="leadingIsbn" items="${leadingIsbn}">
			leadingIsbn("${leadingIsbn}")
			</c:forEach>
		});

		// 		'보는 중' 도서 불러오기(1개)
		function leadingIsbn(isbn) {
			$
					.ajax(
							{ //카카오 검색요청 / [요청]
								method : "GET",
								traditional : true,
								url : "https://dapi.kakao.com/v3/search/book?target=isbn",
								data : {
									query : isbn
								},
								headers : {
									Authorization : "KakaoAK 6f9ab74953bbcacc4423564a74af264e"
								}
							})

					.done(
							function(msg) { //검색 결과 담기 / [응답]
								console.log(msg);
								$("#leading")
										.append(
												"<img src='" + msg.documents[0].thumbnail + "'/>"); //표지
							});
		}

		//		'독서 완료' 페이지 실행 되면 isbn for문으로 담기
		$(document).ready(
				function() {
					<c:forEach var="completeIsbn" items="${completeIsbn}">
					completeIsbn("${completeIsbn.isbn}",
							"${completeIsbn.appraisal_num}")
					</c:forEach>
				});

		// 		'독서 완료' 도서 불러오기(1개)
		function completeIsbn(isbn, appraisal_num) {
			// 			let appraisal_num = $("#appraisal_num").val();

			$
					.ajax(
							{ //카카오 검색요청 / [요청]
								method : "GET",
								traditional : true,
								async : false,
								url : "https://dapi.kakao.com/v3/search/book?target=isbn",
								data : {
									query : isbn
								},
								headers : {
									Authorization : "KakaoAK 6f9ab74953bbcacc4423564a74af264e"
								}
							})

					.done(
							function(msg) { //검색 결과 담기 / [응답]
								console.log(msg);
								$("#img" + appraisal_num)
										.append(
												"<img src='" + msg.documents[0].thumbnail + "'/>"); //표지
							});
		}
	</script>
</body>
</html>