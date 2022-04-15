package kr.co.iei.msg.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import kr.co.iei.msg.vo.Message;
import kr.co.iei.msg.vo.MessageName;

public class MessageDao {
	
	private Message getMsg(ResultSet rset) {
		Message msg = new Message();
		try {
			msg.setMsgContent(rset.getString("MSG_CONTENT"));
			msg.setMsgDate(rset.getString("MSG_DATE"));
			msg.setMsgNo(rset.getInt("MSG_NO"));
			msg.setMsgRead(rset.getInt("MSG_READ"));
			msg.setMsgReceiver(rset.getString("MSG_RECEIVER"));
			msg.setMsgReceiverDel(rset.getInt("MSG_RECEIVER_DEL"));
			msg.setMsgSender(rset.getString("MSG_SENDER"));
			msg.setMsgSenderDel(rset.getInt("MSG_SENDER_DEL"));
			msg.setReceiverName(rset.getString("RECEIVER_NAME"));
			msg.setSenderName(rset.getString("SENDER_NAME"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return msg;
	}

	public int newMsg(Connection conn, Message msg) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "INSERT INTO MESSAGE_TBL VALUES(MSG_SEQ.NEXTVAL, ?, ?, ?, TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI'), 0, 0, 0)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, msg.getMsgSender());
			pstmt.setString(2, msg.getMsgReceiver());
			pstmt.setString(3, msg.getMsgContent());
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}		
		return result;
	}

	public int readMsg(Connection conn, int msgNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "UPDATE MESSAGE_TBL SET MSG_READ = 1 WHERE MSG_NO=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, msgNo);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}		
		return result;
	}

	public Message selectMsg(Connection conn, int msgNo) {
		PreparedStatement pstmt = null;
		Message msg = null;
		ResultSet rset = null;
		
		String query = "SELECT * FROM MSG_VIEW WHERE MSG_NO=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, msgNo);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				msg = getMsg(rset);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}		
		return msg;
	}

	public int updateDelMsgSender(Connection conn, int msgNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "UPDATE MESSAGE_TBL SET MSG_SENDER_DEL = 1 WHERE MSG_NO=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, msgNo);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	public int updateDelMsgReceiver(Connection conn, int msgNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "UPDATE MESSAGE_TBL SET MSG_RECEIVER_DEL = 1 WHERE MSG_NO=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, msgNo);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int deleteMsg(Connection conn, int msgNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "DELETE FROM MESSAGE_TBL WHERE MSG_NO=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, msgNo);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public ArrayList<Message> readAllMsgSender(Connection conn, String memberId) {
		// 보낸편지함 읽기
		PreparedStatement pstmt = null;
		ArrayList<Message> list = new ArrayList<Message>();
		ResultSet rset = null;
		String query = "SELECT * FROM (SELECT ROWNUM AS RNUM, MSG.* FROM (SELECT * FROM MSG_VIEW)MSG WHERE MSG_SENDER=? AND MSG_SENDER_DEL=0)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Message msg = getMsg(rset);				
				list.add(msg);
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
	
	public ArrayList<Message> readAllMsgReceiver(Connection conn, String memberId) {
		// 받은편지함 읽기
		PreparedStatement pstmt = null;
		ArrayList<Message> list = new ArrayList<Message>();
		ResultSet rset = null;
		String query = "SELECT * FROM (SELECT ROWNUM AS RNUM, MSG.* FROM (SELECT * FROM MSG_VIEW)MSG WHERE MSG_RECEIVER=? AND MSG_RECEIVER_DEL=0)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Message msg = getMsg(rset);				
				list.add(msg);
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

	public int unreadMsg(Connection conn, int msgNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "UPDATE MESSAGE_TBL SET MSG_READ = 0 WHERE MSG_NO=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, msgNo);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}		
		return result;
	}

	public ArrayList<MessageName> selectUserList(Connection conn, String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT MEMBER_ID, MEMBER_NICKNAME FROM MEMBER_TBL WHERE (MEMBER_ID LIKE '%'||?||'%') OR (MEMBER_NICKNAME LIKE '%'||?||'%')";
		ArrayList<MessageName> list = new ArrayList<MessageName>();
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, keyword);
			pstmt.setString(2, keyword);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				MessageName name = new MessageName();
				name.setMemberId(rset.getString("MEMBER_ID"));
				name.setMemberName(rset.getString("MEMBER_NICKNAME"));				
				list.add(name);
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

	public int countUnreadMsg(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		ResultSet rset= null;
		String query = "SELECT COUNT(*) AS CNT FROM MSG_VIEW WHERE (MSG_RECEIVER=?) AND (MSG_READ=0)";
	
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = rset.getInt("CNT");
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




	
	

	
	
}
