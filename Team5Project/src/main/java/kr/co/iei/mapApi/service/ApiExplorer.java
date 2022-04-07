package kr.co.iei.mapApi.service;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import kr.co.iei.mapApi.vo.Clinic;

import java.io.BufferedReader;
import java.io.IOException;

public class ApiExplorer {
    public static String main(String pageNo, String numOfRows) throws IOException {
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B551182/rprtHospService/getRprtHospService"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=aZgnd9FXHlwr%2FaUjJKh8XgW7sh9DIiuxXVRki%2Beg6LzQC3GGaLH7uVm16NtYTSmJYE6tWEZ%2BaCiHGP31GFG%2Big%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode(pageNo, "UTF-8")); /*페이지번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode(numOfRows, "UTF-8")); /*한 페이지 결과 수*/
      
        URL url = new URL(urlBuilder.toString());
        
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        // System.out.println(sb.toString());
        return sb.toString();
    }

	public ArrayList<Clinic> getData(String sidoNm, String sgguNm, String detailAddr, int requestPage) {
		// sidoNm/sgguNm이 일치하고 detailAddr을 포함하는 리스트의 requestPage를 불러온다.
		// 한 페이지당 리스트 수
		int pageRow = 10;
		int listRow = requestPage*pageRow;
		
		// requestPage*pageRow 만큼 리턴받기 = 리스트 이만큼 리턴받으면 됨
		ArrayList<Clinic> list = parsingData(sidoNm, sgguNm, detailAddr, listRow);

		// 리스트 뒤로부터 pageRow만큼 빼오기
		List<Clinic> subList = list.subList(listRow-pageRow, listRow);
		ArrayList<Clinic> answerList = new ArrayList<Clinic>(subList);
		
		return answerList;
	}
	
	private ArrayList<Clinic> parsingData(String sidoNm, String sgguNm, String detailAddr, int listRow){
		// 1페이지부터 시작, 목표하는 데이터를 모을때까지 페이지를 계속 올려가면서 루틴을 계속함
		int pageNo = 1; // 이 숫자는 사용자가 요청한 페이지No와 아무런 상관도없음
		
		// 리턴용 리스트 생성
		ArrayList<Clinic> list = new ArrayList<Clinic>();
		
		// 리스트 사이즈가 목표치가 될때까지만 함수 실행
		while(true) {
			// parsing 할 url 지정 (API키 포함)
			String url = "http://apis.data.go.kr/B551182/rprtHospService/getRprtHospService?serviceKey=aZgnd9FXHlwr%2FaUjJKh8XgW7sh9DIiuxXVRki%2Beg6LzQC3GGaLH7uVm16NtYTSmJYE6tWEZ%2BaCiHGP31GFG%2Big%3D%3D&pageNo="+pageNo+"&numOfRows=1000";
			
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
						
						// 편의를위해 조건 boolean형으로 다 꺼냅니다
						boolean sido = sidoNm.equals(getTagValue("sidoCdNm", eElement)); 
						boolean sggu = getTagValue("sgguCdNm", eElement).contains(sgguNm);
						boolean detail = false;
						if(detailAddr==null) {
							detail = true;
						} else if(getTagValue("addr", eElement).contains(detailAddr)) {
							detail = true;
						}											
						// 요구하는 detailAddr을 포함해야한다
						if(sido&sggu&detail){
							Clinic c = new Clinic();
							c.setClinicAddr(getTagValue("addr", eElement));
							c.setClinicCode(getTagValue("recuClCd", eElement));
							c.setClinicName(getTagValue("yadmNm", eElement));
							c.setPcrAble(getTagValue("pcrPsblYn", eElement));
							c.setRatAble(getTagValue("ratPsblYn", eElement));
							c.setSgguNm(sgguNm);
							c.setSidoNm(sidoNm);
							c.setxPos(getTagValue("XPosWgs84", eElement));
							c.setyPos(getTagValue("YPosWgs84", eElement));
							list.add(c);
						}
					}
					if(list.size()==listRow) {
						break;
					}
				} // for문 종료
			} catch (SAXException | IOException | ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(list.size()==listRow) {
				break;
			}
			// 한바퀴를 다 돌았으면 pageNo 추가
			pageNo++;
		} // while문 종료
		
		return list;
	}	
	
    // tag값의 정보를 가져오는 메소드
	private String getTagValue(String tag, Element eElement) {
	    NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
	    Node nValue = (Node) nlList.item(0);
	    if(nValue == null) 
	        return null;
	    return nValue.getNodeValue();
	}
    
}