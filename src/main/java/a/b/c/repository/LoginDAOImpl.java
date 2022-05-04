package a.b.c.repository;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import a.b.c.model.AdministratorVO;
import a.b.c.model.MemberVO;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class LoginDAOImpl implements LoginDAO {

	private final SqlSessionTemplate sqlSessionTemplate;

	// 회원 로그인
	@Override
	public MemberVO selectById(String mem_id) {
		return sqlSessionTemplate.selectOne("selectById", mem_id);
	}

	// 관리자 로그인
	@Override
	public AdministratorVO selectByAdminId(String adm_id) {
		return sqlSessionTemplate.selectOne("selectByAdminId", adm_id);
	}

	// 아이디 찾기
	@Override
	public String findById(String mem_email) {
		return sqlSessionTemplate.selectOne("findById", mem_email);
	}
}
