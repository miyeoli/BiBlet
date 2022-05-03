package a.b.c.service;

import java.util.List;

import org.springframework.stereotype.Service;

import a.b.c.model.AllCommentCmd;
import a.b.c.model.CompleteCmd;
import a.b.c.model.MemberVO;
import a.b.c.repository.MypageDAO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MypageServiceImpl implements MypageService {

	private final MypageDAO mypageDAO;

	// 회원 정보 조회
	@Override
	public MemberVO memberInfo(Long num) {
		return mypageDAO.memberInfo(num);
	}
	
	//회원 정보 수정
	@Override
	public void updateMemInfo(MemberVO newInfo) {
		mypageDAO.updateMemInfo(newInfo);
	}

	//회원 탈퇴
	@Override
	public void deleteMemInfo(Long mem_num) {
		mypageDAO.deleteMemInfo(mem_num);
	}

	// 한 회원이 작성한 모든 평가 호출
	@Override
	public List<AllCommentCmd> selectMemComment(Long mem_num) {
		return mypageDAO.selectMemComment(mem_num);
	}

	// 한 회원의 '독서 완료' 도서 개수 호출
	@Override
	public int memCommentCount(Long mem_num) {
		return mypageDAO.memCommentCount(mem_num);
	}

	// 한 회원의 '찜' 도서 개수 호출
	@Override
	public int memLikeCount(Long mem_num) {
		return mypageDAO.memLikeCount(mem_num);
	}

	// 한 회원의 '보는 중' 도서 개수 호출
	@Override
	public int memLeadingCount(Long mem_num) {
		return mypageDAO.memLeadingCount(mem_num);
	}

	// 한 회원의 '찜' 도서 isbn 호출
	@Override
	public List<String> likeIsbn(Long mem_num) {
		return mypageDAO.likeIsbn(mem_num);
	}

	// 한 회원의 '보는 중' 도서 isbn 호출
	@Override
	public List<String> leadingIsbn(Long mem_num) {
		return mypageDAO.leadingIsbn(mem_num);
	}

	// 한 회원의 '독서 완료' 도서 isbn 호출
	@Override
	public List<CompleteCmd> completeIsbn(Long mem_num) {
		return mypageDAO.completeIsbn(mem_num);
	}

}
