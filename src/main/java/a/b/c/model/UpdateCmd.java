package a.b.c.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCmd {
	private String isbn;
	private String query;
	private Long appraisal_num;
	private Long mem_num;
	private int star; 
	private String book_comment; 
	private String start_date; 
	private String end_date; 
	private String co_prv; 
	private Long book_status_num;
	private String mem_pass;
	private String passCheck;
}
