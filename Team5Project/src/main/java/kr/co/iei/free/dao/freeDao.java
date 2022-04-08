package kr.co.iei.free.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import kr.co.iei.free.vo.Free;
import kr.co.iei.free.vo.FreeboardTable;

public class freeDao {

	public int totalFreeBoardCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = "select count(*) as cnt from FreeBoard_tbl";
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				result = rset.getInt("cnt");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
			
		}
		return result;
	}

	public ArrayList<FreeboardTable> selectFreeBoardTable(Connection conn, int start, int end, String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<FreeboardTable> list = new ArrayList<FreeboardTable>();
		String query = "select * from (select rownum as rnum,n.* from \r\n"
				+ "(select t1.free_no, t2.member_nickname,t1.free_title, t1.free_date, t1.free_count, \r\n"
				+ "(select count(*) from like2_tbl t3 where t3.free_no=t1.free_no) likes \r\n"
				+ "from freeboard_tbl t1 inner join member_tbl t2 on t1.free_id = t2.member_id \r\n"
				+ "where (t2.member_nickname LIKE ('%'||?||'%') or t1.free_title LIKE ('%'||?||'%')) \r\n"
				+ "order by free_no desc)n) \r\n"
				+ "where rnum between ? and ?";

				
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1,  keyword);
			pstmt.setString(2,  keyword);
			pstmt.setInt(3,  start);
			pstmt.setInt(4,  end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				FreeboardTable fbt = new FreeboardTable();
				fbt.setNo(rset.getInt("free_no"));
				fbt.setWriter(rset.getString("member_nickname"));
				fbt.setTitle(rset.getString("free_title"));
				fbt.setLikeCount(rset.getInt("free_count"));
				fbt.setDate(rset.getDate("free_date"));
				fbt.setLikeCount(rset.getInt("likes"));
				list.add(fbt);
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
