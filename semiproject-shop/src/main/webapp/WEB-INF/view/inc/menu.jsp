<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
		<!-- Required meta tags -->
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<title>PC-Mall</title>
		
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
    <!-- Page Preloder -->
    <div id="preloder">
        <div class="loader"></div>
    </div>

    <!-- Offcanvas Menu Begin -->
    <div class="offcanvas-menu-overlay"></div>
    <div class="offcanvas-menu-wrapper">
        <div class="offcanvas__option">
            <div class="offcanvas__top__hover">            
				<c:if test="${loginCustomer != null}">
					<span>
						<a href = "${pageContext.request.contextPath }/question/questionList">
							고객센터
						</a>
						<i class="arrow_carrot-down"></i>
					</span>
                    <ul>
                        <li>
                            <a href = "${pageContext.request.contextPath }/question/questionListUser">
								나의문의
							</a>
                        </li>
                        <li>			
							<a href = "${pageContext.request.contextPath }/question/addQuestion">
								문의작성
							</a>
                        </li>
                	</ul>
            	</c:if>
				<c:if test="${loginEmp != null}">
					<span>
						<a href = "${pageContext.request.contextPath }/questionComment/questionCommentList">
							고객센터(관리자)
						</a>
					</span>
				</c:if>
			</div>			
            <div class="offcanvas__top__hover">  
				<c:if test="${loginCustomer != null}">
					<span>${loginCustomer.customerId}님 <i class="arrow_carrot-down"></i></span>
                   	<ul>
                       	<li>
                           	<a href = "${pageContext.request.contextPath }/customer/checkPw?targetUrl=/customer/modifyCustomer">
                               	내 정보 수정
							</a>
						</li>
                        <li>
                           	<a href = "${pageContext.request.contextPath }/customer/checkPw?targetUrl=/customer/modifyCustomerPw">
								비밀번호 변경
							</a>
						</li>
                        <li>
                           	<a href = "${pageContext.request.contextPath }/customer/checkPw?targetUrl=/customer/deleteCustomer?customerId=${loginCustomer.customerId }">
								회원 탈퇴
							</a>
						</li>
                        <li>
                          	<a href = "${pageContext.request.contextPath }/customer/addressList">
								배송지 관리
							</a>
						</li>
                    </ul>
				</c:if>
				<c:if test="${loginEmp != null}">
					<span>사업자/관리자 ${loginEmp.empId}님 <i class="arrow_carrot-down"></i></span>
                   	<ul>
                       	<li>
                           	<a href = "${pageContext.request.contextPath }/emp/checkPw?targetUrl=/emp/modifyEmp">
								내 정보 수정
							</a>
						</li>
                        <li>
                        	<a href = "${pageContext.request.contextPath }/emp/checkPw?targetUrl=/emp/modifyEmpPw">
								비밀번호 변경
							</a>
						</li>
                        <li>
                           	<a href = "${pageContext.request.contextPath }/emp/checkPw?targetUrl=/emp/deleteEmp?empCode=${loginEmp.empCode }">
								회원 탈퇴
							</a>
						</li>
                    </ul>
				</c:if>
            </div>
        </div>
        <div class="offcanvas__links">
        </div>
        <div class="offcanvas__nav__option">
            	<c:choose>
            		<c:when test="${loginCustomer == null && loginEmp == null }">
						<a href = "${pageContext.request.contextPath }/login">
							로그인
						</a>
					</c:when>
					<c:otherwise>
						<a href = "${pageContext.request.contextPath }/logout">
							로그아웃
						</a>
					</c:otherwise>
                </c:choose>
            <a href="#"><img src="${pageContext.request.contextPath }/resources/img/icon/cart.png" alt=""> <span>0</span></a>
            <div class="price">$0.00</div>
        </div>
        <div id="mobile-menu-wrap"></div>
        <div class="offcanvas__text">
            <p>Free shipping, 30-day return or refund guarantee.</p>
        </div>
    </div>
    <!-- Offcanvas Menu End -->

    <!-- Header Section Begin -->
    <header class="header">
        <div class="header__top">
            <div class="container">
                <div class="row">
                    <div class="col-lg-6 col-md-7">
                        <div class="header__top__left">
                            <p>Free shipping, 30-day return or refund guarantee.</p>
                        </div>
                    </div>
                    <div class="col-lg-6 col-md-5">
                        <div class="header__top__right">
                            <div class="header__top__links">
                            	<c:choose>
	                            	<c:when test="${loginCustomer == null && loginEmp == null }">
										<a href = "${pageContext.request.contextPath }/login">
											로그인
										</a>
	                            	</c:when>
									<c:otherwise>
										<a href = "${pageContext.request.contextPath }/logout">
											로그아웃
										</a>
									</c:otherwise>		
                            	</c:choose>
                            </div>
                                
                            <div class="header__top__hover">
								<c:if test="${loginCustomer != null}">
									<span>
										<a href = "${pageContext.request.contextPath }/question/questionList">
											고객센터
										</a>
										<i class="arrow_carrot-down"></i>
									</span>
                                		<ul>
		                                    <li>
		                                    	<a href = "${pageContext.request.contextPath }/question/questionListUser"> 
													나의문의
												</a>
		                                    </li>
		                                    <li>			
												<a href = "${pageContext.request.contextPath }/question/addQuestion">
													문의작성
												</a>
		                                    </li>
                                   		</ul>
                               	</c:if>
								<c:if test="${loginEmp != null}">
									<span>
										<a href = "${pageContext.request.contextPath }/questionComment/questionCommentList">
											고객센터(관리자)
										</a>
									</span>
								</c:if>
                            </div>
                            <div class="header__top__hover">
								<c:if test="${loginCustomer != null}">
									<span>${loginCustomer.customerId}님 <i class="arrow_carrot-down"></i></span>
	                                <ul>
	                                    <li>
	                                    	<a href = "${pageContext.request.contextPath }/customer/checkPw?targetUrl=/customer/modifyCustomer">
	                                    		내 정보 수정
											</a>
										</li>
	                                    <li>
	                                    	<a href = "${pageContext.request.contextPath }/customer/checkPw?targetUrl=/customer/modifyCustomerPw">
												비밀번호 변경
											</a>
										</li>
	                                    <li>
	                                    	<a href = "${pageContext.request.contextPath }/customer/checkPw?targetUrl=/customer/deleteCustomer?customerId=${loginCustomer.customerId }">
												회원 탈퇴
											</a>
										</li>
	                                    <li>
	                                    	<a href = "${pageContext.request.contextPath }/customer/addressList">
												배송지 관리
											</a>
										</li>
                                    </ul>
								</c:if>
								<c:if test="${loginEmp != null}">
									<span>사업자/관리자 ${loginEmp.empId}님 <i class="arrow_carrot-down"></i></span>
	                                <ul>
	                                    <li>
	                                    	<a href = "${pageContext.request.contextPath }/emp/checkPw?targetUrl=/emp/modifyEmp">
												내 정보 수정
											</a>
										</li>
	                                    <li>
	                                    	<a href = "${pageContext.request.contextPath }/emp/checkPw?targetUrl=/emp/modifyEmpPw">
												비밀번호 변경
											</a>
										</li>
	                                    <li>
	                                    	<a href = "${pageContext.request.contextPath }/emp/checkPw?targetUrl=/emp/deleteEmp?empCode=${loginEmp.empCode }">
												회원 탈퇴
											</a>
										</li>
                                    </ul>
								</c:if>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="container">
            <div class="row">
                <div class="col-lg-3 col-md-3">
                    <div class="header__logo">
        				<a href="${pageContext.request.contextPath}/home"><img src="${pageContext.request.contextPath}/resources/img/logo.png" alt=""></a>
                    </div>
                </div>
                <div class="col-lg-6 col-md-6">
                    <nav class="header__menu mobile-menu">
                        <ul>
                            <li>
                            	<a href = "${pageContext.request.contextPath }/notice/noticeList">
                            		공지사항
                            	</a>
                            </li>
                            <li>
                            	<!-- authCode 수정되면 eq뒤에 값 1로 변경예정 -->
								<c:choose>
									<c:when test="${loginEmp != null && loginEmp.empId eq 'compuzone'}">
										<a href = "${pageContext.request.contextPath }/goods/goodsListByCompany">
											사업자용리스트
										</a>
									</c:when>
									<c:otherwise>
										<a href = "${pageContext.request.contextPath }/goods/goodsList">
											상품리스트
										</a>
									</c:otherwise>
								</c:choose>
							</li>
							<c:if test="${loginCustomer != null}">
								<li>
									<a href = "${pageContext.request.contextPath }/order/orderList">
										내주문
									</a>
								</li>
							</c:if>
                            <li><a href = "${pageContext.request.contextPath }/review/reviewList">리뷰</a></li>
                            
                            <!-- 관리자만 접근 가능 -->
                            <c:if test="${loginEmp != null && loginEmp.authCode == 0 }">
	                            <li><a href="#">관리 <i class="arrow_carrot-down"></i></a>
	                                <ul class="dropdown">
	                                    <li><a href="${pageContext.request.contextPath }/customer/customerList">고객 관리</a></li>
	                                    <li><a href="${pageContext.request.contextPath }/emp/empList">사원 관리</a></li>
	                                </ul>
	                            </li>                            
                            </c:if>
                            
                        </ul>
                    </nav>
                </div>
                <div class="col-lg-3 col-md-3">
                    <div class="header__nav__option">
                        <c:choose>
							<c:when test="${loginCustomer == null }">
								<a href = "${pageContext.request.contextPath }/cart/nonMemberCartList?action=cartList">
									<img src="${pageContext.request.contextPath }/resources/img/icon/cart.png" alt="">&nbsp;장바구니
									<!-- 카트 품목 수 -->
									<span></span>
								</a>								
							</c:when>
							<c:otherwise>
								<a class="price" href = "${pageContext.request.contextPath }/cart/customerCartList?action=cartList">
									<img src="${pageContext.request.contextPath }/resources/img/icon/cart.png" alt="">&nbsp;장바구니
									<!-- 카트 품목 수 -->
									<span></span>
								</a>
							</c:otherwise>
						</c:choose>
                        <div class="price">| Point 0.00</div>
                    </div>
                </div>
            </div>
            <div class="canvas__open"><i class="fa fa-bars"></i></div>
        </div>
    </header>
    <!-- Header Section End -->
    <!-- Js Plugins -->
    <script src="${pageContext.request.contextPath }/resources/js/jquery-3.3.1.min.js"></script>
    <script src="${pageContext.request.contextPath }/resources/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath }/resources/js/jquery.nice-select.min.js"></script>
    <script src="${pageContext.request.contextPath }/resources/js/jquery.nicescroll.min.js"></script>
    <script src="${pageContext.request.contextPath }/resources/js/jquery.magnific-popup.min.js"></script>
    <script src="${pageContext.request.contextPath }/resources/js/jquery.countdown.min.js"></script>
    <script src="${pageContext.request.contextPath }/resources/js/jquery.slicknav.js"></script>
    <script src="${pageContext.request.contextPath }/resources/js/mixitup.min.js"></script>
    <script src="${pageContext.request.contextPath }/resources/js/owl.carousel.min.js"></script>
    <script src="${pageContext.request.contextPath }/resources/js/main.js"></script>
	
</body>
</html>