package a.b.c.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.SystemPropertyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import a.b.c.model.AppraisalVO;
import a.b.c.model.BookShelfVO;
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
	public String bookDetailAndComment(@RequestParam(required = false) String query, @PathVariable String isbn,
			Model model) {

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
	private String writeComment(@ModelAttribute("insertCmd") InsertCmd insertCmd) throws UnsupportedEncodingException {
		AppraisalVO appraisal = new AppraisalVO();
		BookShelfVO bookShelf = new BookShelfVO();
		String encodedParam = URLEncoder.encode(insertCmd.getQuery(), "UTF-8");

		Long mem_num = (long) 6; // 테스트용 회원 번호(현재 테이블에 6번회원까지 있음)

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
	 */
	@ResponseBody
	@PostMapping("/insertStatus")
	public void insertStatus(@RequestBody InsertCmd insertCmd) {
		BookShelfVO bookShelf = new BookShelfVO();

		// 테스트 하기 전마다 회원 등록 후 평가작성을 하지 않은 새로운 회원번호로 진행해야함
		MemberVO member = new MemberVO();
		Long mem_num = (long) 6; // 테스트용 회원 번호(현재 테이블에 6번회원까지 있음)
		member.setMem_num(mem_num);

		insertCmd.setIsbn(insertCmd.getIsbn().substring(0, 10));

		bookShelf.setBook_status(insertCmd.getOption());
		bookShelf.setMem_num(mem_num);
		bookShelf.setIsbn(insertCmd.getIsbn());
		bookShelf = appraisalService.insertBookShelf(bookShelf);
	}
}
