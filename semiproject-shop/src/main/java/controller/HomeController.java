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

import service.GoodsService;
import vo.Customer;
import vo.Emp;

@WebServlet("/home")
public class HomeController extends HttpServlet {	
	private GoodsService goodsService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		

		// 호출
		goodsService = new GoodsService();

		
		// hit 상품 리스트 초기화
		ArrayList<HashMap<String, Object>> topList = null;
		topList = goodsService.getItemListByTop();
		

		request.setAttribute("topList", topList);
		
		request.getRequestDispatcher("/WEB-INF/view/home.jsp").forward(request, response);		
	}
}
