package a.b.c.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.SystemPropertyUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import a.b.c.model.AppraisalVO;
import a.b.c.model.BookShelfVO;
import a.b.c.model.CommandLogin;
import a.b.c.model.DeleteCmd;
import a.b.c.model.InsertCmd;
import a.b.c.model.MemberVO;
import a.b.c.model.PassCheckCmd;
import a.b.c.model.UpdateCmd;
import a.b.c.model.AllCommentCmd;
import a.b.c.service.AppraisalService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor // @Autowried
public class AppraisalController {

	private final AppraisalService appraisalService;

	/**
	 * 도서 상세보기 - 해당 도서의 대한 모든 평가 추출
	 */
	@GetMapping("/read/{isbn}")
	public String bookDetailAndComment(CommandLogin loginMember, Model model,
			HttpSession session, HttpServletResponse response, Errors errors, 
			@RequestParam(required = false) String query, 
			@PathVariable String isbn) {

		// 해당 도서의 대한 평가 개수
		int commentCount = appraisalService.commentCount(isbn);

		// 해당 도서의 대한 모든 평가 불러오기
		List<AllCommentCmd> commentsByMembers = appraisalService.findAllComment(isbn);
							
		model.addAttribute("query", query);	//query.split(",")[0]
		model.addAttribute("commentCount", commentCount);
		model.addAttribute("commentsByMembers", commentsByMembers);

		return "detailAndComment";
	}

	/**
	 * 평가 등록
	 */
	@PostMapping("/read/{isbn}")
	private String writeComment(@ModelAttribute("insertCmd") InsertCmd insertCmd,
			CommandLogin loginMember, Model model,
			HttpSession session, HttpServletResponse response, Errors errors) throws UnsupportedEncodingException {
		
		AppraisalVO appraisal = new AppraisalVO();
		BookShelfVO bookShelf = new BookShelfVO();
		String encodedParam = URLEncoder.encode(insertCmd.getQuery(), "UTF-8");

		
		/**
		 * 에러시 반환
		 */
		if (errors.hasErrors()) {
			return "detailAndComment";
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
		

		insertCmd.setIsbn(insertCmd.getIsbn().substring(0, 10));
		
		String query = insertCmd.getQuery();
		String redirectquery = query.substring(query.lastIndexOf(",")+1);

		bookShelf.setBook_status(insertCmd.getOption());
		bookShelf.setMem_num(mem_num);
		bookShelf.setIsbn(insertCmd.getIsbn());
		bookShelf = appraisalService.insertBookShelf(bookShelf);
		
		appraisal.setStar(insertCmd.getStar());
		appraisal.setBook_comment(insertCmd.getBook_comment());
		appraisal.setStart_date(insertCmd.getStart_date());
		appraisal.setEnd_date(insertCmd.getEnd_date());
		appraisal.setCo_prv(insertCmd.getCo_prv());
		appraisal.setBook_status_num(bookShelf.getBook_status_num());

		appraisalService.writeComment(appraisal);
		
		return "redirect:/read/" + insertCmd.getIsbn() + "?query=" + redirectquery;
	}

	/**
	 * 평가 수정
	 */
	@ResponseBody
	@PostMapping("/edit")
	public void updateComment(@RequestBody UpdateCmd updateCmd, Long mem_num) {
		UpdateCmd updateAppraisal = new UpdateCmd();
		updateCmd.setIsbn(updateCmd.getIsbn().substring(0, 10));

		updateAppraisal.setMem_num(mem_num);
		updateAppraisal.setIsbn(updateCmd.getIsbn());
		updateAppraisal.setAppraisal_num(updateCmd.getAppraisal_num());
		updateAppraisal.setStar(updateCmd.getStar());
		updateAppraisal.setBook_comment(updateCmd.getBook_comment());
		updateAppraisal.setStart_date(updateCmd.getStart_date());
		updateAppraisal.setEnd_date(updateCmd.getEnd_date());
		updateAppraisal.setCo_prv(updateCmd.getCo_prv());
		updateAppraisal.setBook_status_num(updateCmd.getBook_status_num());

		appraisalService.updateComment(updateAppraisal);
	}

	/**
	 * 평가 삭제
	 */
	@ResponseBody
	@PostMapping("/delete")
	public void deleteComment(@RequestBody DeleteCmd deleteCmd) {
		DeleteCmd deleteComment = new DeleteCmd();

		deleteComment.setAppraisal_num(deleteCmd.getAppraisal_num());

		appraisalService.deleteComment(deleteComment);
	}

	/**
	 * 비밀번호 확인
	 */
	@ResponseBody
	@PostMapping("/passCheck")
	public int passCheck(@RequestBody PassCheckCmd passCheckCmd) {
		if (passCheckCmd.getMem_pass().equals(passCheckCmd.getPassCheck())) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * 독서 상태 등록
	 * @return 
	 */
	@ResponseBody
	@PostMapping("/insertStatus")
	public String insertStatus(@RequestBody InsertCmd insertCmd,
			CommandLogin loginMember, Model model,
			HttpSession session, HttpServletResponse response, Errors errors) {
		
		BookShelfVO bookShelf = new BookShelfVO();

		/**
		 * 에러시 반환
		 */
		if (errors.hasErrors()) {
			return "detailAndComment";
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
		System.out.println(mem_num);
		/**
		 * 세션 테이블에 다시 저장
		 */
		session.setAttribute("authInfo", authInfo);		

		insertCmd.setIsbn(insertCmd.getIsbn().substring(0, 10));

		bookShelf.setBook_status(insertCmd.getOption());
		bookShelf.setMem_num(mem_num);
		bookShelf.setIsbn(insertCmd.getIsbn());
		bookShelf = appraisalService.insertBookShelf(bookShelf);
		
		return null;
	}
}
