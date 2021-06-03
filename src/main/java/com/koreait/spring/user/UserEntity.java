package com.koreait.spring.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@ToString
@Alias("UserEntity")
//자동으로 자기이름으로 AS 등록 굳이 변경외 할 필요 없다.
public class UserEntity {

    private int iuser;
    private String uid;
    private String upw;
    private String unm;
    private int gender;
    private String regdt;
    private String profileImg;
}
