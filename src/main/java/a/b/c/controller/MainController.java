package a.b.c.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;

import a.b.c.model.AllCommentCmd;
import a.b.c.model.CommandLogin;
import a.b.c.model.MemberVO;
import a.b.c.service.MainService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor // @Autowried
public class MainController {

	private final MainService mainService;

	/**
	 * 메인 페이지
	 */
	@GetMapping("/Main")
	public String main(CommandLogin loginMember, Model model, 
			HttpSession session, HttpServletResponse response, Errors errors) {

		/**
		 * 에러시 반환
		 */
		if (errors.hasErrors()) {
			return "auth/login";
		}
		
		/**
		 * session에서 데이터를 꺼내 MemberVO객체에 저장
		 */
		MemberVO authInfo = null;
		if (session != null) {
			session.getAttribute("authInfo");
		}

		
		authInfo = (MemberVO) session.getAttribute("authInfo");
		
		/**
		 * 비 로그인
		 */
		if(authInfo == null) {
			
			// 최근 평가 불러오기
			List<AllCommentCmd> latestList = mainService.latestComment();

			// 인기 도서 불러오기
			List<String> popularList = mainService.popularList();

			// 총 평가 개수 불러오기
			Long allCommentCount = mainService.allCommentCount();
			
			//UnLogin 회원 메인 페이지
			model.addAttribute("popularList", popularList);
			model.addAttribute("latestList", latestList);
			model.addAttribute("allCommentCount", allCommentCount);
			
			return "redirect:/Main";
		}
		/**
		 * 로그인 
		 */
		
		// Long mem_num으로 변환
		Long mem_num = authInfo.getMem_num();
		
		// 세션 테이블에 다시 저장
		session.setAttribute("authInfo", authInfo);
		
		
		// 로그인된 회원이 작성한 총 평가 개수 불러오기
		Long memCommentCount = mainService.memCommentCount(mem_num);

		// 로그인된 회원의 '찜' 도서 정보
		List<AllCommentCmd> myBookInfo = mainService.myBookInfo(mem_num);

		
		

		//Login 회원 메인페이지
		model.addAttribute("myCommentCount", memCommentCount);
		model.addAttribute("myBookInfo", myBookInfo);
		
		

		return "Main";
	}

//	/**
//	 * 로그인 메인 페이지
//	 */
//	@SuppressWarnings("unused")
//	@GetMapping("/MainLogin")
//	public String LoginMain(CommandLogin loginMember, Model model, 
//			HttpSession session, HttpServletResponse response, Errors errors) {
//
//		/**
//		 * 에러시 반환
//		 */
//		if (errors.hasErrors()) {
//			return "auth/login";
//		}
//		
//		/**
//		 * session에서 데이터를 꺼내 MemberVO객체에 저장
//		 */
//		MemberVO authInfo = null;
//		if (session != null) {
//			session.getAttribute("authInfo");
//		}
//
//		if (authInfo != null) {
//			return "redirect:/MainLogin";
//		}
//		
//		authInfo = (MemberVO) session.getAttribute("authInfo");
//		
//		/**
//		 * Long mem_num으로 변환
//		 */
//		Long mem_num = authInfo.getMem_num();
//		
//		
//		/**
//		 * 세션 테이블에 다시 저장
//		 */
//		session.setAttribute("authInfo", authInfo);
//		
//		
////		// 로그인된 회원의 id 불러오기
////		String myID = mainService.myID(mem_num);
////		System.out.println(myID);
//
//		// 로그인된 회원이 작성한 총 평가 개수 불러오기
//		Long memCommentCount = mainService.memCommentCount(mem_num);
//
//		// 로그인된 회원의 '찜' 도서 정보
//		List<AllCommentCmd> myBookInfo = mainService.myBookInfo(mem_num);
//
//		// 최근 평가 불러오기
//		List<AllCommentCmd> latestList = mainService.latestComment();
//
//		// 인기 도서 불러오기
//		List<String> popularList = mainService.popularList();
//
//		// 총 평가 개수 불러오기
//		Long allCommentCount = mainService.allCommentCount();
//
//		model.addAttribute("popularList", popularList);
//		model.addAttribute("latestList", latestList);
//		model.addAttribute("allCommentCount", allCommentCount);
//
//		model.addAttribute("myID", myID);
//		model.addAttribute("myCommentCount", memCommentCount);
//		model.addAttribute("myBookInfo", myBookInfo);
//
//		return "LoginMainPage";
//	}

}
