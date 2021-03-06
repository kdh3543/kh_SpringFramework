package kh.spring.controller;

import java.io.File;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/file/")
public class FileController {

	@Autowired
	private HttpSession session;
	
	@RequestMapping("upload")
	public String upload(String title, String contents,MultipartFile[] file) throws Exception{
			for(MultipartFile mf : file) {
				if(!mf.isEmpty()) { //업로드 된 파일 중 첫번째 파일이 비어있지 않다면
				String realPath = session.getServletContext().getRealPath("upload");
				File realPathFile = new File(realPath);
				if(!realPathFile.exists()) {realPathFile.mkdir();}
				
				String oriName = mf.getOriginalFilename();	// 사용자가 업로드 한 파일의 원본 이름
				String sysName = UUID.randomUUID()+"_"+oriName; // 서버쪽에 저장할 파일 이름
				// 절대로 겹치지 않는 무작위의 배열을 생성해줌
				
				//서버에 업로드되어 메모리에 적재된 파일의 내용을 어디에 저장할지 결정하는 부분
				mf.transferTo(new File(realPath+"/"+sysName));
				}
			}
		return "redirect:/";
	}
	
	// 파일 한개만 업로드 할 때
//	@RequestMapping("upload")
//	public String upload(String title, String contents,MultipartFile file) throws Exception{
//
//		String realPath = session.getServletContext().getRealPath("upload");
//		File realPathFile = new File(realPath);
//		if(!realPathFile.exists()) {realPathFile.mkdir();}
//		
//		String oriName = file.getOriginalFilename();	// 사용자가 업로드 한 파일의 원본 이름
//		String sysName = UUID.randomUUID()+"_"+oriName; // 서버쪽에 저장할 파일 이름
//		// 절대로 겹치지 않는 무작위의 배열을 생성해줌
//		
//		//서버에 업로드되어 메모리에 적재된 파일의 내용을 어디에 저장할지 결정하는 부분
//		file.transferTo(new File(realPath+"/"+sysName));
//		
//		return "redirect:/";
//	}
}
