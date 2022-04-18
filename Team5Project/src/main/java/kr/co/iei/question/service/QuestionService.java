package kr.co.iei.question.service;

import java.sql.Connection;
import java.util.ArrayList;

import common.JDBCTemplate;
import kr.co.iei.question.dao.QuestionDao;
import kr.co.iei.question.vo.Question;
import kr.co.iei.question.vo.QuestionComment;
import kr.co.iei.question.vo.QuestionPageData;
import kr.co.iei.question.vo.QuestionViewData;



public class QuestionService {

	private static final String Question = null;

	public QuestionPageData selectQuestionList(int reqPage) {
		//연결
		Connection conn = JDBCTemplate.getConnection();
		QuestionDao dao = new QuestionDao();
		/**
		 * 
		 * 페이징 처리
		 */
		//1. 결정사항 : 한 페이지당 게시물 수
		int numPerPage = 15;		
		int end = reqPage*numPerPage;
		int start = end - numPerPage + 1;
		
		ArrayList<Question> list = dao.selectQuestionList(conn,start,end);
		
		int totalCount = dao.totalQuestionCount(conn);
		//전체페이지 수
		int totalPage = 0;
		if(totalCount%numPerPage == 0) {
			totalPage = totalCount/numPerPage;
		}else {
			totalPage = totalCount/numPerPage + 1;
		}
		//페이지 네비게이션의 길이 지정
		int pageNaviSize = 5;
		//페이지 네비게이션 시작번호 계산
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize + 1;
		//페이지 네비게이션 제작 시작
		String pageNavi = "<ul class='pagination pagination-lg' style='justify-content: center'>";
		//이전버튼
		if(pageNo !=1) {
			pageNavi += "<li class='page-item'>";
			pageNavi += "<a class='page-link' href='/questionList.do?reqPage="+(pageNo-1)+"'>";
			pageNavi += "<span class='material-icons'>chevron_left</span>";
			pageNavi +=	"</a></li>";
		}
		//페이지숫자
		for(int i=0;i<pageNaviSize;i++) {
			if(pageNo == reqPage) {
				pageNavi += "<li class='page-item active'>";
				pageNavi += "<a class='page-link' href='/questionList.do?reqPage="+pageNo+"'>";
				pageNavi += pageNo;
				pageNavi +=	"</a></li>";
			}else {
				pageNavi += "<li class='page-item'>";
				pageNavi += "<a class='page-link' href='/questionList.do?reqPage="+pageNo+"'>";
				pageNavi += pageNo;
				pageNavi +=	"</a></li>";
			}
			pageNo++;
			if(pageNo > totalPage) {
				
				break;
			}
		}
		//다음버튼
		if(pageNo<=totalPage) {
			pageNavi += "<li class='page-item'>";
			pageNavi += "<a class='page-link' href='/questionList.do?reqPage="+pageNo+"'>";
			pageNavi += "<span class='material-icons'>chevron_right</span>";
			pageNavi +=	"</a></li>";
		}
		pageNavi += "</ul>";
		QuestionPageData qpd = new QuestionPageData(list, pageNavi);
		JDBCTemplate.close(conn);
		return qpd;
	}
	
	public int insertQuestion(Question q) {
		Connection conn = JDBCTemplate.getConnection();
		QuestionDao dao = new QuestionDao();
		int result = dao.insertQuestion(conn, q);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public QuestionViewData selectQuestionView(int questionNo) {
		Connection conn = JDBCTemplate.getConnection();
		QuestionDao dao = new QuestionDao();
		int result = dao.updateReadCount(conn, questionNo);
		//공지사항 정보
		Question q = dao.selectOneQuestion(conn, questionNo);
		//공지사항에 달려있는 일반댓글 조회
		ArrayList<QuestionComment> commentList = dao.selectQuestionComment(conn, questionNo);
		//공지사항에 달려있는 댓글의 댓글을 조회
		ArrayList<QuestionComment> reCommentList = dao.selectQuestionReComment(conn, questionNo);
		JDBCTemplate.close(conn);
		QuestionViewData qvd = new QuestionViewData(q, commentList, reCommentList);
		return qvd;
	}

	public Question getQuestion(int questionNo) {
		Connection conn = JDBCTemplate.getConnection();
		QuestionDao dao = new QuestionDao();
		Question q = dao.selectOneQuestion(conn, questionNo);
		JDBCTemplate.close(conn);
		return q;
	}

	public int insertQuestionComment(QuestionComment qc) {
		Connection conn = JDBCTemplate.getConnection();
		QuestionDao dao = new QuestionDao();
		int result = dao.insertQuestionComment(conn,qc);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int questionDelete(int questionNo) {
		Connection conn = JDBCTemplate.getConnection();
		QuestionDao dao = new QuestionDao();
		int result = dao.questionDelete(conn,questionNo);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int updateQuestionComment(QuestionComment qc) {
		Connection conn = JDBCTemplate.getConnection();
		QuestionDao dao = new QuestionDao();
		int result = dao.updateQuestionComment(conn, qc);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.close(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int deleteComment(int qcNo) {
		Connection conn = JDBCTemplate.getConnection();
		QuestionDao dao = new QuestionDao();
		int result = dao.deleteComment(conn, qcNo);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.close(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int insertAnswer(Question q) {
		Connection conn = JDBCTemplate.getConnection();
		QuestionDao dao = new QuestionDao();
		int result = dao.insertAnswer(conn, q);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public Question oneQuestionSelect(int questionNo) {
		Connection conn = JDBCTemplate.getConnection();
		QuestionDao dao = new QuestionDao();
		Question que = dao.OneQuestionSelect(conn, questionNo);
		JDBCTemplate.close(conn);
		return que;
	}
	
	public int questionUpdate(Question q) {
		Connection conn = JDBCTemplate.getConnection();
		QuestionDao dao = new QuestionDao();
		int result = dao.questionUpdate(conn,q);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public kr.co.iei.question.vo.Question selectOneQuestion(int questionNo) {
		Connection conn = JDBCTemplate.getConnection();
		QuestionDao dao = new QuestionDao();
		int result = dao.updateReadCount(conn, questionNo);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
			JDBCTemplate.close(conn);
			return null;
		}
		Question n = dao.selectOneNotice(conn, questionNo);
		JDBCTemplate.close(conn);
		return n;
	}

}
