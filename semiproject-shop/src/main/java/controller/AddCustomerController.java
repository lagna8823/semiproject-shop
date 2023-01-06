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
import service.EmpService;
import service.OutidService;
import service.PwHistoryService;
import vo.Customer;
import vo.CustomerAddress;
import vo.Emp;
import vo.Outid;
import vo.PwHistory;

@WebServlet("/customer/addCustomer")
public class AddCustomerController extends HttpServlet {
	
	private CustomerService customerService;
	private PwHistoryService pwHistoryService;		// customer 비밀번호 이력 추가
	private CustomerAddressService customerAddressService;	// customer 주소 추가
	
	private EmpService empService;		// 중복 확인 용
	private OutidService outidService;	// 중복 확인 용
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*
		 * 로그인 검사(세션 로그인 있을시 홈 화면으로
		 * 
		 */
		
		request.getRequestDispatcher("/WEB-INF/view/customer/addCustomer.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 인코딩 : UTF-8
		request.setCharacterEncoding("UTF-8");
		
		String customerId = request.getParameter("customerId");
		String customerPw = request.getParameter("customerPw");
		String customerName = request.getParameter("customerName");
		String customerPhone = request.getParameter("customerPhone");
		String address = request.getParameter("address");
		
		if(customerId == null || customerPw == null || customerName == null || customerPhone == null || address == null
				 || customerId.equals("") || customerPw.equals("") || customerName.equals("") || customerPhone.equals("") || address.equals("")) {
			
			response.sendRedirect(request.getContextPath() + "/customer/addCustomer");
			return;
			
		}
		
		Customer customer = new Customer();
		customer.setCustomerId(customerId);
		customer.setCustomerPw(customerPw);
		customer.setCustomerName(customerName);
		customer.setCustomerPhone(customerPhone);
		
		Emp emp = new Emp();
		emp.setEmpId(customerId);	// empId 중복확인
		
		Outid outid = new Outid();	
		outid.setId(customerId);	// outid 중복확인
		
		// checkCId, checkEId, checkOId
		// true : ID가 이미 존재(가입불가) false : ID 사용 가능(가입가능)
		this.customerService = new CustomerService();
		boolean checkCId = this.customerService.checkCustomerId(customer);
		
		this.empService = new EmpService();
		boolean checkEId = this.empService.checkEmpId(emp);
		
		this.outidService = new OutidService();
		boolean checkOId = this.outidService.checkOutid(outid); 
		
		if(checkCId || checkEId || checkOId) {
			// 셋중 하나라도 중복(true)되면 가입 불가
			
			// msg 중복아이디 입니다. 다시 입력하세요
			System.out.println("중복입니다.");
			response.sendRedirect(request.getContextPath() + "/customer/addCustomer");
			return;
			
		}

		PwHistory pwHistory = new PwHistory();
		pwHistory.setCustomerId(customerId);
		pwHistory.setPw(customerPw);
		
		CustomerAddress customerAddress = new CustomerAddress();
		customerAddress.setCustomerId(customerId);
		customerAddress.setAddress(address);
		
		// customer 추가
		int resultRowC = this.customerService.addCustomer(customer);

		// 비밀번호 이력 추가
		this.pwHistoryService = new PwHistoryService();
		int resultRowP = this.pwHistoryService.addPwHistory(pwHistory);
		
		// 주소 추가
		this.customerAddressService = new CustomerAddressService();
		int resultRowCA = this.customerAddressService.addAddress(customerAddress);
		
		if(resultRowC == 1 && resultRowP == 1 && resultRowCA ==1) {
			
			// 회원가입 성공시
			
		}
		
		// 실제로는 로그인 화면으로 가야함
		response.sendRedirect(request.getContextPath() + "/home");
		
		
	}

}
