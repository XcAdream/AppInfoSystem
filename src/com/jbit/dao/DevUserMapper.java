package com.jbit.dao;

import com.jbit.entity.DevUser;
import org.apache.ibatis.annotations.Param;

public interface DevUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DevUser record);

    int insertSelective(DevUser record);

    DevUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DevUser record);

    int updateByPrimaryKey(DevUser record);

    /**
     * 用户登录
     * @param devcode
     * @param devpassword
     * @return
     */
    DevUser findlogin(@Param("devcode") String devcode, @Param("devpassword") String devpassword);
}