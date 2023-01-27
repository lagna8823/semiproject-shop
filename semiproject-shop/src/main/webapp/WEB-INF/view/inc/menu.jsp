<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="description" content="Male_Fashion Template">
    <meta name="keywords" content="Male_Fashion, unica, creative, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Male-Fashion | Template</title>
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
            <div class="offcanvas__links">
                <a href="#">Sign in</a>
                <a href="#">FAQs</a>
            </div>
            <div class="offcanvas__top__hover">
                <span>Usd <i class="arrow_carrot-down"></i></span>
                <ul>
                    <li>USD</li>
                    <li>EUR</li>
                    <li>USD</li>
                </ul>
            </div>
        </div>
        <div class="offcanvas__nav__option">
            <a href="#" class="search-switch"><img src="${pageContext.request.contextPath }/resources/img/icon/search.png" alt=""></a>
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
								<a href = "${pageContext.request.contextPath }/login">
									로그인
								</a>
								<span>&nbsp;</span>				
								<a href = "${pageContext.request.contextPath }/logout">
									로그아웃
								</a>
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
													나의문의보기
												</a>
		                                    </li>
		                                    <li>			
												<a href = "${pageContext.request.contextPath }/question/addQuestion">
													고객센터 문의글작성
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
	                                    	<a href = "${pageContext.request.contextPath }/emp/empList">
												emp회원관리
											</a>
										</li>
	                                    <li>
	                                    	<a href = "${pageContext.request.contextPath }/customer/customerList">
												customer회원관리
											</a>
										</li>
	                                    <li>
	                                    	<a href = "${pageContext.request.contextPath }/emp/checkPw?targetUrl=/emp/modifyEmp">
												emp 정보 수정
											</a>
										</li>
	                                    <li>
	                                    	<a href = "${pageContext.request.contextPath }/emp/checkPw?targetUrl=/emp/modifyEmpPw">
												emp 비밀번호 변경
											</a>
										</li>
	                                    <li>
	                                    	<a href = "${pageContext.request.contextPath }/emp/checkPw?targetUrl=/emp/deleteEmp?empCode=${loginEmp.empCode }">
												emp 회원 탈퇴
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
                        <a href="./index.html"><img src="${pageContext.request.contextPath }/resources/img/logo.png" alt=""></a>
                    </div>
                </div>
                <div class="col-lg-6 col-md-6">
                    <nav class="header__menu mobile-menu">
                        <ul>
                            <li class="active"><a href="./index.html">Home</a></li>
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
                            <li><a href="${pageContext.request.contextPath }/resources//blog.html">Blog</a></li>
                            <li><a href = "${pageContext.request.contextPath }/review/reviewList">리뷰</a></li>
                        </ul>
                    </nav>
                </div>
                <div class="col-lg-3 col-md-3">
                    <div class="header__nav__option">
                        <a href="#" class="search-switch"><img src="${pageContext.request.contextPath }/resources/img/icon/search.png" alt=""></a>
                        <c:choose>
							<c:when test="${loginCustomer == null }">
								<a href = "${pageContext.request.contextPath }/cart/nonMemberCartList?action=cartList">
									<img src="${pageContext.request.contextPath }/resources/img/icon/cart.png" alt="">
									<!-- 카트 품목 수 -->
									<span>0</span>
								</a>								
							</c:when>
							<c:otherwise>
								<a href = "${pageContext.request.contextPath }/cart/customerCartList?action=cartList">
									<img src="${pageContext.request.contextPath }/resources/img/icon/cart.png" alt="">
									<!-- 카트 품목 수 -->
									<span>0</span>
								</a>
							</c:otherwise>
						</c:choose>
                        <div class="price">$0.00</div>
                    </div>
                </div>
            </div>
            <div class="canvas__open"><i class="fa fa-bars"></i></div>
        </div>
    </header>
    <!-- Header Section End -->
    
	<div>
		<div>
			<h3>
				<a href = "${pageContext.request.contextPath }/home">
					홈으로
				</a>
				<span>&nbsp;</span>
				<a href = "${pageContext.request.contextPath }/login">
					로그인
				</a>
				<span>&nbsp;</span>				
				<a href = "${pageContext.request.contextPath }/logout">
					로그아웃
				</a>				
				<span>&nbsp;</span>
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
				<c:if test="${loginCustomer != null}">
					<span>&nbsp;</span>
					<a href = "${pageContext.request.contextPath }/order/orderList">
						내주문
					</a>
				</c:if>
				
					<span>&nbsp;</span>
					
				<c:choose>
					<c:when test="${loginCustomer == null }">
						<a href = "${pageContext.request.contextPath }/cart/nonMemberCartList?action=cartList">
							장바구니(비회원)
						</a>
						
						<span>&nbsp;</span>
						
					</c:when>
					<c:otherwise>
						<a href = "${pageContext.request.contextPath }/cart/customerCartList?action=cartList">
							장바구니(회원)
						</a>
					
						<span>&nbsp;</span>
						
					</c:otherwise>
				</c:choose>					
				
				
				<a href = "${pageContext.request.contextPath }/notice/noticeList">
					공지사항
				</a>
				<span>&nbsp;</span>
				<a href = "${pageContext.request.contextPath }/review/reviewList">
					리뷰
				</a>
			</h3>
		</div>
		
		<c:if test="${loginCustomer != null}">
			<div>
				<h3>
					<a href = "${pageContext.request.contextPath }/customer/customerList">
						customer회원관리
					</a>				
					<span>&nbsp;</span>			
					<a href = "${pageContext.request.contextPath }/customer/addCustomer">
						customer회원가입
					</a>			
					<span>&nbsp;</span>				
					<a href = "${pageContext.request.contextPath }/customer/checkPw?targetUrl=/customer/modifyCustomer">
						customer 정보 수정
					</a>				
					<span>&nbsp;</span>			
					<a href = "${pageContext.request.contextPath }/customer/checkPw?targetUrl=/customer/modifyCustomerPw">
						customer 비밀번호 변경
					</a>				
					<span>&nbsp;</span>				
					<a href = "${pageContext.request.contextPath }/customer/checkPw?targetUrl=/customer/deleteCustomer?customerId=${loginCustomer.customerId }">
						customer 회원 탈퇴
					</a>				
					<a href = "${pageContext.request.contextPath }/customer/addressList">
						customer 배송지 관리
					</a>
				</h3>		
			</div>
		</c:if>
		
		<c:if test="${loginEmp != null}">
			<div>
				<h3>
					<a href = "${pageContext.request.contextPath }/emp/empList">
						emp회원관리
					</a>				
					<span>&nbsp;</span>			
					<a href = "${pageContext.request.contextPath }/emp/addEmp">
						emp회원가입
					</a>				
					<span>&nbsp;</span>				
					<a href = "${pageContext.request.contextPath }/emp/checkPw?targetUrl=/emp/modifyEmp">
						emp 정보 수정
					</a>				
					<span>&nbsp;</span>
					<a href = "${pageContext.request.contextPath }/emp/checkPw?targetUrl=/emp/modifyEmpPw">
						emp 비밀번호 변경
					</a>			
					<span>&nbsp;</span>				
					<a href = "${pageContext.request.contextPath }/emp/checkPw?targetUrl=/emp/deleteEmp?empCode=${loginEmp.empCode }">
						emp 회원 탈퇴
					</a>									
				</h3>
			</div>
		</c:if>
		
		<div>
			<h3>
				<a href = "${pageContext.request.contextPath }/question/questionList">
					고객센터
				</a>				
				<c:if test="${loginCustomer != null}">
					<span>&nbsp;</span>				
					<a href = "${pageContext.request.contextPath }/question/questionListUser">
						나의문의보기
					</a>
					<span>&nbsp;</span>				
					<a href = "${pageContext.request.contextPath }/question/addQuestion">
						고객센터 문의글작성
					</a>
				</c:if>
				
				<c:if test="${loginEmp != null}">	
					<span>&nbsp;</span>			
					<a href = "${pageContext.request.contextPath }/questionComment/questionCommentList">
						고객센터(관리자)
					</a>			
				</c:if>	
			</h3>
		</div>	
	</div>
</body>
</html>