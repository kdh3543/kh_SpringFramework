package kh.spring.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import kh.spring.dao.ContactDAO;
import kh.spring.dto.ContactDTO;

@Controller
public class HomeController {

	@Autowired
	public ContactDAO dao;

	@Autowired
	private HttpSession session;

	@RequestMapping("/")
	public String home() {
		System.out.println("/로 들어온 요청은 이 메소드를 실행합니다.");

		return "home";
		//void
		//String
		//ModelAndView
		//셋중에 하나만 가능
	}

	@RequestMapping("toInput")
	public String toInput() {
		System.out.println("toInput으로 가는 메서드가 실행되었습니다.");
		return "inputForm";
	}

	@RequestMapping("inputProc")
	public String inputProc(ContactDTO dto) throws Exception{

		int result = dao.insert(dto);

		return "redirect:/";
	}

	@RequestMapping("toOutput")
	public String outputProc(Model model) throws Exception{

		List<ContactDTO> list = dao.selectAll();
		int count = dao.selectCount();
		model.addAttribute("list",list);
		model.addAttribute("count",count);
		return "output";
	}	
	
	@RequestMapping("search")
	public String search(int searchSeq, Model model) throws Exception{
		List<ContactDTO> list = dao.search(searchSeq);
		model.addAttribute("list",list);
		return "output";
	}
	
	@RequestMapping("toSearch")
	public String toSearch() {
		return "search";
	}
	
	@RequestMapping("searchByMultiCon")
	public String multiSearch(ContactDTO dto) {
		System.out.println(dto.getName()+" : "+dto.getContact());
		List<ContactDTO> list = dao.searchByMultiCon(dto);
		for(ContactDTO dtox : list) {
			System.out.println(dtox.getSeq()+" : " +dtox.getName()+" : "+dtox.getContact());
		}
		return "search";
	}
	
//	//		ModelAndView mav = new ModelAndView();
//	//		
//	//		try {
//	//			List<ContactDTO> list = dao.selectAll();
//	//			mav.addObject("list",list); // list의 값을 list 로 세팅해서 전달
//	//			mav.setViewName("output"); // output.jsp에 보내고(jsp 이름과 동일해야됨)
//	//		}catch(Exception e) {
//	//			e.printStackTrace();
//	//			mav.setViewName("error");
//	//		}
//	//		return mav;
//	
//	
	@RequestMapping("deleteProc")
	public String deleteProc(int delTarget) throws Exception{
		dao.deleteBySeq(delTarget);
		
		return "redirect:toOutput";	
	}
	
	@RequestMapping("updateProc")
	public String updateProc(String column, String value, int seq) throws Exception{
		int result = dao.update(column,value,seq);
		return "redirect:toOutput";
		
	}
	
	
//	@ExceptionHandler(Exception.class)
//	public String exceptionHandler(Exception e) {
//		e.printStackTrace();
//		System.out.println("예외 처리 코드가 실행되었습니다.");
//		return "redirect:/";
//	}
	
}
