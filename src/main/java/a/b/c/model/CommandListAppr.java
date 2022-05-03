package a.b.c.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommandListAppr {
	private int appraisal_num;
	private String mem_id;
	private String book_comment;
	private int star;
	private String isbn;
	private String coKeyword;
	private String coOption;
}
