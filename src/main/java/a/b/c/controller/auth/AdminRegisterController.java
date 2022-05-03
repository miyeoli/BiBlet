package a.b.c.controller.auth;

import a.b.c.exception.AlreadyExistEmailException;
import a.b.c.exception.AlreadyExistIdException;
import a.b.c.exception.SecurityCodeException;
import a.b.c.model.AdministratorVO;
import a.b.c.service.MailSendService;
import a.b.c.service.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

/**
 * 관리자 회원가입 컨트롤러
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminRegisterController {

    private final RegisterService registerService;
    private final MailSendService mailSendService;

    /**
     * 관리자 회원가입 폼
     */
    @GetMapping("/signup")
    public String adminSignupForm(@ModelAttribute("admin") AdministratorVO admin) {
        return "auth/admin_signup";
    }

    /**
     * 관리자 회원가입
     */
    @PostMapping("/signup")
    public String adminSignup(
            @Valid @ModelAttribute("admin") AdministratorVO admin,
            Errors errors) {
        /**
         * 에러시 반환
         */
        if(errors.hasErrors()) {
            return "auth/admin_signup";
        }

        try {
            /**
             * 회원가입
             */
            registerService.admRegister(admin);

            /**
             * 이메일 인증 메일 발송
             */
            String authKey = mailSendService.sendAdmAuthMail(admin.getAdm_email());
            admin.setAdm_authkey(authKey);

            /**
             * 이메일 인증키 업데이트
             */
            registerService.updateAdmKey(admin.getAdm_email(), admin.getAdm_authkey());
            return "auth/signup_check";
        }catch(AlreadyExistEmailException e) {
            errors.rejectValue("adm_email", "alreadyExistEmail");
            return "auth/admin_signup";
        }catch(AlreadyExistIdException e) {
            errors.rejectValue("adm_id", "alreadyExistId");
            return "auth/admin_signup";
        }catch(SecurityCodeException e) {
            errors.rejectValue("securitycode", "SecurityCodeException");
            return "auth/admin_signup";
        }
    }

    /**
     * 이메일 인증 확인
     */
    @GetMapping("/confirm")
    public String admConfirm(@ModelAttribute("admin") AdministratorVO admin) {
        admin.setAdm_authstatus(1);
        registerService.updateAdmStatus(admin);
        return "auth/register_confirm";
    }
}
