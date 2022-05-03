package a.b.c.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeleteCmd {
private String isbn;
private Long mem_num;
private String query;
private Long book_status_num;
private Long appraisal_num;
private String mem_pass;
private String passCheck;
}
