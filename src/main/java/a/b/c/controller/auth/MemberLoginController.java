package a.b.c.controller.auth;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import a.b.c.model.MemberVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import a.b.c.exception.AuthstatusException;
import a.b.c.exception.IdPasswordNotMatchingException;
import a.b.c.model.CommandAdminLogin;
import a.b.c.model.CommandLogin;
import a.b.c.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberLoginController {
	
	private final LoginService loginService;

	/**
	 * 사용자 로그인 폼
	 */
	@GetMapping("/login")
	public String loginForm (CommandLogin login, HttpSession session,
			@CookieValue(value = "REMEMBER", required = false) Cookie rememberCookie) throws Exception {

		/**
		 * 세션 꺼내기
		 */
		Object authInfo = null;
		if (session != null) {
			authInfo = session.getAttribute("authInfo");
		}

		/**
		 * 로그인 정보가 없으면 main으로 리다이렉트
		 */
		if (authInfo != null) {
			return "redirect:/Main";
		}

		/**
		 * 쿠키에 REMEMBER가 있다면 꺼내서 반환
		 */
		if (rememberCookie != null) {
			login.setMem_id(rememberCookie.getValue());
			login.setRememberId(true);
		}

		return "auth/login";
	}

	@PostMapping("/login")
	public String login (@Valid CommandLogin loginMember, Model model, HttpSession session,
						 HttpServletResponse response, Errors errors) throws Exception {
		/**
		 * 에러시 반환
		 */
		if (errors.hasErrors()) {
			return "auth/login";
		}

		MemberVO authInfo = null;
		if (session != null) {
			session.getAttribute("authInfo");
		}

		if (authInfo != null) {
			return "redirect:/Main";
		}

		try {
			/**
			 * 로그인 인증하고 인증 객체 반환
			 */
			authInfo = loginService.authenticate(loginMember);

			/**
			 * 로그인 인증된 객체 세션 테이블에 저장
			 */
			session.setAttribute("authInfo", authInfo);

			/**
			 * 아이디 기억하기를 클릭했다면 쿠키에 아이디 저장
			 */
			if (loginMember.isRememberId()) {
				Cookie rememberCookie = new Cookie("REMEMBER", authInfo.getMem_id());
				rememberCookie.setPath("/");
				rememberCookie.setMaxAge(60 * 60 * 24 * 7);
				response.addCookie(rememberCookie);
			}

			return "redirect:/MainLogin";

		} catch (IdPasswordNotMatchingException e) {
			errors.rejectValue("mem_pass", "IdPasswordNotMatching");
			return "auth/login_error";
		} catch (AuthstatusException e) {
			return "auth/email_error";
		}
	}

	@GetMapping("/logout")
	public String logout (HttpSession session) {
		session.invalidate();
		return "redirect:/Main";
	}
}
