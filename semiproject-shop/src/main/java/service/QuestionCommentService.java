package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import dao.QuestionCommentDao;
import dao.QuestionDao;
import util.DBUtil;
import vo.Customer;
import vo.Question;

public class QuestionCommentService {
	/* 답변 페이지는 관리자만 사용가능*/
	private QuestionCommentDao questionCommentDao;
	
	// addQuestionComment (question 정보조회)
	// 사용하는 곳 : addQuestionCommentController	
	public HashMap<String, Object> selectOrdersCode(int questionCode) {
		this.questionCommentDao = new QuestionCommentDao();
		ArrayList<Question> list = null;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			list = new ArrayList<Question>();
			
			questionCommentDao = new QuestionCommentDao();
			list = questionCommentDao.selectOrdersCode(conn, questionCode);
			
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch(SQLException e1) {
				e1.printStackTrace();
			}
		}
		return list;
	}
	
	// questionList 출력
	// 사용하는 곳 : questionListController
	public ArrayList<HashMap<String, Object>> getQuestionListByPage(int beginRow, int rowPerPage) {
		this.questionCommentDao = new QuestionCommentDao();
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String,Object>>();
		Connection conn  = null;
		try {
			conn = DBUtil.getConnection();
			questionCommentDao = new QuestionCommentDao();
			list = questionCommentDao.selectQuestionListByPage(conn, beginRow, rowPerPage);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch(SQLException e1){
				e1.printStackTrace();
			}
		}
		return list;
	}
	
	// questionList 페이징
	// 사용하는 곳 : questionListController
		public int count() {
			  this.questionCommentDao = new QuestionCommentDao();
			  int cnt = 0;
		      Connection conn = null;
		      try {
		         conn = DBUtil.getConnection();
		         questionCommentDao = new QuestionCommentDao();
		         cnt = questionCommentDao.count(conn);
		      } catch (Exception e) {
		         e.printStackTrace();
		      } finally {
		         try {
		            conn.close();
		         } catch (SQLException e1) {
		            e1.printStackTrace();
		         }
		      }
		      return cnt;
		}
}
