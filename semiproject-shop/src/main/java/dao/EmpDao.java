package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import vo.Customer;
import vo.Emp;

public class EmpDao {
	
	// empList
	// 사용하는 곳 : EmpListController
	public ArrayList<Emp> selectEmpList(Connection conn) throws Exception {
		
		ArrayList<Emp> list = new ArrayList<Emp>();
		
		String sql = "SELECT emp_code empCode"
				+ "			, emp_id empId"
				+ "			, emp_name empName"
				+ "			, active"
				+ "			, auth_code authCode"
				+ "			, createdate"
				+ "	 FROM emp";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			
			Emp emp = new Emp();
			emp.setEmpCode(rs.getInt("empCode"));
			emp.setEmpId(rs.getString("empId"));
			emp.setEmpName(rs.getString("empName"));
			emp.setActive(rs.getString("active"));
			emp.setAuthCode(rs.getInt("authCode"));
			emp.setCreatedate(rs.getString("createdate"));
			
			list.add(emp);
			
		}
		
		return list;
		
	}
		
	// Login
	// 사용하는 곳: LoginController
	public Emp selectEmpLogin(Connection conn, Emp emp) throws Exception {
		Emp retrunEmp = null;
		String sql = "SELECT emp_code empCode, emp_id empId, emp_name empName"
				+ " FROM emp WHERE emp_id =? AND emp_pw = password(?)";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, emp.getEmpId());
		stmt.setString(2, emp.getEmpPw());
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			retrunEmp= new Emp();
			retrunEmp.setEmpCode(rs.getInt("empCode"));
			retrunEmp.setEmpId(rs.getString("empId"));
			retrunEmp.setEmpName(rs.getString("empName"));
		}
		rs.close();
		stmt.close();
		return retrunEmp;
	}
}
