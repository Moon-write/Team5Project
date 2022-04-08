package kr.co.iei.question.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import kr.co.iei.question.vo.Question;

public class QuestionDao {

	public ArrayList<Question> selectQuestionList(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Question> list = new ArrayList<Question>();
		String query = "select * from question_tbl";
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Question q = new Question();
				q.setQuestionNo(rset.getInt("question_no"));
				q.setQuestionTitle(rset.getString("question_title"));
				q.setQuestionWriter(rset.getString("question_writer"));
				q.setQuestionContent(rset.getString("question_content"));
				q.setQuestionCount(rset.getInt("question_count"));
				q.setQuestionDate(rset.getString("qeustion_date"));
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

}
