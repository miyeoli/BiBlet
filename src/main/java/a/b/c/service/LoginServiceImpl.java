package a.b.c.service;

import org.springframework.stereotype.Service;

import a.b.c.exception.AuthstatusException;
import a.b.c.exception.IdPasswordNotMatchingException;
import a.b.c.model.AdministratorVO;
import a.b.c.model.CommandAdminLogin;
import a.b.c.model.CommandLogin;
import a.b.c.model.MemberVO;
import a.b.c.repository.LoginDAO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

	private final LoginDAO loginDAO;

	/**
	 * 사용자 로그인 인증
	 */
	@Override
	public MemberVO authenticate(CommandLogin loginMember) throws Exception {
		MemberVO member = loginDAO.selectById(loginMember.getMem_id());

		/**
		 * 테이블에 사용자가 없을 때 예외처리
		 */
		if (member == null) {
			throw new IdPasswordNotMatchingException();
		}

		/**
		 * 패스워드가 같지 않다면 예외 처리
		 */
		if (!member.getMem_pass().equals(loginMember.getMem_pass())) {
			throw new IdPasswordNotMatchingException();
		}

		/**
		 * 이메일 인증이 되어있지 않다면 예외처리
		 */
		if (member.getAuthstatus() == 0) {
			throw new AuthstatusException();
		}

		return member;
	}

	/**
	 * 관리자 로그인 인증
	 */
	@Override
	public AdministratorVO adminAuthenticate(CommandAdminLogin loginAdmin) throws Exception {
		/**
		 * 테이블에서 관리자 정보 추출
		 */
		AdministratorVO admin = loginDAO.selectByAdminId(loginAdmin.getAdm_id());

		/**
		 * 관리자 정보가 없으면 예외처리
		 */
		if (admin == null) {
			throw new IdPasswordNotMatchingException();
		}

		/**
		 * 관리자 비밀번호가 틀리면 예외처리
		 */
		if (!admin.getAdm_pass().equals(loginAdmin.getAdm_pass())) {
			throw new IdPasswordNotMatchingException();
		}

		/**
		 * 이메일 인증이 되어있지 않다면 예외처리
		 */
		if (admin.getAdm_authstatus() == 0) {
			throw new AuthstatusException();
		}

		return admin;
	}

	/**
	 * 아이디 찾기
	 */
	@Override
	public String findById(String mem_email) throws Exception {
		return loginDAO.findById(mem_email);

	}
}
