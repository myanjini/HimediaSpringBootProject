package board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import board.dto.BoardDto;

@Mapper
public interface BoardMapper {
	List<BoardDto> selectBoardListForSample() throws Exception;
	
	// 검색 조건과 일치하는 게시판 개수를 반환
	int selectBoardListCount() throws Exception;
	// 검색 조건과 일치하는 게시판 중 offset에서 부터 10개만 조회해서 반환
	List<BoardDto> selectBoardList(int offset) throws Exception;
	
	void insertBoard(BoardDto boardDto) throws Exception;
	void updateHitCount(int boardIdx) throws Exception;
	BoardDto selectBoardDetail(int boardIdx) throws Exception;
	void updateBoard(BoardDto boardDto) throws Exception;
	void deleteBoard(int boardIdx) throws Exception;
}
