package a.b.c.controller;

import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import a.b.c.model.AdministratorVO;
import a.b.c.model.CommandListAppr;
import a.b.c.model.MemberVO;
import a.b.c.service.AdmPageService;
import a.b.c.service.LoginService;
import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor // @Autowried
public class AdministratorController {

	private static final Object Object = null;
	private static final String String = null;
	private final AdmPageService admPageService;

	@GetMapping("/adminPage")
	public String adminPage(Model model) {
		
		// 회원정보탭
		List<MemberVO> memberList = admPageService.listOfMember();
		model.addAttribute("memberList", memberList);
		int memcount = admPageService.countMember();
		model.addAttribute("memcount", memcount);

		//평가탭
		List<CommandListAppr> apprList = admPageService.listOfAppraisal();
		model.addAttribute("apprList", apprList);
		
		int starcount = admPageService.countStar();
		model.addAttribute("starcount", starcount);

		//코멘트탭
		List<CommandListAppr> commentList = admPageService.listOfAppraisal();
		model.addAttribute("commentList", commentList);
		
		int commentcount = admPageService.countComment();
		model.addAttribute("commentcount", commentcount);
		
		// 관리자정보탭
		List<AdministratorVO> adminList = admPageService.listOfAdmin();
		model.addAttribute("adminList", adminList);
		int admcount = admPageService.countAdmin();
		model.addAttribute("admcount", admcount);

		return "admin/admin_supervise";
	}


	@PostMapping("/search")
	public String searchInfo(@ModelAttribute("member") MemberVO member, Model model) throws Exception {

		if ("".equals(member.getOption()) || member.getOption() == null) {
			member.setOption("mem_num");
			member.setKeyword(null);
		}
		
		//회원검색
		List<MemberVO> searchList = admPageService.searchMember(member);
		model.addAttribute("searchList", searchList);

		int memcount = admPageService.countMember();
		model.addAttribute("memcount", memcount);
		
		//평가 정보 전달
		List<CommandListAppr> apprList = admPageService.listOfAppraisal();
		model.addAttribute("apprList", apprList);
		
		int starcount = admPageService.countStar();
		model.addAttribute("starcount", starcount);

		return "admin/admin_supervise";
	}

	@ResponseBody
	@PostMapping(value = "/commentPage", produces = "application/json; charset=UTF-8")
	public String searchAdmInfo(@RequestBody CommandListAppr appr) {
		//System.out.println("0:"+appr.getCoKeyword());
		if("".equals(appr.getCoOption())||appr.getCoOption()==null) {
			//System.out.println("1:"+appr.getCoKeyword());
			appr.setCoOption("mem_id");
			appr.setCoKeyword(null);
			List<CommandListAppr> searchApprList=admPageService.searchComments(appr);
			
			JSONObject jo=new JSONObject();
			
			JSONArray ja=new JSONArray();
			for (int i=0;i<searchApprList.size();i++) {
				JSONObject jso=new JSONObject();
				jso.put("bookcomment",searchApprList.get(i).getBook_comment());
				jso.put("isbn", searchApprList.get(i).getIsbn());
				jso.put("mem_id", searchApprList.get(i).getMem_id());
				jso.put("appraisal_num",searchApprList.get(i).getAppraisal_num());
				ja.put(jso);
			}
			jo.put("item",ja);
			return jo.toString();
		}else {
		List<CommandListAppr> searchApprList=admPageService.searchComments(appr);
		
		JSONObject jo=new JSONObject();
		
		JSONArray ja=new JSONArray();
		for (int i=0;i<searchApprList.size();i++) {
			JSONObject jso=new JSONObject();
			jso.put("bookcomment",searchApprList.get(i).getBook_comment());
			jso.put("isbn", searchApprList.get(i).getIsbn());
			jso.put("mem_id", searchApprList.get(i).getMem_id());
			jso.put("appraisal_num",searchApprList.get(i).getAppraisal_num());
			ja.put(jso);
		}
		jo.put("item",ja);
		

		return jo.toString();
		
		}
		
	}
	
	@ResponseBody
	@PostMapping(value = "/admInfoPage", produces = "application/json; charset=UTF-8")
	public String searchComment(@RequestBody AdministratorVO admin) {
		
		if ("".equals(admin.getAdmOption()) || admin.getAdmOption() == null) {
			admin.setAdmOption("adm_num");
			admin.setAdmKeyword(null);
		
			List<AdministratorVO> searchAdmList = admPageService.searchAdmin(admin);

			JSONObject jo=new JSONObject();
			
			JSONArray ja=new JSONArray();
			for (int i=0;i<searchAdmList.size();i++) {
				JSONObject jso=new JSONObject();
				jso.put("adm_num",searchAdmList.get(i).getAdm_num());
				jso.put("adm_name", searchAdmList.get(i).getAdm_name());
				jso.put("adm_id", searchAdmList.get(i).getAdm_id());
				jso.put("adm_regdate",searchAdmList.get(i).getAdm_regdate());
				jso.put("adm_email",searchAdmList.get(i).getAdm_email());
				jso.put("adm_authstatus",searchAdmList.get(i).getAdm_authstatus());
				ja.put(jso);
			}
			jo.put("item",ja);
			return jo.toString();
		}else {
			List<AdministratorVO> searchAdmList = admPageService.searchAdmin(admin);

			JSONObject jo=new JSONObject();
			
			JSONArray ja=new JSONArray();
			for (int i=0;i<searchAdmList.size();i++) {
				JSONObject jso=new JSONObject();
				jso.put("adm_num",searchAdmList.get(i).getAdm_num());
				jso.put("adm_name", searchAdmList.get(i).getAdm_name());
				jso.put("adm_id", searchAdmList.get(i).getAdm_id());
				jso.put("adm_regdate",searchAdmList.get(i).getAdm_regdate());
				jso.put("adm_email",searchAdmList.get(i).getAdm_email());
				jso.put("adm_authstatus",searchAdmList.get(i).getAdm_authstatus());
				ja.put(jso);
			}
			jo.put("item",ja);
			return jo.toString();		
		}
		
	}



	@GetMapping("/adminPage/deleteMember/{mem_num}")
	public String delete(@PathVariable int mem_num, Model model) {
		model.addAttribute("mem_num", mem_num);
		return "/deleteMember";
	}
	
	@GetMapping("/adminPage/deleteComment/{appraisal_num}")
	public String deleteComment(@PathVariable int appraisal_num,Model model) {
		model.addAttribute("appraisal_num",appraisal_num);
		return "/deleteComment";
	}

	@PostMapping(value = "/adminpage/deleteMember")
	public String delete(Long mem_num, String adm_pass, String adm_email, Model model) {
		int rowCount;
		MemberVO member = new MemberVO();
		member.setMem_num(mem_num);
		AdministratorVO admin = new AdministratorVO();
		admin.setAdm_email(adm_email);
		admin.setAdm_pass(adm_pass);

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("MEM_NUM", member.getMem_num());// sqlmapper에 이름과 map의 key값이 같아야함
		map.put("ADM_EMAIL", admin.getAdm_email());
		map.put("ADM_PASS", admin.getAdm_pass());

		
		rowCount = admPageService.delete(map);

		if (rowCount == 0) {
			model.addAttribute("mem_num", mem_num);
			model.addAttribute("msg", "관리자 비밀번호가 일치하지 않습니다.");
			return "/deleteMember";
		} else {
			return "redirect:/adminPage";
		}
	}

	
	@PostMapping("/adminpage/deleteComment")
	public String deleteComment(int appraisal_num, String adm_pass, String adm_email, Model model) {
		int rowCount;
		CommandListAppr appr= new CommandListAppr();
		appr.setAppraisal_num(appraisal_num);
		AdministratorVO admin = new AdministratorVO();
		admin.setAdm_email(adm_email);
		admin.setAdm_pass(adm_pass);

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("APPRAISAL_NUM", appr.getAppraisal_num());// sqlmapper에 이름과 map의 key값이 같아야함
		map.put("ADM_EMAIL", admin.getAdm_email());
		map.put("ADM_PASS", admin.getAdm_pass());

		
		rowCount = admPageService.deleteComment(map);

		if (rowCount == 0) {
			model.addAttribute("appraisal_num", appraisal_num);
			model.addAttribute("msg", "관리자 비밀번호가 일치하지 않습니다.");
			return "/deleteComment";
		} else {
			return "redirect:/adminPage";
		}
	}
}
