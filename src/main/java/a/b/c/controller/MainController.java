package a.b.c.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import a.b.c.model.AllCommentCmd;
import a.b.c.service.MainService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor // @Autowried
public class MainController {

	private final MainService mainService;

	/**
	 * 비로그인 메인 페이지
	 */
	@GetMapping("/Main")
	public String main(Model model) {
		
		// 최근 평가 불러오기
		List<AllCommentCmd> latestList = mainService.latestComment();

		// 인기 도서 불러오기
		List<String> popularList = mainService.popularList();

		// 총 평가 개수 불러오기
		Long allCommentCount = mainService.allCommentCount();

		model.addAttribute("popularList", popularList);
		model.addAttribute("latestList", latestList);
		model.addAttribute("allCommentCount", allCommentCount);

		return "/UnLoginMainPage";
	}

	/**
	 * 로그인 메인 페이지
	 */
	@GetMapping("/MainLogin")
	public String LoginMain(HttpSession session, HttpServletResponse response, Model model) {

		Long mem_num = (long) 6; // 테스트용 회원 번호
		
		// 로그인된 회원의 id 불러오기
		String myID = mainService.myID(mem_num);
		System.out.println(myID);
		
		// 로그인된 회원이 작성한 총 평가 개수 불러오기
		Long memCommentCount = mainService.memCommentCount(mem_num);
		
		// 로그인된 회원의 '찜' 도서 정보
		List<AllCommentCmd> myBookInfo = mainService.myBookInfo(mem_num);

		// 최근 평가 불러오기
		List<AllCommentCmd> latestList = mainService.latestComment();

		// 인기 도서 불러오기
		List<String> popularList = mainService.popularList();

		// 총 평가 개수 불러오기
		Long allCommentCount = mainService.allCommentCount();

		model.addAttribute("popularList", popularList);
		model.addAttribute("latestList", latestList);
		model.addAttribute("allCommentCount", allCommentCount);

		model.addAttribute("myID", myID);
		model.addAttribute("myCommentCount", memCommentCount);
		model.addAttribute("myBookInfo", myBookInfo);

		return "LoginMainPage";
	}

}
