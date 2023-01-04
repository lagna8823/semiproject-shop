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
				<th>주문번호</th>
				<th>상품번호</th>
				<th>고객번호</th>
				<th>주문수량</th>
				<th>상품가격</th>
				<th>상품가격</th>
				<th>주문상태</th>
				<th>주문일</th>
			</tr>
			<tr>
				<td><input type="text" id="createdate" name="createdate"></td>
				<td><input type="text" id="createdate" name="createdate"></td>
				<td><input type="text" id="createdate" name="createdate"></td>
				<td><input type="text" id="createdate" name="createdate"></td>
				<td><input type="text" id="createdate" name="createdate"></td>
				<td><input type="text" id="createdate" name="createdate"></td>
				<td><input type="text" id="createdate" name="createdate"></td>
				<td><input type="text" id="createdate" name="createdate"></td>
			</tr>
		</table>
		<div>		
			<button id="btnAdd" type="submit">작성완료</button>
		</div>
	</form>
</body>
</html>