package board.service;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import board.dto.ArticleDto;
import board.dto.CategoryDto;
import board.dto.NewsDto;
import board.dto.TopicDto;
import board.mapper.SampleMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SampleServiceImpl implements SampleService {
	@Autowired
	private SampleMapper sampleMapper;
	
	
	// 설정 파일에서 업로드 파일이 저장되는 경로를 가져와서 가지고 있는 변수
	@Value("${application.upload-path}")
	private String uploadPath;
	
	
	
	@Override
	public TopicDto selectOneArticle() throws Exception {
		return sampleMapper.selectOneTopic();
	}

	@Override
	public List<CategoryDto> selectCategoryList() throws Exception {
		return sampleMapper.selectCategoryList();
	}

	@Override
	public List<CategoryDto> selectCategory(int topicId) throws Exception {
		return sampleMapper.selectCategory(topicId);
	}

	@Override
	public List<ArticleDto> selectSubArticle(int topicId) throws Exception {
		return sampleMapper.selectArticle(topicId);
	}

	@Override
	public List<TopicDto> selectFourTopic() throws Exception {
		return sampleMapper.selectFourTopic();
	}

	@Override
	public TopicDto selectOneArticleByTopicId(int topicId) throws Exception {
		return sampleMapper.selectOneArticleByTopicId(topicId);
	}

	@Override
	public void topicInsert(TopicDto topicDto, MultipartFile file) throws Exception {
		String savedFilePath = saveFile(file);
		topicDto.setTopicImage(savedFilePath);
		sampleMapper.topicInsert(topicDto);	
		sampleMapper.insertTopicCategory(topicDto);
	}

	// 파일을 저장하고 저장 경로를 반환하는 메서드
	@Override
	public String saveFile(MultipartFile file) throws Exception {
		String savedFilePath = uploadPath + file.getOriginalFilename();
		log.debug(savedFilePath);
		
		File uploadFile = new File(savedFilePath);
		file.transferTo(uploadFile);
		
		return savedFilePath;
	}

	@Override
	public List<NewsDto> selectNews() throws Exception {
		List<NewsDto> newsList = sampleMapper.selectNews();
		
		for (NewsDto news : newsList) {
			List<String> categoryNames = sampleMapper.selectCategoryByNewsId(news.getNewsId());
			news.setCategoryNames(categoryNames.toArray(new String[categoryNames.size()]));
		}
		
		return newsList;
	}

	@Override
	public NewsDto selectOneNewsByNewsId(int newsId) throws Exception {
		return sampleMapper.selectOneNewsByNewsId(newsId);
	}

}
