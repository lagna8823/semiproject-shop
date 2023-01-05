package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.OrderDao;
import util.DBUtil;
import vo.Orders;

public class OrdersService {
	private OrderDao orderDao;
	
	// 주문목록
	public ArrayList<Orders> getOrderListByPage(int currentPage, int rowPerPage) {
		/*
		 	1) connection 생성 <- DBUtil.class
		 	2) beginRow, endRow 생성 <- currentPage,rowPerPage를 가공
		 */
		ArrayList<Orders> orders = null;
		Connection conn = null;
		try {
			int beginRow = (currentPage-1)*rowPerPage+1;
			int endRow = beginRow + rowPerPage - 1;			
			conn = DBUtil.getConnection();
			orderDao = new OrderDao();
			orders = orderDao.selectOrderListByPage(conn, beginRow, endRow);
			conn.commit(); // DBUtil.class에서 conn.setAutoCommit(false);
		} catch (Exception e) {
			try {
				conn.rollback(); // DBUtil.class에서 conn.setAutoCommit(false);
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
		return orders;
	}
	
	// 주문상세보기
	public Orders getOtderOne(int boardNo) {
		orderDao = new OrderDao();
		Connection conn = null;
		Orders orders = null;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			orders = orderDao.selectOrderOne(conn, boardNo);
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
		return orders;
	}
	
	// 주문하기
	public int addOrderService(Orders orders) {
		orderDao = new OrderDao();
		Connection conn = null;
		int row = 0;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			row = orderDao.addOrder(conn, orders);
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