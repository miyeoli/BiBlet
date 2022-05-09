package a.b.c.service;

import java.util.List;

import a.b.c.model.AllCommentCmd;
import a.b.c.model.AppraisalVO;
import a.b.c.model.BookShelfVO;
import a.b.c.model.CommentCmd;
import a.b.c.model.DeleteCmd;
import a.b.c.model.UpdateCmd;

public interface AppraisalService {
	void writeComment(AppraisalVO appraisal);	//평가 작성
	
	BookShelfVO insertBookShelf(BookShelfVO bookShelf); 	//독서 상태 삽입
	BookShelfVO selectBookShelf(BookShelfVO bookShelf);		//독서 상태 호출
	
	int commentCount(String isbn); 		//해당 도서의 대한 평가 개수 호출
	List<AllCommentCmd> findAllComment(String isbn); 	//해당 도서의 대한 모든 평가 호출
	void updateComment(UpdateCmd updateComment); 	//평가 수정
	void deleteComment(DeleteCmd deleteCmd);	//평가 삭제 
	
	CommentCmd getComment(Long appraisal_num);
	String getMemPass(Long appraisal_num);
}
