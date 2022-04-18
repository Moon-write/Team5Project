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
		String query = "select * from(select rownum as qnum, q.*from(SELECT QUESTION_NO,QUESTION_WRITER,QUESTION_TITLE,QUESTION_CONTENT,QUESTION_COUNT,QUESTION_DATE,QUE_REF,(NVL(que_ref,QUESTION_NO)+(-0.01)*QUESTION_NO) FROM QUESTION_TBL ORDER BY (NVL(que_ref,QUESTION_NO)+(-0.01)*QUESTION_NO) DESC)q) where qnum between ? and ?";
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
				q.setQueRef((rset.getString("que_ref")==null?0:Integer.valueOf(rset.getInt("que_ref"))));
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
					q.setQueRef((rset.getString("que_ref")==null?0:Integer.valueOf(rset.getInt("que_ref"))));
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
		String query = "select * from question_comment where qc_ref=? and qc_comment_ref is null";
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
		String query = "select * from question_comment where qc_ref=? and qc_comment_ref is not null";
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

	public Question selectOneNotice(Connection conn, int questionNo) {
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
				q.setQueRef((rset.getString("que_ref")==null?0:Integer.valueOf(rset.getInt("que_ref"))));	
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

	public int insertQuestionComment(Connection conn, QuestionComment qc) {
		PreparedStatement pstmt = null;
		int result = 0;
		String  query = "insert into question_comment values(qc_comment_seq.nextval,?,?,?,to_char(sysdate,'yyyy-mm-dd'),?)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, qc.getQcRef());
			pstmt.setString(2, qc.getQcWriter());
			pstmt.setString(3, qc.getQcComment());
			/*
			if(nc.getNcRef() == 0) {
			
				pstmt.setString(4, null);
			}else {
				pstmt.setInt(4, nc.getNcRef());
			}
			*/
			pstmt.setString(4, (qc.getQcCommentRef()==0)?null:String.valueOf(qc.getQcCommentRef()));
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int questionDelete(Connection conn, int questionNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "delete from question_tbl where question_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, questionNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int updateQuestionComment(Connection conn, QuestionComment qc) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update question_comment set qc_comment=? where qc_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, qc.getQcComment());
			pstmt.setInt(2, qc.getQcNo());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int deleteComment(Connection conn, int qcNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "delete from question_comment where qc_no=?";	
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, qcNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int insertAnswer(Connection conn, Question q) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into question_tbl values(question_seq.nextval,?,?,?,0,to_char(sysdate,'yyyy-mm-dd'),?)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, q.getQuestionWriter());
			pstmt.setString(2, q.getQuestionTitle());
			pstmt.setString(3, q.getQuestionContent());
			pstmt.setInt(4, q.getQueRef());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public Question OneQuestionSelect(Connection conn, int questionNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Question que = null;
		String query = "select * from question_tbl where question_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, questionNo);
			rset= pstmt.executeQuery();
			if(rset.next()) {
				que = new Question();
				que.setQuestionNo(rset.getInt("question_no"));
				que.setQuestionWriter(rset.getString("question_writer"));
				que.setQuestionTitle(rset.getString("question_title"));
				que.setQuestionContent(rset.getString("question_content"));
				que.setQuestionCount(rset.getInt("question_count"));
				que.setQuestionDate(rset.getString("question_date"));
				que.setQueRef((rset.getString("que_ref")==null?0:Integer.valueOf(rset.getInt("que_ref"))));	
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return que;
	}

	public int questionUpdate(Connection conn, Question q) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update question_tbl set question_title=?, question_content=?  where question_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, q.getQuestionTitle());
			pstmt.setString(2, q.getQuestionContent());
			pstmt.setInt(3, q.getQuestionNo());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public ArrayList<Question> selectQuestion(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Question> list = new ArrayList<Question>();
		String query = "select * from(select rownum as qqnum, qq.*from(select*from question_tbl where que_ref is null order by question_no desc)qq)";
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Question q = new Question();
				q.setQuestionNo(rset.getInt("question_no"));
				q.setQuestionWriter(rset.getString("question_writer"));
				q.setQuestionTitle(rset.getString("question_title"));
				q.setQuestionContent(rset.getString("question_content"));
				q.setQuestionCount(rset.getInt("question_count"));
				q.setQuestionDate(rset.getString("question_date"));
				q.setQueRef((rset.getString("que_ref")==null?0:Integer.valueOf(rset.getInt("que_ref"))));	
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

	public ArrayList<Question> selectreQuestion(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Question> list = new ArrayList<Question>();
		String query = "select * from(select rownum as qqnum, qq.*from(select*from question_tbl where que_ref is not null order by question_no desc)qq)";
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Question qcc = new Question();
				qcc.setQuestionNo(rset.getInt("question_no"));
				qcc.setQuestionWriter(rset.getString("question_writer"));
				qcc.setQuestionTitle(rset.getString("question_title"));
				qcc.setQuestionContent(rset.getString("question_content"));
				qcc.setQuestionCount(rset.getInt("question_count"));
				qcc.setQuestionDate(rset.getString("question_date"));
				qcc.setQueRef(rset.getInt("que_ref"));	
				list.add(qcc);
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

	public int updateReadCount(Connection conn, int questionNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update question_tbl set question_count = question_count+1 where question_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, questionNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
}
