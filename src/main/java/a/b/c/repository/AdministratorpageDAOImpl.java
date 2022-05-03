package a.b.c.repository;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import a.b.c.model.AdministratorVO;
import a.b.c.model.CommandListAppr;
import a.b.c.model.MemberVO;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class AdministratorpageDAOImpl implements AdministratorpageDAO{

	private final SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public List<MemberVO> listOfMember() {
		return sqlSessionTemplate.selectList("listOfMember");
	};
	
	@Override
	public int countMember() {
		return sqlSessionTemplate.selectOne("countMember");
	};
	
	@Override
	public int deleteMember(HashMap<String,Object> map) {
		return sqlSessionTemplate.delete("deleteMember", map);
	};
	
	@Override
	public List<MemberVO> searchMember(MemberVO member){
		return sqlSessionTemplate.selectList("searchMember",member);
	};
	
	@Override
	public List<AdministratorVO> listOfAdmin(){
		return sqlSessionTemplate.selectList("listOfAdmin");
	};
	
	@Override
	public int countAdmin() {
		return sqlSessionTemplate.selectOne("countAdmin");
	};
	
	@Override
	public List<AdministratorVO> searchAdmin(AdministratorVO admin){
		return sqlSessionTemplate.selectList("searchAdmin",admin);
	};
	
	@Override
	public List<CommandListAppr> listOfAppraisal(){
		return sqlSessionTemplate.selectList("listOfAppraisal");
	};
	
	@Override
	public List<CommandListAppr> searchComments(CommandListAppr loa){;
		return sqlSessionTemplate.selectList("searchComments", loa);
	}
	
	@Override
	public int countStar() {
		return sqlSessionTemplate.selectOne("countStar");
	}
	
	@Override
	public int countComment() {
		return sqlSessionTemplate.selectOne("countComment");
	}
	
	@Override
	public int deleteComment(HashMap<String,Object> map) {
		return sqlSessionTemplate.delete("deleteCommentByAdm", map);
	};
}
