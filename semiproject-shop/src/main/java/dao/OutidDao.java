package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import vo.Outid;

public class OutidDao {

	// outid 중복 확인
	// true : ID가 이미 존재(가입불가) false : ID 사용 가능(가입가능)
	// 사용하는 곳 : AddCustomerController, AddEmpController
	public boolean checkOutid(Connection conn, Outid outid) throws Exception {
		
		boolean result = false;
		
		String sql = "SELECT id"
				+ "	 FROM outid"
				+ "	 WHERE id = ?";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		stmt.setString(1, outid.getId());
		
		ResultSet rs = stmt.executeQuery();
		
		if(rs.next()) {
			result = true;
		}
		
		return result;
		
	}
	
}
