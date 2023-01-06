package service;

import java.sql.Connection;
import java.sql.SQLException;

import dao.OutidDao;
import util.DBUtil;
import vo.Outid;

public class OutidService {

	private OutidDao outidDao;
	
	// outid ID 중복확인
	// true : ID가 이미 존재(가입불가) false : ID 사용 가능(가입가능)
	// 사용하는 곳 : AddCustomerController, AddEmpController
	public boolean checkOutid(Outid outid) {
		
		boolean result = false;
		
		Connection conn = null;
		
		try {
			
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			
			this.outidDao = new OutidDao();
			result = this.outidDao.checkOutid(conn, outid);
			
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
		
		
		return result;
		
	}	
}
