package service;

import java.sql.Connection;
import java.sql.SQLException;

import dao.PointDao;
import util.DBUtil;
import vo.Customer;

public class PointService {
	// 구매확정 시 포인트 처리
	public int updatePointService(Customer customer) {
		PointDao pointDao = new PointDao();
		Connection conn = null;
		int row = 0;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			row = pointDao.updatePoint(conn, customer);
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
		return row;
	}
}