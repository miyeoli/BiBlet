package a.b.c.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import a.b.c.model.AllCommentCmd;
import a.b.c.model.CommandLogin;
import a.b.c.model.MemberVO;
import a.b.c.service.MainService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class MainController {

	private final MainService mainService;

	/**
	 * 메인 페이지
	 */
	@GetMapping
	public String main(CommandLogin loginMember, Errors errors, Model model, 
			HttpSession session, HttpServletResponse response) {
		
		if (errors.hasErrors()) {
			return "auth/login";
		}
		
		model.addAttribute("popularList", mainService.popularList());
		model.addAttribute("latestList", mainService.latestComment());
		model.addAttribute("allCommentCount", mainService.allCommentCount());
		
		if (session == null || session.getAttribute("authInfo") == null) {
			return "LoginMain";
		}
		
		MemberVO authInfo = (MemberVO) session.getAttribute("authInfo");
		
		Long mem_num = authInfo.getMem_num();
		model.addAttribute("myID", mainService.myID(mem_num));
		model.addAttribute("myCommentCount", mainService.memCommentCount(mem_num));
		model.addAttribute("myBookInfo", mainService.myBookInfo(mem_num));
		
		return "LoginMain";
	}
}
