package board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import board.dto.ArticleDto;
import board.dto.BoardDto;
import board.dto.CategoryDto;
import board.dto.SubArticleDto;
import board.service.BoardService;
import board.service.SampleService;

@Controller
public class SampleController {
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private SampleService sampleService;
	
	@GetMapping("/sample")
	public ModelAndView sample() throws Exception {
		ModelAndView mv = new ModelAndView("sample.html");
		
		List<BoardDto> list = boardService.selectBoardListForSample();
		mv.addObject("sidemenu", list);
		
		ArticleDto articleDto = sampleService.selectOneArticle();
		mv.addObject("article", articleDto);
		
		List<CategoryDto> categoryDto = sampleService.selectCategory(articleDto.getTopicId());
		mv.addObject("category", categoryDto);
		
		List<SubArticleDto> subArticleDto = sampleService.selectSubArticle(articleDto.getTopicId());
		mv.addObject("subArticle", subArticleDto);
				
		return mv;
	}
}
