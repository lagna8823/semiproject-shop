package controller;

import java.io.IOException;


import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.GoodsService;

@WebServlet("/goods/goodsOne")
public class GoodsOneController extends HttpServlet {
	private GoodsService goodsService;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 값 받아오기
		int goodsCode = Integer.parseInt(request.getParameter("goodsCode"));
		
		// 호출
		goodsService = new GoodsService();
		ArrayList<HashMap<String, Object>> list = null;
		list = goodsService.getGoodsOne(goodsCode);
		System.out.println("list:"+list);
		
		// hit 상품 리스트 초기화
		ArrayList<HashMap<String, Object>> topList = null;
		topList = goodsService.getItemListByTop();
		
		for(HashMap<String, Object> hm : list) {
			
			request.setAttribute("soldout", (String) hm.get("soldout"));
		}
		
		request.setAttribute("list", list);
		request.setAttribute("topList", topList);
		
		request.getRequestDispatcher("/WEB-INF/view/goods/goodsOne.jsp").forward(request, response);
	}
}
