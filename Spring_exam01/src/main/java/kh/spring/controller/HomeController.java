package kh.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kh.spring.dao.DAO;
import kh.spring.dto.DTO;

@Controller
public class HomeController {
	
	@Autowired
	public DAO dao;
	
	@RequestMapping("/")
	public String home() {

		return "home";
	}
	
	@RequestMapping("inputForm")
	public String input() {

		return "inputForm";
	}
	
	@RequestMapping("inputProc")
	public String inputProc(DTO dto) {
		try {
			dao.insert(dto);
		}catch(Exception e) {
			e.printStackTrace();
			return "error";
		}
		return "home";
	}
}
