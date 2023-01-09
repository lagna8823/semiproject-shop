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
		<a href="${pageContext.request.contextPath}/questionComment/questionCommentList">고객센터(관리자 페이지)</a>
	</header>
	<h2>고객센터(관리자 페이지)</h2>
	<div>
		<!-- 고객센터 내용 -->
		<div>
			<table border="0">
			<thead>
			<tr>
				<th>category</th>
				<th>questionMemo</th>
				<th>createdate</th>
				<th>상태</th>
				<th>작성자</th>
				<th>답변일</th>
				<th>답변 작성</th>
			</tr>
			</thead>
			<tbody>
				<c:forEach var="q" items="${questionlist}">
					<tr>
						<td>${q.category}</td>
						<td>
							${q.questionMemo}	
						</td>
						<td>${q.createdate}</td>
						<td>
							<c:if test="${q.commentMemo == null}">
								<a href="${pageContext.request.contextPath}/question/questionCommentOne?questionCode=${q.questionCode}">
									답변전
								</a>
							</c:if> 
							<c:if test="${q.commentMemo != null}">
								<a href="${pageContext.request.contextPath}/question/questionCemmentOne?questionCode=${q.questionCode}">
									답변완료
								</a>
							</c:if> 
						</td>
						<td>
							<c:if test="${q.empId == null}">
								&nbsp;
							</c:if> 
							<c:if test="${q.empId != null}">
								${q.empId}
							</c:if> 
						</td>
						<td>
							<c:if test="${q.commentCreatedate == null}">
								&nbsp;
							</c:if> 
							<c:if test="${q.commentCreatedate != null}">
								${q.commentCreatedate}
							</c:if> 
						</td>
						<td>
							<c:if test="${q.empId == null}">
								<a href="${pageContext.request.contextPath}/questionComment/addQuestioncomment?questionCode=${q.questionCode}">
									작성하기
								</a>
							</c:if> 
							<c:if test="${q.empId != null}">
								&nbsp;
							</c:if> 
						</td>
					</tr>	
				</c:forEach>
			</tbody>
			</table>
		</div>
		<br>
		<!-- 페이징 -->
		<div>
			<!-- 첫 페이지 -->
			<a href="${pageContext.request.contextPath}/questionComment/questionCommentList?currentPage=1">처음</a>
			
			<!-- 이전 페이지 -->
			<c:if test="${currentPage>1}">
				<a href="${pageContext.request.contextPath}/questionComment/questionCommentList?currentPage=${currentPage-1}">이전</a>
			</c:if>	
			
			<!-- 현재 페이지 -->
			<span>${currentPage}</span>
			
			<!-- 다음 페이지 -->
			<c:if test="${currentPage<lastPage}">
				<a href="${pageContext.request.contextPath}/questionComment/questionCommentList?currentPage=${currentPage+1}">다음</a>
			</c:if>	
			
			<!-- 마지막 페이지 -->
			<a href="${pageContext.request.contextPath}/questionComment/questionCommentList?currentPage=${lastPage}">마지막</a>
		</div>
	</div>
</body>
</html>