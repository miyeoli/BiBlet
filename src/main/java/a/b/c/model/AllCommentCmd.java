package a.b.c.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor // 디폴트 생성자 생성
@AllArgsConstructor // 오버로딩 생성자 생성
public class AllCommentCmd {
	private Long appraisal_num;	// 평가 번호
	private int star; // 별점
	private String book_comment; // 코멘트(평가)
	private String start_date; // 도서 구독 시작 날짜
	private String end_date; // 도서 구독 완료 날짜
	private String mem_id; // 회원 아이디
	private String mem_pic; // 회원 프로필 사진
	private Long book_status_num;	//상태번호
	private String mem_pass;	//회원비밀번호
	private String isbn;
	private Long mem_num;
}
