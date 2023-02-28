<jsp:include page = "/WEB-INF/view/inc/menu.jsp"></jsp:include>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 숫자 표시에 콤마 찍기위한 포맷 -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
	<head>
		<!-- Required meta tags -->
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<title>상품 수정</title>
		
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
					<div class="col-md-auto grid-margin stretch-card">
						<div class="card">
							<div class="card-body">
								<div class="content-wrapper">
									<h2 class = "font-weight-bold text-center">상품 수정</h2>
									<div align="left"> 
										<button class="btn btn-sm btn-primary" onclick="history.back()">뒤로가기</button>
									</div>	
									<div class="row justify-content-center mt-3">
										<div class="col-12">										
											<div class="table-responsive mb-5">
												<form method="post" action="${pageContext.request.contextPath}/goods/modifyGoods" enctype="multipart/form-data">
													<c:forEach var="m" items="${list}" varStatus="s">
														<input type="hidden" name="noticeCode" value="${n.noticeCode}">
														<div>
															<img src="${pageContext.request.contextPath}/upload/${m.filename}" style="display: block; margin: 0 auto; width:300px; height:300px;">
														</div>
														<table class = "table expandable-table table-hover text-center mt-3">
															<tr>
																<td>상품 번호</td>
																<td><input class="form-control" type="text" name="goodsCode" value="${m.goodsCode}" readonly="readonly"></td>
															</tr>
															<tr>
																<td>상품 등록자 ID</td>
																<td><input class="form-control" type="text" name="empId" value="${m.empId}" readonly="readonly"></td>
															</tr>
															<tr>
																<td>상품명</td>
																<td><textarea class="form-control" rows="3" cols="30" name="goodsName">${m.goodsName}</textarea></td>
															</tr>
															<tr>
																<td>상품 설명</td>
																<td><textarea class="form-control" rows="5" cols="50" name="goodsMemo">${m.goodsMemo}</textarea></td>
															</tr>
															<tr>
																<td>상품 가격</td>
																<td><input class="form-control" type="number" name="goodsPrice" value="${m.goodsPrice}"></td>
															</tr>
															<tr>
																<td>상품 재고(Y선택시 품절)</td>
																<td>
																	<input type="radio" name="soldout" value="Y" <c:if test="${soldeout == Y}"> checked </c:if>>Y
																	<input type="radio" name="soldout" value="N" <c:if test="${soldeout == N}"> checked </c:if>>N
																</td>
															</tr>
															<tr>
																<td>상단 등록 레벨</td>
																<td><input class="form-control" type="number" name="hit" value="${m.hit}"></td>
															</tr>
															<tr>
																<td>상품 이미지</td>
																<td><input class="form-control" type="file" name="goodsImg" value="${m.filename}"></td>
															</tr>														
														</table>
													</c:forEach>
													<br>
													<div class="input-group-append justify-content-center"">
														<button class="btn btn-block btn-primary" type="submit" >수정하기</button>
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
			<jsp:include page = "/WEB-INF/view/inc/footer.jsp"></jsp:include> 
		</div>
	</body>
</html>