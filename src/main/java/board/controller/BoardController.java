package board.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import board.dto.BoardDto;
import board.dto.UserDto;
import board.service.BoardService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class BoardController {
	
	// private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private BoardService boardService;
	
	
	// (로그인 기능 구현 전) 테스트를 위해 사용자 정보를 세션에 저장
	private void makeSessionForTest(HttpSession session) {
		UserDto userDto = new UserDto();
		userDto.setUserId("tester");
		userDto.setUserName("테스터");
		userDto.setUserEmail("tester@test.com");
		
		session.setAttribute("user", userDto);
	}
	
	
	@GetMapping("/board/openBoardList.do")
	public ModelAndView openBoardList() throws Exception {
		ModelAndView mv = new ModelAndView("/board/boardList");
		
		List<BoardDto> list = boardService.selectBoardList();
		mv.addObject("list", list);
		
		return mv;
	}
	
	@GetMapping("/board/openBoardWrite.do")
	public String openBoardWrite(HttpSession session) throws Exception {
		// 사용자 정보를 세션에 저장 
		// 로그인 기능이 구현되면 필요 없음 
		makeSessionForTest(session);
		return "/board/boardWrite";
	}
	
	@PostMapping("/board/insertBoard.do")
	public String insertBoard(BoardDto boardDto, HttpSession session) throws Exception {
		// 글쓴이 아이디를 세션에서 가져와서 저장
		UserDto userDto = (UserDto)session.getAttribute("user");
		boardDto.setCreatedId(userDto.getUserId());
		
		boardService.insertBoard(boardDto);
		return "redirect:/board/openBoardList.do";
	}
	
	@GetMapping("/board/openBoardDetail.do")
	public ModelAndView openBoardDetail(@RequestParam int boardIdx) throws Exception {
		ModelAndView mv = new ModelAndView("/board/boardDetail");
		
		BoardDto boardDto = boardService.selectBoardDetail(boardIdx);
		mv.addObject("board", boardDto);
		
		return mv;
	}
	
	@PostMapping("/board/updateBoard.do")
	public String updateBoard(BoardDto boardDto, HttpSession session) throws Exception {
		// 글쓴이 아이디를 세션에서 가져와서 저장
		UserDto userDto = (UserDto)session.getAttribute("user");
		boardDto.setUpdatedId(userDto.getUserId());
		
		boardService.updateBoard(boardDto);
		return "redirect:/board/openBoardList.do";
	}
	
	@PostMapping("/board/deleteBoard.do")
	public String deleteBoard(BoardDto boardDto, HttpSession session) throws Exception {
		// 글삭제 아이디를 세션에서 가져와서 저장
		UserDto userDto = (UserDto)session.getAttribute("user");
		boardDto.setUpdatedId(userDto.getUserId());
		
		boardService.deleteBoard(boardDto.getBoardIdx());
		return "redirect:/board/openBoardList.do";
	}
}
