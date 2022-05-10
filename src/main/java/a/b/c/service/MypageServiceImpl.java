package a.b.c.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import a.b.c.model.AllCommentCmd;
import a.b.c.model.CompleteCmd;
import a.b.c.model.MemInfoUpdateCmd;
import a.b.c.model.MemberVO;
import a.b.c.repository.MypageDAO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MypageServiceImpl implements MypageService {

	private final MypageDAO mypageDAO;

	// 회원 정보 조회
	@Override
	public MemberVO memberInfo(Long num) {
		return mypageDAO.memberInfo(num);
	}
	
	//회원 정보 수정
	@Override
	public void updateMemInfo(MemInfoUpdateCmd memInfoUpdateCmd, MultipartFile multipartFile, HttpServletRequest request) throws IllegalStateException, IOException {
		MemberVO newInfo = new MemberVO();
		
		//사용자가 선택한 프로필 이름 추출
		String orgimagename = multipartFile.getOriginalFilename();
		
		//사용자가 선택한 프로픨 확장자 추출
		String orgimagenameExtension = orgimagename.substring(orgimagename.lastIndexOf("."));
		
		//프로젝트 내 폴더에 사진 파일을 저장할 때 uuid값에 orgimagenameExtension(확장자)를 붙혀 저장 (= sjf743ifhrht32 + .png)
		String storedimagename = UUID.randomUUID().toString().replaceAll("-", "") + orgimagenameExtension;
		
		//파일이 저장될 경로(서버 측)
		String savePath = "/Users/kim-yurim/Desktop/workspace/image/";		
				
		//파일이 저장될 경로 + 최종 파일명
		String uploadFile = savePath + storedimagename;
		
		//업로드요청으로 전달받은 파일을 위에서 설정한 특정 경로에 특정 파일명으로 저장
		File file = new File(uploadFile);
		
		multipartFile.transferTo(file);
		
		newInfo.setMem_id(memInfoUpdateCmd.getMem_id());
		newInfo.setMem_pass(memInfoUpdateCmd.getMem_passU());
		newInfo.setMem_email(memInfoUpdateCmd.getMem_email());
		newInfo.setMem_num(memInfoUpdateCmd.getMem_num());
		newInfo.setMem_name(memInfoUpdateCmd.getMem_name());
		newInfo.setMem_pic(orgimagename);
		newInfo.setMem_storedpic(storedimagename);
		
		mypageDAO.updateMemInfo(newInfo);
	}

	//회원 탈퇴
	@Override
	public void deleteMemInfo(Long mem_num) {
		mypageDAO.deleteMemInfo(mem_num);
	}

	// 한 회원이 작성한 모든 평가 호출
	@Override
	public List<AllCommentCmd> selectMemComment(Long mem_num) {
		return mypageDAO.selectMemComment(mem_num);
	}

	// 한 회원의 '독서 완료' 도서 개수 호출
	@Override
	public int memCommentCount(Long mem_num) {
		return mypageDAO.memCommentCount(mem_num);
	}

	// 한 회원의 '찜' 도서 개수 호출
	@Override
	public int memLikeCount(Long mem_num) {
		return mypageDAO.memLikeCount(mem_num);
	}

	// 한 회원의 '보는 중' 도서 개수 호출
	@Override
	public int memLeadingCount(Long mem_num) {
		return mypageDAO.memLeadingCount(mem_num);
	}

	// 한 회원의 '찜' 도서 isbn 호출
	@Override
	public List<String> likeIsbn(Long mem_num) {
		return mypageDAO.likeIsbn(mem_num);
	}

	// 한 회원의 '보는 중' 도서 isbn 호출
	@Override
	public List<String> leadingIsbn(Long mem_num) {
		return mypageDAO.leadingIsbn(mem_num);
	}

	// 한 회원의 '독서 완료' 도서 isbn 호출
	@Override
	public List<CompleteCmd> completeIsbn(Long mem_num) {
		return mypageDAO.completeIsbn(mem_num);
	}

}
