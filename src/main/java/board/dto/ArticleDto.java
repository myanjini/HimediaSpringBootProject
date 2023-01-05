package board.dto;

import lombok.Data;

@Data
public class ArticleDto {
	private int articleId;
	private String articleTitle;
	private String articleContents;
	private int topicId;
}
