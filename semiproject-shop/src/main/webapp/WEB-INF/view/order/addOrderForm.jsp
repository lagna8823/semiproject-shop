<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>AddOrderForm</title>
</head>
<body>
	<h1>주문</h1>
	<form method="post" action="${pageContext.request.contextPath}/order/addOrderForm">
		<table class="table">
			<tr>
				<th>상품번호</th>	<!-- goods에서 받아옴 -->
				<td><input type="text" id="goodscode" name="goodscode"></td>
			</tr>
			<tr>
				<th>고객아이디</th> <!-- 로그인 후 받아옴  -->
				<td><input type="hidden" id="customerId" name="customerId"></td>
			</tr>
			<tr>
				<th>배송지</th>
				<td><input type="text" id="address" name="address"></td> <!-- addresscode로 변환? select?-->
			</tr>
			<tr>
				<th>주문수량</th>
				<td><input type="text" id="orderQuantity" name="orderQuantity"></td>
			</tr>
			<tr>
				<th>상품가격</th>
				<td><input type="text" id="orderPrice" name="orderPrice"></td>
			</tr>
			<tr>
				<th>주문상태</th>
				<td><input type="hidden" id="orderState" name="orderState"></td> <!-- 일단 결제 -->
			</tr>
			<tr>
				<th>주문일</th>
				<td><input type="text" id="createdate" name="createdate"></td>
			</tr>
		</table>
		<div>		
			<button id="btnAdd" type="submit">작성완료</button>
		</div>
	</form>
</body>
</html>