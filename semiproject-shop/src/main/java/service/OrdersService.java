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
	public ArrayList<Orders> getOrderListByPage(int currentPage, int rowPerPage, String customerId) {
		/*
		 	1) connection 생성 <- DBUtil.class
		 	2) beginRow, endRow 생성 <- currentPage,rowPerPage를 가공
		 */
		ArrayList<Orders> list = null;
		Connection conn = null;
		try {
			int beginRow = (currentPage-1)*rowPerPage+1;
			int endRow = beginRow + rowPerPage - 1;			
			conn = DBUtil.getConnection();
			orderDao = new OrderDao();
			list = orderDao.selectOrderListByPage(conn, beginRow, endRow, customerId);
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
		return list;
	}
	
	// 주문목록 검색추가
	public ArrayList<Orders> getOrderListByPage(int currentPage, int rowPerPage, String customerId, String word) {
		/*
		 	1) connection 생성 <- DBUtil.class
		 	2) beginRow, endRow 생성 <- currentPage,rowPerPage를 가공
		 */
		ArrayList<Orders> list = null;
		Connection conn = null;
		try {
			int beginRow = (currentPage-1)*rowPerPage+1;
			int endRow = beginRow + rowPerPage - 1;
			conn = DBUtil.getConnection();
			orderDao = new OrderDao();
			list = orderDao.selectOrderListByPage(conn, beginRow, endRow, customerId, word);
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
		return list;
	}
	
	// 페이징을 위한 BoardList 수 수하기
	public int cntOrderListServie(String customerId) {
		int resultCnt = 0;
		orderDao = new OrderDao();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			resultCnt = orderDao.cntOrderList(conn, customerId);
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
		return resultCnt;
	}
	
	// 페이징+검색을 위한 BoardList 수 수하기
	public int cntOrderListServie(String customerId, String word) {
		int resultCnt = 0;
		orderDao = new OrderDao();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			resultCnt = orderDao.cntOrderList(conn, word, customerId);
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
		return resultCnt;
	}
	
	// 주문상세보기
	public Orders getOtderOne(int boardNo,  String customerId) {
		orderDao = new OrderDao();
		Connection conn = null;
		Orders orders = null;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			orders = orderDao.selectOrderOne(conn, boardNo, customerId);
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
	
	// 주문수정(배송 전까지만 가능)
	public int updateOrderService(Orders orders) {
		orderDao = new OrderDao();
		Connection conn = null;
		int row = 0;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			row = orderDao.updateOrderList(conn, orders);
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
	
	// 주문취소(배송 전까지만 가능)
	public int deleteOrderService(Orders orders) {
		orderDao = new OrderDao();
		Connection conn = null;
		int row = 0;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			row = orderDao.deleteOrderList(conn, orders);
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