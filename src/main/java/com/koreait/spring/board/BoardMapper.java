package com.koreait.spring.board;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    List<BoardDomain> selBoardList();
    /* 리스트 형태로 resultType으로 반환 */
    BoardDomain selBoard(BoardDTO param);
    int writeMod(BoardCmtEntity param);
}
