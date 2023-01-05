package board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import board.dto.ArticleDto;
import board.dto.CategoryDto;
import board.dto.SubArticleDto;
import board.mapper.SampleMapper;

@Service
public class SampleServiceImpl implements SampleService {
	@Autowired
	private SampleMapper sampleMapper;
	
	@Override
	public ArticleDto selectOneArticle() throws Exception {
		return sampleMapper.selectOneArticle();
	}

	@Override
	public List<CategoryDto> selectCategory(int topicId) throws Exception {
		return sampleMapper.selectCategory(topicId);
	}

	@Override
	public List<SubArticleDto> selectSubArticle(int topicId) throws Exception {
		return sampleMapper.selectSubArticle(topicId);
	}
}
