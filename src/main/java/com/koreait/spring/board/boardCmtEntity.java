package com.koreait.spring.board;

import lombok.Setter;
import lombok.Getter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardCmtEntity {
    private int icmt;
    private int iboard;
    private int iuser;
    private String cmt;
    private String regdate;
}
