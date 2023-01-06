package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import dao.CustomerDao;
import dao.EmpDao;
import util.DBUtil;
import util.Page;
import vo.Customer;
import vo.Emp;

public class EmpService {
	private EmpDao empDao;
	
	// Login
	// 사용하는곳 LoginController
	public Emp login(Emp emp) {
		this.empDao = new EmpDao();
		Emp returnEmp= new Emp();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			empDao= new EmpDao();
			returnEmp = empDao.selectEmpLogin(conn, emp);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return returnEmp;	
	}
	

	// EmpList 출력
	// 사용하는 곳 : EmpListController
	public ArrayList<Emp> getEmpList(String searchCategory, String searchText, int currentPage, int rowPerPage) {
		
		ArrayList<Emp> list = null;
		
		Connection conn = null;
		
		try {
			
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			
			list = new ArrayList<Emp>();
			
			int beginRow = Page.getBeginRow(currentPage, rowPerPage);
			
			this.empDao = new EmpDao();
			list = this.empDao.selectEmpList(conn, searchCategory, searchText, beginRow, rowPerPage);
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
	
	
	// EmpList 페이징
	// 사용하는 곳 : EmpListController
	public ArrayList<HashMap<String, Object>> getPage(String searchCategory, String searchText, int currentPage, int rowPerPage) {
		
		ArrayList<HashMap<String, Object>> list = null;
		
		Connection conn = null;
		
		try {
			
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			this.empDao = new EmpDao();
			
			// 페이징 처리
			int pageLength = 10;
			int count = this.empDao.countEmp(conn, searchCategory, searchText);
			
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
	
	// empOne emp 한명의 정보를 출력
	// 사용하는 곳 : UpdateEmpController
	public Emp getEmpOne(int empCode) {

		Emp resultEmp = null;
		
		Connection conn = null;
		
		try {
			
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);

			this.empDao = new EmpDao();
			resultEmp = this.empDao.selectEmpOne(conn, empCode);
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
		
		return resultEmp;
		
	}
	
	
	
	// emp 수정
	// 사용하는 곳 : UpdateEmpController
	public int updateEmp(Emp emp) {

		int resultRow = 0;
		
		Connection conn = null;
		
		try {
			
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);

			this.empDao = new EmpDao();
			resultRow = this.empDao.updateEmp(conn, emp);
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
		
		return resultRow;
		
	}
	
	
	// emp 삭제
	// 사용하는곳 : DeleteEmpController
	public int deleteEmp(int empCode) {
		
		int resultRow = 0;
		
		Connection conn = null;
		
		try {
			
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);

			this.empDao = new EmpDao();
			resultRow = this.empDao.deleteEmp(conn, empCode);
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
		
		return resultRow;
		
	}
	
	// emp 추가 -> 회원 가입
	// 사용하는 곳 : AddEmpController
	public int addEmp(Emp emp) {
		
		int resultRow = 0;
		
		Connection conn = null;
		
		try {
			
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);

			this.empDao = new EmpDao();
			
			resultRow = this.empDao.addEmp(conn, emp);
			
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
		
		
		return resultRow;
		
	}
	
	
	
	
	
}
