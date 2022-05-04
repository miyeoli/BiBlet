package a.b.c.repository;

import java.util.List;

import a.b.c.model.AllCommentCmd;

public interface MainDAO {
	List<String> popularList();	// bookshelf에서 ibsn 가져오기
	List<AllCommentCmd> latestComment();	// 최근 코멘트
	Long allCommentCount();	//모든 평가 불러오기
	List<AllCommentCmd> myBookInfo(Long mem_num);	//'찜' 도서 목록 불러오기
	String myID(Long mem_num);	//로그인한 회원의 id 불러오기
	Long memCommentCount(Long mem_num);	//로그인한 회원이 작성한 총 평가 개수 불러오기
}
