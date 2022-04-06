package kr.co.iei.msg.service;

import java.sql.Connection;

import common.JDBCTemplate;
import kr.co.iei.msg.dao.MessageDao;
import kr.co.iei.msg.vo.Message;

public class MessageService {

	public int newMsg(Message msg) {
		// 3칸짜리 msg객체 받아오기 (보낸이/받는이/내용)
		Connection conn = JDBCTemplate.getConnection();
		MessageDao dao = new MessageDao();
		
		int result = dao.newMsg(conn, msg);
		
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public Message readMsg(int msgNo) {
		// No 제시하면
		Connection conn = JDBCTemplate.getConnection();
		MessageDao dao = new MessageDao();
		Message msg = null;
		
		// 메세지 읽음처리하고
		int result = dao.readMsg(conn, msgNo);
		
		if(result>0) {
			// 읽음처리 업뎃이 되면 커밋
			JDBCTemplate.commit(conn);
			// 메세지 내용 가져오기
			 msg = dao.selectMsg(conn, msgNo);
			 
		}else {
			// 읽음처리 업뎃이 안되면 롤뱃
			JDBCTemplate.rollback(conn);
			
			// 실패리턴
			return null;
		}
		return msg;
	}

}
