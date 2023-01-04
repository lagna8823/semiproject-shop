package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.OrdersService;
import vo.Orders;

@WebServlet("/order/addOrder")
public class AddOrderController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		request.getRequestDispatcher("/WEB-INF/view/addOrderForm.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		int orderQuantity = 0;
		int orderPrice = 0;
		String orderState = null;
		String createdate = null;
		
		orderQuantity = Integer.parseInt(request.getParameter("orderQuantity"));
		orderPrice = Integer.parseInt(request.getParameter("orderPrice"));
		orderState = request.getParameter("orderState");
		createdate = request.getParameter("createdate");

		/* 나중에 처리
		if(boardTitle == null || boardContent == null || "".equals(boardTitle) || "".equals(boardContent)) {
			response.sendRedirect(request.getContextPath()+"/board/addBoard?boardTitle="+boardTitle+"&boardContent="+boardContent);
			return;
		} else {
		*/
		
		Orders orders = new Orders();
		orders.setOrderQuantity(orderQuantity);
		orders.setOrderPrice(orderPrice);
		orders.setOrderState(orderState);
		orders.setCreatedate(createdate);
		
		// 모델호출
		OrdersService ordersService = new OrdersService();
		ordersService.insertOrderService(orders);
		
		// view
		response.sendRedirect(request.getContextPath()+"/order/ordeList");
	}
}
