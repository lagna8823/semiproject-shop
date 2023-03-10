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
			$('#rowPerPage').change(function(){
				$('#pageForm').submit();
				alert('change')
			})
		});
	</script>
</head>
<body>
	<div>
		<jsp:include page = "/WEB-INF/view/inc/menu.jsp"></jsp:include>
	</div>

    <!-- Breadcrumb Section Begin -->
    <section class="breadcrumb-option">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="breadcrumb__text">
                        <h4>?????????</h4>
                        <div class="breadcrumb__links">
                            <a href="./index.html">Home</a>
                            <a href="./shop.html">Shop</a>
                            <span>?????????</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Breadcrumb Section End -->

    <!-- OrderOne Section Begin -->
    <section class="orderOne spad">
    	<div class = "container">
			<div class="d-flex justify-content-end">
				<form id="pageForm" method="get" action="${pageContext.request.contextPath}/order/orderList">
					<!-- n?????? ?????? ????????? -->
					<select class="nice-select" name="rowPerPage" id="rowPerPage">
						<c:if test="${rowPerPage == 10}">
							<option value="10" selected="selected">10??????</option>
							<option value="20">20??????</option>
							<option value="30">30??????</option>
						</c:if>
						<c:if test="${rowPerPage == 20}">
							<option value="10">10??????</option>
							<option value="20" selected="selected">20??????</option>
							<option value="30">30??????</option>
						</c:if>
						<c:if test="${rowPerPage == 30}">
							<option value="10">10??????</option>
							<option value="20">20??????</option>
							<option value="30" selected="selected">30??????</option>
						</c:if>
					</select>
				</form>
			</div>
		
			<!-- ???????????? -->
			<table class="table">
				<tr class = "text-center">
					<th>????????????</th><!--  -->
					<th>????????????</th><!--  -->
					<th>?????????</th><!--  -->
					
					<th>????????????(??????)</th><!--  -->
					<th colspan="2">????????????</th>
				</tr>
				<c:forEach var="o" items="${orderList}">
					<tr>
						<td>
							<c:if test="${o.filename ne null}">
								<img src="${pageContext.request.contextPath}/upload/${o.filename}" width="100" height="100">
							</c:if>
							<c:if test="${o.filename eq null}">
								<span>?????? ?????????!</span>
							</c:if>
							<br>
							<a type="button" href="${pageContext.request.contextPath}/goods/goodsOne?goodsCode=${o.goodsCode}">${o.goodsName}</a>
						</td>
						<td><a type="button" href="${pageContext.request.contextPath}/order/orderOne?orderCode=${o.orderCode}">${o.orderCode}</a></td>
						<td class="createdate">${o.createdate}</td>
						
						<td>${o.orderPrice}<br>${o.orderQuantity}???</td>
						<td>
							<a type="button" href="${pageContext.request.contextPath}/order/orderConfirm?orderCode=${o.orderCode}">????????????</a>
							<br>
							${o.orderState}
						</td>
						
						<c:choose>
							<c:when test="${o.orderState eq '????????????'}">
								<td><a type="button" href="${pageContext.request.contextPath}/review/addReview?orderCode=${o.orderCode}">????????????</a></td>
							</c:when>
							<c:otherwise>
								<td><a type="button" href="${pageContext.request.contextPath}/order/deleteOrder?orderCode=${o.orderCode}&point=${o.point}">????????????</a></td>
							</c:otherwise>
						</c:choose>
					</tr>
				</c:forEach>
			</table>
		</div>
		
		<!-- ????????? -->
		<nav aria-label="pagiantion">
			<ul class="pagination justify-content-center">
				<li class="page-item">
					<a class="page-link" href="${pageContext.request.contextPath}/order/orderList?rowPerPage=${rowPerPage}&currentPage=${1}&word=${word}" aria-label="Previous">
						<span aria-hidden="true">&laquo;</span>
					</a>
				</li>		      
				<c:if test="${lastPage - currentPage <= 0 and lastPage > 4}">
					<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/order/orderList?rowPerPage=${rowPerPage}&currentPage=${currentPage-4}&word=${word}">${currentPage-4}</a></li>
				</c:if>		    	
				<c:if test="${lastPage - currentPage <= 1 and lastPage > 3}">
			    	<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/order/orderList?rowPerPage=${rowPerPage}&currentPage=${currentPage-3}&word=${word}">${currentPage-3}</a></li>
		    	</c:if>		    	
				<c:if test="${currentPage >= 3}">
					<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/order/orderList?rowPerPage=${rowPerPage}&currentPage=${currentPage-2}&word=${word}">${currentPage-2}</a></li>
		    	</c:if>		    	
				<c:if test="${currentPage >= 2}">
					<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/order/orderList?rowPerPage=${rowPerPage}&currentPage=${currentPage-1}&word=${word}">${currentPage-1}</a></li>
		    	</c:if>
				<c:if test="${currentPage > 0}">
					<li class="page-item active"><a class="page-link" href="${pageContext.request.contextPath}/order/orderList?rowPerPage=${rowPerPage}&currentPage=${currentPage}&word=${word}">${currentPage}</a></li>
		    	</c:if>
				<c:if test="${lastPage - currentPage >= 1}">
					<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/order/orderList?rowPerPage=${rowPerPage}&currentPage=${currentPage+1}&word=${word}">${currentPage+1}</a></li>
		    	</c:if>
				<c:if test="${lastPage - currentPage >= 2}">
					<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/order/orderList?rowPerPage=${rowPerPage}&currentPage=${currentPage+2}&word=${word}">${currentPage+2}</a></li>
		    	</c:if>
				<c:if test="${currentPage <= 2 and lastPage > 3}">
		   	 		<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/order/orderList?rowPerPage=${rowPerPage}&currentPage=${currentPage+3}&word=${word}">${currentPage+3}</a></li>
		    	</c:if>
				<c:if test="${currentPage <= 1 and lastPage > 4}">
		    		<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/order/orderList?rowPerPage=${rowPerPage}&currentPage=${currentPage+4}&word=${word}">${currentPage+4}</a></li>
		    	</c:if>		    
			    <li class="page-item">
					<c:choose>
						<c:when test="${lastPage eq 0}">
							<a class="page-link" href="${pageContext.request.contextPath}/order/orderList?rowPerPage=${rowPerPage}&currentPage=${0}&word=${word}" aria-label="Next">
								<span aria-hidden="true">&raquo;</span>
							</a>
						</c:when>
						<c:otherwise>
							<a class="page-link" href="${pageContext.request.contextPath}/order/orderList?rowPerPage=${rowPerPage}&currentPage=${lastPage}&word=${word}" aria-label="Next">
								<span aria-hidden="true">&raquo;</span>
							</a>
						</c:otherwise>
					</c:choose>
				</li>
			</ul>
		</nav>
		
		<!-- ????????? -->
		<div class="d-flex justify-content-center pt-3">
			<form action="${pageContext.request.contextPath}/order/orderList" method="get">
				<div class="row">
					<div class="col-auto d-grid mx-auto">
						<c:choose>
							<c:when test="${word == null}"> 
								<input type="search" class="form-control-sm" name="word" id="word" placeholder="???????????? ??????????????????">
							</c:when>
							<c:otherwise>
								<input type="search" class="form-control-sm" name="word" id="word" placeholder="???????????? ??????????????????" value="${word}">
							</c:otherwise>
						</c:choose>
					</div>
					<div class="col-auto">
			        	<button type="submit" class="btn btn-primary">??????</button>
					</div>
				</div>
			</form>
		</div> 
    </section>
    <!-- OrderList Section End --> 

    <!-- Footer Section Begin -->
    <div>
		<jsp:include page = "/WEB-INF/view/inc/footer.jsp"></jsp:include>
	</div>
    <!-- Footer Section End -->

    <!-- Search Begin -->
    <div class="search-model">
        <div class="h-100 d-flex align-items-center justify-content-center">
            <div class="search-close-switch">+</div>
            <form class="search-model-form">
                <input type="text" id="search-input" placeholder="Search here.....">
            </form>
        </div>
    </div>
    <!-- Search End -->

    <!-- Js Plugins -->
    <script src="${pageContext.request.contextPath }/resources/js/jquery-3.3.1.min.js"></script>
    <script src="${pageContext.request.contextPath }/resources/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath }/resources/js/jquery.nicescroll.min.js"></script>
    <script src="${pageContext.request.contextPath }/resources/js/jquery.magnific-popup.min.js"></script>
    <script src="${pageContext.request.contextPath }/resources/js/jquery.countdown.min.js"></script>
    <script src="${pageContext.request.contextPath }/resources/js/jquery.slicknav.js"></script>
    <script src="${pageContext.request.contextPath }/resources/js/mixitup.min.js"></script>
    <script src="${pageContext.request.contextPath }/resources/js/owl.carousel.min.js"></script>
    <script src="${pageContext.request.contextPath }/resources/js/main.js"></script>
</body>
</html>