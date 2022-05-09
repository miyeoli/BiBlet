package a.b.c.controller.auth;

import a.b.c.exception.AuthstatusException;
import a.b.c.exception.IdPasswordNotMatchingException;
import a.b.c.model.AdministratorVO;
import a.b.c.model.CommandAdminLogin;
import a.b.c.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminLoginController {

    private final LoginService loginService;

    /**
     * 관리자 로그인 폼
     */
    @GetMapping("/login")
    public String adminLoginForm (
             CommandAdminLogin adminLoginCommand, HttpSession session,
            @CookieValue(value = "REMEMBER", required = false) Cookie rememberCookie) throws Exception {

        AdministratorVO adminAuthInfo = null;
        if (session != null) {
            session.getAttribute("adminAuthInfo");
        }

        if (adminAuthInfo != null) {
            return "redirect:/Main";
        }

        if (rememberCookie != null) {
            adminLoginCommand.setAdm_id(rememberCookie.getValue());
            adminLoginCommand.setRememberAdmId(true);
        }

        return "auth/admin_login";
    }

    /**
     * 관리자 로그인
     */
    @PostMapping("/login")
    public String adminLogin (HttpSession session, HttpServletResponse response, 
    		@Valid CommandAdminLogin adminLoginCommand, Errors errors) throws Exception {

        if (errors.hasErrors()) {
            return "auth/admin_login";
        }

        try {
            /**
             * 관리자 인증
             */
            AdministratorVO adminAuthInfo = loginService.adminAuthenticate(adminLoginCommand);

            /**
             * 인증 완료시 세션에 관리자 정보 저장
             */
            session.setAttribute("adminAuthInfo", adminAuthInfo);
            session.setAttribute("adm_id", adminAuthInfo.getAdm_id());

            /**
             * 아이디 기억하기를 클릭했다면 쿠키 생성
             */
            
                Cookie rememberCookie = new Cookie("REMEMBER", adminLoginCommand.getAdm_id());
                rememberCookie.setPath("/");
                if (adminLoginCommand.isRememberAdmId()) {
                rememberCookie.setMaxAge(60 * 60 * 24 * 7);
                } else {
    				rememberCookie.setMaxAge(0);
                }
                response.addCookie(rememberCookie);

            return "redirect:/adminPage";

        } catch (IdPasswordNotMatchingException e) {
            errors.rejectValue("adm_pass", "IdPasswordNotMatching");
            System.out.println("실패");
            return "auth/login_error";
        } catch (AuthstatusException e) {
            return "auth/email_error";
        }
    }
    
}
