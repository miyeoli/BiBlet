package a.b.c.model;


import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommandLogin {

	private Long mem_num;

	@NotEmpty(message="필수 입력란 입니다.")
	private String mem_id;

	@NotEmpty(message="필수 입력란 입니다.")
	private String mem_pass;

	private String mem_name;

	private boolean rememberId;

	private int authstatus;
}
