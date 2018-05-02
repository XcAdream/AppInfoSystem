package com.jbit.dao;

import com.jbit.entity.AppCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AppCategoryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AppCategory record);

    int insertSelective(AppCategory record);

    AppCategory selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AppCategory record);

    int updateByPrimaryKey(AppCategory record);

    /**
     * 分类
     * @param pid
     * @return
     */
    List<AppCategory> findcategoryLevel(@Param("pid") Integer pid);
}