package a.b.c.service;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import a.b.c.exception.AlreadyExistEmailException;
import a.b.c.exception.AlreadyExistIdException;
import a.b.c.exception.SecurityCodeException;
import a.b.c.model.AdministratorVO;
import a.b.c.model.MemberVO;
import a.b.c.repository.RegisterDAO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegisterServiceImpl implements RegisterService {

	private final RegisterDAO registerDAO;

	/**
	 * 회원가입 서비스
	 */
	@Override
	public void memRegister(MemberVO member) {

		/**
		 * 이메일 중복 확인
		 */
		int emailChkResult = registerDAO.memEmailChk(member.getMem_email());
		if(emailChkResult == 1) {
			throw new AlreadyExistEmailException();
		}

		/**
		 * 아이디 중복 확인
		 */
		int idChkResult = registerDAO.memIdChk(member.getMem_id());
		if(idChkResult == 1) {
			throw new AlreadyExistIdException();
		}

		/**
		 * 회원정보 DB에 저장
		 */
		String defaultPic = "profile.png";
		member.setMem_pic(defaultPic);
		registerDAO.memRegister(member);
	}
	
	@Override
	public void updateKey(String email, String authKey) {
		HashMap<String, String> map = new HashMap<>();
		map.put("mem_email", email);
		map.put("authkey", authKey);
		registerDAO.updateKey(map);
	}
	
	@Override
	public void updateStatus(MemberVO member) {
		registerDAO.updateStatus(member);
	}
	
	@Override
	public void admRegister(AdministratorVO admin) {
		String adm_email=admin.getAdm_email();
		int emailChkResult= registerDAO.admEmailChk(adm_email);

		if(!admin.getSecuritycode().equals("ABC")) {
			throw new SecurityCodeException();
		}
		
		if(emailChkResult==1) {
			throw new AlreadyExistEmailException();
		}
		
		String adm_id=admin.getAdm_id();
		int idChkResult= registerDAO.admIdChk(adm_id);
		
		if(idChkResult==1) {
			throw new AlreadyExistIdException();
		}

		registerDAO.admRegist(admin);
	}
	
	@Override
	public void updateAdmKey(String email, String authKey) {
		HashMap<String, String> map = new HashMap<String,String>();
		map.put("adm_email", email);
		map.put("adm_authkey", authKey);
		registerDAO.updateAdmKey(map);
	};
	
	@Override
	public void updateAdmStatus(AdministratorVO admin) {
		registerDAO.updateAdmStatus(admin);
	};
}
