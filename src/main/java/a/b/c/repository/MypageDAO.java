package a.b.c.repository;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import a.b.c.model.AllCommentCmd;
import a.b.c.model.CompleteCmd;
import a.b.c.model.MemberVO;

public interface MypageDAO {
	
	public MemberVO memberInfo(Long num);	//회원 정보 조회
	
	void updateMemInfo(MemberVO newInfo);	//회원 정보 수정
	void deleteMemInfo(Long mem_num);	// 회원 탈퇴
	
	/**
	 * 보관함
	 */
	int memCommentCount(Long mem_num);	//한 회원의 '독서 완료' 도서 개수 호출
	List<AllCommentCmd> selectMemComment(Long mem_num);	//한 회원이 작성한 모든 평가 호출
	List<CompleteCmd> completeIsbn(Long mem_num);	// 한 회원의 '독서 완료' 도서 isbn 호출
	
	int memLikeCount(Long mem_num);	//한 회원의 '찜' 도서 개수 호출
	List<String> likeIsbn(Long mem_num);	// 한 회원의 '찜' 도서 isbn 호출
	
	int memLeadingCount(Long mem_num);	//한 회원의 '보는 중' 도서 개수 호출
	List<String> leadingIsbn(Long mem_num);	// 한 회원의 '보는 중' 도서 isbn 호출
}
