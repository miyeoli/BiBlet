package a.b.c.model;

import java.util.Date;

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
public class MemberVO {
	private Long mem_num;//sequence.nextval로 넣을 것

	@NotEmpty(message="필수 입력란 입니다.")
	private String mem_name;//50->한글6글자,영어50자

	@NotEmpty(message="필수 입력란 입니다.")
	private String mem_id;//100->한글33자,영어100자

	@NotEmpty(message="필수 입력란 입니다.")
	private String mem_pass;//200->한글 66자,영어200자

	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	private Date mem_regdate;

	@NotEmpty(message="필수 입력란 입니다.")
	@Email(message="올바른 이메일 형식이 아닙니다.")
	private String mem_email;//200->한글 66자,영어200자

	private String mem_pic;	//orgimgname

	private String authkey;//null값이어도 됨

	private int authstatus;//default=0 => 인증 후 =1

	private String keyword;

	private String option;

	private boolean rememberId;
	
	private String mem_storedpic;	//폴더에 저장되는 image이름(ex:dkfiwfi)
	
	
}
