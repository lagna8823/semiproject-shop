<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>OrderList</title>
</head>
<body>
	<h1>주문목록</h1>
	<table class="table">
		<tr>
			<th>주문번호</th>
			<th>상품번호</th>
			<th>고객번호</th>
			<th>주문수량</th>
			<th>상품가격</th>
			<th>상품가격</th>
			<th>주문상태</th>
			<th>주문일</th>
		</tr>		
		<c:forEach var="o" items="${odersList}">
			<tr>
				<td>${o.oderCode}</td>
				<td>${o.goodscode}</td>
				<td>${o.customerId}</td>
				<td>${o.addressCode}</td>
				<td>${o.orderQuantity}</td>
				<td>${o.orderPrice}</td>
				<td>${o.orderState}</td>
				<td>${o.createdate}</td>
			</tr>
		</c:forEach>
	</table>	
</body>
</html>