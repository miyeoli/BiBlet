package a.b.c.service;

import a.b.c.model.AdministratorVO;
import a.b.c.model.CommandAdminLogin;
import a.b.c.model.CommandLogin;
import a.b.c.model.MemberVO;

public interface LoginService {
	MemberVO authenticate(CommandLogin loginMember) throws Exception;
	AdministratorVO adminAuthenticate(CommandAdminLogin loginAdmin) throws Exception;
	String findById(String mem_email) throws Exception;
}
