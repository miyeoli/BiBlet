package a.b.c.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InsertCmd {
	private int star; 
	private String book_comment; 
	private String start_date; 
	private String end_date; 
	private String co_prv; 
	private String isbn;
	private String query;
	private int option;
}
