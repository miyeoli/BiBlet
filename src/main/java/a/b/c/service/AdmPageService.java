package a.b.c.service;

import java.util.HashMap;
import java.util.List;

import a.b.c.model.AdministratorVO;
import a.b.c.model.CommandListAppr;
import a.b.c.model.MemberVO;

public interface AdmPageService {
	List<MemberVO> listOfMember();
	int countMember();
	int delete(HashMap<String,Object> map);
	List<MemberVO> searchMember(MemberVO member);
	
	List<AdministratorVO> listOfAdmin();
	int countAdmin();
	List<AdministratorVO> searchAdmin(AdministratorVO admin);
	
	List<CommandListAppr> listOfAppraisal();
	int countStar();
	int countComment();
	List<CommandListAppr> searchComments(CommandListAppr loa);
	int deleteComment(HashMap<String,Object> map);
}
