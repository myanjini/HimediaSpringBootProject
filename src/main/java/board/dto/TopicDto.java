package board.dto;

import lombok.Data;

@Data
public class TopicDto {
	private int topicId;
	private String topicTitle;
	private String topicImage;
	private String topicContents;
	private String registDt;
}
