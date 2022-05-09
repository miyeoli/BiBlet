package a.b.c.controller.auth;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
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
	public String loginForm(CommandLogin login, HttpSession session, HttpServletRequest request,
			@CookieValue(value = "REMEMBER", required = false) Cookie rememberCookie) throws Exception {

		if (session != null && session.getAttribute("authInfo") != null) {
			return "redirect:/";
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
	public String login(@Valid CommandLogin loginMember, Errors errors, Model model, HttpSession session, HttpServletResponse response) throws Exception {
		
	    /**
		 * 에러시 반환
		 */
		if (errors.hasErrors()) {
			return "auth/login";
		}

		MemberVO authInfo = null;
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
			Cookie rememberCookie = new Cookie("REMEMBER", null);
			rememberCookie.setMaxAge(0);
			rememberCookie.setPath("/");
			
			if (loginMember.isRememberId()) {
				rememberCookie = new Cookie("REMEMBER", authInfo.getMem_id());
				rememberCookie.setMaxAge(60 * 60 * 24 * 7);
			}
			
			response.addCookie(rememberCookie);

			return "redirect:/";

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
            return "auth/email_error";
        }
        
        model.addAttribute("findedId", findedId);
		return "findIdComplete";
		
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
}
