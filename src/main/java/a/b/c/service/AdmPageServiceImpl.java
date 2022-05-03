package a.b.c.service;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import a.b.c.model.AdministratorVO;
import a.b.c.model.CommandListAppr;
import a.b.c.model.MemberVO;
import a.b.c.repository.AdministratorpageDAO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdmPageServiceImpl implements AdmPageService{

	private final AdministratorpageDAO administratorpageDAO;
	
	@Override
	public List<MemberVO> listOfMember() {
		return administratorpageDAO.listOfMember();
	};
	
	@Override
	public int countMember() {
		return administratorpageDAO.countMember();
	}
	
	@Override
	public int delete(HashMap<String,Object> map) {
		int result=administratorpageDAO.deleteMember(map);
		return result;
	};
	
	@Override
	public List<MemberVO> searchMember(MemberVO member){
		return administratorpageDAO.searchMember(member);
	};
	
	@Override
	public List<AdministratorVO> listOfAdmin(){
		return administratorpageDAO.listOfAdmin();
	};
	
	@Override
	public int countAdmin() {
		return administratorpageDAO.countAdmin();
	};
	
	@Override
	public List<AdministratorVO> searchAdmin(AdministratorVO admin){
		return administratorpageDAO.searchAdmin(admin);
	};
	
	@Override
	public List<CommandListAppr> listOfAppraisal(){
		return administratorpageDAO.listOfAppraisal();
	};
	
	@Override
	public int countStar() {
		return administratorpageDAO.countStar();
	}
	
	@Override
	public int countComment() {
		return administratorpageDAO.countComment();
	}
	
	@Override
	public List<CommandListAppr> searchComments(CommandListAppr loa){
		return administratorpageDAO.searchComments(loa);
	};
	
	@Override
	public int deleteComment(HashMap<String,Object> map) {
		int result=administratorpageDAO.deleteComment(map);
		return result;
	};
}
