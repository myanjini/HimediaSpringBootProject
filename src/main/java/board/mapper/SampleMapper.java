package board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import board.dto.TopicDto;
import board.dto.CategoryDto;
import board.dto.ArticleDto;

@Mapper
public interface SampleMapper {
	TopicDto selectOneTopic() throws Exception;
	List<CategoryDto> selectCategory(int topicId) throws Exception;
	List<ArticleDto> selectArticle(int topicId) throws Exception;
	List<TopicDto> selectFourTopic() throws Exception;
	TopicDto selectOneArticleByTopicId(int topicId) throws Exception;
	void topicInsert(TopicDto topicDto) throws Exception;
}
