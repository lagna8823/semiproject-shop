package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.QuestionCommentService;
import vo.Emp;
import vo.Question;

@WebServlet("/questionComment/addQuestionComment")
public class AddQuestionCommentController extends HttpServlet {
	private QuestionCommentService questionCommentService;
	// 고객센터(관리자 페이지) 답변글 작성
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// VIEW -> /WEB-INF/view/question/addQuestion.jsp
		// 관리지만 진입가능(세션값 request)
		HttpSession session = request.getSession(); 
		
		// 관리자 체크
		Emp loginEmp = (Emp)session.getAttribute("loginEmp");
		if(loginEmp == null) {
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		
		// 매개변수, request 값세팅
		request.setCharacterEncoding("UTF-8"); // request 한글코딩	
		int questionCode = Integer.parseInt(request.getParameter("questionCode"));
		
		// 모델호출
		this.questionCommentService = new QuestionCommentService();
		HashMap<String, Object> list = questionCommentService.selectOrdersCode(questionCode);
		
		request.setCharacterEncoding("UTF-8");
		request.setAttribute("ordersCodeList", list);
		
		// 글작성 폼 View
		request.getRequestDispatcher("/WEB-INF/view/question/addQuestion.jsp").forward(request, response);
			
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}
}
