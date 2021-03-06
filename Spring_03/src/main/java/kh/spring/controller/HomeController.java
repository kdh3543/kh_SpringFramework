package kh.spring.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import kh.spring.dao.MessageDAO;
import kh.spring.dto.MessageDTO;

@Controller
public class HomeController {
	@Autowired
	public MessageDAO dao;
	
	@RequestMapping("/")
	public String home() {

		return "index";
	}
	
	@RequestMapping("toInput")
	public String toInput() {
		System.out.println("메세지 입력창입니다.");
		return "inputForm";
	}
	
	@RequestMapping("inputProc")
	public String inputProc(MessageDTO dto) throws Exception{
		dao.insert(dto);
		return "redirect:/";
	}
	
	@RequestMapping("toOutput")
	public String toOutput(Model model) throws Exception{	
		List<MessageDTO> list = dao.selectAll();
		model.addAttribute("list",list);
		return "outputForm";
	}
	
	@RequestMapping("deleteProc")
	public String deleteProc(int seq) throws Exception{	
		dao.delete(seq);
		return "redirect:toOutput";
	}
	
	@RequestMapping("updateProc")
	public String updateProc(int seq, String column, String value) throws Exception{	
		dao.update(seq, column, value);
		return "redirect:toOutput";
	}

	@RequestMapping("search")
	public String search(int seq, Model model) throws Exception{
		List<MessageDTO> list = dao.selectBySeq(seq);
		model.addAttribute("list",list);
		return "outputForm";
	}
	
	@ExceptionHandler(Exception.class)
	public String exceptionHandler(Exception e) {
		System.out.println("에러가 발생했습니다.");
		e.printStackTrace();
		return "error";
	}
	

	
}
