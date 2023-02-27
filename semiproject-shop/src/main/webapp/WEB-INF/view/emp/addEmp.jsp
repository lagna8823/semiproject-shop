<jsp:include page = "/WEB-INF/view/inc/menu.jsp"></jsp:include>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">



	<head>
		<!-- Required meta tags -->
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<title>EMP 회원가입</title>
		
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
		
		<script>
		</script>		
	</head>
	<body>
		<div class="container-scroller">
		    <div class="container-fluid">
				<div class="row justify-content-center">
					<div class="content-wrapper row justify-content-center">
						<div class="col-md-auto grid-margin stretch-card">
							<div class="card">
								<div class="card-body">
									<h2 class = "font-weight-bold text-center text-primary">EMP 회원가입</h2>
									<div class="row justify-content-center mt-3">
										<div class="col-auto" style="width: 100%; min-width: 400px">
											<div>												
												<form class = "pt-3" action = "${pageContext.request.contextPath }/emp/addEmp" method = "post">
													<div class="form-group">
														<label for = "empId">empId</label>
														<input type = "text" name = "empId" id = "empId" class="form-control form-control-lg" placeholder="empId">
													</div>
													<div class="form-group">
														<label for = "empPw">empPw</label>
														<input type = "password" name = "empPw" id = "empPw" class="form-control form-control-lg" placeholder="empPw">
													</div>
													<div class="form-group">
														<label for = "empName">empName</label>
														<input type = "text" name = "empName" id = "empName" class="form-control form-control-lg" placeholder="empName">
													</div>
									                <div class="mt-5">
														<button type = "submit" class="btn btn-block btn-primary btn-lg font-weight-medium auth-form-btn">회원가입</button>
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
			<!-- page-body-wrapper ends -->
			<jsp:include page = "/WEB-INF/view/inc/footer.jsp"></jsp:include>
		</div>
	</body>
</html>