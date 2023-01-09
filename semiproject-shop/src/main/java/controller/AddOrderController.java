package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.CustomerAddressService;
import service.OrdersService;
import vo.Customer;
import vo.CustomerAddress;
import vo.Emp;
import vo.Orders;

@WebServlet("/order/addOrder")
public class AddOrderController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//String customerId = null; //id에 해당하는 주소 가져옴 + 폼에서 선택		
		
		/*
		// 로그인 여부확인
		HttpSession session = request.getSession();		
		// 로그인 값 체크  - 비 로그인 시 로그인 창으로
		Customer loginCustomer = (Customer)session.getAttribute("loginCustomer");
		if(loginCustomer == null) {
			response.sendRedirect(request.getContextPath()+"/login");
			System.out.println("로그인 값 없음");
			return;
		}
		System.out.println(loginCustomer+"loginCustomer");
		
		*/
		// 상품번호 ,고객아이디, 배송지 필요
		int goodsCode = 1; //Integer.parseInt(request.getParameter("goodsCode"));
		String loginId = "test"; //loginCustomer.getCustomerId();
		int addressCode = 1; //request.getParameter("addressCode");

		//CustomerAddress address= new CustomerAddress();
		CustomerAddressService customerAddressService = new CustomerAddressService();
		int address = addressCode; //customerAddressService.getCustomerAddress(loginId); // customerId

		System.out.println("goodsCode : " + goodsCode);
		System.out.println("customerId : " + loginId);
		System.out.println("addressCode : " + addressCode);
		
		// view와 공유할 모델데이터 설정
		request.setAttribute("goodsCode", goodsCode);
		request.setAttribute("loginId", loginId);
		request.setAttribute("address", addressCode);
		request.getRequestDispatcher("/WEB-INF/view/order/addOrderForm.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		/*
		// 로그인 여부확인
		HttpSession session = request.getSession();
		// 로그인 값 체크  - 비 로그인 시 로그인 창으로
		Customer loginCustomer = (Customer)session.getAttribute("loginCustomer");
		if(loginCustomer == null) {
			response.sendRedirect(request.getContextPath()+"/login");
			System.out.println("로그인 값 없음");
			return;
		}
		*/
		
		int goodsCode = Integer.parseInt(request.getParameter("goodsCode"));
		String customerId = "test"; //loginCustomer.getCustomerId();
		int addressCode = Integer.parseInt(request.getParameter("addressCode"));
		int orderQuantity = Integer.parseInt(request.getParameter("orderQuantity"));		
		int orderPrice = Integer.parseInt(request.getParameter("orderPrice"));
		String orderState = request.getParameter("orderState");
		String createdate = request.getParameter("createdate");
		
		Orders orders = new Orders();
		orders.setGoodsCode(goodsCode);
		orders.setCustomerId(customerId);
		orders.setAddressCode(addressCode);	
		
		orders.setOrderQuantity(orderQuantity);
		orders.setOrderPrice(orderPrice);
		orders.setOrderState(orderState);
		orders.setCreatedate(createdate);
		// 주문상태는 일단 결제
		
		// 모델호출
		OrdersService ordersService = new OrdersService();
		ordersService.addOrderService(orders);
		
		// view
		response.sendRedirect(request.getContextPath()+"/order/ordeList");
	}
}
