<jsp:include page = "/WEB-INF/view/inc/menuTest.jsp"></jsp:include>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<!-- Required meta tags -->
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<title>문의 수정</title>
		
		<!-- plugins:css -->
		<link rel="stylesheet" href="${pageContext.request.contextPath }/resources2/vendors/feather/feather.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath }/resources2/vendors/ti-icons/css/themify-icons.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath }/resources2/vendors/css/vendor.bundle.base.css">
		<!-- endinject -->
		
		<!-- Plugin css for this page -->
		<link rel="stylesheet" href="${pageContext.request.contextPath }/resources2/vendors/datatables.net-bs4/dataTables.bootstrap4.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath }/resources2/vendors/ti-icons/css/themify-icons.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources2/js/select.dataTables.min.css">
		<!-- End plugin css for this page -->
		
		<!-- inject:css -->
		<link rel="stylesheet" href="${pageContext.request.contextPath }/resources2/css/vertical-layout-light/style.css">
		<!-- endinject -->
		
		<link rel="shortcut icon" href="${pageContext.request.contextPath }/resources2/images/favicon.png" />
	  	
	  	<!-- style -->
	  	<style>
	  	
	  		.tr {
	  			text-align:center;
	  			align:center;
	  		}
	  		
	  		.th {
	  			text-align:center;
	  			align:center;
	  		}
	  		
	  		.td {
	  			text-align:center;
	  			align:center;
	  		}
	  	</style>
	  	
	  	<!-- jQuery -->
		<!-- CDN 주소 추가 방식 -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
		<script>
			$(document).ready(function() {
				// 최대 글자수
				${'MAX_COUNT'} = 500;
				
				// 입력된 메모값
				let commentMemo = document.querySelector('#commentMemo')
				
				$('#commentMemo').keyup(function() {
					// 입력한 값의 글자수
					${'len'} = $('#commentMemo').val().length; 
					
					// 결과
					if(len < MAX_COUNT){
						document.querySelector('#count').innerHTML = len;
					} else if(len > MAX_COUNT-1){
						alert(MAX_COUNT+'자까지 입력가능합니다');
						commentMemo.value = commentMemo.value.substring(0, MAX_COUNT);
					} 
				});	
				
				// 수정 버튼 클릭시 확인 및 공백값 체크
				$('#modifyBtn').click(function(){
					var out = confirm('수정 하시겠습니까?');
					if(out == true){
							// 미입력시
							if( ($('#commentMemo').val().length) < 1 )  {
								alert('입력된값이 없습니다.');
								$('#commentMemo').focus();
								return;
							}
							// 공백만 입력시
							if($('#commentMemo').val().trim() == '')  {
								alert('공백만 입력할 수 없습니다.');
								$('#commentMemo').text('');
								$('#commentMemo').focus();
								return;
							} 
							$('#modifyForm').submit();
							alert('수정되었습니다.')
					} else { 
						alert('취소되었습니다.')
						return false;
					}
				});
			});
		</script>
	</head>
	
	<body>
		<div class="container-scroller">
		    <div class="container-fluid">
				<div class="row justify-content-center">
					<div class="col-md-auto grid-margin stretch-card">
						<div class="card">
							<div class="card-body">
								<div class="content-wrapper">
									<div align="center" style="padding-right: 55em"> 
										<button class="btn btn-sm btn-primary" onclick="history.back()">뒤로가기</button>
									</div>	
									<h2 class = "font-weight-bold text-center" align="center" style="padding-left: 3em">답변 수정</h2>
									<div class="row justify-content-center mt-3">
										<div class="col-12">
										<br>
										
											<!-- 글자수 상태바 -->
											<div align="center" style="padding-left: 50em"> 
												글자수 : <span id="count">0</span> / 500
											</div>
											
											<!-- 고객센터 내용 (분류/주문번호, 문의작성일, 문의내용, 답변일, 답변내용-->
											<div class="table-responsive mb-5">
												<form id="modifyForm" method="post" action="${pageContext.request.contextPath}/questionComment/modifyQuestionComment">
													<table border="1" class = "expandable-table table-hover text-center" align="center">
														<input type="hidden" name="commentCode" value="${q.commentCode}">
														
														<!-- 고객 문의내용 -->
														<tr class="tr">
															<th>문의번호/주문번호</th>
															<td>${q.questionCode}번 문의 / ${q.orderCode}번 주문</td>
														</tr>
														<tr class="tr">
															<th>카테고리/상품명</th>
															<td>${q.category} : ${q.goodsName}</td>
														</tr>
														<tr class="tr">
															<th>작성일</th>
															<td>${q.createdate}</td>
														</tr>
														<tr class="tr">
															<th>문의내용</th>
															<td>
																<textarea id="questionMemo" rows="8" cols="100" name="questionMemo" readonly="readonly">${q.questionMemo}</textarea>
															</td>
														</tr>
														<!-- 첨부 파일 있을시에만 보임 -->
														<c:if test="${q.questionImg == null}">
															<div>&nbsp;</div>
														</c:if> 
														<c:if test="${q.questionImg != null}">
															<tr class="tr">
																<th>첨부파일</th>
																<td>
																	<div><img src="${pageContext.request.contextPath}/upload/${q.questionImg}" width="200" height="200"></div>
																</td>
															</tr>
														</c:if> 
							
														<!-- 답변정보 -->
														<tr class="tr">
															<th>답변일</th>
															<td>
																${q.commentCreatedate}
															</td>
														</tr>
														<tr class="tr">
															<th>답변내용</th>
															<td>
																<textarea id="commentMemo" rows="8" cols="100" name="commentMemo">${q.commentMemo}</textarea>
															</td>
														</tr>
													</table>
													<br>
											
													<!-- button -->
													<div class="input-group-append justify-content-center" style="padding-left: 6em">
														<button class="btn btn-sm btn-primary" id="modifyBtn" type="button" >수정 하기</button>
													</div>	
												</form>
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
	</body>
</html>	