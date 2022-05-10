package a.b.c.controller.auth;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import a.b.c.exception.AlreadyExistEmailException;
import a.b.c.exception.AlreadyExistIdException;
import a.b.c.model.MemberVO;
import a.b.c.service.MailSendService;
import a.b.c.service.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 사용자 회원가입 컨트롤러
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberRegisterController {
	
	private final RegisterService registerService;
	private final MailSendService mailSendService;

	/**
	 * 회원가입 폼
	 */
	@GetMapping("/signup")
	public String signupForm(@ModelAttribute("member") MemberVO member) {
		return "auth/signup";
	}

	/**
	 * 회원가입
	 */
	@PostMapping("/signup")
	public String signup(@Valid @ModelAttribute("member") MemberVO member, Errors errors) {

		if(errors.hasErrors()) {
			return "auth/signup";
		}

		try {
			/**
			 * 회원가입
			 */
			
			registerService.memRegister(member);

			/**
			 * 이메일 확인 메일 발송
			 * 	- random key 발급
			 */
			String authKey = mailSendService.sendAuthMail(member.getMem_email());
			member.setAuthkey(authKey);

			/**
			 * authKey 저장
			 */
			registerService.updateKey(member.getMem_email(), member.getAuthkey());

			return "auth/signup_check";

		} catch(AlreadyExistEmailException e) {
			errors.rejectValue("mem_email", "alreadyExistEmail");
			return "auth/signup";
		} catch(AlreadyExistIdException e) {
			errors.rejectValue("mem_id", "alreadyExistId");
			return "auth/signup";
		}
	}

	/**
	 * 이메일 인증 확인
	 */
	@GetMapping("/confirm")
	public String confirm(@ModelAttribute("member")MemberVO member) {
		member.setAuthstatus(1);
		registerService.updateStatus(member);
		return "auth/register_confirm";
	}
}
