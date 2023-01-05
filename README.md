# 스마트웹 모바일 서비스를 위한 클라우드 기반의 MSA 풀스택 자바 개발 과정

## 스프링 부트 게시판 실습

### 데이터베이스 연동 설정
- application.properties
- DatabaseConfiguration.java 

### 게시판 목록 조회 기능
- BoardController.openBoardList()
- BoardService.selectBoardList()
- BoardServiceImpl.selectBoardList()
- BoardMapper.selectBoardList()
- sql-board.xml > selectBoardList
- boardList.html

### 게시판 등록 기능 구현
- BoardController.openBoardWrite()
- BoardController.insertBoard(BoardDto boardDto)
- BoardService.insertBoard(BoardDto boardDto)
- BoardServiceImpl.insertBoard(BoardDto boardDto)
- BoardMapper.insertBoard(BoardDto boardDto)
- sql-board.xml > insertBoard
- boardWrite.html

### 게시판 수정 기능 구현

### 게시판 삭제 기능 구현

### 로깅 기능 구현
- logback
- log4jdbc 


