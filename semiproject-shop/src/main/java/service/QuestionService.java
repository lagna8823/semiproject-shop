package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.QuestionDao;
import util.DBUtil;
import vo.Customer;
import vo.Question;

public class QuestionService {
	private QuestionDao questionDao;
	
	// addQuestion (문의글 추가)
	// 사용하는 곳 : addQuestionController	
	public int addQuestion(Question addQuestion) {
		int resultRow = 0;
		this.questionDao = new QuestionDao();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			resultRow = questionDao.addQuestion(conn, addQuestion);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch(SQLException e1) {
				e1.printStackTrace();
			}
		}
		return resultRow;
	}
	
	// addQuestion (ordersCode조회)
	// 사용하는 곳 : addQuestionController	
	public ArrayList<Question> selectOrdersCode(Customer loginCustomer) {
		this.questionDao = new QuestionDao();
		ArrayList<Question> list = null;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			list = new ArrayList<Question>();
			
			questionDao = new QuestionDao();
			list = questionDao.selectOrdersCode(conn, loginCustomer);
			
			
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
	public ArrayList<Question> getQuestionListByPage(int beginRow, int rowPerPage) {
		this.questionDao = new QuestionDao();
		ArrayList<Question> list = null;
		Connection conn  = null;
		try {
			conn = DBUtil.getConnection();
			questionDao = new QuestionDao();
			list = questionDao.selectQuestionListByPage(conn, beginRow, rowPerPage);
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
			  this.questionDao = new QuestionDao();
			  int cnt = 0;
		      Connection conn = null;
		      try {
		         conn = DBUtil.getConnection();
		         questionDao = new QuestionDao();
		         cnt = questionDao.count(conn);
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
