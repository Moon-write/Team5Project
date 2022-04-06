package kr.co.iei.free.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import kr.co.iei.free.vo.Free;
import kr.co.iei.free.vo.Freeboard;

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
		// TODO Auto-generated method stub
		return 0;
	}

	
}
