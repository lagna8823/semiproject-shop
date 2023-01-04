package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.CustomerDao;
import dao.EmpDao;
import util.DBUtil;
import vo.Customer;
import vo.Emp;

public class EmpService {
	private EmpDao empDao;
	
	// EmpList 출력
	// 사용하는 곳 : EmpListController
	public ArrayList<Emp> getEmpList() {
		
		ArrayList<Emp> list = new ArrayList<Emp>();
		
		Connection conn = null;
		
		try {
			
			conn = DBUtil.getConnection();
			
			
			this.empDao = new EmpDao();
			list = this.empDao.selectEmpList(conn);
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
}
