package kr.co.iei.main.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import kr.co.iei.main.vo.Survey;

public class SurveyDao {

	public int newSurvey(Connection conn, Survey sv) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "INSERT INTO SURVEY VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, sv.getSurveyId());
			pstmt.setInt(2, sv.getSymptom1());
			pstmt.setInt(3, sv.getSymptom2());
			pstmt.setInt(4, sv.getSymptom3());
			pstmt.setInt(5, sv.getSymptom4());
			pstmt.setInt(6, sv.getSymptom5());
			pstmt.setInt(7, sv.getSymptom6());
			pstmt.setInt(8, sv.getSymptom7());
			pstmt.setString(9, sv.getPainDate());
			pstmt.setString(10, sv.getDecideDate());
			pstmt.setInt(11, sv.getVaccinate());
			pstmt.setString(12, sv.getStory());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public ArrayList<Survey> getSymptomResult(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM SURVEY ORDER BY ROWNUM DESC";
		ArrayList<Survey> list = new ArrayList<Survey>();
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Survey sv = new Survey();
				sv.setSymptom1(rset.getInt(2));
				sv.setSymptom2(rset.getInt(3));
				sv.setSymptom3(rset.getInt(4));
				sv.setSymptom4(rset.getInt(5));
				sv.setSymptom5(rset.getInt(6));
				sv.setSymptom6(rset.getInt(7));
				sv.setSymptom7(rset.getInt(8));
				sv.setVaccinate(rset.getInt("SURVEY_VACCINATE"));
				list.add(sv);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		
		return list;
	}

	public String getGapResult(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT ROUND(AVG(TO_DATE(SURVEY_DECIDEDATE)-TO_DATE(SURVEY_PAINDATE)),2) AS GAPDATE FROM SURVEY";
		String result = "";
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				if(rset.getString("GAPDATE").indexOf('.')==0) {
					result = "0"+rset.getString("GAPDATE");
				}else {
					result = rset.getString("GAPDATE");
				};
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return result;
	}

	public ArrayList<String> getStoryResult(Connection conn, int readNum) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM (SELECT ROWNUM AS RNUM, S.* FROM (SELECT SURVEY.* FROM SURVEY ORDER BY ROWNUM DESC)S) WHERE RNUM BETWEEN ? AND ?";
		ArrayList<String> list = new ArrayList<String>();
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, readNum);
			pstmt.setInt(2, readNum+2); // 3개씩 불러올거라 +2함
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				list.add(rset.getString("SURVEY_STORY"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}	
		return list;
	}

	public int updateSurvey(Connection conn, String surveyId) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "UPDATE MEMBER_TBL SET SURVEY_CHECK=1 WHERE MEMBER_ID=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, surveyId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

}
