package a.b.c.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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
	public String memberInfo(CommandLogin loginMember, Model model,
			HttpSession session, HttpServletResponse response, Errors errors) {

		/**
		 * 에러시 반환
		 */
		if (errors.hasErrors()) {
			return "Mypage";
		}

		/**
		 * session에서 데이터를 꺼내 MemberVO객체에 저장
		 */
		MemberVO authInfo = null;
		if (session != null) {
			session.getAttribute("authInfo");
		}

		if (authInfo != null) {
			return "redirect:/MainLogin";
		}

		authInfo = (MemberVO) session.getAttribute("authInfo");

		/**
		 * Long mem_num으로 변환
		 */
		Long mem_num = authInfo.getMem_num();

		/**
		 * 세션 테이블에 다시 저장
		 */
		session.setAttribute("authInfo", authInfo);

		MemberVO member = mypageService.memberInfo(mem_num);

		model.addAttribute("myInfo", member);

		return "/Mypage";
	}

	/**
	 * 회원 정보 수정 폼
	 */
	@GetMapping("/edit")
	public String infoUpdateForm(CommandLogin loginMember, Model model,
			HttpSession session, HttpServletResponse response, Errors errors) {
		
		/**
		 * 에러시 반환
		 */
		if (errors.hasErrors()) {
			return "Mypage";
		}

		/**
		 * session에서 데이터를 꺼내 MemberVO객체에 저장
		 */
		MemberVO authInfo = null;
		if (session != null) {
			session.getAttribute("authInfo");
		}

		if (authInfo != null) {
			return "redirect:/MainLogin";
		}

		authInfo = (MemberVO) session.getAttribute("authInfo");

		/**
		 * Long mem_num으로 변환
		 */
		Long mem_num = authInfo.getMem_num();

		/**
		 * 세션 테이블에 다시 저장
		 */
		session.setAttribute("authInfo", authInfo);
		
		MemberVO member = mypageService.memberInfo(mem_num);
		model.addAttribute("myInfo", member);
		return "/infoUpdate";
	}

	/**
	 * 회원 정보 수정
	 */
	@PostMapping("/infoUpdate")
	public String infoUpdate(@ModelAttribute("memInfoCmd") MemInfoUpdateCmd memInfoUpdateCmd,
			HttpServletRequest request, Model model) throws IllegalStateException, IOException {
		
		MemberVO newInfo = new MemberVO();
		
		MultipartFile multipartFile = memInfoUpdateCmd.getFile();
		
		newInfo.setMem_name(memInfoUpdateCmd.getMem_name());
		newInfo.setMem_id(memInfoUpdateCmd.getMem_id());
		newInfo.setMem_pass(memInfoUpdateCmd.getMem_passU());	//변경된 비밀번호
		newInfo.setMem_email(memInfoUpdateCmd.getMem_email());
		newInfo.setMem_num(memInfoUpdateCmd.getMem_num());

		mypageService.updateMemInfo(newInfo, multipartFile, request);
		
//		MemberVO member = 업데이트 반환 없음 하고 다시 정보 불러 
//		model.addAttribute("profile", member);
		
		return "redirect:/Mypage";
	}

	/**
	 * 탈퇴 폼
	 */
	@GetMapping("/delete")
	public String infoDeleteForm(CommandLogin loginMember, Model model,
			HttpSession session, HttpServletResponse response, Errors errors) {

		/**
		 * 에러시 반환
		 */
		if (errors.hasErrors()) {
			return "Mypage";
		}

		/**
		 * session에서 데이터를 꺼내 MemberVO객체에 저장
		 */
		MemberVO authInfo = null;
		if (session != null) {
			session.getAttribute("authInfo");
		}

		if (authInfo != null) {
			return "redirect:/MainLogin";
		}

		authInfo = (MemberVO) session.getAttribute("authInfo");

		/**
		 * Long mem_num으로 변환
		 */
		Long mem_num = authInfo.getMem_num();

		/**
		 * 세션 테이블에 다시 저장
		 */
		session.setAttribute("authInfo", authInfo);

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

	/**
	 * 보관함
	 */
	@GetMapping("/bookShelf")
	public String BookShelf(CommandLogin loginMember, Model model,
			HttpSession session, HttpServletResponse response, Errors errors) {
		
		/**
		 * 에러시 반환
		 */
		if (errors.hasErrors()) {
			return "UnLoginMainPage";
		}

		/**
		 * session에서 데이터를 꺼내 MemberVO객체에 저장
		 */
		MemberVO authInfo = null;
		if (session != null) {
			session.getAttribute("authInfo");
		}

		if (authInfo != null) {
			return "redirect:/MainLogin";
		}

		authInfo = (MemberVO) session.getAttribute("authInfo");

		/**
		 * Long mem_num으로 변환
		 */
		Long mem_num = authInfo.getMem_num();

		/**
		 * 세션 테이블에 다시 저장
		 */
		session.setAttribute("authInfo", authInfo);
		

		// 한 회원의 '찜' 도서 개수
		int memLikeCount = mypageService.memLikeCount(mem_num);
		// 한 회원의 '찜' 도서 isbn 검색
		List<String> likeIsbn = mypageService.likeIsbn(mem_num);

		// 한 회원의 '보는 중' 도서 개수
		int memLeadingCount = mypageService.memLeadingCount(mem_num);
		// 한 회원의 '보는 중' 도서 isbn 검색
		List<String> leadingIsbn = mypageService.leadingIsbn(mem_num);

		// 한 회원의 '독서 완료' 도서개수
		int memCommentCount = mypageService.memCommentCount(mem_num);
		// 한 회원의 '독서 완료' 도서 isbn,평가번호 검색
		List<CompleteCmd> completeIsbn = mypageService.completeIsbn(mem_num);

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
