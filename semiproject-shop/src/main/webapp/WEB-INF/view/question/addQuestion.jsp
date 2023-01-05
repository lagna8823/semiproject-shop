<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<header>
		<a href="${pageContext.request.contextPath}/logout">로그아웃</a>
		<a href="${pageContext.request.contextPath}/question/questionList">고객센터</a>
		<a href="${pageContext.request.contextPath}/question/questionCommentList">commnet</a>
	</header>
	<h2>문의글 작성</h2>
	<div>
		<!-- 문의글 작성 페이지-->
		<div>
		<form action="${pageContext.request.contextPath}/question/addQuestion" method="post">
			<table border="1">
				<tr>
					<th>주문번호</th>
					<td>
						<select name="ordersCode" id="ordersCode">
							<c:forEach var="q" items="${ordersCodeList}">
								<option value="${q.orderCode}" >${q.orderCode}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<th>카테고리</th>
					<td>
						<select name="category" id="category">
							<option value="배송" >배송</option>
							<option value="반품" >반품</option>
							<option value="교환" >교환</option>
							<option value="기타" >기타</option>
						</select>
					</td>
				</tr>
				<tr>
					<th>문의내용</th>
					<td>
						<textarea id="questionMemo" rows="6" cols="80" name="questionMemo"></textarea>>
					</td>
				</tr>
			</table>
			<button type="submit">작성</button>
		</form>
		</div>
	</div>
</body>
</html>