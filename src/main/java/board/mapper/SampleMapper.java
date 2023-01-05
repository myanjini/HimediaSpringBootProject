package board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import board.dto.ArticleDto;
import board.dto.CategoryDto;
import board.dto.SubArticleDto;

@Mapper
public interface SampleMapper {
	ArticleDto selectOneArticle() throws Exception;
	List<CategoryDto> selectCategory(int topicId) throws Exception;
	List<SubArticleDto> selectSubArticle(int topicId) throws Exception;
}
