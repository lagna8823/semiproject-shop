<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 숫자 표시에 콤마 찍기위한 포맷 -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="zxx">

	<head>
	    <meta charset="UTF-8">
	    <meta name="description" content="Male_Fashion Template">
	    <meta name="keywords" content="Male_Fashion, unica, creative, html">
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	    <meta http-equiv="X-UA-Compatible" content="ie=edge">
	    <title>Male-Fashion | Template</title>
	
	    <!-- Google Font -->
	    <link href="https://fonts.googleapis.com/css2?family=Nunito+Sans:wght@300;400;600;700;800;900&display=swap"
	    rel="stylesheet">
	
	    <!-- Css Styles -->
	    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" type="text/css">
	    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/font-awesome.min.css" type="text/css">
	    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/elegant-icons.css" type="text/css">
	    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/magnific-popup.css" type="text/css">
	    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/nice-select.css" type="text/css">
	    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/owl.carousel.min.css" type="text/css">
	    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/slicknav.min.css" type="text/css">
	    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" type="text/css">
	</head>

	<body>
	    <div>
			<jsp:include page = "/WEB-INF/view/inc/menu.jsp"></jsp:include>
		</div>
	
	    <!-- Shop Details Section Begin -->
		<c:forEach var="m" items="${list}" varStatus="s">
		    <section class="shop-details">
		        <div class="product__details__pic">
		            <div class="container">
		                <div class="row">
		                    <div class="col-lg-12">
		                        <div class="product__details__breadcrumb">
		                            <a href="./index.html">Home</a>
		                            <a href="./shop.html">Shop</a>
		                            <span>Product Details</span>
		                        </div>
		                    </div>
		                </div>
		                <div class="row">
		                    <div class="col-lg-12 col-md-9">
		                        <div class="tab-content">
		                            <div class="tab-pane active" id="tabs-1" role="tabpanel">
		                                <div class="product__details__pic__item">
		                                    <img src="${pageContext.request.contextPath}/upload/${m.filename}" alt="">
		                                </div>
		                            </div>                          
		                        </div>
		                    </div>
		                </div>
		            </div>
		        </div>
		        <div class="product__details__content">
		            <div class="container">
		                <div class="row d-flex justify-content-center">
		                    <div class="col-lg-8">
		                        <div class="product__details__text">
		                            <h4>${m.goodsName}</h4>
		                            <h3><fmt:formatNumber value="${m.goodsPrice}" pattern="#,###"/>원</h3>
		                            <p>${m.goodsMemo}</p>
									
									<form method="get" action="${pageContext.request.contextPath}/order/addOrder">									
			                            <div class="product__details__cart__option">	                 
			                                <!-- 상품 품절상태일 경우 메세지 출력 -->
											<c:if test="${m.soldout eq 'Y'}">
												<span style=color:red;><strong>죄송합니다! 상품이 품절되었습니다.</strong></span>
											</c:if>
			                                <c:if test="${m.soldout eq 'N'}">
			                                <div class="quantity">
			                                    <div class="pro-qty">
			                                        <input type="text" name="cartQuantity" value="1">
			                                        <input type="hidden" name="goodsCode" value="${m.goodsCode}">
			                                    </div>
			                                </div>
			                                <!-- 장바구니(비회원, 회원) 분기 -->
			                                <div class="product__details__btns__option">
			                                <c:choose>
												<c:when test="${loginCustomer == null }">
													<a href="${pageContext.request.contextPath }/cart/nonMemberCartList?action=addCart&goodsCode=${m.goodsCode}"><i class="fa fa-heart"></i> Add To Cart</a>
												</c:when>
												<c:otherwise>
													<a href="${pageContext.request.contextPath }/cart/customerCartList?action=addCart&goodsCode=${m.goodsCode}"><i class="fa fa-heart"></i> Add To Cart</a>
												</c:otherwise>
											</c:choose>	                                         	
			                            	</div>										
												<button type="submit" class="primary-btn">Buy Now</button>		
											</c:if>
			                            </div>		                            
		                        	</form>
		                        	
		                            <div class="product__details__last__option">
		                                <h5><span>Guaranteed Safe Checkout</span></h5>
		                                <img src="${pageContext.request.contextPath}/resources/img/shop-details/details-payment.png" alt="">
		                            </div>
		                        </div>
		                    </div>
		                </div>
		                <div class="row">
		                    <div class="col-lg-12">
		                        <div class="product__details__tab">
		                            <ul class="nav nav-tabs" role="tablist">
		                                <li class="nav-item">
		                                    <a class="nav-link active" data-toggle="tab" href="#tabs-5"
		                                    role="tab">Description</a>
		                                </li>
		                                <li class="nav-item">
		                                    <a class="nav-link" data-toggle="tab" href="#tabs-6" role="tab">Customer
		                                    Previews(5)</a>
		                                </li>
		                                <li class="nav-item">
		                                    <a class="nav-link" data-toggle="tab" href="#tabs-7" role="tab">배송,환불,AS,기타</a>
		                                </li>
		                            </ul>
		                            <div class="tab-content">
		                                <div class="tab-pane active" id="tabs-5" role="tabpanel">
		                                    <div class="product__details__tab__content">
		                                        <div class="product__details__tab__content__item">
		                                            <h5>조립 과정</h5>
		                                            <p><img src="${pageContext.request.contextPath}/upload/recom.jpg"></p>
		                                        </div>
		                                    </div>
		                                </div>
		                                <div class="tab-pane" id="tabs-6" role="tabpanel">
		                                    <div class="product__details__tab__content">
		                                        <div class="product__details__tab__content__item">
		                                            <h5>Products Infomation</h5>
		                                            <p>A Pocket PC is a handheld computer, which features many of the same
		                                                capabilities as a modern PC. These handy little devices allow
		                                                individuals to retrieve and store e-mail messages, create a contact
		                                                file, coordinate appointments, surf the internet, exchange text messages
		                                                and more. Every product that is labeled as a Pocket PC must be
		                                                accompanied with specific software to operate the unit and must feature
		                                            a touchscreen and touchpad.</p>
		                                            <p>As is the case with any new technology product, the cost of a Pocket PC
		                                                was substantial during it’s early release. For approximately $700.00,
		                                                consumers could purchase one of top-of-the-line Pocket PCs in 2003.
		                                                These days, customers are finding that prices have become much more
		                                                reasonable now that the newness is wearing off. For approximately
		                                            $350.00, a new Pocket PC can now be purchased.</p>
		                                        </div>
		                                        <div class="product__details__tab__content__item">
		                                            <h5>Material used</h5>
		                                            <p>Polyester is deemed lower quality due to its none natural quality’s. Made
		                                                from synthetic materials, not natural like wool. Polyester suits become
		                                                creased easily and are known for not being breathable. Polyester suits
		                                                tend to have a shine to them compared to wool and cotton suits, this can
		                                                make the suit look cheap. The texture of velvet is luxurious and
		                                                breathable. Velvet is a great choice for dinner party jacket and can be
		                                            worn all year round.</p>
		                                        </div>
		                                    </div>
		                                </div>
		                                <div class="tab-pane" id="tabs-7" role="tabpanel">
		                                    <div class="product__details__tab__content">
		                                      
		                                        <div class="product__details__tab__content__item">
		                                            <h5>배송,환불,AS,기타</h5>
		                                            <p><img src="${pageContext.request.contextPath}/upload/AS.png"></p>
		                                        </div>
		                                    </div>
		                                </div>
		                            </div>
		                        </div>
		                    </div>
		                </div>
		            </div>
		        </div>
		    </section>
	    </c:forEach>
	    <!-- Shop Details Section End -->
	
	    <!-- Related Section Begin -->
	    <section class="related spad">
	        <div class="container">
	            <div class="row">
	                <div class="col-lg-12">
	                    <h3 class="related-title">Hot Product</h3>
	                </div>
	            </div>
	            <div class="row">
	            <c:forEach var="m" items="${topList}">
	                <div class="col-lg-3 col-md-6 col-sm-6 col-sm-6">
	                    <div class="product__item">
	                        <div class="product__item__pic set-bg" data-setbg="${pageContext.request.contextPath}/upload/${m.filename}">
	                            <span class="label">Hot</span>
	                            <ul class="product__hover">
									<c:if test="${loginEmp != null}">
										<li><a href="${pageContext.request.contextPath}/emp/checkPw?targetUrl=/goods/deleteGoods?goodsCode=${m.goodsCode}"><img src="${pageContext.request.contextPath}/resources/img/icon/trash.png" alt=""> <span>Delete</span></a></li>
										<li><a href="${pageContext.request.contextPath}/goods/modifyGoods?goodsCode=${m.goodsCode}"><img src="${pageContext.request.contextPath}/resources/img/icon/compare.png" alt=""> <span>Update</span></a></li>
									</c:if>                                        	
	 								<li><a href="${pageContext.request.contextPath}/goods/goodsOne?goodsCode=${m.goodsCode}"><img src="${pageContext.request.contextPath}/resources/img/icon/search.png" alt=""></a></li>
	                            </ul>
	                        </div>
	                        <div class="product__item__text">
	                            <h6>${m.goodsName}</h6>
	                            <!-- 장바구니(비회원, 회원) 분기 -->
	                            <c:if test="${m.soldout eq 'N'}">								
									<c:choose>
										<c:when test="${loginCustomer == null }">
											<a href="${pageContext.request.contextPath }/cart/nonMemberCartList?action=addCart&goodsCode=${m.goodsCode }" class="add-cart">+ Add To Cart</a>
										</c:when>
										<c:otherwise>
											<a href="${pageContext.request.contextPath }/cart/customerCartList?action=addCart&goodsCode=${m.goodsCode }" class="add-cart">+ Add To Cart</a>
										</c:otherwise>
									</c:choose>					
								</c:if>
	                            <h5><fmt:formatNumber value="${m.goodsPrice}" pattern="#,###"/>원</h5>
	                        </div>
	                    </div>
	                </div>                                          
	                </c:forEach>
	            </div>
	        </div>
	    </section>
	    <!-- Related Section End -->
	
	    <!-- Footer Section Begin -->
	    <div>
			<jsp:include page = "/WEB-INF/view/inc/footer.jsp"></jsp:include>
		</div>
	    <!-- Footer Section End -->	
	
	    <!-- Js Plugins -->
	    <script src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
	    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
	    <script src="${pageContext.request.contextPath}/resources/js/jquery.nice-select.min.js"></script>
	    <script src="${pageContext.request.contextPath}/resources/js/jquery.nicescroll.min.js"></script>
	    <script src="${pageContext.request.contextPath}/resources/js/jquery.magnific-popup.min.js"></script>
	    <script src="${pageContext.request.contextPath}/resources/js/jquery.countdown.min.js"></script>
	    <script src="${pageContext.request.contextPath}/resources/js/jquery.slicknav.js"></script>
	    <script src="${pageContext.request.contextPath}/resources/js/mixitup.min.js"></script>
	    <script src="${pageContext.request.contextPath}/resources/js/owl.carousel.min.js"></script>
	    <script src="${pageContext.request.contextPath}/resources/js/main.js"></script>
	</body>

</html>