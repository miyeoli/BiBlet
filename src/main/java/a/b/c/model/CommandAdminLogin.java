package a.b.c.model;

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
	private String adm_id;
	private String adm_pass;
	private String adm_name;
	private boolean rememberAdmId;
	private int adm_authstatus;
}
