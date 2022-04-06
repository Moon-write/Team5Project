package kr.co.iei.msg.service;

import java.sql.Connection;
import java.util.ArrayList;

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
			JDBCTemplate.close(conn);
			// 실패리턴
			return null;
		}
		JDBCTemplate.close(conn);
		return msg;
	}

	public int deleteMsg(int msgNo, String deleteId) {
		Connection conn = JDBCTemplate.getConnection();
		MessageDao dao = new MessageDao();
		int result = 0;
		
		// 상대편이 해당메세지 삭제했는지 여부 체크
		Message msg = dao.selectMsg(conn, msgNo);
		int delChk = 2;
		String deleteFrom = null; // 보내는 사람이 누군지 확인
		
		if(deleteId.equals(msg.getMsgSender())) {
			// 보낸사람이 지우려면
			result = dao.updateDelMsgSender(conn, msgNo);
			if(msg.getMsgReceiverDel()==1) { // 받는사람도 지웠는지 체크
				delChk = dao.deleteMsg(conn, msgNo);
				
				if(delChk<=0) {
					JDBCTemplate.rollback(conn);
					JDBCTemplate.close(conn);
					return -3;
				}
			}
			
		} else if(deleteId.equals(msg.getMsgReceiver())) {
			// 받는사람이 지우려면
			result = dao.updateDelMsgReceiver(conn, msgNo);
			if(msg.getMsgSenderDel()==1) { // 보낸사람도 지웠는지 체크
				delChk = dao.deleteMsg(conn, msgNo);
				
				if(delChk<=0) {
					JDBCTemplate.rollback(conn);
					JDBCTemplate.close(conn);
					return -3;
				}
			}
		}
		
		if(result>0) {
			// 정상 삭제/업데이트되면
			JDBCTemplate.commit(conn);
		}else {
			// 안되면
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);		
		return result;
	}

	public ArrayList<Message> readAllMsg(String memberId, String msgBoardTitle) {
		Connection conn = JDBCTemplate.getConnection();
		MessageDao dao = new MessageDao();
		ArrayList<Message> list = null; 
		
		if(msgBoardTitle.equals("sendMsg")) {
			// 보낸편지함일때
			list = dao.readAllMsgSender(conn, memberId);
			
		}else if(msgBoardTitle.equals("receiveMsg")) {
			// 받은편지함일때
			list = dao.readAllMsgReceiver(conn, memberId);
		}
		
		JDBCTemplate.close(conn);
		return list;
	}

	public int cancelMsg(int msgNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result = 0;
		MessageDao dao = new MessageDao();
		
		// 수신확인
		Message msg = dao.selectMsg(conn, msgNo);
		if(msg.getMsgRead()==0) {
			// 안읽음
			result = dao.deleteMsg(conn, msgNo);
			if(result>0) {
				// 삭제완료시
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
		} else {
			// 읽음 - 삭제불가!
			result = -1;
		}		
		JDBCTemplate.close(conn);
		return result;
	}

}
