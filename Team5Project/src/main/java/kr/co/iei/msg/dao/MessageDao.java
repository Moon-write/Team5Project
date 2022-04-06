package kr.co.iei.msg.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import common.JDBCTemplate;
import kr.co.iei.msg.vo.Message;

public class MessageDao {

	public int newMsg(Connection conn, Message msg) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "INSERT INTO MESSAGE_TBL VALUES(MSG_SEQ.NEXTVAL, ?, ?, ?, TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MM'), 0, 0, 0)";
		
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
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
