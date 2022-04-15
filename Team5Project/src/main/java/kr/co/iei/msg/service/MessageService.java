package kr.co.iei.msg.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import common.JDBCTemplate;
import kr.co.iei.member.vo.Member;
import kr.co.iei.msg.dao.MessageDao;
import kr.co.iei.msg.vo.Message;
import kr.co.iei.msg.vo.MessageList;
import kr.co.iei.msg.vo.MessageName;

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

	public int deleteMsg(int msgNo, String memberId) {
		Connection conn = JDBCTemplate.getConnection();
		MessageDao dao = new MessageDao();
		int result = 0;
		
		// 상대편이 해당메세지 삭제했는지 여부 체크
		Message msg = dao.selectMsg(conn, msgNo);
		int delChk = 2;

		if(memberId.equals(msg.getMsgSender())) {

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
		}	
		if(memberId.equals(msg.getMsgReceiver())) {
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
		
		// 전체 메세지갯수 리턴하기
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

	public MessageList selectMsgList(ArrayList<Message> allList, int pageNo) {
		// 최종보낼객체
		MessageList list = new MessageList();
		
		// 매개변수로 온 pageNo은 요청한 페이지
				
		int rowOfList = 5; // 페이지당 줄 수
		int totalRow = allList.size() ; // 총 사이즈
		int totalPage = 0; // 최종 페이지
		
		int prevRow = rowOfList*(pageNo-1); // 이전페이지의 끝
		int nextRow = rowOfList*pageNo+1; // 다음페이지의 시작
		
		if(allList.size()!=0) {
			// 공허한 리스트가 아니라면
			if(totalRow%rowOfList==0) {
				// 나머지가 0이면= 딱 떨어지면
				totalPage = totalRow / rowOfList;
			} else {
				// 나머지가 있으면 = 1페이지 추가
				totalPage = (totalRow / rowOfList) +1;
			}
		}
		
		// 요소 1개 보냄
		list.setTotalPage(totalPage);
		
		// 리스트 자르기
		ArrayList<Message> mainList = new ArrayList<Message>();
		
		// 경우1. 이전페이지보다 작을때
		if(totalRow<=prevRow) {
			
		} else if(totalRow>prevRow&&totalRow<nextRow) {
			// 경우2. 다음페이지에는 못미칠\
			List<Message> subList = allList.subList(prevRow, totalRow);
			mainList =  new ArrayList<Message>(subList);
	
		} else if(totalRow>prevRow&&totalRow>=nextRow) {
			List<Message> subList = allList.subList(prevRow, nextRow-1);
			mainList =  new ArrayList<Message>(subList);
		}	
		list.setList(mainList);		
		return list;
	}

	public int[] cancelAllMsg(String list) {
		int[] result = {0,0};

		
		String[] noList = list.split("/");
		for(int i=0;i<noList.length;i++) {
			int msgNo = Integer.parseInt(noList[i]);
			int cancelresult = cancelMsg(msgNo);
			if(cancelresult>0) {
				result[0]++;
			}else {
				result[1]++;
			}
		}		
		return result;
	}

	public int[] deleteAllMsg(String list, String memberId) {
		String[] noList = list.split("/");
		int[] result = {noList.length,0};
				
		for(int i=0;i<noList.length;i++) {
			int msgNo = Integer.parseInt(noList[i]);
			int deleteResult = deleteMsg(msgNo, memberId);
			if(deleteResult>0) {
				result[1] ++;
			}
		}
		return result;
	}

	public int checkAllMsg(String list, String memberId) {
		Connection conn = JDBCTemplate.getConnection();
		MessageDao dao = new MessageDao();
		
		String[] noList = list.split("/");
				
		for(int i=0;i<noList.length;i++) {
			int msgNo = Integer.parseInt(noList[i]);
			int readResult = dao.readMsg(conn, msgNo);
			if(readResult<=0) {
				JDBCTemplate.rollback(conn);
				JDBCTemplate.close(conn);
				return -3;
			}
		}
		JDBCTemplate.commit(conn);
		JDBCTemplate.close(conn);
		return 1;
	}

	public Message selectMsg(int msgNo) {
		// No 제시하면
		Connection conn = JDBCTemplate.getConnection();
		MessageDao dao = new MessageDao();
		Message msg = dao.selectMsg(conn, msgNo);
			 		JDBCTemplate.close(conn);
		return msg;
	}

	public int checkMsg(int msgNo) {
		// No 제시하면
		Connection conn = JDBCTemplate.getConnection();
		MessageDao dao = new MessageDao();
		
		// 메세지 읽음처리하고
		int result = dao.readMsg(conn, msgNo);
		
		if(result>0) {
			// 읽음처리 업뎃이 되면 커밋
			JDBCTemplate.commit(conn);
			
		}else {
			// 읽음처리 업뎃이 안되면 롤뱃
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int uncheckMsg(int msgNo) {
		// No 제시하면
		Connection conn = JDBCTemplate.getConnection();
		MessageDao dao = new MessageDao();
				
		// 메세지 읽음처리하고
		int result = dao.unreadMsg(conn, msgNo);
		
		if(result>0) {
			// 읽음처리 업뎃이 되면 커밋
			JDBCTemplate.commit(conn);
			
		}else {
			// 읽음처리 업뎃이 안되면 롤뱃
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public ArrayList<MessageName> selectUserList(String keyword) {
		Connection conn = JDBCTemplate.getConnection();
		MessageDao dao = new MessageDao();
		
		ArrayList<MessageName> list = dao.selectUserList(conn, keyword);
		
		JDBCTemplate.close(conn);
		return list;
	}

	public int countUnreadMsg(String memberId) {
		Connection conn = JDBCTemplate.getConnection();
		MessageDao dao = new MessageDao();
		
		int result = 0;
		
		result = dao.countUnreadMsg(conn, memberId);
		
		JDBCTemplate.close(conn);
		return result;
	}

}
