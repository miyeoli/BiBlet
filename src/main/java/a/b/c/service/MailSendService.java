package a.b.c.service;

import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.Random;

import javax.mail.MessagingException;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import a.b.c.utils.MailUtils;

@Service
public class MailSendService {

	/**
	 * 사용자 이메일 인증 폼 보내기
	 */
	public String sendAuthMail(String email) {
		String authKey = getAuthCode(6);

		try {
			MailUtils sendMail = new MailUtils();
			sendMail.setSubject("회원가입 이메일 인증");
			sendMail.setText(new StringBuffer().append("<h1>[이메일 인증]</h1>").append("<p>아래 링크를 클릭하시면 이메일 인증이 완료됩니다.</p>")
					.append("<a href='http://localhost:8080/member/confirm?mem_email=").append(email)
					.append("&authkey=").append(authKey).append("' target='_blenk'>이메일 인증 확인</a>").toString());
			sendMail.setFrom("dbflarla496695@gmail.com", "관리자");
			sendMail.setTo(email);
			sendMail.send();
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return authKey;
	}

	/**
	 * 관리자 이메일 인증 폼 보내기
	 */
	public String sendAdmAuthMail(String email) {
		String authKey = getAuthCode(6);

		try {
			MailUtils sendMail = new MailUtils();
			sendMail.setSubject("관리자가입 이메일 인증");
			sendMail.setText(new StringBuffer()
					.append("<h1>[이메일 인증]</h1>")
					.append("<p>아래 링크를 클릭하시면 이메일 인증이 완료됩니다.</p>")
					.append("<a href='http://localhost:8080/admin/confirm?adm_email=").append(email)
					.append("&adm_authkey=").append(authKey).append("' target='_blenk'>이메일 인증 확인</a>")
					.toString()
			);
			sendMail.setFrom("bookperi99@gmail.com", "관리자");
			sendMail.setTo(email);
			sendMail.send();
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return authKey;
	}

	/**
	 * 인증코드 생성
	 */
	private String getAuthCode(int size) {
		Random random = new Random();
		StringBuffer buffer = new StringBuffer();
		int num = 0;

		while (buffer.length() < size) {
			num = random.nextInt(10);
			buffer.append(num);
		}
		return buffer.toString();
	}
}
