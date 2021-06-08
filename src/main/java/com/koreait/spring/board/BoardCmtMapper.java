package com.koreait.spring.board;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardCmtMapper {
    int insBoardCmt(BoardCmtEntity param);
    List<BoardCmtDomain> selBoardCmtList(BoardCmtEntity param);
    /* list 로 만들면 While 문으로 모두 가져옴
     list로 안만들면 if문으로 딱 한줄 가져온다 .*/
    int delBoardCmt(BoardCmtEntity param);
    int updBoardCmt(BoardCmtEntity param);
}