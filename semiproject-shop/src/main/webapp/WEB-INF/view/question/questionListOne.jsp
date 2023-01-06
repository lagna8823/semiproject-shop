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
		<a href="${pageContext.request.contextPath}/question/questionCommentList">comment</a>
	</header>
	<h2>고객센터</h2>
	<div>
		<!-- 고객센터 내용 (분류/주문번호, 문의작성일, 문의내용, 답변일, 답변내용-->
		<div>
			<table border="0">
			<tr> 
				<th>category / orderCode</th>
				<td>${q.category}</td>
				<td>${q.orderCode}</td>
			</tr>
			<tr>
				<th>작성일</th>
				<td>${q.createdate}</td>
			</tr>
			<tr>
				<th>questionMemo</th>
				<td>${q.questionMemo}</td>
			</tr>
			<tr>
				<th>답변일</th>
				<td></td>
			</tr>
			<tr>
				<th>commentMemo</th>
				<td>${q.commentMemo}</td>
			</tr>
			</table>
		</div>
		<!-- 문의글 수정, 삭제 -->
		<div>
			<a href=" ">수정</a>
			<a href=" ">삭제</a>
		</div>
	</div>
</body>
</html>