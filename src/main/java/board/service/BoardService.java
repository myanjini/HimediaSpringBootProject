package board.service;

import java.util.List;

import board.dto.BoardDto;

public interface BoardService {
	public List<BoardDto> selectBoardListForSample() throws Exception;
	
	int selectBoardListCount() throws Exception;
	public List<BoardDto> selectBoardList(int offset) throws Exception;
	public void insertBoard(BoardDto boardDto) throws Exception;
	public BoardDto selectBoardDetail(int boardIdx) throws Exception;
	void updateBoard(BoardDto boardDto) throws Exception;
	void deleteBoard(int boardIdx) throws Exception;
}
