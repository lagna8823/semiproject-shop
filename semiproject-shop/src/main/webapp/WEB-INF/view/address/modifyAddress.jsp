<jsp:include page = "/WEB-INF/view/inc/menu.jsp"></jsp:include>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

	<head>
		<!-- Required meta tags -->
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<title>modifyAddress.jsp</title>
		
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
	  
		<!-- jQuery -->
		<!-- CDN 주소 추가 방식 -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
		
	</head>


	<body>

		<div class="container-scroller">
		    <div class="container-fluid">
				<div class="row justify-content-center">
					<div class="content-wrapper row justify-content-center">
						<div class="col-lg-4 grid-margin stretch-card">
							<div class="card">
								<div class="card-body">
									<h2 class = "font-weight-bold text-center text-primary">배송지 수정</h2>
									<div class="row justify-content-center mt-3">
										<div class="col mt-3">
											<form action = "${pageContext.request.contextPath }/customer/modifyAddress" method = "post">
												<input type = "hidden" name = "addressCode" value = "${address.addressCode }">
												<div class="form-group">
													<textarea rows="5" name = "address" class="form-control">${address.address }</textarea>
												</div>
												
												<div>
													<button class="btn btn-lg btn-primary btn-block" type = "submit">수정</button>
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
			<!-- page-body-wrapper ends -->
			<jsp:include page = "/WEB-INF/view/inc/footer.jsp"></jsp:include>
		</div>
		<!-- container-scroller -->
	
		<!-- plugins:js -->
		<!-- endinject -->
		<!-- Plugin js for this page -->
		
		<!-- End plugin js for this page -->
		<!-- inject:js -->
		<!-- endinject -->
		<!-- Custom js for this page-->
		<!-- End custom js for this page-->
		
	</body>

</html>