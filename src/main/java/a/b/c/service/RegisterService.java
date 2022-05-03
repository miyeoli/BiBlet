package a.b.c.service;

import java.util.HashMap;

import a.b.c.model.AdministratorVO;
import a.b.c.model.MemberVO;

public interface RegisterService {
	void memRegister(MemberVO member);
	void updateKey(String email, String authKey);
	void updateStatus(MemberVO member);
	void admRegister(AdministratorVO admin);
	void updateAdmKey(String email, String authKey);
	void updateAdmStatus(AdministratorVO admin);
}
