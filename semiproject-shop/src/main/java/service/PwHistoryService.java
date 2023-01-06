package service;

import java.sql.Connection;
import java.sql.SQLException;

import dao.PwHistoryDao;
import util.DBUtil;
import vo.PwHistory;

public class PwHistoryService {

	private PwHistoryDao pwHistoryDao;
	
	// Password 이력 추가
	// 사용하는 곳 : AddCustomerController
	public int addPwHistory(PwHistory pwHistory) {
		
		int resultRow = 0;
		
		Connection conn = null;
		
		try {
			
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);

			this.pwHistoryDao = new PwHistoryDao();
			
			resultRow = this.pwHistoryDao.addPwHistory(conn, pwHistory);
			
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
	
	

	// 비밀번호 이력 전체 삭제(관리자 삭제, 탈퇴)
	// 사용하는 곳 : DeleteCustomerController
	public int deletePwHistory(PwHistory pwHistory) {
		
		int resultRow = 0;
		
		Connection conn = null;
		
		try {
			
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);

			this.pwHistoryDao = new PwHistoryDao();
			
			resultRow = this.pwHistoryDao.deletePwHistory(conn, pwHistory);
			
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
