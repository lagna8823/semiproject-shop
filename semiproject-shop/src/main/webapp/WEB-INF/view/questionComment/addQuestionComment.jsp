<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<style>
		body {
		  padding:1.5em;
		  background: #f5f5f5
		}
		
		table {
		  border: 1px #a39485 solid;
		  font-size: .9em;
		  box-shadow: 0 2px 5px rgba(0,0,0,.25);
		  width: 40%;
		  border-collapse: collapse;
		  border-radius: 5px;
		  overflow: hidden;
		}
		th {
		  border: 1px solid rgba(0,0,0,.1);
		  text-align: center;
		}
		  
		thead {
		  font-weight: bold;
		  color: #fff;
		}
		  
		 td, th {
		  padding: 1em .5em;
		  vertical-align: middle;
		}
		  
		 td {
		  border-bottom: 1px solid rgba(0,0,0,.1);
		  text-align: center
		}
		a {
		  text-decoration: none;
		}
	</style>
	</head>
	
	<body>
		<header>
			<a href="${pageContext.request.contextPath}/home">홈으로</a>
			<a href="${pageContext.request.contextPath}/logout">로그아웃</a>
			<a href="${pageContext.request.contextPath}/question/questionList">고객센터</a>
			<a href="${pageContext.request.contextPath}/question/questionList">나의문의</a>
			<a href="${pageContext.request.contextPath}/questionComment/questionCommentList">고객센터(관리자 페이지)</a>
		</header>
		<h2 align="center">답변 작성</h2>
		<div align="center" style="padding-right: 42em"> 
			<button onclick="history.back()">뒤로가기</button>
		</div>
		<br>
		<div>
			<!-- 문의글 작성 페이지-->
			<div align="center">
			<form action="${pageContext.request.contextPath}/questionComment/addQuestionComment" method="post">
				<table border>
				<input type="hidden" name="questionCode" value="${q.questionCode}">
					<!-- 사용자 문의 정보 -->
					<tr>
						<th>문의번호/카테고리</th>
						<td>
							${q.questionCode}/${q.category}
						</td>
					</tr>
					<tr>
						<th>주문번호/상품명</th>
						<td>
							${q.orderCode} : ${q.goodsName}
						</td>
					</tr>
					<tr>
						<th>문의날짜</th>
						<td>
							${q.createdate}
						</td>
					</tr>
					<tr>
						<th>문의내용</th>
						<td>
							${q.questionMemo}
						</td>
					</tr>
					<!-- 첨부 파일 있을시에만 보임 -->
					<c:if test="${q.questionImg == null}">
						<div>&nbsp;</div>
					</c:if> 
					<c:if test="${q.questionImg != null}">
						<tr>
							<th>첨부파일</th>
							<td>
								<div><img src="${pageContext.request.contextPath}/upload/${q.questionImg}" width="200" height="200"></div>
							</td>
						</tr>
					</c:if> 
					<!-- 문의에 대한 답변 작성란 -->
					<tr>
						<th>답변내용</th>
						<td>
							<textarea rows="6" cols="80" name="commentMemo"></textarea>
						</td>
					</tr>
				</table>
				<br>
				<button type="submit">답변 작성</button>
			</form>
			</div>
		</div>
	</body>
</html>