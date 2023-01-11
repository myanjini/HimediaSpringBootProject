package board.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import board.dto.LoginDto;
import board.dto.UserDto;
import board.service.LoginService;

@Controller
public class LoginController {
	@Autowired
	private LoginService loginService;
	
	@GetMapping("/login.do")
	public String login(HttpSession session) throws Exception {
		if (session.getAttribute("user") == null) {
			return "login.html";
		} else {
			return "redirect:/board/openBoardList.do";
		}
	}
	
	@PostMapping("/login.do")
	public String login(LoginDto loginDto, HttpSession session) throws Exception {
		UserDto userDto = loginService.login(loginDto);
		if (userDto == null) {
			session.setAttribute("message", "일치하는 정보가 존재하지 않습니다.");
			return "redirect:login.do";
		} else {
			session.setAttribute("user", userDto);
			return "redirect:/board/openBoardList.do";
		}
	}
	
	@GetMapping("/logout.do")
	public String logout(HttpSession session) throws Exception {
		session.removeAttribute("user");
		session.invalidate();
		return "redirect:login.do";
	}
}
