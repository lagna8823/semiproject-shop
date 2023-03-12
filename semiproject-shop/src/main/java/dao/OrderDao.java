package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import vo.Customer;
import vo.CustomerAddress;
import vo.Goods;
import vo.Goods;
import vo.Orders;
import vo.PointHistory;

public class OrderDao {
	// 주문목록
	public ArrayList<HashMap<String, Object>> selectOrderListByPage(Connection conn, int beginRow, int endRow, String customerId) throws Exception {
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		
		String sql = "SELECT r.order_code orderCode"
				+ "			, g.goods_code goodsCode, g.goods_name goodsName, g.goods_price goodsPrice, g.soldout soldout"
				+ "			, gi.filename filename"
				+ "			, c.customer_id customerId, c.customer_name customerName, c.customer_phone customerPhone, c.point customerPoint"
				+ "			, ca.address_code addressCode, ca.address address"
				+ "			, r.order_quantity orderQuantity, r.order_price orderPrice, r.order_state orderState, date_format(r.createdate, '%Y-%m-%d') createdate"
				+ "			, p.point_kind pointKind, p.point point"
				+ " 	FROM (SELECT ROW_NUMBER() OVER(ORDER BY order_code desc) rnum, order_code, goods_code, customer_id, address_code"
				+ "					, order_quantity, order_price, order_state, createdate"
				+ " 			FROM orders) r"
				+ " 	INNER JOIN goods g ON r.goods_code = g.goods_code"
				+ "		INNER JOIN goods_img gi ON g.goods_code = gi.goods_code"
				+ " 	INNER JOIN customer c ON r.customer_id = c.customer_id"
				+ " 	INNER JOIN customer_address ca ON r.address_code = ca.address_code"
			 	+ "		INNER JOIN (SELECT order_code, point_kind, POINT"
			 	+ "					 FROM point_history"
			 	+ "						GROUP BY order_code"
			 	+ "						 ORDER BY order_code asc) p ON r.order_code = p.order_code"
				+ " 	WHERE rnum BETWEEN ? AND ? AND r.customer_id = ? ORDER BY r.order_code desc"; // WHERE rnum >=? AND rnum <=?;
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, beginRow);
		stmt.setInt(2, endRow);
		stmt.setString(3, customerId);
		
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			HashMap<String, Object> m = new HashMap<String, Object>();
			
			m.put("orderCode", rs.getInt("orderCode"));
			m.put("goodsCode", rs.getInt("goodsCode"));
			m.put("goodsName", rs.getString("goodsName"));
			m.put("goodsPrice", rs.getInt("goodsPrice"));
			m.put("soldout", rs.getString("soldout"));
			m.put("filename", rs.getString("filename"));
			m.put("customerID", rs.getString("customerId"));
			m.put("customerName", rs.getString("customerName"));
			m.put("customerPhone", rs.getString("customerPhone"));
			m.put("customerPoint", rs.getInt("customerPoint"));
			m.put("pointKind", rs.getString("pointKind"));
			m.put("point", rs.getInt("point"));
			m.put("addressCode", rs.getInt("addressCode"));
			m.put("address", rs.getString("address"));
			m.put("orderQuantity", rs.getInt("orderQuantity"));
			m.put("orderPrice", rs.getInt("orderPrice"));
			m.put("orderState", rs.getString("orderState"));
			m.put("createdate", rs.getString("createdate"));
			
			list.add(m);
		}
		return list;
	}
	
	// 주문목록 검색추가 - (goods_code로 goods_name 조인해와야 함)
	public ArrayList<HashMap<String, Object>> selectOrderListByPage(Connection conn, int beginRow, int endRow, String customerId, String word) throws Exception {
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		
		String sql = "SELECT r.order_code orderCode"
				+ "			, g2.goods_code goodsCode, g2.goods_name goodsName, g2.goods_price goodsPrice, g2.soldout soldout"
				+ "			, gi.filename filename"
				+ "			, c.customer_id customerId, c.customer_name customerName, c.customer_phone customerPhone, c.point customerPoint"
				+ "			, ca.address_code addressCode, ca.address address"
				+ "			, r.order_quantity orderQuantity, r.order_price orderPrice, r.order_state orderState, r.createdate createdate"
				+ "			, p.point_kind pointKind, p.point point"
				+ " 	FROM (SELECT ROW_NUMBER() OVER(ORDER BY order_code desc) rnum, o.order_code, o.goods_code, o.customer_id, o.address_code"
				+ "					, o.order_quantity, o.order_price, o.order_state, o.createdate"
				+ "				FROM orders o INNER JOIN goods g1 ON o.goods_code = g1.goods_code WHERE g1.goods_name LIKE ?) r"
				+ " 	INNER JOIN goods g2 ON r.goods_code = g2.goods_code"
				+ "		INNER JOIN goods_img gi ON g2.goods_code = gi.goods_code"
				+ " 	INNER JOIN customer c ON r.customer_id = c.customer_id"
				+ " 	INNER JOIN customer_address ca ON r.address_code = ca.address_code"
			 	+ "		INNER JOIN (SELECT order_code, point_kind, POINT"
			 	+ "					 FROM point_history"
			 	+ "						GROUP BY order_code"
			 	+ "						 ORDER BY order_code asc) p ON r.order_code = p.order_code"
				+ " 	WHERE rnum BETWEEN ? AND ? AND r.customer_id = ? ORDER BY r.order_code desc"; // WHERE rnum >=? AND rnum <=?;
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, "%"+word+"%");
		stmt.setInt(2, beginRow);
		stmt.setInt(3, endRow);
		stmt.setString(4, customerId);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			HashMap<String, Object> m = new HashMap<String, Object>();
			
			m.put("orderCode", rs.getInt("orderCode"));
			m.put("goodsCode", rs.getInt("goodsCode"));
			m.put("goodsName", rs.getString("goodsName"));
			m.put("goodsPrice", rs.getInt("goodsPrice"));
			m.put("soldout", rs.getString("soldout"));
			m.put("filename", rs.getString("filename"));
			m.put("customerID", rs.getString("customerId"));
			m.put("customerName", rs.getString("customerName"));
			m.put("customerPhone", rs.getString("customerPhone"));
			m.put("customerPoint", rs.getInt("customerPoint"));
			m.put("pointKind", rs.getString("pointKind"));
			m.put("point", rs.getInt("point"));
			m.put("addressCode", rs.getInt("addressCode"));
			m.put("address", rs.getString("address"));
			m.put("orderQuantity", rs.getInt("orderQuantity"));
			m.put("orderPrice", rs.getInt("orderPrice"));
			m.put("orderState", rs.getString("orderState"));
			m.put("createdate", rs.getString("createdate"));
			
			list.add(m);
		}
		return list;
	}
	
	// 페이징을 위한 주문목록 페이지 수
	public int cntOrderList (Connection conn, String customerId) throws Exception {
		int cnt = 0;
		String sql = "SELECT COUNT(*) cnt FROM orders WHERE customer_id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, customerId);
		
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			cnt = rs.getInt("cnt");
		}		
		return cnt;	
	}
	
	// 페이징+검색을 위한 주문목록 페이지 수
	public int cntOrderList (Connection conn, String customerId, String word) throws Exception {
		int cnt = 0;
		String sql = "SELECT COUNT(*) cnt from orders WHERE customer_id = ? AND goods_name LIKE ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, customerId);
		stmt.setString(2, "%"+word+"%");
		
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			cnt = rs.getInt("cnt");
		}		
		return cnt;
	}
	
	// 주문 시 필요 : 고객정보
	public Customer selectCustomerInfoForOrder (Connection conn, String customerId) throws Exception {
		Customer customer = null;
		
		String sql = "SELECT customer_id customerId, customer_name customerName, customer_phone customerPhone, point FROM customer WHERE customer_id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, customerId);
		
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			customer = new Customer();
			customer.setCustomerId(rs.getString("customerId"));
			customer.setCustomerName(rs.getString("customerName"));
			customer.setCustomerPhone(rs.getString("customerPhone"));
			customer.setPoint(rs.getInt("point"));
		}		
		return customer;
	}
	
	// 주문 시 필요 : 고객주소
	public ArrayList<CustomerAddress> selectCustomerAddressForOrder (Connection conn, String customerId) throws Exception {
		CustomerAddress customerAddress = null;
		ArrayList<CustomerAddress> list = null;
		
		String sql = "SELECT address_code addressCode, address FROM customer_address WHERE customer_id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, customerId);

		list = new ArrayList<CustomerAddress>();
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			customerAddress = new CustomerAddress();
			customerAddress.setAddressCode(rs.getInt("addressCode"));
			customerAddress.setAddress(rs.getString("address"));
			list.add(customerAddress);
			System.out.println(customerAddress.getAddressCode() + "code");
		}		
		return list;
	}
	
	// 주문 시 필요 : 상품정보
	public Goods selectGoodsForOrder (Connection conn, int goodsCode, int cartQuantity) throws Exception {
		Goods goods= null;
		
		String sql = "SELECT g.goods_code goodsCode, g.goods_name goodsName, g.goods_price goodsPrice, g.soldout"
				+ "			, gi.filename filename"
				+ "		 FROM goods g"
				+ "		 INNER JOIN goods_img gi ON g.goods_code = gi.goods_code"
				+ "		 WHERE g.goods_code = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, goodsCode);
		
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			goods = new Goods();
			goods.setGoodsCode(rs.getInt("goodsCode"));
			goods.setGoodsName(rs.getString("goodsName"));
			goods.setGoodsPrice(rs.getInt("goodsPrice"));
			goods.setSoldout(rs.getString("soldout"));
			goods.setFilename(rs.getString("filename"));
			goods.setCartQuantity(cartQuantity);
		}
		return goods;
	}
	
	// 주문상세보기
	public ArrayList<HashMap<String, Object>> selectOrderOne (Connection conn, int orderCode, String customerId) throws Exception {
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

		String sql = "SELECT r.order_code orderCode"
				+ "			, g.goods_code goodsCode, g.goods_name goodsName, g.goods_price goodsPrice, g.soldout soldout"
				+ "			, gi.filename filename"
				+ "			, c.customer_id customerId, c.customer_name customerName, c.customer_phone customerPhone, c.point customerPoint"
				+ "			, ca.address_code addressCode, ca.address address"
				+ "			, r.order_quantity orderQuantity, r.order_price orderPrice, r.order_state orderState, r.createdate createdate"
				+ "			, p.point_kind pointKind, p.point point"
				+ " 	FROM (SELECT ROW_NUMBER() OVER(ORDER BY order_code desc) rnum, order_code, goods_code, customer_id, address_code"
				+ "					, order_quantity, order_price, order_state, createdate"
				+ " 			FROM orders) r"
				+ " 	INNER JOIN goods g ON r.goods_code = g.goods_code"
				+ "		INNER JOIN goods_img gi ON g.goods_code = gi.goods_code"
				+ " 	INNER JOIN customer c ON r.customer_id = c.customer_id"
				+ " 	INNER JOIN customer_address ca ON r.address_code = ca.address_code"
			 	+ "		INNER JOIN (SELECT order_code, point_kind, POINT"
			 	+ "					 FROM point_history"
			 	+ "						GROUP BY order_code"
			 	+ "						 ORDER BY order_code asc) p ON r.order_code = p.order_code"
				+ " 	WHERE r.order_code = ? AND r.customer_id = ? ORDER BY r.order_code desc"; // WHERE rnum >=? AND rnum <=?;
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, orderCode);
		stmt.setString(2, customerId);
		
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			HashMap<String, Object> m = new HashMap<String, Object>();
			
			m.put("orderCode", rs.getInt("orderCode"));
			m.put("goodsCode", rs.getInt("goodsCode"));
			m.put("goodsName", rs.getString("goodsName"));
			m.put("goodsPrice", rs.getInt("goodsPrice"));
			m.put("soldout", rs.getString("soldout"));
			m.put("filename", rs.getString("filename"));
			m.put("customerID", rs.getString("customerId"));
			m.put("customerName", rs.getString("customerName"));
			m.put("customerPhone", rs.getString("customerPhone"));
			m.put("customerPoint", rs.getInt("customerPoint"));
			m.put("pointKind", rs.getString("pointKind"));
			m.put("point", rs.getInt("point"));
			m.put("addressCode", rs.getInt("addressCode"));
			m.put("address", rs.getString("address"));
			m.put("orderQuantity", rs.getInt("orderQuantity"));
			m.put("orderPrice", rs.getInt("orderPrice"));
			m.put("orderState", rs.getString("orderState"));
			m.put("createdate", rs.getString("createdate"));
			
			list.add(m);
		}		
		return list;
	}
	
	// 주문하기
	public int addOrder(Connection conn, Orders orders) throws Exception {
		int row = 0;

		String sql = "INSERT INTO orders(goods_code, customer_id, address_code"
				+ "				, order_quantity, order_price, order_state, createdate)"
				+ " 		VALUES (?, ?, ?, ?, ?, ?, NOW())"; //sysdate는 시시각각 변함, now는 한 번 호출되면 변하지 않음
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, orders.getGoodsCode());
		stmt.setString(2, orders.getCustomerId());
		stmt.setInt(3, orders.getAddressCode());
		stmt.setInt(4, orders.getOrderQuantity());
		stmt.setInt(5, orders.getOrderPrice());
		stmt.setString(6, orders.getOrderState());
		
		row = stmt.executeUpdate();
		return row;
	}
	
	// 포인트 처리를 위한 검색
	public int selectOrderForPoint (Connection conn, String customerId) throws Exception {
		int orderCode = 0;
		
		String sql = "SELECT MAX(order_code) orderCode FROM orders WHERE customer_id = ?"; // WHERE rnum >=? AND rnum <=?;
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, customerId);
		
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			orderCode = rs.getInt("orderCode");
		}		
		return orderCode;
	}
	
	// 구매확정 시 포인트 적립
	public int updatePointByOrderConfirm(Connection conn, int orderCode, String customerId) throws Exception {
		int row = 0;

		String sql = "UPDATE orders o"
				+ "			JOIN customer c ON o.customer_id = c.customer_id"
				+ "			JOIN point_history p ON o.order_code = p.order_code"
				+ "			SET c.point = 5000, p.point_kind='적립', o.order_state='구매확정'"
				+ "			WHERE o.order_code = ? AND c.customer_id = ? AND p.point_kind='적립예정'";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, orderCode);
		stmt.setString(2, customerId);
		System.out.println(orderCode + ": orderCode");
		System.out.println(customerId + ": customerId");
			
		row = stmt.executeUpdate();
		return row;
	}
	
	// 주문취소(배송 전까지만 가능)
	public int deleteOrderList(Connection conn, int orderCode, String customerId) throws Exception {
		int row = 0;

		String sql = "DELETE FROM orders WHERE order_code = ? AND customer_id = ?";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, orderCode);
		stmt.setString(2, customerId);
		System.out.println(orderCode + ": orderCode");
		System.out.println(customerId + ": customerId");
			
		row = stmt.executeUpdate();
		return row;
	}
}
