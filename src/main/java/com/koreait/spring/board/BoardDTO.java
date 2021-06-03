package com.koreait.spring.board;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BoardDTO {
    private int  iboard;
    private int startIdx;
    private int recordCnt;
    private int searchType;
    private String searchText;
}
