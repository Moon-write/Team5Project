package kr.or.iei.notice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import kr.or.iei.notice.vo.Notice;
import kr.or.iei.notice.vo.NoticeComment;

public class NoticeDao {
	//겹치지 않는 고정사항
	public ArrayList<Notice> selectNoticeList(Connection conn,String memberId, int start, int end) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Notice> list = new ArrayList<Notice>();
		String query = "select * from \r\n"
				+ "    (select rownum as rnum,\r\n"
				+ "            n.*,\r\n"
				+ "            (select count(*) from noticelike where like_no=n.notice_no)as likenumber,\r\n"
				+ "            (select count(*) from noticelike where like_no=n.notice_no and like_id=?)as clicklike\r\n"
				+ "     from (select * from notice where top_fixed=0 order by notice_no desc)n) \r\n"
				+ "where rnum between ? and ?";
		try {
			pstmt = conn.prepareStatement(query);
			//start, end변수를 넣어줬으니까 setting해주기
			pstmt.setString(1, memberId);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Notice n = new Notice();
				n.setNoticeNo(rset.getInt("notice_no"));
				n.setNoticeTitle(rset.getString("notice_title"));
				n.setNoticeWriter(rset.getString("notice_writer"));
				n.setNoticeContent(rset.getString("notice_content"));
				n.setReadCount(rset.getInt("read_count"));
				n.setRegDate(rset.getString("reg_date"));
				n.setFilename(rset.getString("filename"));
				n.setFilepath(rset.getString("filepath"));
				n.setTopFixed(rset.getInt("top_fixed"));
				n.setLikeNumber(rset.getInt("likenumber"));
				n.setClickLike(rset.getInt("clicklike"));
				list.add(n);
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

	public int totalNoticeCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = "select count(*) as cnt from notice";
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

	public int insertNotice(Connection conn, Notice n) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into notice values(notice_seq.nextval,?,?,?,0,to_char(sysdate,'yyyy-mm-dd'),?,?,?)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, n.getNoticeTitle());
			pstmt.setString(2, n.getNoticeWriter());
			pstmt.setString(3, n.getNoticeContent());
			pstmt.setString(4, n.getFilename());
			pstmt.setString(5, n.getFilepath());
			pstmt.setInt(6, n.getTopFixed());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int updateReadCount(Connection conn, int noticeNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update notice set read_count = read_count+1 where notice_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, noticeNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public Notice selectOneNotice(Connection conn, int noticeNo, String memberId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Notice n = null;
		String query = "select n.*, (select count(*) from noticelike where like_no=n.notice_no)as likenumber, (select count(*) from noticelike where like_no=n.notice_no and like_id=?)as clicklike from(select * from notice where notice_no=?)n";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			pstmt.setInt(2, noticeNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				n = new Notice();
				n.setNoticeNo(rset.getInt("notice_no"));
				n.setNoticeTitle(rset.getString("notice_title"));
				n.setNoticeWriter(rset.getString("notice_writer"));
				n.setNoticeContent(rset.getString("notice_content"));
				n.setReadCount(rset.getInt("read_count"));
				n.setRegDate(rset.getString("reg_date"));
				n.setFilename(rset.getString("filename"));
				n.setFilepath(rset.getString("filepath"));
				n.setTopFixed(rset.getInt("top_fixed"));
				n.setLikeNumber(rset.getInt("likenumber"));
				n.setClickLike(rset.getInt("clicklike"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return n;
	}

	public int noticeDelete(Connection conn, int noticeNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "delete from notice where notice_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, noticeNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int noticeUpdate(Connection conn, Notice n) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update notice set notice_title=?, notice_content=?, filename=?, filepath=? where notice_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, n.getNoticeTitle());
			pstmt.setString(2, n.getNoticeContent());
			pstmt.setString(3, n.getFilename());
			pstmt.setString(4, n.getFilepath());
			pstmt.setInt(5, n.getNoticeNo());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int insertNoticeComment(Connection conn, NoticeComment nc) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into notice_comment values(nc_seq.nextval,?,?,to_char(sysdate,'yyyy-mm-dd'),?,?)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, nc.getNcWriter());
			pstmt.setString(2, nc.getNcContent());
			pstmt.setInt(3, nc.getNoticeRef());
			pstmt.setString(4, (nc.getNcRef()==0)?null:String.valueOf(nc.getNcRef()));
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public ArrayList<NoticeComment> selectNoticeComment(Connection conn, int noticeNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<NoticeComment> list = new ArrayList<NoticeComment>();
		String query = "select * from notice_comment where notice_ref=? and nc_ref is null";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, noticeNo);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				NoticeComment nc = new NoticeComment();
				nc.setNcNo(rset.getInt("nc_no"));
				nc.setNcWriter(rset.getString("nc_writer"));
				nc.setNcContent(rset.getString("nc_content"));
				nc.setNcDate(rset.getString("nc_date"));
				nc.setNoticeRef(rset.getInt("notice_ref"));
				nc.setNcRef(rset.getInt("nc_ref"));
				list.add(nc);
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

	public ArrayList<NoticeComment> selectNoticeReComment(Connection conn, int noticeNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<NoticeComment> list = new ArrayList<NoticeComment>();
		String query = "select * from notice_comment where notice_ref=? and nc_ref is not null";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, noticeNo);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				NoticeComment nc = new NoticeComment();
				nc.setNcNo(rset.getInt("nc_no"));
				nc.setNcWriter(rset.getString("nc_writer"));
				nc.setNcContent(rset.getString("nc_content"));
				nc.setNcDate(rset.getString("nc_date"));
				nc.setNoticeRef(rset.getInt("notice_ref"));
				nc.setNcRef(rset.getInt("nc_ref"));
				list.add(nc);
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

	public int updateNoticeComment(Connection conn, NoticeComment nc) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update notice_comment set nc_content=? where nc_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, nc.getNcContent());
			pstmt.setInt(2, nc.getNcNo());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int deleteNoticeComment(Connection conn, int ncNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "delete from notice_comment where nc_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, ncNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int clicklike(Connection conn, int noticeNo, String memberId) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update notice set noticelike = noticelike+1 where notice_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			pstmt.setInt(2, noticeNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	//좋아요를 취소하는 dao메소드
	public int cancellike(Connection conn, int noticeNo, String memberId) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "DELETE FROM noticelike WHERE like_id = ? and like_no = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			pstmt.setInt(2, noticeNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	//좋아요를 클릭하는 dao메소드
	public int pluslike(Connection conn, int noticeNo, String memberId) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "INSERT INTO noticelike VALUES(?,?)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			pstmt.setInt(2, noticeNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int countlike(Connection conn, int noticeNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = "select count(*) as cnt from noticelike where like_no = ?";
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, noticeNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				result = rset.getInt("cnt");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public ArrayList<Notice> searchNoticeList(Connection conn, int start, int end, String select, String value,String memberId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Notice> list = new ArrayList<Notice>();
		
		String query = null;
		if(select.equals("noticeTitle")) {
			query = "select * from \r\n"
					+ "				     (select rownum as rnum,\r\n"
					+ "				             n.*,\r\n"
					+ "				             (select count(*) from noticelike where like_no=n.notice_no)as likenumber,\r\n"
					+ "				             (select count(*) from noticelike where like_no=n.notice_no and like_id=?)as clicklike\r\n"
					+ "				      from (select * from notice where notice_title like ? order by notice_no desc)n) \r\n"
					+ "				 where rnum between ? and ?";
		}else if(select.equals("noticeWriter")) {
			query = "select * from \r\n"
					+ "				     (select rownum as rnum,\r\n"
					+ "				             n.*,\r\n"
					+ "				             (select count(*) from noticelike where like_no=n.notice_no)as likenumber,\r\n"
					+ "				             (select count(*) from noticelike where like_no=n.notice_no and like_id=?)as clicklike\r\n"
					+ "				      from (select * from notice where notice_writer like ? order by notice_no desc)n) \r\n"
					+ "				 where rnum between ? and ?";
		}else if(select.equals("0")) {
			query = "select * from \r\n"
					+ "				     (select rownum as rnum,\r\n"
					+ "				             n.*,\r\n"
					+ "				             (select count(*) from noticelike where like_no=n.notice_no)as likenumber,\r\n"
					+ "				             (select count(*) from noticelike where like_no=n.notice_no and like_id=?)as clicklike\r\n"
					+ "				      from (select * from notice where (notice_title||notice_writer||notice_content) like ? order by notice_no desc)n) \r\n"
					+ "				 where rnum between ? and ?";
		}
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);			
			pstmt.setString(2, "%"+value+"%");
			pstmt.setInt(3, start);
			pstmt.setInt(4, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Notice n = new Notice();
				n.setNoticeNo(rset.getInt("notice_no"));
				n.setNoticeTitle(rset.getString("notice_title"));
				n.setNoticeWriter(rset.getString("notice_writer"));
				n.setNoticeContent(rset.getString("notice_content"));
				n.setReadCount(rset.getInt("read_count"));
				n.setRegDate(rset.getString("reg_date"));
				n.setFilename(rset.getString("filename"));
				n.setFilepath(rset.getString("filepath"));
				n.setTopFixed(rset.getInt("top_fixed"));
				n.setLikeNumber(rset.getInt("likenumber"));
				n.setClickLike(rset.getInt("clicklike"));
				list.add(n);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return list;
	}

	public int searchTotalNoticeCount(Connection conn, String select, String value) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = null;
		if(select.equals("noticeTitle")) {
			
			query = "select count(*) as cnt from(select * from notice where notice_title like ? order by notice_no desc)";
			
		}else if(select.equals("noticeWriter")){
			
			query = "select count(*) as cnt from(select * from notice where notice_writer like ? order by notice_no desc)";
			
		}else if(select.equals("0")){
			
			query = "select count(*) as cnt from(select * from notice where (notice_title||notice_writer||notice_content) like ? order by notice_no desc)";
		}
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+value+"%");
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
	//top_fixed가 1인 값을 찾는 dao
	public ArrayList<Notice> fixedList(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Notice> list = new ArrayList<Notice>();
		String query = "select * from \r\n"
				+ "            (select rownum as rnum,\r\n"
				+ "                     n.*,\r\n"
				+ "                        (select count(*) from noticelike where like_no=n.notice_no)as likenumber,\r\n"
				+ "                        (select count(*) from noticelike where like_no=n.notice_no and like_id= ? )as clicklike\r\n"
				+ "                 from (select * from (select * from notice where top_fixed=1) order by notice_no desc)n)";
		try {
			pstmt = conn.prepareStatement(query);
			//start, end변수를 넣어줬으니까 setting해주기
			pstmt.setString(1, memberId);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Notice n = new Notice();
				n.setNoticeNo(rset.getInt("notice_no"));
				n.setNoticeTitle(rset.getString("notice_title"));
				n.setNoticeWriter(rset.getString("notice_writer"));
				n.setNoticeContent(rset.getString("notice_content"));
				n.setReadCount(rset.getInt("read_count"));
				n.setRegDate(rset.getString("reg_date"));
				n.setFilename(rset.getString("filename"));
				n.setFilepath(rset.getString("filepath"));
				n.setTopFixed(rset.getInt("top_fixed"));
				n.setLikeNumber(rset.getInt("likenumber"));
				n.setClickLike(rset.getInt("clicklike"));
				list.add(n);
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
