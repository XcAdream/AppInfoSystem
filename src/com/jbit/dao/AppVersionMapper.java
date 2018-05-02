package com.jbit.dao;

import com.jbit.entity.AppVersion;

import java.util.List;

public interface AppVersionMapper {
    int deleteByPrimaryKey(Long id);

    int insertAppVersion(AppVersion appVersion);

    int insertSelective(AppVersion record);

    AppVersion selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AppVersion record);

    int updateAppVersion(AppVersion appVersion);

    AppVersion findAppVersionOne(Long vid);

    List<AppVersion> findAppVersion(Long id);

    int deleteAppversion(Long id);

    AppVersion findAppVersionlast();

    int updatedel(Long id);
}