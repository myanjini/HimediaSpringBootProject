package board.dto;

import lombok.Data;

@Data
public class NewsDto {
	private int newsId;
	private String newsTitle;
	private String newsImage;
	private String newsContents;
	private String registDt;
	private int typeId;
	private String typeName;
	private String[] categoryNames;
}
