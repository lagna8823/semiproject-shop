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
	<title>OrderList</title>

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
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
	<script>
		$(document).ready(function(){
			$(function(){
			    parseInt($('.orderPrice').val(parseInt($('.sumGoodsPrice').text())));				
			})
			
			/* 입력 값 제한 */
			$( '#usePoint' ).on("input", function() {
			    let point = Number($('#point').val());
			    let usePoint = Number($('#usePoint').val());
			    let orderPrice = Number($('#orderPrice').val());
			    if (usePoint > point) {
			    	$('#usePoint').val(point) ;
			    	return;
			    }
			    if ( usePoint < -1 || usePoint == null || usePoint == '') {
			    	alert("포인트를 확인해주세요");
			    	$('#usePoint').val(0) ;
			    	return;
			    }
		    }); /* 	var b = a.replace(/(^0+)/, ""); */
	
			/* 결제가격 계산 */
			$( '#usePoint' ).on("blur", function() {
			    let sumGoodsPrice = parseInt($('.sumGoodsPrice').text());
			    console.log(sumGoodsPrice);
			    let usePoint = Number($('#usePoint').val());
			    console.log(usePoint);
			    let resultPrice = sumGoodsPrice - usePoint;
			    console.log(resultPrice);
			    parseInt($('.orderPrice').text(resultPrice + " 원"));
			    parseInt($('.orderPrice').val(resultPrice));
			    if(parseInt($('.sumGoodsPrice').text()) < usePoint || parseInt($('#usePoint').val()) < 0 || usePoint > Number(${point})){
			    	alert("입력값를 확인해주세요");		
			    	$('#usePoint').val(0);
			    }
		    });
			
			/* 주문일 계산 */
		    let now = new Date();
		    let year = ('0' + now.getFullYear()).slice(-2);
		    let month = ('0' + (now.getMonth() + 1)).slice(-2);
		    let day = ('0' + now.getDate()).slice(-2);
	
		    let today = year + '/' + month  + '/' + day;
		    $('#createdate').val(today); 
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
                        <h4>주문하기</h4>
                        <div class="breadcrumb__links">
                            <a href="./index.html">Home</a>
                            <a href="./shop.html">Shop</a>
                            <span>주문하기</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Breadcrumb Section End -->
	
	<form method="post" action="${pageContext.request.contextPath}/order/addOrder">
    <!-- AddOrder Section Begin -->
    <section class="shopping-cart spad">
        <div class="container">
			<!-- 고객아이디 : 로그인 후 받아옴  -->
			<input type="hidden" id="loginId" name="loginId" value="${loginId}">
			<!-- 주문상태 -->
			<input type="hidden" id="orderState" name="orderState" value="결제"> <!-- 일단 결제 -->		
			<!-- 고객 보유포인트 -->		
			<input type="hidden" id="point" name="point" value="${point}">
			<!-- 주문일 -->
			<input type="hidden" id="createdate" name=createdate>
				
            <div class="shopping__cart__table">
				<table>
					<thead>
                        <tr>
	                        <th class = "text-center">상품</th>
	                        <th>수량</th>
	                       	<th>합계</th>
                   		</tr>
                    </thead>
					<c:forEach var="g" items="${goodsList}">
						<!-- 상품번호 -->
						<input type="hidden" id="goodsCode" name="goodsCode" value="${g.goodsCode}" readonly>
                        <tbody>
                            <tr>
                                <td class="product__cart__item">
                    	                <div class="product__cart__item__pic imgCart">
                                     	<img src="${pageContext.request.contextPath}/upload/${g.filename}" alt="">
                                    </div>
                                    <div class="product__cart__item__text">
                                        <h6>${g.goodsName}</h6>
                                        <h5><i class="fa fa-won"></i> ${g.goodsPrice}</h5>
                                    </div>
                                </td>
                                <td class="quantity__item "><input type="number" name="orderQuantity" style="border:none;" value="${g.cartQuantity}"></td>
                                <td class="cart__price"><i class="fa fa-won"></i> ${g.goodsPrice * g.cartQuantity}</td>
                            </tr>
                        </tbody>
                    </c:forEach>
               	</table>
			</div>
		</div>
	</section>
    <!-- AddOrder Section End -->
    
    <!-- Checkout Section Begin -->
    <section class="checkout spad">
        <div class="container">
            <div class="checkout__form">
            	<div class="row">
                	<div class="col-lg-8 col-md-6">
                    	<h6 class="checkout__title">주문자 정보</h6>
                        	<div class="row">
                            	<div class="col-lg-6">
                                	<div class="checkout__input">
                                    	<p>이름<span>*</span></p>
                                    	<input type="text" id="customerName" name="customerName" value="${customerName}" readonly>
                                	</div>
                                </div>
                                <div class="col-lg-6">
                                	<div class="checkout__input">
                                    	<p>연락처<span>*</span></p>
                                    	<input type="text" id="customerPhone" name="customerPhone" value="${customerPhone}" readonly>
                                	</div>
                            	</div>
                            </div>
                            <div class="checkout__input">
                            	<p>배송지<span>*</span></p>
								<select id="addressCode" name="addressCode" style="width:100%;">
									<c:forEach var="ad" items="${customerAddress}">
										<option value="${ad.addressCode}">${ad.address}</option> <!-- 실제주소 text를 보여주고 주소코드로 값 처리 -->
									</c:forEach>
								</select>
                        	</div>
                        </div>                        
                        
                        <div class="col-lg-4 col-md-6">
                       	<div class="checkout__order">
                           	<h4 class="order__title">결제 정보</h4>
                           	<h6></h6>
                           	<ul class="checkout__total__products">
                               	<li>주문금액 <span class="sumGoodsPrice">${sumGoodsPrice} 원</span></li>
                               	<li>보유포인트 <span>${point} 원</span></li>
                               	<li>사용포인트 <span>
						    		<input type="number" id="usePoint" name="usePoint" min="0" max="${point}" style="text-align:right; border:none;" value="0">원
						    		</span>
                               	</li>
                           	</ul>
                           	<ul class="checkout__total__all">
                               	<li>최종결제 금액 <span class="orderPrice"></span>
                               	<input type="hidden" class="orderPrice" name="orderPrice" min="0" style="border:none;" value="${sumGoodsPrice}" readonly></li>
                           	</ul>
                           	<button type="submit" class="site-btn">PLACE ORDER</button>
                       	</div>
                   	</div>
               	</div>            
            </div>
        </div>
    </section>
    <!-- Checkout Section End -->
    </form>

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
