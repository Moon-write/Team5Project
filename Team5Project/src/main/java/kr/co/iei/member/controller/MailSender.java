package kr.co.iei.member.controller;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import javax.mail.PasswordAuthentication;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session; //중요 이메일 API에서는 javax.mail.Session을 써야한다
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailSender {

	public boolean sendMail(String mailTitle, String mailContent, String receiver) {
		boolean result = false;
		//이메일 통신 설정
		Properties prop = System.getProperties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", 465);
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.ssl.enable", "true");
		prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		
		//인증정보설정(gmail 로그인)
		Session session = Session.getDefaultInstance(prop, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				PasswordAuthentication pa 
				= new PasswordAuthentication("kkamimus123123@gmail.com", "dhalzmfhs01@");
				return pa;
			}
		});
		//이메일을 작성해서 전송하는 객체 생성
		MimeMessage msg = new MimeMessage(session);
		
		//이메일작성
		try {
			//메일 전송 날짜 설정
			msg.setSentDate(new Date());
			//보내는사람 정보 설정
			msg.setFrom(new InternetAddress("kkamimus123123@gmail.com","위드 코로나"));
			//받는사람 정보 설정
			InternetAddress to = new InternetAddress(receiver);
			msg.setRecipient(Message.RecipientType.TO, to);
			//이메일 제목설정
			msg.setSubject(mailTitle,"UTF-8");
			//이메일 본문설정
			msg.setContent(mailContent,"text/html;charset=UTF-8");
			//이메일 전송
			Transport.send(msg);
			//전송이완료되면 전송결과 값 true로 변경
			result = true;
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return result;
	}

	public String sendMail(String email) {
		boolean result = false;
		//이메일 통신 설정
		Properties prop = System.getProperties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", 465);
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.ssl.enable", "true");
		prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		
		//인증정보설정(gmail 로그인)
		Session session = Session.getDefaultInstance(prop, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				PasswordAuthentication pa 
				= new PasswordAuthentication("kkamimus123123@gmail.com", "dhalzmfhs01@");
				return pa;
			}
		});
		//이메일을 작성해서 전송하는 객체 생성
		MimeMessage msg = new MimeMessage(session);
		//인증용 랜덤코드 생성
		Random r = new Random();
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<6; i++) {
			//숫자, 영어소문자, 영어 대문자를 랜덤으로 조합하여 6글자를 만들기위해 반복 6회
			int flag = r.nextInt(3);//0: 0~9사이에서 랜덤 1:A-Z 사이에서 랜덤 2: a-z사이 랜덤
			if(flag == 0) {
				//0~9
				int randomNum = r.nextInt(10);
				sb.append(randomNum);
			}else if(flag == 1) {
				//A~Z
				char randomChar = (char)(r.nextInt(26)+65);
				sb.append(randomChar);
			}else if(flag == 2) {
				//a~z
				char randomChar = (char)(r.nextInt(26)+97);
				sb.append(randomChar);
			}
		}
		
		//이메일작성
		try {
			//메일 전송 날짜 설정
			msg.setSentDate(new Date());
			//보내는사람 정보 설정
			msg.setFrom(new InternetAddress("kkamimus123123@gmail.com","위드 코로나"));
			//받는사람 정보 설정
			System.out.println("email : "+email);
			InternetAddress to = new InternetAddress(email);
			msg.setRecipient(Message.RecipientType.TO, to);
			//이메일 제목설정
			msg.setSubject("코로나 정보공유 커뮤니티 위드 코로나 인증메일입니다.","UTF-8");
			//이메일 본문설정
			msg.setContent("<h1>안녕하세요 위드 코로나 입니다.</h1>"
					+ "<h3>인증번호는 [<span style='color:red;'>"+sb.toString()+"</span>]입니다.</h3>"
							,"text/html;charset=UTF-8");
			//이메일 전송
			Transport.send(msg);
			//전송이완료되면 전송결과 값 true로 변경
			result = true;
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		if(result) {
			return sb.toString();
		}else {
			return null;
		}
	}
}
