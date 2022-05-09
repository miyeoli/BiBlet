package a.b.c.controller;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import a.b.c.model.AllCommentCmd;
import a.b.c.model.AppraisalVO;
import a.b.c.model.BookShelfVO;
import a.b.c.model.CommandLogin;
import a.b.c.model.CommentCmd;
import a.b.c.model.DeleteCmd;
import a.b.c.model.InsertCmd;
import a.b.c.model.MemberVO;
import a.b.c.model.PassCheckCmd;
import a.b.c.model.UpdateCmd;
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
		model.addAttribute("isbn", isbn);
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
		if (session == null || session.getAttribute("authInfo") == null) {
			return "redirect:/";
		}
		

		MemberVO authInfo = (MemberVO) session.getAttribute("authInfo");
		
		/**
		 * Long mem_num으로 변환
		 */
		Long mem_num = authInfo.getMem_num();		

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
		
	
		
		return "redirect:/read/" + insertCmd.getIsbn();
	}

	/**
	 * 평가 수정
	 */
	@ResponseBody
	@PostMapping("/edit")
	public void updateComment(@RequestBody UpdateCmd updateCmd) {
		UpdateCmd updateAppraisal = new UpdateCmd();

		updateAppraisal.setAppraisal_num(updateCmd.getAppraisal_num());
		updateAppraisal.setStar(updateCmd.getStar());
		updateAppraisal.setBook_comment(updateCmd.getBook_comment());
		updateAppraisal.setStart_date(updateCmd.getStart_date());
		updateAppraisal.setEnd_date(updateCmd.getEnd_date());
		updateAppraisal.setCo_prv(updateCmd.getCo_prv());

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

	@ResponseBody
	@PostMapping("/comment")
	public ResponseEntity<?> modifyComment(@RequestBody PassCheckCmd passCheckCmd, HttpSession session) {
		
		if (session == null || session.getAttribute("authInfo") == null) {
			return ResponseEntity.status(NOT_FOUND).build();
		}
		
		MemberVO authInfo = (MemberVO) session.getAttribute("authInfo");
		
		
		String mem_pass = appraisalService.getMemPass(passCheckCmd.getAppraisal_num());
		
		if (!authInfo.getMem_pass().equals(passCheckCmd.getPassCheck())|| !mem_pass.equals(passCheckCmd.getPassCheck())) {
			return ResponseEntity.status(NOT_FOUND).build();
		}
		
		CommentCmd comment = appraisalService.getComment(passCheckCmd.getAppraisal_num());
		
		return ResponseEntity.ok().body(comment);
	}
	
	@ResponseBody
	@PostMapping("/passCheck")
	public int passCheck(@RequestBody PassCheckCmd passCheckCmd, HttpSession session) {
		
		MemberVO authInfo = (MemberVO) session.getAttribute("authInfo");
		
		String mem_pass = appraisalService.getMemPass(passCheckCmd.getAppraisal_num());
		
		if (!authInfo.getMem_pass().equals(passCheckCmd.getPassCheck()) || !mem_pass.equals(passCheckCmd.getPassCheck())) {
			return 0;
		}
	
		return 1;
	}
	

	/**
	 * 독서 상태 등록
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
			return "redirect:/";
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

		bookShelf.setBook_status(insertCmd.getOption());
		bookShelf.setMem_num(mem_num);
		bookShelf.setIsbn(insertCmd.getIsbn());
		bookShelf = appraisalService.insertBookShelf(bookShelf);
		
		return null;
	}
}
