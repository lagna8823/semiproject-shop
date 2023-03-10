<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
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
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
		<script>
			$(document).ready(function(){
				$('#category').change(function(){
					$('.pageForm').submit();
					alert('change')
				})
				$('#searchBtn').click(function(){
					$('.pageForm').submit();
					alert('search')
				})
			});
		</script>
	</head>

	<body>
	    <div>
			<jsp:include page = "/WEB-INF/view/inc/menu.jsp"></jsp:include>
		</div>	
	    <!-- Header Section End -->
	
	    <!-- Breadcrumb Section Begin -->
	    <section class="breadcrumb-option">
	        <div class="container">
	            <div class="row">
	                <div class="col-lg-12">
	                    <div class="breadcrumb__text">
	                        <h4>Shop</h4>
	                        <div class="breadcrumb__links">
	                            <a href="./index.html">Home</a>
	                            <span>Shop</span>
	                        </div>
	                    </div>
	                </div>
	            </div>
	        </div>
	    </section>
	    <!-- Breadcrumb Section End -->
	
	    <!-- Shop Section Begin -->
	    <section class="shop spad">
	        <div class="container">
	            <div class="row">	            
	                <div class="col-lg-3">
	                    <div class="shop__sidebar">
	                        <div class="shop__sidebar__search">
	                        	<form method="get" action="${pageContext.request.contextPath}/goods/goodsList" class="pageForm">
	                            	<input type="search" name="searchWord" id="searchWord" value="${searchWord}" placeholder="Search...">
	                            	<button type="submit"><span class="icon_search"></span></button>
								</form>
								<div>
									<c:if test="${loginEmp != null}">
										<button type="button" class="btn btn-lg btn-primary btn-block mt-4" onclick="location.href='${pageContext.request.contextPath}/goods/addGoods'">
											상품추가
										</button>
									</c:if>
								</div>
	                        </div>
	                    </div>
	                </div> 
	    
	                <div class="col-lg-9">
	                    <div class="shop__product__option">
	                        <div class="row">
	                            <div class="col-lg-12 col-md-12 col-sm-12">
	                                <div>
	                        			<form method="get" action="${pageContext.request.contextPath}/goods/goodsList" class="pageForm">
		                                    <span>Showing ${beginRow}–${endRow} of ${totalCnt} results</span>
	                                    		<span style="float: right;">Sort by :
			                                    <select class="nice-select" name="category" id="category">
													<c:if test="${category eq ''}">
														<option value="" selected="selected">==선택==</option>
														<option value="dPrice">낮은가격순</option>
														<option value="uPrice">높은가격순</option>
														<option value="nCreatedate">최신등록순</option>
														<option value="nHit">판매인기순</option>
													</c:if>
													<c:if test="${category eq 'dPrice'}">
														<option value="">==선택==</option>
														<option value="dPrice" selected="selected">낮은가격순</option>
														<option value="uPrice">높은가격순</option>
														<option value="nCreatedate">최신등록순</option>
														<option value="nHit">판매인기순</option>
													</c:if>
													<c:if test="${category eq 'uPrice'}">
														<option value="">==선택==</option>
														<option value="dPrice">낮은가격순</option>
														<option value="uPrice" selected="selected">높은가격순</option>
														<option value="nCreatedate">최신등록순</option>
														<option value="nHit">판매인기순</option>
													</c:if>
													<c:if test="${category eq 'nCreatedate'}">
														<option value="">==선택==</option>
														<option value="dPrice">낮은가격순</option>
														<option value="uPrice">높은가격순</option>
														<option value="nCreatedate" selected="selected">최신등록순</option>
														<option value="nHit">판매인기순</option>
													</c:if>
													<c:if test="${category eq 'nHit'}">
														<option value="">==선택==</option>
														<option value="dPrice">낮은가격순</option>
														<option value="uPrice">높은가격순</option>
														<option value="nCreatedate">최신등록순</option>
														<option value="nHit" selected="selected">판매인기순</option>
													</c:if>
												</select>
											</span>
										</form>
									</div>
	                            </div>
	                    	</div>
		                    <div class="row">
		                    	<c:forEach var="m" items="${list}" varStatus="s">
		                        	<div class="col-lg-4 col-md-6 col-sm-6">
		                            	<div class="product__item">
		                                	<div class="product__item__pic set-bg" data-setbg="${pageContext.request.contextPath}/upload/${m.filename}">
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
		                                    	<div><fmt:formatNumber value="${m.goodsPrice}" pattern="#,###"/>원</div>	                                 
		                                	</div>
		                            	</div>
		                        	</div>
		                    	</c:forEach>
		                	</div>
		                </div>
		                <div class="row">
		                    <div class="col-lg-10">
		                        <div class="product__pagination">
		                            <div>
										<c:choose>
											<c:when test="${searchWord == null || searchWord.equals('')}"> <!-- 검색값이 없다면 -->
												<a href="${pageContext.request.contextPath}/goods/goodsList?currentPage=1&category=${category}">first</a>		
												<c:if test="${currentPage > 1}">
													<a href="${pageContext.request.contextPath}/goods/goodsList?currentPage=${currentPage-1}&category=${category}">&lt;</a>
												</c:if>	
												<span>${currentPage}</span>	
												<c:if test="${currentPage < lastPage}">
													<a href="${pageContext.request.contextPath}/goods/goodsList?currentPage=${currentPage+1}&category=${category}">&gt;</a>
												</c:if>	
												<a href="${pageContext.request.contextPath}/goods/goodsList?currentPage=${lastPage}&category=${category}">last</a>
											</c:when>
											<c:otherwise> <!-- 검색값이 있다면 -->
												<a href="${pageContext.request.contextPath}/goods/goodsList?searchWord=${searchWord}&category=${category}&currentPage=1">first</a>		
												<c:if test="${currentPage > 1}">
													<a href="${pageContext.request.contextPath}/goods/goodsList?searchWord=${searchWord}&category=${category}&currentPage=${currentPage-1}">&lt;</a>
												</c:if>	
												<span>${currentPage}</span>	
												<c:if test="${currentPage < lastPage}">
													<a href="${pageContext.request.contextPath}/goods/goodsList?searchWord=${searchWord}&category=${category}&currentPage=${currentPage+1}">&gt;</a>
												</c:if>	
												<a href="${pageContext.request.contextPath}/goods/goodsList?searchWord=${searchWord}&category=${category}&currentPage=${lastPage}">last</a>
											</c:otherwise>
										</c:choose>
									</div>
		                        </div>
		                    </div>
		                </div>
		        	</div>
	            </div>
	        </div>
	    </section>
	    <!-- Shop Section End -->
	
	    <!-- Footer Section Begin -->
		<jsp:include page = "/WEB-INF/view/inc/footer.jsp"></jsp:include>
	    <!-- Footer Section End -->
	
	    <!-- Js Plugins -->
	    <script src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
	    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
	    <script src="${pageContext.request.contextPath}/resources/js/jquery.nicescroll.min.js"></script>
	    <script src="${pageContext.request.contextPath}/resources/js/jquery.magnific-popup.min.js"></script>
	    <script src="${pageContext.request.contextPath}/resources/js/jquery.countdown.min.js"></script>
	    <script src="${pageContext.request.contextPath}/resources/js/jquery.slicknav.js"></script>
	    <script src="${pageContext.request.contextPath}/resources/js/mixitup.min.js"></script>
	    <script src="${pageContext.request.contextPath}/resources/js/owl.carousel.min.js"></script>
	    <script src="${pageContext.request.contextPath}/resources/js/main.js"></script>
	</body>

</html>