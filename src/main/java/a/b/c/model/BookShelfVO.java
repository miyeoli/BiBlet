package a.b.c.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookShelfVO {
	private Long book_status_num;	//상태번호PK
	private int book_status;	//상태 0:찜, 1:보는 중, 2:완료
	private Long mem_num;	//회원번호FK
	private String isbn;	//isbn
}
