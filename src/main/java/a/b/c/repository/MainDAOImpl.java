package a.b.c.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import a.b.c.model.AllCommentCmd;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MainDAOImpl implements MainDAO {

	private final SqlSessionTemplate sqlSessionTemplate;

	// 인기 도서 리스트
	@Override
	public List<String> popularList() {
		return sqlSessionTemplate.selectList("popularList");
	}

	// 최근 코멘트
	@Override
	public List<AllCommentCmd> latestComment() {
		return sqlSessionTemplate.selectList("latestComment");
	}

	// 모든 평가 불러오기
	@Override
	public Long allCommentCount() {
		return sqlSessionTemplate.selectOne("allCommentCount");
	}

	// '찜' 도서 목록 불러오기
	@Override
	public List<AllCommentCmd> myBookInfo(Long mem_num) {
		return sqlSessionTemplate.selectList("myBookInfo", mem_num);
	}

	// 로그인한 회원의 id 불러오기
	@Override
	public String myID(Long mem_num) {
		return sqlSessionTemplate.selectOne("myID", mem_num);
	}

	// 로그인한 회원이 작성한 총 평가 개수 불러오기
	@Override
	public Long memCommentCount(Long mem_num) {
		return sqlSessionTemplate.selectOne("memCommentCountMain", mem_num);
	}

}
