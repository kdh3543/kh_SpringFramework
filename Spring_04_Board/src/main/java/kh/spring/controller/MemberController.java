package kh.spring.controller;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kh.spring.dao.MemberDAO;
import kh.spring.dto.MemberDTO;
import kh.spring.utils.EncryptionUtils;

@Controller
@RequestMapping("/member/")
public class MemberController {
	
	@Autowired
	private MemberDAO dao;
	
	@Autowired
	private HttpSession session;
	
	@RequestMapping("join")
	public String memberJoin() {
		return "member/join";
	}
	
	@ResponseBody
	@RequestMapping(value="idDuplCheck",produces="text/html;charset=utf8")
	public String idDuplCheck(String id) throws Exception{
		int result = dao.idCheck(id);
		return String.valueOf(result);
	}
	
	@RequestMapping("signupProc")
	public String signupProc(MemberDTO dto) throws Exception{
		
		String encPw = EncryptionUtils.pwdEncrypt(dto.getPw());
		dto.setPw(encPw);
		dao.insert(dto);
		return "home";
	}
	
	@RequestMapping("login")
	public String login(String id, String pw) throws Exception{
		pw = EncryptionUtils.pwdEncrypt(pw);
		int result = dao.login(id,pw);
		if(result>0) {
			session.setAttribute("loginID", id);
		}
		return "redirect:/";
	}
	
	@RequestMapping("logout")
	public String logout() throws Exception{
		session.invalidate();
		return "redirect:/";
	}
	
	@RequestMapping("resign")
	public String resign() throws Exception{
		String id = (String)session.getAttribute("loginID");
		int result = dao.delete(id);
		session.invalidate();
		return "redirect:/";		
	}
	
	@RequestMapping("mypage")
	public String mypage(Model model) throws Exception{
		String id = (String)session.getAttribute("loginID");
		MemberDTO dto = dao.selectById(id);
		model.addAttribute("dto",dto);
		return "/member/mypage";
	}
	
	@RequestMapping("update")
	public String update(MemberDTO dto) throws Exception{
		String id = (String)session.getAttribute("loginID");
		dto.setId(id);
		dao.update(dto);
		
		return "redirect:/";
	}
	
	@ExceptionHandler(Exception.class)
	public String exceptionHandler(Exception e) {
		e.printStackTrace();
		System.out.println("????????? ?????????");
		return "error";
	}
}
