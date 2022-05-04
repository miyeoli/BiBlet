package a.b.c.repository;

import a.b.c.model.AdministratorVO;
import a.b.c.model.MemberVO;

public interface LoginDAO {

	//로그인
	MemberVO selectById(String mem_id);
	
	//관리자 로그인
	AdministratorVO selectByAdminId(String adm_id);
	
	//아이디 찾기
	String findById(String mem_email);
}
