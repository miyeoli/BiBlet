package a.b.c.model;

import java.sql.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdministratorVO {

	private int adm_num;
	@NotEmpty(message="필수 입력란 입니다.")
	private String adm_name;
	@NotEmpty(message="필수 입력란 입니다.")
	private String adm_id;
	@NotEmpty(message="필수 입력란 입니다.")
	private String adm_pass;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	private Date adm_regdate;
	@NotEmpty(message="필수 입력란 입니다.")
	@Email(message="올바른 이메일 형식이 아닙니다.")
	private String adm_email;
	private String adm_authkey;
	private int adm_authstatus;
	@NotEmpty(message="필수 입력란 입니다.")
	private String securitycode;
	
	private String admKeyword;
	private String admOption;

	private boolean rememberAdmId;
	
	
	public AdministratorVO(String adm_id, String adm_pass, String adm_email, String securitycode) {
		super();
		this.adm_id = adm_id;
		this.adm_pass = adm_pass;
		this.adm_email = adm_email;
		this.securitycode = securitycode;
	}
}
