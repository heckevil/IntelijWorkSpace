package com.koreait.spring.board;

import lombok.Setter;
import lombok.Getter;

@Getter
@Setter
public class boardCmtEntity {
    private int icmt;
    private int iboard;
    private int iuser;
    private String cmt;
    private String regdate;
}
