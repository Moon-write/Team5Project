package kr.co.iei.question.service;

import java.sql.Connection;
import java.util.ArrayList;

import common.JDBCTemplate;
import kr.co.iei.question.dao.QuestionDao;
import kr.co.iei.question.vo.Question;

public class QuestionService {

	public ArrayList<Question> selectQuestionList() {
		Connection conn = JDBCTemplate.getConnection();
		QuestionDao dao = new QuestionDao();
		ArrayList<Question> list = dao.selectQuestionList(conn);
		return list;
	}

}
