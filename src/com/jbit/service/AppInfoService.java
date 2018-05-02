package com.jbit.service;

import com.jbit.entity.AppInfo;
import com.jbit.utils.Page;

import java.util.List;

/**
 * Created by Xc on 2018/4/19.
 */
public interface AppInfoService {

    List<AppInfo> findAppList(Integer queryStatus,String querySoftwareName,Integer queryFlatformId,Integer queryCategoryLevel1,Integer queryCategoryLevel2,Integer queryCategoryLevel3,Page pages);
    List<AppInfo> findBackendAppList(String querySoftwareName,Integer queryFlatformId,Integer queryCategoryLevel1,Integer queryCategoryLevel2,Integer queryCategoryLevel3,Page pages);

    AppInfo findAppOne(Long aid);

    int UpdateAppVersion(Long vid,Long aid);

    int updateAppinfo(AppInfo appInfo);

    Integer upStatus(Long id, Long status);

    Integer upStatusSale(AppInfo appInfo);

    AppInfo findAPKName(String apkName);

    int insertApp(AppInfo appinfo);

    Integer delApp(Long id);

    Integer updateApp(Long id);
}
