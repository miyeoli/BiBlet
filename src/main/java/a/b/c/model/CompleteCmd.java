package a.b.c.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor // 디폴트 생성자 생성
@AllArgsConstructor // 오버로딩 생성자 생성
public class CompleteCmd {
	private Long appraisal_num;	// 평가 번호
	private String isbn;
}
