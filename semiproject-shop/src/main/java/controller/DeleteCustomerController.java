package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.CustomerAddressService;
import service.CustomerService;
import service.PwHistoryService;
import vo.Customer;
import vo.CustomerAddress;
import vo.Emp;
import vo.PwHistory;

@WebServlet("/customer/deleteCustomer")
public class DeleteCustomerController extends HttpServlet {
	
	private CustomerService customerService;
	private PwHistoryService pwHistoryService;
	private CustomerAddressService customerAddressService;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 로그인 후 진입가능
		HttpSession session = request.getSession();
		
		// 로그인 값 체크
		Customer loginCustomer = (Customer) session.getAttribute("loginCustomer");
		Emp loginEmp = (Emp)session.getAttribute("loginEmp");
		
		if(loginCustomer == null && loginEmp == null) {
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		
		// 인코딩 : UTF-8
		request.setCharacterEncoding("UTF-8");
		
		// request
		String customerId = request.getParameter("customerId");
		
		// null, 공백 검사 
		if(customerId == null || customerId.equals("")) {
			
			response.sendRedirect(request.getContextPath() + "/customer/customerList");
			return;
			
		}
		
		Customer customer = new Customer();
		customer.setCustomerId(customerId);
		
		PwHistory pwHistory = new PwHistory();
		pwHistory.setCustomerId(customerId);
		
		CustomerAddress customerAddress = new CustomerAddress();
		customerAddress.setCustomerId(customerId);
		
		
		// 해당 ID 비밀번호 이력 전체 삭제
		this.pwHistoryService = new PwHistoryService();
		int resultRowP = this.pwHistoryService.deletePwHistory(pwHistory);
		
		// 해당 ID 주소 전체 삭제
		this.customerAddressService = new CustomerAddressService();
		int resultRowCA = this.customerAddressService.deleteAddress(customerAddress);
		
		// customer 삭제
		this.customerService = new CustomerService();
		int resultRowC = this.customerService.deleteCustomer(customer);
		
		// 삭제 실패하면 회원 탈퇴 누르기 전 화면 or 관리자가 삭제 누르기 전 화면
		String targetUrl = "/customer/customerList";
		
		if(resultRowC == 1 && resultRowP == 1 && resultRowCA == 1) {
			
			// 삭제성공하면
			
			request.getSession().invalidate();  // 받아온 세션값을 완전히 삭제.
			
			targetUrl = "/login";
			
		}
		
		response.sendRedirect(request.getContextPath() + targetUrl);
		
		
	}

}
