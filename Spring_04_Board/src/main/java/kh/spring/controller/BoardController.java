package kh.spring.controller;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import kh.spring.dao.BoardDAO;
import kh.spring.dao.FilesDAO;
import kh.spring.dto.BoardDTO;
import kh.spring.dto.FilesDTO;

@Controller
@RequestMapping("/board/")
public class BoardController {
	@Autowired
	private HttpSession session;
	
	@Autowired
	private BoardDAO bdao;
	
	@Autowired
	private FilesDAO fdao;
	
	@RequestMapping("list")
	public String list(Model model) throws Exception{
		List<BoardDTO> list = bdao.selectAll();
		model.addAttribute("list",list);
		return "board/boardView";
	}
	
	@RequestMapping("writeForm")
	public String writeForm() throws Exception{
		return "board/writeForm";
	}
	
	@RequestMapping("writeProc")
	public String writeProc(BoardDTO dto, MultipartFile[] file) throws Exception{
		dto.setWriter((String)session.getAttribute("loginID"));
		int parentSeq = bdao.insert(dto); // 게시판에 작성된 내용을 DB에 저장하는 부분
		// 게시글의 번호 / 제목 / 내용...
		
		for(MultipartFile mf : file) {
			if(!mf.isEmpty()) {
				String realPath=session.getServletContext().getRealPath("upload");
				File realPathFile = new File(realPath);
				if(!realPathFile.exists()) {realPathFile.mkdir();}
				
				String oriName = mf.getOriginalFilename();
				String sysName = UUID.randomUUID()+"_"+oriName;
				
				mf.transferTo(new File(realPath+"/"+sysName)); // 첨부된 파일 폴더를 업로드 하는 부분
				fdao.insert(new FilesDTO(0,oriName,sysName,parentSeq)); // 첨부된 파일 정보를 DB에 저장하는 부분
			}
		}
		return "redirect:list";
	}
	
	@RequestMapping("detail")
	public String detail(int seq, Model model) throws Exception{
		bdao.addCount(seq);
		BoardDTO dto = bdao.selectBySeq(seq);
		List<FilesDTO> fList = fdao.selectAll(seq);
		String id = (String)session.getAttribute("loginID");
		model.addAttribute("loginID",id);
		model.addAttribute("dto",dto);
		model.addAttribute("fList",fList);
		return "board/detail";
	}
	
	@RequestMapping("update")
	public String update(BoardDTO dto) throws Exception{	
		bdao.update(dto);
		return "redirect:list";
	}
	
	@RequestMapping("delete")
	public String delete(int seq) throws Exception{
		bdao.delete(seq);
		return "redirect:list";
	}
	
	@RequestMapping("searchByMulti")
	public String searchByMulti(String title, String writer, Model model) throws Exception{
		System.out.println(title+" : "+writer);
		List<BoardDTO> list = bdao.searchByMulti(title,writer);
		model.addAttribute("list",list);
		return "board/boardView";
	}
	
	@ExceptionHandler(Exception.class)
	public String exceptionHandler(Exception e) {
		e.printStackTrace();
		System.out.println("에러가 발생함");
		return "error";
	}
}
