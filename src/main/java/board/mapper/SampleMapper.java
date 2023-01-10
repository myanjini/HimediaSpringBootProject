package board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import board.dto.ArticleDto;
import board.dto.CategoryDto;
import board.dto.NewsDto;
import board.dto.TopicDto;

@Mapper
public interface SampleMapper {
	TopicDto selectOneTopic() throws Exception;
	List<CategoryDto> selectCategoryList() throws Exception;
	List<CategoryDto> selectCategory(int topicId) throws Exception;
	List<ArticleDto> selectArticle(int topicId) throws Exception;
	List<TopicDto> selectFourTopic() throws Exception;
	TopicDto selectOneArticleByTopicId(int topicId) throws Exception;
	void topicInsert(TopicDto topicDto) throws Exception;
	void insertTopicCategory(TopicDto topicDto) throws Exception;
	List<NewsDto> selectNews() throws Exception;
	NewsDto selectOneNewsByNewsId(int newsId) throws Exception;
	List<String> selectCategoryByNewsId(int newsId) throws Exception;
}
