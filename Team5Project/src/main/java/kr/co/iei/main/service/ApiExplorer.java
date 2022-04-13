package kr.co.iei.main.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import kr.co.iei.main.vo.LiveData;

import java.io.IOException;

public class ApiExplorer {
	public ArrayList<LiveData> getData(int num) {
		// 오늘날짜와 일주일전 날짜 구하기
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String sdfToday = sdf.format(today);
		
		// 일주일전 날짜
		Date prevDay = new Date();
		prevDay = new Date(prevDay.getTime()+(1000*60*60*24*-6));
		String sdfPrevDay = sdf.format(prevDay);
		
		ArrayList<LiveData> list = parsingData(sdfToday, sdfPrevDay, num);
		
		return list;		
	}
	
	private ArrayList<LiveData> parsingData(String today, String prevday, int num){
		// 리턴용 리스트 생성
		ArrayList<LiveData> list = new ArrayList<LiveData>();
		
		// parsing 할 url 지정 (API키 포함)
		String url = "http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19InfStateJson?serviceKey=aZgnd9FXHlwr%2FaUjJKh8XgW7sh9DIiuxXVRki%2Beg6LzQC3GGaLH7uVm16NtYTSmJYE6tWEZ%2BaCiHGP31GFG%2Big%3D%3D&pageNo=1&numOfRows=1&startCreateDt="+prevday+"&endCreateDt="+today;
		
		// 페이지에 접근해줄 Document 객체 생성
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

		try {
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			org.w3c.dom.Document doc = dBuilder.parse(url);
			
			doc.getDocumentElement().normalize();
			
			// 파싱할 정보가 있는 tag에 접근
			// 여기서 파싱할 데이터는 item 태그 안에 있어서 여기로 접근해야함
			NodeList nList = doc.getElementsByTagName("item");
			// System.out.println("파싱할 리스트 수 : "+nList.getLength());

			// 리스트에 담긴 데이터 출력하기
			// 위 노드리스트를 반복문을 이용해 출력
			// getTextContent() : 전체 정보
			// getTagValue("tag",element) 입력한 tag 정보 (따로 메소드를 정의해줘야 한다~)
			
			for(int i=0; i<nList.getLength();i++) {
				Node nNode = nList.item(i);
				if(nNode.getNodeType()==Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					
					LiveData data= new LiveData();
					if(num==0) {
						// 분류값이 0이면 확진자수 리턴
						data.setCheckCount(getTagValue("decideCnt", eElement));						
					}else {
						// 분류값이 1이면 사망자수 리턴
						data.setCheckCount(getTagValue("deathCnt", eElement));						
					}
					String date = getTagValue("stateDt", eElement);
					data.setCheckDate(date.substring(4));
					
					list.add(data);
				}
			} // for문 종료
		} catch (SAXException | IOException | ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return list;
	}	
	
    // tag값의 정보를 가져오는 메소드
	private String getTagValue(String tag, Element eElement) {
		if(eElement.getElementsByTagName(tag).item(0)==null) {
			return null;
		}else {			
			NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
		    Node nValue = (Node) nlList.item(0);
		    if(nValue == null) 
		        return null;
		    return nValue.getNodeValue();
		}
	}
}