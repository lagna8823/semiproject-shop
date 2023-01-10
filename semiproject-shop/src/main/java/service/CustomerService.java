package service;

import java.sql.Connection;	
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import dao.CustomerAddressDao;
import dao.CustomerDao;
import dao.EmpDao;
import dao.OutidDao;
import dao.PwHistoryDao;
import util.DBUtil;
import util.Page;
import vo.Customer;
import vo.CustomerAddress;
import vo.PwHistory;

public class CustomerService {
	
	private CustomerDao customerDao;
	private PwHistoryDao pwHistoryDao;
	private CustomerAddressDao customerAddressDao;
	
	private EmpDao empDao;
	private OutidDao outidDao;
	
	// 로그인 LoginController
	public Customer login(Customer customer) {
		this.customerDao = new CustomerDao();
		Customer returnCustomer= new Customer();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			customerDao= new CustomerDao();
			returnCustomer = customerDao.selectCustomerLogin(conn, customer);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return returnCustomer;	
	}
	
	
	// CustomerList 출력
	// 사용하는 곳 : CustomerListController
	public ArrayList<Customer> getCustomerList(String searchCategory, String searchText, int currentPage, int rowPerPage) {
		
		ArrayList<Customer> list = null;
		
		Connection conn = null;
		
		try {
			
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			
			list = new ArrayList<Customer>();
			
			int beginRow = Page.getBeginRow(currentPage, rowPerPage);
			
			this.customerDao = new CustomerDao();
			list = this.customerDao.selectCustomerList(conn, searchCategory, searchText, beginRow, rowPerPage);
			conn.commit();
			
		} catch (Exception e) {
			
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
			e.printStackTrace();
			
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
		
	}
	
	// CustomerList 페이징
	// 사용하는 곳 : CustomerListController
	public ArrayList<HashMap<String, Object>> getPage(String searchCategory, String searchText, int currentPage, int rowPerPage) {
		
		ArrayList<HashMap<String, Object>> list = null;
		
		Connection conn = null;
		
		try {
			
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			this.customerDao = new CustomerDao();
			
			// 페이징 처리
			int pageLength = 10;
			int count = this.customerDao.countCustomer(conn, searchCategory, searchText);
			
			int previousPage = Page.getPreviousPage(currentPage, pageLength);
			int nextPage = Page.getNextPage(currentPage, pageLength);
			int lastPage = Page.getLastPage(count, rowPerPage);
			ArrayList<Integer> pageList = Page.getPageList(currentPage, pageLength);
			
			list = new ArrayList<HashMap<String, Object>>();
			
			HashMap<String, Object> m1 = new HashMap<String, Object>();
			m1.put("previousPage", previousPage);
			m1.put("nextPage", nextPage);
			m1.put("lastPage", lastPage);
			m1.put("pageList", pageList);
			
			list.add(m1);
			
			conn.commit();
			
		} catch (Exception e) {
			
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
			e.printStackTrace();
			
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
		
	}
	
	// customerOne customer 한명의 정보를 출력
	// 사용하는 곳 : CustomerOneController, ModifyCustomerController, ModifyCustomerByAdminController
	public Customer getCustomerOne(String customerId) {

		Customer resultCustomer = null;
		
		Connection conn = null;
		
		try {
			
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);

			this.customerDao = new CustomerDao();
			resultCustomer = this.customerDao.selectCustomerOne(conn, customerId);
			conn.commit();
			
		} catch (Exception e) {
			
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
			e.printStackTrace();
			
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return resultCustomer;
		
	}

	
	// 관리자가 customer 수정
	// 사용하는 곳 : ModifyCustomerByAdminController
	public int modifyCustomerByAdmin(Customer customer) {

		int resultRow = 0;
		
		Connection conn = null;
		
		try {
			
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);

			this.customerDao = new CustomerDao();
			resultRow = this.customerDao.modifyCustomerByAdmin(conn, customer);

			if(resultRow == 1) {
				conn.commit();
			}
				
			
		} catch (Exception e) {
			
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
			e.printStackTrace();
			
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return resultRow;
		
	}	
	
	
	
	
	// customer 수정
	// 사용하는 곳 : ModifyCustomerController
	public int modifyCustomer(Customer customer) {

		int resultRow = 0;
		
		Connection conn = null;
		
		try {
			
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);

			this.customerDao = new CustomerDao();
			resultRow = this.customerDao.modifyCustomer(conn, customer);

			if(resultRow == 1) {
				conn.commit();
			}
				
			
		} catch (Exception e) {
			
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
			e.printStackTrace();
			
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return resultRow;
		
	}		
	
	
	
	
	// customer 삭제
	// 탈퇴 or 관리자 삭제 시 
	// 1) 해당 ID의 pwHistory 모두 삭제
	// 2) 해당 ID의 customer 삭제
	// 3) 해당 ID outid에 추가하여 이후에 같은 ID로 가입 X
	// 사용하는곳 : DeleteCustomerController
	public int deleteCustomer(Customer customer) {
		
		int resultRow = 0;
		
		int resultRowP = 0;
		int resultRowC = 0;
		int resultRowO = 0;
		
		Connection conn = null;
		
		try {
			
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			
			// pwHistory 삭제
			this.pwHistoryDao = new PwHistoryDao();
			resultRowP = this.pwHistoryDao.deletePwHistory(conn, customer.getCustomerId());
			
			// customer 삭제
			this.customerDao = new CustomerDao();
			resultRowC = this.customerDao.deleteCustomer(conn, customer.getCustomerId());
			
			// outid 추가
			this.outidDao = new OutidDao();
			resultRowO = this.outidDao.addOutid(conn, customer.getCustomerId());
			
			if(resultRowP == 1 && resultRowC == 1 && resultRowO == 1) {
				
				// 모든 단계 성공하면 commit
				resultRow = 1;
				
				conn.commit();
				
			}
			
		} catch (Exception e) {
			
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
			e.printStackTrace();
			
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return resultRow;
		
	}
	
	
	
	
	// customer 추가 -> 회원 가입
	// 1) customerId, empId, outid ID 중복확인
	// 2) customer, pwHistory, address 추가
	// 3) 모두 성공하면 commit
	// 사용하는 곳 : AddCustomerController
	public int addCustomer(Customer customer, String address) {
		
		int resultRow = 0;
		
		boolean checkCId = false;
		boolean checkEId = false;
		boolean checkOId = false;
		
		int resultRowC = 0;
		int resultRowP = 0;
		int resultRowA = 0;
		
		PwHistory pwHistory = null;
		CustomerAddress customerAddress = null;
		
		Connection conn = null;
		
		try {
			
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);

			this.customerDao = new CustomerDao();
			this.pwHistoryDao = new PwHistoryDao();		// customer 비밀번호 이력 추가
			this.customerAddressDao = new CustomerAddressDao();	// customer 주소 추가
			this.empDao = new EmpDao();		// ID 중복 확인
			this.outidDao = new OutidDao();	// ID 중복 확인
			
			// checkCId, checkEId, checkOId
			// true : ID가 이미 존재(가입불가) false : ID 사용 가능(가입가능)
			checkCId = this.customerDao.checkCustomerId(conn, customer.getCustomerId());
			checkEId = this.empDao.checkEmpId(conn, customer.getCustomerId());
			checkOId = this.outidDao.checkOutid(conn, customer.getCustomerId());
			
			if(checkCId || checkEId || checkOId) {
				// 셋중 하나라도 중복(true)되면 가입 불가
				
				System.out.println("CustomerService ID 중복입니다.");
				return resultRow;
				
			}
			
			pwHistory = new PwHistory();
			pwHistory.setCustomerId(customer.getCustomerId());
			pwHistory.setPw(customer.getCustomerPw());
			
			customerAddress = new CustomerAddress();
			customerAddress.setCustomerId(customer.getCustomerId());
			customerAddress.setAddress(address);
			
			
			resultRowC = this.customerDao.addCustomer(conn, customer);
			resultRowP = this.pwHistoryDao.addPwHistory(conn, pwHistory);
			resultRowA = this.customerAddressDao.addAddress(conn, customerAddress);
			
			if(resultRowC == 1 && resultRowP == 1 && resultRowA == 1) {
				// customer, pwHistory, address 모두 추가 성공하면
				resultRow = 1;
				
				conn.commit();
			}
			
		} catch (Exception e) {
			
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
			e.printStackTrace();
			
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return resultRow;
		
	}
	
	
	// 비밀번호 확인
	// 사용하는 곳 : DeleteCustomerController, ModifyCustomerController
	// true : 비밀번호 일치(메뉴사용가능) / false : 불일치(메뉴사용불가)
	public boolean checkPw(Customer customer) {
		
		boolean result = false;
		
		Connection conn = null;
		
		try {
			
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			
			this.customerDao = new CustomerDao();
			result = this.customerDao.checkPw(conn, customer);
			
			if(result) {
				conn.commit();
			}
			
			
		} catch (Exception e) {
			
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
			e.printStackTrace();
			
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return result;
		
	}
	
	
	
	
	
	
	
	
	
	
}