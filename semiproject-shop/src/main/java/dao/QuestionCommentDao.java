package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import vo.Customer;
import vo.Question;

public class QuestionCommentDao {
	/* 답변 페이지는 관리자만 사용가능*/
	
	// addQuestionComment (문의글에 대한 답변추가)
	// 사용하는 곳 : addQuestionCommentController	
	public int addQuestionComment(Connection conn, Question addQuestionComment) throws Exception {
		int resultRow=0;
		String sql = "INSERT INTO question_comment (question_code, emp_id, comment_memo, createdate)"
				+ " VALUES(?,?,?,now())";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, addQuestionComment.getQuestionCode());
		stmt.setString(2, addQuestionComment.getEmpId());
		stmt.setString(3, addQuestionComment.getCommentMemo());
		resultRow = stmt.executeUpdate();
		return resultRow;
	}
		
	// addQuestionComment (question 정보조회)
	// 사용하는 곳 : addQuestionCommentController	
	public ArrayList<Question> selectOrdersCode(Connection conn, int questionCode) throws Exception{
		ArrayList<Question> list = null;
		String sql = "SELECT q.question_code questionCode, q.orders_code ordersCode, q.category category"
				+ "				, q.question_memo questionMemo, q.createdate createdate"
				+ "	 FROM question_comment qc"
				+ "		 INNER JOIN question q"
				+ "		 ON qc.question_code = q.question_code"
				+ "	 WHERE q.question_code = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, questionCode);
		ResultSet rs = stmt.executeQuery();
		list = new ArrayList<Question>();
	    while(rs.next()) {
	    	Question q = new Question();
	    	q.setOrderCode(rs.getInt("ordersCode"));
	    	list.add(q);

	    }
		return list;
	}
		
	// questionCommentList 출력
	// 사용하는 곳 : questionCommentListController
	public ArrayList<HashMap<String, Object>> selectQuestionListByPage(Connection conn, int beginRow, int rowPerPage) throws Exception {
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String,Object>>();
		String sql = "SELECT r.rnum rnum, r.question_code questionCode, r.orders_code ordersCode, r.category category, r.question_memo questionMemo, r.createdate createdate"
				+ " , qc.comment_memo commentMemo, qc.createdate commentCreatedate, qc.emp_id empId"
				+ " 	FROM (SELECT ROW_NUMBER() OVER(ORDER BY question_code DESC) rnum"
				+ "				, question_code, orders_Code, category, question_memo, createdate FROM question ) r"
				+ "			LEFT OUTER JOIN question_comment qc"
				+ "			ON r.question_code = qc.question_code"
				+ " 	ORDER BY createdate DESC LIMIT ?, ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, beginRow);
		stmt.setInt(2, rowPerPage);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			HashMap<String, Object> q = new HashMap<String, Object>();
			q.put("questionCode", rs.getInt("questionCode"));
			q.put("ordersCode", rs.getInt("ordersCode"));
			q.put("category", rs.getString("category"));
			q.put("questionMemo", rs.getString("questionMemo"));
			q.put("createdate", rs.getString("createdate"));
			q.put("commentMemo", rs.getString("commentMemo"));
			q.put("commentCreatedate", rs.getString("commentCreatedate"));
			q.put("empId", rs.getString("empId"));
			list.add(q);
		}
		return list;
	}
	
	
	// questionCommentList 페이징
	// 사용하는 곳 : questionCommentListController
	public int count(Connection conn) throws Exception{
		int cnt = 0; // 전체 행의 수
		String sql = "SELECT COUNT(*) cnt FROM question_comment";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
	    if(rs.next()) {
	    	cnt = rs.getInt("cnt");
	    }
		return cnt;
	}
}
