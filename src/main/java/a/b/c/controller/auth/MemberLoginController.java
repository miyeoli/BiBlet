package a.b.c.controller.auth;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import a.b.c.exception.AuthstatusException;
import a.b.c.exception.IdPasswordNotMatchingException;
import a.b.c.model.CommandLogin;
import a.b.c.model.MemberVO;
import a.b.c.service.LoginService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberLoginController {

	private final LoginService loginService;

	/**
	 * 사용자 로그인 폼
	 */
	@GetMapping("/login")
	public String loginForm(CommandLogin login, HttpSession session,
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
	public String login(Model model, HttpSession session, HttpServletResponse response,
			@Valid CommandLogin loginMember, Errors errors) throws Exception {
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
			
			Cookie rememberCookie = new Cookie("REMEMBER", authInfo.getMem_id());
			rememberCookie.setPath("/");
			if (loginMember.isRememberId()) {
			rememberCookie.setMaxAge(60 * 60 * 24 * 7); 
			} else {
				rememberCookie.setMaxAge(0);
			}
			response.addCookie(rememberCookie);

			return "redirect:/MainLogin";

		} catch (IdPasswordNotMatchingException e) {
			errors.rejectValue("mem_pass", "IdPasswordNotMatching");
			return "auth/login_error";
		} catch (AuthstatusException e) {
			return "auth/email_error";
		}
	}

	/**
	 * 아이디 찾기(회원)
	 */
	@GetMapping("/findId")
	public String findId() {
		return "findId";
	}

	@PostMapping("/findId")
	public String findid(MemberVO member, Model model, String mem_email) throws Exception {
		System.out.println("mem_email : "+ mem_email);
		
		String findedId = loginService.findById(mem_email);
		
        if(findedId == null) {
            return "auth/errorpage";
        }
        
        model.addAttribute("findedId", findedId);
		return "findIdComplete";
		
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/Main";
	}
}
