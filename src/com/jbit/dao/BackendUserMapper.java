package com.jbit.dao;

import com.jbit.entity.BackendUser;
import org.apache.ibatis.annotations.Param;

public interface BackendUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BackendUser record);

    int insertSelective(BackendUser record);

    BackendUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BackendUser record);

    int updateByPrimaryKey(BackendUser record);

    BackendUser findlogin(@Param("usercode") String usercode, @Param("userpassword") String userpassword);
}