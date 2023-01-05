package board.dto;

import lombok.Data;

@Data
public class SubArticleDto {
	private int articleId;
	private String articleTitle;
	private String articleContents;
	private int topicId;
}
