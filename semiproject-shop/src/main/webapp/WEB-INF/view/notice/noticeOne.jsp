<jsp:include page = "/WEB-INF/view/inc/menu.jsp"></jsp:include>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<!-- Required meta tags -->
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<title>공지 상세페이지</title>
		
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
	</head>
	
	<body>
		<div class="container-scroller">
		    <div class="container-fluid">
				<div class="row justify-content-center">
					<div class="content-wrapper row justify-content-center">
						<div class="col-md-auto grid-margin stretch-card notice" style="width: 100%">
							<div class="card">
								<div class="card-body">
										<h2 class = "font-weight-bold text-center">공지사항</h2>
										<div class="row justify-content-center mt-3">
											<c:forEach var="n" items="${list}">
												<div class="col-12">
													<div align="left"> 
														<button class="btn btn-sm btn-primary" onclick="history.back()">목록</button>
													</div>
													<div align="right">작성일 : ${n.createdate}</div>
												
													<!-- 공지 상세보기 (공지 종류, 공지 내용)-->
													<div class="table-responsive mb-5">
														<table class = "table expandable-table table-hover text-center">
															<thead>
																<tr>
																	<th></th>
																	<th class="col-12">${n.noticeTitle}</th>
																	<th></th>
																</tr>
															</thead>
															<tbody>
																<tr>
																	<td></td>
																	<td>${n.noticeContent}</td>
																	<td></td>
																</tr>
															</tbody>
														</table>												
													</div>
														
													<div class="input-group-append justify-content-end">
														<!-- 공지수정, 관리자만 접근가능 -->
														<c:if test="${loginEmp != null}">
															<button  class="btn btn-sm btn-primary" onClick="location.href='${pageContext.request.contextPath}/notice/modifyNotice?noticeCode=${n.noticeCode}'">
																<span>수정하기</span> 
															</button>	
															<button id="remove" class="btn btn-sm btn-primary" onClick="location.href='${pageContext.request.contextPath}/emp/checkPw?targetUrl=/notice/deleteNotice?noticeCode=${n.noticeCode}'">
																<span>삭제하기</span> 
															</button>
														</c:if>
													</div>
												</div>
											</c:forEach>
										</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- page-body-wrapper ends -->
			<jsp:include page = "/WEB-INF/view/inc/footer.jsp"></jsp:include>
		</div>
	</body>
</html>