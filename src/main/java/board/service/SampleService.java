package board.service;

import java.util.List;

import board.dto.TopicDto;
import board.dto.CategoryDto;
import board.dto.ArticleDto;

public interface SampleService {
	TopicDto selectOneArticle() throws Exception;
	List<CategoryDto> selectCategory(int topicId) throws Exception;
	List<ArticleDto> selectSubArticle(int topicId) throws Exception;
}
