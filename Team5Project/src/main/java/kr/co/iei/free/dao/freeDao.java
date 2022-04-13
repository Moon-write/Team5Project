package kr.co.iei.free.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import common.JDBCTemplate;
import kr.co.iei.free.vo.Free;
import kr.co.iei.free.vo.FreeView;
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

	public ArrayList<FreeboardTable> selectFreeList3(Connection conn, int start, int end, String keyword) {
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
				fbt.setViewCount(rset.getInt("free_count"));
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
	public ArrayList<FreeboardTable> selectFreeList2(Connection conn, int start, int end, String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<FreeboardTable> list = new ArrayList<FreeboardTable>();
		String query = "select * from \r\n"
				+ "(select rownum as rnum,n.* \r\n"
				+ "from (select t1.free_no, t2.member_nickname,t1.free_title, t1.free_date, t1.free_count, \r\n"
				+ "(select count(*) from like2_tbl t3 where t3.free_no=t1.free_no) as likes \r\n"
				+ "from freeboard_tbl t1 inner join member_tbl t2 on t1.free_id = t2.member_id \r\n"
				+ "where (t2.member_nickname LIKE '%'||?||'%' or t1.free_title LIKE '%'||?||'%')\r\n"
				+ "order by t1.free_count desc)n)\r\n"
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
				fbt.setViewCount(rset.getInt("free_count"));
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

	public ArrayList<FreeboardTable> selectFreeList1(Connection conn, int start, int end, String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<FreeboardTable> list = new ArrayList<FreeboardTable>();
		String query = "select * from \r\n"
				+ "(select rownum as rnum,n.* \r\n"
				+ "from (select t1.free_no, t2.member_nickname,t1.free_title, t1.free_date, t1.free_count, \r\n"
				+ "(select count(*) from like2_tbl t3 where t3.free_no=t1.free_no) as likes \r\n"
				+ "from freeboard_tbl t1 inner join member_tbl t2 on t1.free_id = t2.member_id \r\n"
				+ "where (t2.member_nickname LIKE '%'||?||'%' or t1.free_title LIKE '%'||?||'%')\r\n"
				+ "order by likes desc)n)\r\n"
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
				fbt.setViewCount(rset.getInt("free_count"));
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
	public int insertFree(Connection conn, Free f) {
		PreparedStatement pstmt = null;
		String query = "insert into FreeBoard_tbl values(FreeBoard_seq.nextval,?,?,sysdate,0,?)";
		int result = 0;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, f.getFree_Id());
			pstmt.setString(2, f.getFree_Title());
			pstmt.setString(3, f.getFree_Content());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public FreeView freeView(Connection conn, int freeNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		FreeView view = new FreeView();
		String query = "select t1.free_no, t1.free_title, t2.member_nickname, t1.free_content, t1.free_date, t1.free_count,(select count(*) from like2_tbl t3 where t3.free_no=t1.free_no)likes \r\n"
				+ "from freeboard_tbl t1 inner join member_tbl t2 on t1.free_id = t2.member_id\r\n"
				+ "where t1.free_no = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, freeNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				view.setNo(rset.getInt("free_no"));
				view.setTitle(rset.getString("free_title"));
				view.setContents(rset.getString("free_content"));
				view.setWriter(rset.getString("member_nickname"));
				view.setDate(rset.getDate("free_date"));
				view.setLike(rset.getInt("likes"));
				view.setView(rset.getInt("free_count"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return view;
	}
	

	public int viewCountUpdate(Connection conn, int freeNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update freeboard_tbl set free_count = free_count+1 where free_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, freeNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public Free selectOneFree(Connection conn, int freeNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Free f = new Free();
		String query = "select * from FreeBoard_tbl where free_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, freeNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				f.setFree_No(rset.getInt("free_no"));
				f.setFree_Id(rset.getString("free_id"));
				f.setFree_Title(rset.getString("free_title"));
				f.setFree_Count(rset.getInt("free_count"));
				f.setFree_Date(rset.getDate("free_date"));
				f.setFree_Content(rset.getString("free_content"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return f;
	}

	public int freeBoardUpdate(Connection conn, Free f) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update FreeBoard_tbl set free_title=?, free_content=? where Free_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, f.getFree_Title());
			pstmt.setString(2, f.getFree_Content());
			pstmt.setInt(3, f.getFree_No());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	
}
