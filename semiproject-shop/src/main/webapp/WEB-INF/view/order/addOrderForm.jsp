<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>AddOrderForm</title>
<!-- jQuery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<script>
	$(document).ready(function(){
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
	<h1>주문</h1>
	<form method="post" action="${pageContext.request.contextPath}/order/addOrder">
		<table class="table" border="1">
			<tr>
				<th>상품번호</th>	<!-- goods에서 받아옴 -->
				<td><input type="text" id="goodsCode" name="goodsCode" value="${pram.goodsCode}" readonly></td>				
			</tr>
				<!-- 고객아이디 : 로그인 후 받아옴  -->
				<input type="hidden" id="loginId" name="loginId" value="${pram.loginId}">
			<tr>
				<th>배송지</th> <!-- customerAddress에서 받아옴 -->
				<td>
					<select id="addressCode" name="addressCode">
						<c:forEach var="ad" items="${pram.address}">
							<option value="${ad.addressCode}">${ad.address}</option> <!-- 실제주소 text를 보여주고 주소코드로 값 처리 -->
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<th>주문수량</th><!-- 추후 최대 수량 재고로 제한 -->
				<td><input type="number" id="orderQuantity" name="orderQuantity"></td>
			</tr>
			<tr>
				<th>상품가격</th>
				<td><input type="number" id="orderPrice" name="orderPrice"></td> <!-- goods에서 받아옴 -->
			</tr>
				<!-- 주문상태 -->
				<input type="hidden" id="orderState" name="orderState" value="결제"><!-- 일단 결제 -->
			<tr>
				<th>주문일</th>
				<td><input type="text" id="createdate" name="createdate" readonly></td>
			</tr>
		</table>
		<div>
			<button id="btnAdd" type="submit">작성완료</button>
		</div>
	</form>
</body>
</html>