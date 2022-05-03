package a.b.c.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import a.b.c.model.AllCommentCmd;
import a.b.c.model.CommandLogin;
import a.b.c.model.CompleteCmd;
import a.b.c.model.MemInfoUpdateCmd;
import a.b.c.model.MemberVO;
import a.b.c.model.PassCheckCmd;
import a.b.c.service.AppraisalService;
import a.b.c.service.LoginService;
import a.b.c.service.MypageService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor // @Autowried
public class MypageController {

	private final MypageService mypageService;
	private final AppraisalService appraisalService;
	private final LoginService loginService;

	/**
	 * 회원 정보 조회
	 */
	@GetMapping("/MyPage")
	public String memberInfo(HttpSession session, HttpServletResponse response, Model model) {

		if (session != null) {
			Object authInfo = session.getAttribute("authInfo");
			
			if (authInfo != null) {
				return "/LoginMainPage";
			}

			CommandLogin memInfo = (CommandLogin)authInfo;
			Long mem_num = memInfo.getMem_num();
			System.out.println("mem_num"+mem_num);
			
			MemberVO member = mypageService.memberInfo(mem_num);
			
			model.addAttribute("myInfo", member);
		}
		
		return "/Mypage";
	}

	/**
	 * 회원 정보 수정 폼
	 */
	@GetMapping("/edit")
	public String infoUpdateForm(Model model) {
		Long mem_num = (long) 6;

		MemberVO member = mypageService.memberInfo(mem_num);
		model.addAttribute("myInfo", member);
		return "/infoUpdate";
	}

	/**
	 * 회원 정보 수정
	 */
	@ResponseBody
	@PostMapping("/infoUpdate")
	public void infoUpdate(@RequestBody MemInfoUpdateCmd memInfoUpdateCmd) {
		MemberVO newInfo = new MemberVO();

		newInfo.setMem_name(memInfoUpdateCmd.getMem_name());
		newInfo.setMem_id(memInfoUpdateCmd.getMem_id());
		newInfo.setMem_pass(memInfoUpdateCmd.getMem_pass());
		newInfo.setMem_email(memInfoUpdateCmd.getMem_email());
		newInfo.setMem_num(memInfoUpdateCmd.getMem_num());

		mypageService.updateMemInfo(newInfo);
	}

	/**
	 * 탈퇴 폼
	 */
	@GetMapping("/delete")
	public String infoDeleteForm(Model model) {
		Long mem_num = (long) 6;

		MemberVO member = mypageService.memberInfo(mem_num);
		model.addAttribute("myInfo", member);
		return "/infoDelete";
	}

	/**
	 * 탈퇴
	 */
	@ResponseBody
	@PostMapping("/infoDelete")
	public void infoDelete(@RequestBody MemInfoUpdateCmd memInfoUpdateCmd) {

		mypageService.deleteMemInfo(memInfoUpdateCmd.getMem_num());
	}

	/**
	 * 비밀번호 확인
	 */
	@ResponseBody
	@PostMapping("/infoUpdatePassCheck")
	public int PassCheck(@RequestBody PassCheckCmd passCheckCmd) {
		if (passCheckCmd.getMem_pass().equals(passCheckCmd.getPassCheck())) {
			return 1;
		} else {
			return 0;
		}
	}

	// 보관함
	@GetMapping("/bookShelf")
	public String BookShelf(Model model) {
		// 테스트 하기 전마다 회원 등록 후 평가작성을 하지 않은 새로운 회원번호로 진행해야 함
		Long mem_num = (long) 6; // 테스트용 회원 번호
		MemberVO member = new MemberVO();

		member.setMem_num(mem_num);

		// 한 회원의 '찜' 도서 개수
		int memLikeCount = mypageService.memLikeCount(member.getMem_num());
		// 한 회원의 '찜' 도서 isbn 검색
		List<String> likeIsbn = mypageService.likeIsbn(mem_num);
		

		// 한 회원의 '보는 중' 도서 개수
		int memLeadingCount = mypageService.memLeadingCount(member.getMem_num());
		// 한 회원의 '보는 중' 도서 isbn 검색
		List<String> leadingIsbn = mypageService.leadingIsbn(mem_num);
		

		// 한 회원의 '독서 완료' 도서개수
		int memCommentCount = mypageService.memCommentCount(member.getMem_num());
		// 한 회원의 '독서 완료' 도서 isbn,평가번호 검색
		List<CompleteCmd> completeIsbn = mypageService.completeIsbn(mem_num);
			System.out.println(completeIsbn.get(0));
			System.out.println(completeIsbn.get(1));

		// 한 회원이 작성한 모든 평가 불러오기
		List<AllCommentCmd> memComment = mypageService.selectMemComment(mem_num);

		model.addAttribute("MyLikeCount", memLikeCount);
		model.addAttribute("likeIsbn", likeIsbn);

		model.addAttribute("MyLeadingCount", memLeadingCount);
		model.addAttribute("leadingIsbn", leadingIsbn);

		model.addAttribute("MyCommentCount", memCommentCount);
		model.addAttribute("MyComment", memComment);
		model.addAttribute("completeIsbn", completeIsbn);
		return "bookShelf";

	}

}
