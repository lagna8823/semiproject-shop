<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

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
    <link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/font-awesome.min.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/elegant-icons.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/magnific-popup.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/nice-select.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/owl.carousel.min.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/slicknav.min.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/style.css" type="text/css">
    
	<!-- jQuery -->
	<!-- CDN 주소 추가 방식 -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
    
	<style>
		.imgCart {
			width : 150px;
			height : 150px;
		}
	</style>
   
    <script>
    

    
    
		$(document).ready(function() {
    

		    var proQty = $('.pro-qty-3');
		    
		    // quantityForm 의 goodsCode 숨기기
		    proQty.parent().find('.hide').hide();
		    
		    // < > 버튼으로 수량 조정할 때
		    proQty.on('click', '.qtybtn', function () {
		        var $button = $(this);
		        
		        // 디버깅
		        // console.log($button.parent());
		        
		        var oldValue = $button.parent().find('#quantity').val();
		        if ($button.hasClass('inc')) {
		        	// +버튼
		            var newVal = parseFloat(oldValue) + 1;
		        } else {
		        	// -버튼
		            if (oldValue > 1) {
		                var newVal = parseFloat(oldValue) - 1;
		            } else {
		                newVal = 1;
		            }
		        }
		        // 수량 조정 후 input에 값 대입
		        $button.parent().find('#quantity').val(newVal);

		        // quantity, goodsCode Form submit
		        $button.parent().submit();
		        
		        
		    });
			
		    
		    // 수량 input 직접 입력할 때 
		    proQty.find('#quantity').change(function () {
		    	let quantity = $(this);
		    	
		    	console.log(quantity.parent());
		    	
		    	quantity.parent().submit();
		    	
		    });
			

			
		});	    
    
    
    
    </script>
    
    
    
    
</head>

<body>
	<!-- 간이 메뉴바 -->
	<div>
		<jsp:include page = "/WEB-INF/view/inc/menu.jsp"></jsp:include>
	</div>
    <!-- Breadcrumb Section Begin -->
    <section class="breadcrumb-option">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="breadcrumb__text">
                        <h4>Shopping Cart</h4>
                        <div class="breadcrumb__links">
                            <a href="${pageContext.request.contextPath }/resources/index.html">Home</a>
                            <a href="${pageContext.request.contextPath }/resources/shop.html">Shop</a>
                            <span>Shopping Cart</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Breadcrumb Section End -->

    <!-- Shopping Cart Section Begin -->
    <section class="shopping-cart spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-8">
                    <div class="shopping__cart__table">
                        <table>
                            <thead>
                                <tr>
                                    <th>Product</th>
                                    <th>Quantity</th>
                                    <th>Total</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                            	회원(나중에 지우기)
                            	<!-- ========================================================= -->
                            	<!-- ========================================================= -->
                            	<!-- ========================================================= -->
                            	<!-- 모든 장바구니 항목 sumTotalPrice (최종 가격) 초기화 -->
                            	<c:set var="sumTotalPrice" value="0"></c:set>
                            	
                            	<!-- 항목당 수량 계산한 totalPrice 초기화 -->
                            	<c:set var="totalPrice" value="0"></c:set>
                            	<c:forEach var="cart" items="${customerCartList }" varStatus="i">
	                                <tr>
	                                    <td class="product__cart__item">
	                                        <div class="product__cart__item__pic imgCart">
	                                            <img src="${pageContext.request.contextPath }/upload/${cart.filename }" alt="">
	                                        </div>
	                                        <div class="product__cart__item__text">
	                                            <h6>상품명 : ${cart.goodsName }</h6>
	                                            <h5>
													<span class = "price">
														<i class="fa fa-won"></i> ${cart.goodsPrice }
													</span>
												</h5>
	                                        </div>
	                                    </td>
	                                    <td class="quantity__item">
	                                        <div class="quantity">
	                                            <div class="pro-qty-3">
													<form action = "${pageContext.request.contextPath }/cart/customerCartList?action=modifyQuantity" method = "post">
		                                            	<span id = "btnDec" class="fa fa-angle-left dec qtybtn"></span>
														<input type = "text" name = "goodsCode" class = "hide" value = "${cart.goodsCode }">
														<input type = "text" name = "quantity" id = "quantity" value = "${cart.cartQuantity }">
														<span id = "btnInc" class="fa fa-angle-right inc qtybtn"></span>
													</form>
															
													<!-- totalPrcie 구하기 위해 cart.cartQuantity jstl 변수로 설정 -->
													<c:set var="cartQuantity" target="cartQuantity" value="${cart.cartQuantity }"></c:set>													
	                                            </div>
	                                        </div>
	                                    </td>
	                                    <td class="cart__price">
											<span class = "totalPrice">
												<c:set var="totalPrice" value="${cartQuantity * cart.goodsPrice }"></c:set>
												<i class="fa fa-won"></i> ${totalPrice }
												
												<!-- sumTotalPrice 계산 -->
												<c:set var="sumTotalPrice" value="${sumTotalPrice + totalPrice }"></c:set>
											</span>
										</td>
	                                    <td class="cart__close"><i class="fa fa-close" 
                                   									onClick="location.href='${pageContext.request.contextPath}/cart/customerCartList?action=deleteCart&goodsCode=${cart.goodsCode }'"
                                   									style = "cursor : pointer;"></i></td>
	                                </tr>
                                </c:forEach>
                            	<!-- ========================================================= -->
                            	<!-- ========================================================= -->
                            	<!-- ========================================================= -->
                            </tbody>
                        </table>
                    </div>
                    <div class="row">
                        <div class="col-lg-6 col-md-6 col-sm-6">
                            <div class="continue__btn">
                                <a href="${pageContext.request.contextPath }/goods/goodsList">Continue Shopping</a>
                            </div>
                        </div>
                        <div class="col-lg-6 col-md-6 col-sm-6">
                            <div class="continue__btn update__btn">
                                <a href="${pageContext.request.contextPath }/cart/customerCartList?action=cartList"><i class="fa fa-spinner"></i> Update cart</a>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4">
                    <div class="cart__discount">
                        <h6>Discount codes</h6>
                        <form action="#">
                            <input type="text" placeholder="Coupon code">
                            <button type="submit">Apply</button>
                        </form>
                    </div>
                    <div class="cart__total">
                        <h6>Cart total</h6>
                        <ul>
                            <li>Total <span><i class="fa fa-won"></i> ${sumTotalPrice }</span></li>
                        </ul>                       
                        	<a type="button" class="primary-btn" href = "${pageContext.request.contextPath }/cart/customerCartList?action=doOrder">Proceed to checkout</a>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Shopping Cart Section End -->
	
	<!-- Footer Section Begin -->
    <div>
		<jsp:include page = "/WEB-INF/view/inc/footer.jsp"></jsp:include>
	</div>
    <!-- Footer Section End -->

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