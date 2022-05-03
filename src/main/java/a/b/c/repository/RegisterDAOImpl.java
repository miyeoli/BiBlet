package a.b.c.repository;

import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import a.b.c.model.AdministratorVO;
import a.b.c.model.MemberVO;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class RegisterDAOImpl implements RegisterDAO {
	
	private final SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public void memRegister(MemberVO member) {
		sqlSessionTemplate.insert("memRegister",member);
	}
	
	@Override
	public void updateKey(HashMap<String, String> map) {
		sqlSessionTemplate.update("updateKey", map);
	}
	
	@Override
	public void updateStatus(MemberVO member) {
		sqlSessionTemplate.update("updateStatus",member);
	}
	
	@Override
	public int memEmailChk(String mem_email) {
		int emailChkResult = sqlSessionTemplate.selectOne("memEmailChk",mem_email);
		return emailChkResult;
	}
	
	@Override
	public int memIdChk(String mem_id) {
		int idChkResult=sqlSessionTemplate.selectOne("memIdChk",mem_id);
		return idChkResult;
	}
	
	@Override
	public void admRegist(AdministratorVO admin) {
		sqlSessionTemplate.insert("admRegist",admin);
	};
	
	@Override
	public void updateAdmKey(HashMap<String,String> map) {
		sqlSessionTemplate.update("updateAdmKey",map);
	};
	
	@Override
	public void updateAdmStatus(AdministratorVO admin) {
		sqlSessionTemplate.update("updateAdmStatus",admin);
	};
	
	@Override
	public int admEmailChk(String adm_email) {
		int emailChkResult=sqlSessionTemplate.selectOne("admEmailChk",adm_email);
		return emailChkResult;
	};
	
	@Override
	public int admIdChk(String adm_id) {
		int idChkResult=sqlSessionTemplate.selectOne("admIdChk",adm_id);
		return idChkResult;
	};
}
