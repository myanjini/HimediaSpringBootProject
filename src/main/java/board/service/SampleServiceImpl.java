package board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import board.dto.TopicDto;
import board.dto.CategoryDto;
import board.dto.ArticleDto;
import board.mapper.SampleMapper;

@Service
public class SampleServiceImpl implements SampleService {
	@Autowired
	private SampleMapper sampleMapper;
	
	@Override
	public TopicDto selectOneArticle() throws Exception {
		return sampleMapper.selectOneTopic();
	}

	@Override
	public List<CategoryDto> selectCategory(int topicId) throws Exception {
		return sampleMapper.selectCategory(topicId);
	}

	@Override
	public List<ArticleDto> selectSubArticle(int topicId) throws Exception {
		return sampleMapper.selectArticle(topicId);
	}
}
