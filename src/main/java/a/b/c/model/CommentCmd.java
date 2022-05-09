package a.b.c.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentCmd {
	private Long appraisal_num;
	private String star;
	private String book_comment;
	private String start_date;
	private String end_date;
	private String co_prv;
	private int book_status;
	private Long mem_num;
}
