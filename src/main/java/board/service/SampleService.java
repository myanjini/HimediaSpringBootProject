package board.service;

import java.util.List;

import board.dto.ArticleDto;
import board.dto.CategoryDto;
import board.dto.SubArticleDto;

public interface SampleService {
	ArticleDto selectOneArticle() throws Exception;
	List<CategoryDto> selectCategory(int topicId) throws Exception;
	List<SubArticleDto> selectSubArticle(int topicId) throws Exception;
}
