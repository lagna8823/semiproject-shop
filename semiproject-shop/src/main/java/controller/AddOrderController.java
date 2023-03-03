package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.GoodsService;
import service.OrdersService;
import vo.Cart;
import vo.Customer;
import vo.CustomerAddress;
import vo.Goods;
import vo.Orders;
import vo.PointHistory;

@WebServlet("/order/addOrder")
public class AddOrderController extends HttpServlet {
	private OrdersService ordersService;
	private GoodsService goodsService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 로그인 여부확인, 로그인 되어있지 않으면 홈으로 이동
		HttpSession session = request.getSession();		
		// 로그인 값 체크  - 비 로그인 시 로그인 창으로
		System.out.println("부검0");
		Customer loginCustomer = (Customer)session.getAttribute("loginCustomer");
		System.out.println(loginCustomer + "아이디");
		if(loginCustomer == null) {
			response.sendRedirect(request.getContextPath()+"/login");
			System.out.println("로그인 값 없음");
			return;
		}
		String customerId = loginCustomer.getCustomerId();	   	   
		
		Customer customer = null;
		List<Goods> goodsList = new ArrayList<Goods>();
		ArrayList<CustomerAddress> customerAddress = null;
		ArrayList<HashMap<String, Object>> cartList = null;
		int sumGoodsPrice = 0;
		//ArrayList<Goods> list = null;
		//ArrayList<Cart> cartQuantityList = null;
		
		this.ordersService = new OrdersService();
		customer = ordersService.getCustomerInfoForOrderService(customerId);
		customerAddress = ordersService.getCustomerAddressForOrderService(customerId);
		
		
		// 상품번호 ,고객아이디, 배송지 필요
		int goodsCode = 0;
		int cartQuantity = 0;
		// 단품 구매
		if(request.getParameter("goodsCode") != null) {
			System.out.println("부검1");
			goodsCode = Integer.parseInt(request.getParameter("goodsCode"));
			cartQuantity = Integer.parseInt(request.getParameter("cartQuantity"));
			goodsList.add(ordersService.getGoodsForOrder(goodsCode, cartQuantity));
			
			System.out.println("단품구매 goodsCode : " + goodsCode);
			System.out.println("단품구매 cartQuantity : " + cartQuantity);
			System.out.println("단품구매 goodsList : " + goodsList);
			

			// view와 공유할 모델데이터 설정
			request.setAttribute("goodsList", goodsList); //단품 goodsList, 장바구니 cartList
			request.setAttribute("loginId", customerId);
			request.setAttribute("customerName", customer.getCustomerName());
			request.setAttribute("customerPhone", customer.getCustomerPhone());
			request.setAttribute("customerAddress", customerAddress);
			request.setAttribute("point", customer.getPoint());
			request.setAttribute("sumGoodsPrice", goodsList.get(0).getGoodsPrice()); //단품 goodsPrice, 장바구니 sumGoodsPrice
			
		// 장바구니 구매
		} else if (session.getAttribute("customerCartList") != null) {
			System.out.println("부검2");
			cartList = (ArrayList<HashMap<String, Object>>) session.getAttribute("customerCartList");
			int goodsPrice = 0;
			for(int i=0; i < cartList.size() ;i++) {
				goodsPrice = (int) cartList.get(i).get("goodsPrice");
				cartQuantity = (int) cartList.get(i).get("cartQuantity");
				sumGoodsPrice += goodsPrice * cartQuantity;
			}
			System.out.println(goodsPrice + "goodsPrice");
			System.out.println(cartQuantity + "cartQuantity");				
			System.out.println(sumGoodsPrice + "sumGoodsPrice");	
			

			// view와 공유할 모델데이터 설정
			request.setAttribute("goodsList", cartList); //단품 goodsList, 장바구니 cartList
			request.setAttribute("loginId", customerId);
			request.setAttribute("customerName", customer.getCustomerName());
			request.setAttribute("customerPhone", customer.getCustomerPhone());
			request.setAttribute("customerAddress", customerAddress);
			request.setAttribute("point", customer.getPoint());
			request.setAttribute("sumGoodsPrice", sumGoodsPrice); //단품 goodsPrice, 장바구니 sumGoodsPrice
			
		}		
		/*
			if(cartList != null) {
	    		list = new ArrayList<Goods>();
	    		cartQuantityList = new ArrayList<Cart>();
	    		for(int i=0; i<cartList.size(); i+=1) {
	    			cart = new Cart();
	    			goodsCode = (int) cartList.get(i).get("goodsCode");
	    			cartQuantity = (int) cartList.get(i).get("cartQuantity");
	    			System.out.println(goodsCode + "굿즈코드");
	    			System.out.println(cartQuantity + "장바구니 수량");
		    		goods = ordersService.getGoodsForOrderService(goodsCode);
		    		cart.setCartQuantity(cartQuantity);
		    		list.add(goods);
		    		cartQuantityList.add(cart);
		    	}
		    }
		*/
		System.out.println("customer : " + customer);
		System.out.println("customerAddress : " + customerAddress);
		
		request.getRequestDispatcher("/WEB-INF/view/order/addOrderForm.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		request.setCharacterEncoding("UTF-8");

		// 로그인 여부확인
		HttpSession session = request.getSession();
		// 로그인 값 체크  - 비 로그인 시 로그인 창으로
		Customer loginCustomer = (Customer)session.getAttribute("loginCustomer");
		if(loginCustomer == null) {
			response.sendRedirect(request.getContextPath()+"/login");
			System.out.println("로그인 값 없음");
			return;
		}

		int goodsCode = Integer.parseInt(request.getParameter("goodsCode"));
		String customerId = loginCustomer.getCustomerId();
		int addressCode = Integer.parseInt(request.getParameter("addressCode"));
		int orderQuantity = Integer.parseInt(request.getParameter("orderQuantity"));
		int orderPrice = Integer.parseInt(request.getParameter("orderPrice"));
		String orderState = request.getParameter("orderState"); // 주문상태는 일단 결제
		String createdate = request.getParameter("createdate");
		
		String pointKind = null;
		int earnPoint = 0;
		int point = Integer.parseInt(request.getParameter("point"));
		int usePoint = Integer.parseInt(request.getParameter("usePoint"));
		
		System.out.println("goodsCode : "+goodsCode);
		System.out.println("customerId : "+customerId);
		System.out.println("addressCode : "+addressCode);
		System.out.println("orderQuantity : "+orderQuantity);
		System.out.println("orderPrice : "+orderPrice);
		System.out.println("orderState : "+orderState);
		System.out.println("createdate : "+createdate);
		System.out.println("point : "+point);
		System.out.println("usePoint : "+usePoint);
		
		
		Orders orders = new Orders();
		orders.setGoodsCode(goodsCode);
		orders.setAddressCode(addressCode);	
		orders.setCustomerId(customerId);
		
		orders.setOrderQuantity(orderQuantity);
		orders.setOrderPrice(orderPrice);
		orders.setOrderState(orderState);
		orders.setCreatedate(createdate);

		PointHistory pointHistory = new PointHistory();		
		Customer customer = new Customer();		
		customer.setCustomerId(customerId);


		// 모델호출
		ordersService = new OrdersService();
		pointKind = "적립예정";
		earnPoint = Math.round(orderPrice / 100);
		if(usePoint == 0) { // 적립만 point_history '적립 예정으로'
			pointHistory.setPointKind(pointKind);
			pointHistory.setPoint(earnPoint);
			System.out.println(earnPoint + " : 포인트 적립");
			ordersService.addOrderService(orders, pointHistory);
			
		} else { // 포인트 사용 및 기록 : point update, pointHistory
			System.out.println("포인트사용");
			
			pointHistory.setPointKind(pointKind);
			pointHistory.setPoint(earnPoint);
			System.out.println(earnPoint + " : 포인트 적립");
			ordersService.addOrderService(orders, pointHistory);
			
			pointKind = "사용";
			point = point - usePoint;
			pointHistory.setPointKind(pointKind);
			pointHistory.setPoint(usePoint);
			customer.setPoint(point);
			System.out.println("포인트 사용");
			ordersService.addOrderService(orders, pointHistory, customer);
		}
		
		// hit(상품 결제시 hit = hit+1) 설정
		GoodsService goodsService = new GoodsService();
		Goods goods = new Goods();
		int row = goodsService.updateHit(goods);
		if(row == 1) {
			System.out.println("hit값 증가!");
		} else {
			System.out.println("hit 실패!");
		}
		
		// view
		response.sendRedirect(request.getContextPath()+"/order/orderList");
	}
}