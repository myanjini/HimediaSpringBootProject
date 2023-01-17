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
	
	@Autowired
	private BoardService boardService;
	
	
	@GetMapping("/board/openBoardList.do")
	public ModelAndView openBoardList(
			// 현재 페이지 번호를 요청 파라미터로 부터 추출
			@RequestParam(value="currentPage", required=false, defaultValue="1") int currentPage) throws Exception {
		ModelAndView mv = new ModelAndView("/board/boardList");
		
		List<BoardDto> list = boardService.selectBoardList((currentPage - 1) * 10);
		mv.addObject("list", list);
		
		// 페이징 정보 출력에 사용되는 변수
		mv.addObject("pageCount", Math.ceil(boardService.selectBoardListCount() / 10.0));
		mv.addObject("currentPage", currentPage);
		
		return mv;
	}
	
	@GetMapping("/board/openBoardWrite.do")
	public String openBoardWrite(HttpSession session) throws Exception {
		return "/board/boardWrite";
	}
	
	@PostMapping("/board/insertBoard.do")
	public String insertBoard(BoardDto boardDto, HttpSession session) throws Exception {
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
		UserDto userDto = (UserDto)session.getAttribute("user");
		boardDto.setUpdatedId(userDto.getUserId());
		
		boardService.updateBoard(boardDto);
		return "redirect:/board/openBoardList.do";
	}
	
	@PostMapping("/board/deleteBoard.do")
	public String deleteBoard(BoardDto boardDto, HttpSession session) throws Exception {
		UserDto userDto = (UserDto)session.getAttribute("user");
		boardDto.setUpdatedId(userDto.getUserId());
		
		boardService.deleteBoard(boardDto.getBoardIdx());
		return "redirect:/board/openBoardList.do";
	}
}
