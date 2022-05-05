package a.b.c.repository;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import a.b.c.model.AllCommentCmd;
import a.b.c.model.CompleteCmd;
import a.b.c.model.MemberVO;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MypageDAOImpl implements MypageDAO {

	private final SqlSessionTemplate sqlSessionTemplate;

	// 회원 정보 조회
	@Override
	public MemberVO memberInfo(Long num) {
		return sqlSessionTemplate.selectOne("memberInfo", num);
	}
	
	//회원 정보 수정
	@Override
	public void updateMemInfo(MemberVO newInfo) {
//			sqlSessionTemplate.delete("updateMemInfo");
		sqlSessionTemplate.update("updateMemInfo", newInfo);
	}
	

	// 회원 탈퇴
	@Override
	public void deleteMemInfo(Long mem_num) {
		sqlSessionTemplate.delete("deleteMemInfo", mem_num);
	}
	
	/**
	 * 보관함
	 */
	// 한 회원이 작성한 모든 평가 호출
	@Override
	public List<AllCommentCmd> selectMemComment(Long mem_num) {
		return sqlSessionTemplate.selectList("selectMemComment", mem_num);
	}

	// 한 회원의 '독서 완료' 도서 개수 호출
	@Override
	public int memCommentCount(Long mem_num) {
		return sqlSessionTemplate.selectOne("memCommentCount", mem_num);
	}

	// 한 회원의 '찜' 도서 개수 호출
	@Override
	public int memLikeCount(Long mem_num) {
		return sqlSessionTemplate.selectOne("memLikeCount", mem_num);
	}

	// 한 회원의 '보는 중' 도서 개수 호출
	@Override
	public int memLeadingCount(Long mem_num) {
		return sqlSessionTemplate.selectOne("memLeadingCount", mem_num);
	}

	// 한 회원의 '찜' 도서 isbn 호출
	@Override
	public List<String> likeIsbn(Long mem_num) {
		return sqlSessionTemplate.selectList("likeIsbn", mem_num);
	}

	// 한 회원의 '보는 중' 도서 isbn 호출
	@Override
	public List<String> leadingIsbn(Long mem_num) {
		return sqlSessionTemplate.selectList("leadingIsbn", mem_num);
	}

	// 한 회원의 '독서 완료' 도서 isbn 호출
	@Override
	public List<CompleteCmd> completeIsbn(Long mem_num) {
		return sqlSessionTemplate.selectList("completeIsbn", mem_num);
	}
}
