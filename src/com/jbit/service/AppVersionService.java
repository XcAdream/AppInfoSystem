package com.jbit.service;

import com.jbit.entity.AppVersion;

import java.util.List;

/**
 * Created by Xc on 2018/4/20.
 */
public interface AppVersionService {

    AppVersion findAppVersionOne(Long vid);

    List<AppVersion> findAppVersion(Long id);

    int insertAppVersion(AppVersion appVersion);

    int deleteAppversion(Long id);

    AppVersion findAppVersionlast();

    int updateAppVersion(AppVersion appVersion);

    int updatedel(Long id);

}
