package a.b.c.service;

import java.util.List;

import org.springframework.stereotype.Service;

import a.b.c.model.BookShelfVO;
import a.b.c.model.AllCommentCmd;
import a.b.c.repository.MainDAO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MainServiceImpl implements MainService {

	private final MainDAO mainDAO;

	// 인기 도서 리스트
	@Override
	public List<String> popularList() {
		return mainDAO.popularList();
	}

	// 최근 코멘트
	@Override
	public List<AllCommentCmd> latestComment() {
		return mainDAO.latestComment();
	}

	// 모든 평가 불러오기
	@Override
	public Long allCommentCount() {
		return mainDAO.allCommentCount();
	}

	// '찜' 도서 목록 불러오기
	@Override
	public List<AllCommentCmd> myBookInfo(Long mem_num) {
		return mainDAO.myBookInfo(mem_num);
	}

	// 로그인 한 회원의 id불러오기
	@Override
	public String myID(Long mem_num) {
		return mainDAO.myID(mem_num);
	}

	// 로그인 한 회원의 id불러오기
	@Override
	public Long memCommentCount(Long mem_num) {
		return mainDAO.memCommentCount(mem_num);
	}
}
