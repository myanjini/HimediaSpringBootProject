package board.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
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
				
		return mv;
	}	
	
	// 등록 화면을 요청 -> 화면만 전달 
	// http://localhost:8080/topicWrite.do 
	@GetMapping("/topicWrite.do")
	public String topicWrite() throws Exception {
		return "topicWrite.html";
	}
	
		
	// 등록 처리를 요청 -> 전달받은 데이터를 데이터베이스에 저장
	@PostMapping("/topicInsert.do")
	public String topicInsert(@RequestParam("imageFile") MultipartFile file, TopicDto topicDto) throws Exception {
		
		log.debug(file.getName());				// <input type="file"> 태그의 이름
		log.debug(file.getOriginalFilename());	// 사용자가 업로드하기 위해 선택한 파일
		log.debug(file.getContentType());		// 업로드한 파일의 종류
		
		log.debug(topicDto.getTopicTitle());	// <input type="text" name="title"> 태그에 입력한 값
		log.debug(topicDto.getTopicImage());	// 값이 설정되지 않는 것을 확인
		log.debug(topicDto.getTopicContents());	// <textarea name="contents"> 태그에 입력한 값
		
		
		// TODO. file 객체의 내용을 지정된 디렉터리에 저장 후 해당 정보를 DB에 저장 
		
		sampleService.topicInsert(topicDto, file);
		
		return "redirect:sample";
	}	
	
	@GetMapping("/download.do")
	public void downloadFile(@RequestParam int topicId, HttpServletResponse response) throws Exception {
		// topicId에 해당하는 기사 정보 조회
		TopicDto topicDto = sampleService.selectOneArticleByTopicId(topicId);
				
		// 기사 정보에서 topicImage 정보를 추출
		String topicImage = topicDto.getTopicImage();
		
		// topicImage 정보에 해당하는 파일을 읽어서 response 객체를 통해서 클라이언트로 전달
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
}
