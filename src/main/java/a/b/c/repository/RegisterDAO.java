package a.b.c.repository;

import java.util.HashMap;

import a.b.c.model.AdministratorVO;
import a.b.c.model.MemberVO;

public interface RegisterDAO {
	public void memRegister(MemberVO member);
	public void updateKey(HashMap<String,String> map);
	public void updateStatus(MemberVO member);
	public int memEmailChk(String mem_email);
	public int memIdChk(String mem_id);
	
	public void admRegist(AdministratorVO admin);
	public void updateAdmKey(HashMap<String,String> map);
	public void updateAdmStatus(AdministratorVO admin);
	public int admEmailChk(String adm_email);
	public int admIdChk(String adm_id);
}
