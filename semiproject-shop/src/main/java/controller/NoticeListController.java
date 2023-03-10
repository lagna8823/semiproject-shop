package controller;

import java.io.IOException;



import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.NoticeService;
import vo.Notice;


@WebServlet("/notice/noticeList")
public class NoticeListController extends HttpServlet {
	private NoticeService noticeService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int currentPage = 1;
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		int rowPerPage = 10;
		int beginRow = (currentPage-1) * rowPerPage;
		
		int page = 5;
		int startPage = ((currentPage - 1) / page) * page + 1;
		int endPage = startPage + page - 1;
		NoticeService noticeService = new NoticeService();
		ArrayList<Notice> list = null;
		int totalCnt = 0;
		
		list = noticeService.getNoticeList(beginRow, rowPerPage);
		// System.out.println(list+"<-list");
		totalCnt = noticeService.noticeCount();
		// System.out.println(totalCnt+"<-totalCnt값");
		
		int lastPage = totalCnt / rowPerPage;
		System.out.println(totalCnt + " <-- totalCnt");
		if(totalCnt % rowPerPage != 0) {
			lastPage++;
		}

		request.setAttribute("list", list);
		request.setAttribute("beginRow", beginRow);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("lastPage", lastPage);
		request.setAttribute("page", page);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.getRequestDispatcher("/WEB-INF/view/notice/noticeList.jsp").forward(request, response);

	}

}
