package a.b.c.model;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemInfoUpdateCmd {
	private Long mem_num;	//orgimagename
	private String mem_pic;
	private String mem_pass;
	private String mem_id;
	private String mem_name;
	private String mem_email;
	
	private String mem_storedpic;	//폴더에 저장되는 image이름(ex:dkfiwfi)
	private MultipartFile file;
}

