package kr.co.iei.question.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import kr.co.iei.question.vo.Question;
import kr.co.iei.question.vo.QuestionComment;

public class QuestionDao {

	public ArrayList<Question> selectQuestionList(Connection conn, int start, int end) {
		//통로
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Question> list = new ArrayList<Question>();
		String query = "select * from(select rownum as qnum,q. *from(select*from question_tbl order by question_no desc)q) where qnum between ? and ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Question q = new Question();
				q.setQuestionNo(rset.getInt("question_no"));
				q.setQuestionWriter(rset.getString("question_writer"));
				q.setQuestionTitle(rset.getString("question_title"));
				q.setQuestionContent(rset.getString("question_content"));
				q.setQuestionCount(rset.getInt("question_count"));
				q.setQuestionDate(rset.getString("question_date"));
				q.setQueRef(rset.getInt("que_ref"));
				list.add(q);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	public int totalQuestionCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = "select count(*) as cnt3 from question_tbl";
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				result = rset.getInt("cnt3");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int insertQuestion(Connection conn, Question q) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into question_tbl values(question_seq.nextval,?,?,?,0,to_char(sysdate,'yyyy-mm-dd'),?)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, q.getQuestionWriter());
			pstmt.setString(2, q.getQuestionTitle());
			pstmt.setString(3, q.getQuestionContent());
			pstmt.setString(4, (q.getQueRef()==0)?null:String.valueOf(q.getQueRef()));
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public Question selectOneQuestion(Connection conn, int questionNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Question q = null;
		String query = "select * from question_tbl where question_no=?";
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, questionNo);
				rset= pstmt.executeQuery();
				if(rset.next()) {
					q = new Question();
					q.setQuestionNo(rset.getInt("question_no"));
					q.setQuestionWriter(rset.getString("question_writer"));
					q.setQuestionTitle(rset.getString("question_title"));
					q.setQuestionContent(rset.getString("question_content"));
					q.setQuestionCount(rset.getInt("question_count"));
					q.setQuestionDate(rset.getString("question_date"));
					q.setQueRef(rset.getInt("que_ref"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				JDBCTemplate.close(rset);
				JDBCTemplate.close(pstmt);
			}
			return q;
	}

	public ArrayList<QuestionComment> selectQuestionComment(Connection conn, int questionNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<QuestionComment> list = new ArrayList<QuestionComment>();
		String query = "select * from question_comment where que_ref=? and qc_comment_ref is null";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, questionNo);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				QuestionComment qc = new QuestionComment();
				qc.setQcNo(rset.getInt("qc_no"));
				qc.setQcRef(rset.getInt("qc_ref"));
				qc.setQcWriter(rset.getString("qc_writer"));
				qc.setQcComment(rset.getString("qc_comment"));
				qc.setQcDate(rset.getString("qc_date"));
				qc.setQcCommentRef(rset.getInt("qc_comment_ref"));
				list.add(qc);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	public ArrayList<QuestionComment> selectQuestionReComment(Connection conn, int questionNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<QuestionComment> list = new ArrayList<QuestionComment>();
		String query = "select * from question_comment where que_ref=? and qc_comment_ref is not null";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, questionNo);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				QuestionComment qc = new QuestionComment();
				qc.setQcNo(rset.getInt("qc_no"));
				qc.setQcRef(rset.getInt("qc_ref"));
				qc.setQcWriter(rset.getString("qc_writer"));
				qc.setQcComment(rset.getString("qc_comment"));
				qc.setQcDate(rset.getString("qc_date"));
				qc.setQcCommentRef(rset.getInt("qc_comment_ref"));
				list.add(qc);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}
}
