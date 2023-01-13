package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.NoticeService;
import vo.Notice;

@WebServlet("/notice/deleteNotice")
public class DeleteNoticeController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8"); // 한글 인코딩
		
		// 값 받아오기
		int noticeCode = Integer.parseInt(request.getParameter("noticeCode"));
		
		Notice notice = new Notice();
		notice.setNoticeCode(noticeCode);
		
		NoticeService noticeService = new NoticeService();
		int row = noticeService.deleteNotice(notice);
		if(row == 1) {
			System.out.println("상품 삭제 성공!");
		} else {
			System.out.println("상품 삭제 실패!");
		}
		response.sendRedirect(request.getContextPath()+"/notice/noticeList");
	}

}
