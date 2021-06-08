package com.koreait.spring.user;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    int insUser(UserEntity param);
    UserEntity selUser(UserEntity param);
    /*select 문에서 resulttype으로 반환하는 타입
     rs.get 해서 반출하는게 있을때 사용.*/
    int updUser(UserEntity param);
}
