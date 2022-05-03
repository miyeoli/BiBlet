package a.b.c.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PassCheckCmd {
	private String passCheck;
	private String isbn;
	private String query;
	private Long appraisal_num;
	private String mem_pass;
}
