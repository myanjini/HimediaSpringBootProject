package board.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import board.dto.ArticleDto;
import board.dto.CategoryDto;
import board.dto.NewsDto;
import board.dto.TopicDto;
import board.service.SampleService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class SampleController {
//	private BoardService boardService;
	
	@Autowired
	private SampleService sampleService;
	
	@GetMapping("/sample")
	public ModelAndView sample() throws Exception {
		ModelAndView mv = new ModelAndView("sample.html");
		
		// List<BoardDto> list = boardService.selectBoardListForSample();
		List<TopicDto> list = sampleService.selectFourTopic();
		mv.addObject("sidemenu", list);
		
		TopicDto topicDto = sampleService.selectOneArticle();
		mv.addObject("topic", topicDto);
		
		List<CategoryDto> categoryDto = sampleService.selectCategory(topicDto.getTopicId());
		mv.addObject("category", categoryDto);
		
		List<ArticleDto> articleDto = sampleService.selectSubArticle(topicDto.getTopicId());
		mv.addObject("article", articleDto);
				
		List<NewsDto> newsDto = sampleService.selectNews();
		mv.addObject("news", newsDto);
		
		return mv;
	}
	
	// http://localhost:8080/getTopic.do?topicId=...
	@GetMapping("/getTopic.do")
	public ModelAndView getTopic(@RequestParam int topicId) throws Exception {
		ModelAndView mv = new ModelAndView("sample.html");
		
		// List<BoardDto> list = boardService.selectBoardListForSample();
		List<TopicDto> list = sampleService.selectFourTopic();
		mv.addObject("sidemenu", list);
		
		TopicDto topicDto = sampleService.selectOneArticleByTopicId(topicId);
		mv.addObject("topic", topicDto);
		
		List<CategoryDto> categoryDto = sampleService.selectCategory(topicDto.getTopicId());
		mv.addObject("category", categoryDto);
		
		List<ArticleDto> articleDto = sampleService.selectSubArticle(topicDto.getTopicId());
		mv.addObject("article", articleDto);
				
		List<NewsDto> newsDto = sampleService.selectNews();
		mv.addObject("news", newsDto);
		
		return mv;
	}	
	
	// ?????? ????????? ?????? -> ????????? ?????? (X) => ???????????? ????????? ???????????? ?????? 
	@GetMapping("/topicWrite.do")
	public ModelAndView topicWrite() throws Exception {
		ModelAndView mv = new ModelAndView("topicWrite.html");
		
		List<CategoryDto> category = sampleService.selectCategoryList();
		mv.addObject("category", category);
		
		return mv;
	}	
		
	// ?????? ????????? ?????? -> ???????????? ???????????? ????????????????????? ??????
	@PostMapping("/topicInsert.do")
	public String topicInsert(@RequestParam("imageFile") MultipartFile file, TopicDto topicDto) throws Exception {
		
		log.debug(file.getName());				// <input type="file"> ????????? ??????
		log.debug(file.getOriginalFilename());	// ???????????? ??????????????? ?????? ????????? ??????
		log.debug(file.getContentType());		// ???????????? ????????? ??????
		
		log.debug(topicDto.getTopicTitle());	// <input type="text" name="title"> ????????? ????????? ???
		log.debug(topicDto.getTopicImage());	// ?????? ???????????? ?????? ?????? ??????
		log.debug(topicDto.getTopicContents());	// <textarea name="contents"> ????????? ????????? ???
		
		log.debug(Arrays.toString(topicDto.getCategory()));
		
		
		// TODO. file ????????? ????????? ????????? ??????????????? ?????? ??? ?????? ????????? DB??? ?????? 
		
		sampleService.topicInsert(topicDto, file);
		
		return "redirect:sample";
	}	
	
	@GetMapping("/download.do")
	public void downloadFile(@RequestParam int topicId, HttpServletResponse response) throws Exception {
		// topicId??? ???????????? ?????? ?????? ??????
		TopicDto topicDto = sampleService.selectOneArticleByTopicId(topicId);
				
		// ?????? ???????????? topicImage ????????? ??????
		String topicImage = topicDto.getTopicImage();
		
		// topicImage ????????? ???????????? ????????? ????????? response ????????? ????????? ?????????????????? ??????
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			response.setHeader("Content-Disposition", "inline;");
			
			byte[] buf = new byte[1024];
			fis = new FileInputStream(topicImage);
			bis = new BufferedInputStream(fis);
			bos = new BufferedOutputStream(response.getOutputStream());
			int read;
			while((read = bis.read(buf, 0, 1024)) != -1) {
				bos.write(buf, 0, read);
			}
		} finally {
			bos.close();
			bis.close();
			fis.close();
		}
	}	
	
	@GetMapping("/downloadNewsImage.do")
	public void downloadNewsImage(@RequestParam int newsId, HttpServletResponse response) throws Exception {
		NewsDto newsDto = sampleService.selectOneNewsByNewsId(newsId);
				
		String newsImage = newsDto.getNewsImage();
		
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			response.setHeader("Content-Disposition", "inline;");
			
			byte[] buf = new byte[1024];
			fis = new FileInputStream(newsImage);
			bis = new BufferedInputStream(fis);
			bos = new BufferedOutputStream(response.getOutputStream());
			int read;
			while((read = bis.read(buf, 0, 1024)) != -1) {
				bos.write(buf, 0, read);
			}
		} finally {
			bos.close();
			bis.close();
			fis.close();
		}
	}		
}
