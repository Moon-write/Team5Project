package kr.co.iei.main.service;

import java.sql.Connection;
import java.util.ArrayList;

import common.JDBCTemplate;
import kr.co.iei.main.dao.SurveyDao;
import kr.co.iei.main.vo.Survey;
import kr.co.iei.main.vo.SurveyResult;

public class SurveyService {

	public int newSurvey(Survey sv, String[] symptoms) {
		Connection conn = JDBCTemplate.getConnection();
		SurveyDao dao = new SurveyDao();
		
		for(int i=0;i<symptoms.length;i++) {
			// 배열에 담긴 value가 뭔지 파악해서
			String value = symptoms[i];
			
			switch(value) {
			case "1": sv.setSymptom1(1);
				break;
			case "2": sv.setSymptom2(1);
				break;
			case "3": sv.setSymptom3(1);
				break;
			case "4": sv.setSymptom4(1);
				break;
			case "5": sv.setSymptom5(1);
				break;
			case "6": sv.setSymptom6(1);
				break;
			case "7": sv.setSymptom7(1);
				break;
			}
			// 그 번호에 맞는 변수로 등록			
		}
		int result = dao.newSurvey(conn, sv);
		int result2 = 0;
		
		if(result>0) {
			// 서베이 참여여부 업데이트
			result2 = dao.updateSurvey(conn, sv.getSurveyId());
			
			if(result2>0) {
				JDBCTemplate.commit(conn);				
			}else {
				JDBCTemplate.rollback(conn);
			}			
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result2;
	}

	public SurveyResult surveyResult() {
		Connection conn = JDBCTemplate.getConnection();
		SurveyDao dao = new SurveyDao();
		SurveyResult result = new SurveyResult();
		
		// 각 증상별 경우의수/백신도 구하기
		ArrayList<Survey> list = dao.getSymptomResult(conn);
		result.setTotalCount(list.size());		

		for(int i=0;i<list.size();i++) {
			// 1번증상에 유증상이있으면
			if(list.get(i).getSymptom1()==1) { result.setSpt1Count(result.getSpt1Count()+1); }
			if(list.get(i).getSymptom2()==1) { result.setSpt2Count(result.getSpt2Count()+1); }
			if(list.get(i).getSymptom3()==1) { result.setSpt3Count(result.getSpt3Count()+1); }
			if(list.get(i).getSymptom4()==1) { result.setSpt4Count(result.getSpt4Count()+1); }
			if(list.get(i).getSymptom5()==1) { result.setSpt5Count(result.getSpt5Count()+1); }
			if(list.get(i).getSymptom6()==1) { result.setSpt6Count(result.getSpt6Count()+1); }
			if(list.get(i).getSymptom7()==1) { result.setSpt7Count(result.getSpt7Count()+1); }
			
			switch(list.get(i).getVaccinate()) {
			case 0: result.setVaccine0(result.getVaccine0()+1);
				break;
			case 1: result.setVaccine1(result.getVaccine1()+1);
				break;
			case 2: result.setVaccine2(result.getVaccine2()+1);
				break;
			case 3: result.setVaccine3(result.getVaccine3()+1);
				break;
			}
		}
		
		// 증상-확진일간 갭 구하기
		String dateGap = dao.getGapResult(conn);
		result.setDateGap(dateGap);
		
		JDBCTemplate.close(conn);		
		return result;
	}

	public ArrayList<String> storyResult(int readNum) {
		// readNum : 시작숫자
		
		Connection conn = JDBCTemplate.getConnection();
		SurveyDao dao = new SurveyDao();
		
		ArrayList<String> list = dao.getStoryResult(conn, readNum);		
		
		JDBCTemplate.close(conn);
		return list;
	}

}
