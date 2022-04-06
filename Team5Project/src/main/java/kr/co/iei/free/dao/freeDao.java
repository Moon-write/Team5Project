package kr.co.iei.free.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import kr.co.iei.free.vo.Free;
import kr.co.iei.free.vo.Freeboard;
import kr.co.iei.free.vo.FreeboardTable;

public class freeDao {

	public ArrayList<Free> selectFreeList(Connection conn, int Start, int End) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Free> list = new ArrayList<Free>();
		String query = "select * from (select rownum as rnum,n.* from (select * from FreeBoard_tbl order by free_no desc)n) where rnum between ? and ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1,  Start);
			pstmt.setInt(2,  End);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Free f = new Free();
				f.setFree_No(rset.getInt("free_no"));
				f.setFree_Id(rset.getString("free_id"));
				f.setFree_Title(rset.getString("free_title"));
				f.setFree_Content(rset.getString("free_content"));
				f.setFree_Count(rset.getInt("free_count"));
				f.setFree_Date(rset.getDate("free_date"));
				list.add(f);
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

	public int totalFreeBoardCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = "select count(*) as cnt from free_tbl";
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

	public ArrayList<FreeboardTable> selectFreeBoardTable(Connection conn, int start, int end) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<FreeboardTable> list = new ArrayList<FreeboardTable>();
		String query = "select * from (select rownum as rnum,n.* from (select * from FreeBoard_tbl order by free_no desc)n) where rnum between ? and ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1,  start);
			pstmt.setInt(2,  end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				FreeboardTable fbt = new FreeboardTable();
				fbt.setNo(rset.getInt("free_no"));
				fbt.setTitle(rset.getString("free_title"));
				fbt.setLikeCount(rset.getInt("free_count"));
				fbt.setDate(rset.getDate("free_date"));
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
