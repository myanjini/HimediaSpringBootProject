package board.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import board.dto.ArticleDto;
import board.dto.CategoryDto;
import board.dto.TopicDto;

public interface SampleService {
	TopicDto selectOneArticle() throws Exception;
	List<CategoryDto> selectCategory(int topicId) throws Exception;
	List<ArticleDto> selectSubArticle(int topicId) throws Exception;
	List<TopicDto> selectFourTopic() throws Exception;
	TopicDto selectOneArticleByTopicId(int topicId) throws Exception;
	void topicInsert(TopicDto topicDto, MultipartFile file) throws Exception;
	String saveFile(MultipartFile file) throws Exception;
}
