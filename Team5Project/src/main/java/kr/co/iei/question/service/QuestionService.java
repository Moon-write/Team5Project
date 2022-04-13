package kr.co.iei.question.service;

import java.sql.Connection;
import java.util.ArrayList;

import common.JDBCTemplate;
import kr.co.iei.question.dao.QuestionDao;
import kr.co.iei.question.vo.Question;
import kr.co.iei.question.vo.QuestionPageData;

public class QuestionService {

	public QuestionPageData selectQuestionList(int reqPage) {
		//연결
		Connection conn = JDBCTemplate.getConnection();
		QuestionDao dao = new QuestionDao();
		/**
		 * 
		 * 페이징 처리
		 */
		//1. 결정사항 : 한 페이지당 게시물 수
		int numPerPage = 10;		
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
		String pageNavi = "<ul class='pagination circle-style'>";
		//이전버튼
		if(pageNo !=1) {
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='/noticeList.do?reqPage="+(pageNo-1)+"'>";
			pageNavi += "<span class='material-icons'>chevron_left</span>";
			pageNavi +=	"</a></li>";
		}
		//페이지숫자
		for(int i=0;i<pageNaviSize;i++) {
			if(pageNo == reqPage) {
				pageNavi += "<li>";
				pageNavi += "<a class='page-item active-page' href='/noticeList.do?reqPage="+pageNo+"'>";
				pageNavi += pageNo;
				pageNavi +=	"</a></li>";
			}else {
				pageNavi += "<li>";
				pageNavi += "<a class='page-item' href='/noticeList.do?reqPage="+pageNo+"'>";
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
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='/noticeList.do?reqPage="+(pageNo-1)+"'>";
			pageNavi += "<span class='material-icons'>chevron_right</span>";
			pageNavi +=	"</a></li>";
		}
		pageNavi += "</ul>";
		System.out.println(pageNavi);
		QuestionPageData qpd = new QuestionPageData(list, pageNavi);
						

		JDBCTemplate.close(conn);
		return qpd;
	}

}
