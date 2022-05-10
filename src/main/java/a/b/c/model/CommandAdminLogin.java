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
public class CommandAdminLogin {
	private int adm_num;
	
	@NotEmpty(message="필수 입력란 입니다.")
	private String adm_id;
	
	@NotEmpty(message="필수 입력란 입니다.")
	private String adm_pass;
	
	private String adm_name;
	private boolean rememberAdmId;
	private int adm_authstatus;
}
