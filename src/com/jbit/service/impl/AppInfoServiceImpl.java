package com.jbit.service.impl;

import com.jbit.dao.AppInfoMapper;
import com.jbit.entity.AppInfo;
import com.jbit.service.AppInfoService;
import com.jbit.utils.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Xc on 2018/4/19.
 */
@Service("appInfoService")
public class AppInfoServiceImpl implements AppInfoService {
    @Resource
    private AppInfoMapper appInfoMapper;
    @Override
    public List<AppInfo> findAppList(Integer queryStatus,String querySoftwareName,Integer queryFlatformId,Integer queryCategoryLevel1,Integer queryCategoryLevel2,Integer queryCategoryLevel3,Page pages) {
        pages.calcCount(appInfoMapper.totalAppCount(queryStatus,querySoftwareName,queryFlatformId,queryCategoryLevel1,queryCategoryLevel2,queryCategoryLevel3));
        return appInfoMapper.findAppList(queryStatus,querySoftwareName, queryFlatformId,queryCategoryLevel1, queryCategoryLevel2, queryCategoryLevel3,pages.first(),pages.last());
    }

    @Override
    public List<AppInfo> findBackendAppList(String querySoftwareName, Integer queryFlatformId, Integer queryCategoryLevel1, Integer queryCategoryLevel2, Integer queryCategoryLevel3, Page pages) {
        pages.calcCount(appInfoMapper.totalBackendAppCount(querySoftwareName,queryFlatformId,queryCategoryLevel1,queryCategoryLevel2,queryCategoryLevel3));
        return appInfoMapper.findBackendAppList(querySoftwareName, queryFlatformId,queryCategoryLevel1, queryCategoryLevel2, queryCategoryLevel3,pages.first(),pages.last());
    }

    @Override
    public AppInfo findAppOne(Long aid) {
        return appInfoMapper.findAppOne(aid);
    }

    @Override
    public int UpdateAppVersion(Long vid, Long aid) {
        return appInfoMapper.UpdateAppVersion(vid,aid);
    }

    @Override
    public int updateAppinfo(AppInfo appInfo) {
        return appInfoMapper.updateAppinfo(appInfo);
    }

    @Override
    public Integer upStatus(Long id, Long status) {
        return appInfoMapper.upStatus(id,status);
    }

    @Override
    public Integer upStatusSale(AppInfo appInfo) {
        return appInfoMapper.upStatusSale(appInfo);
    }

    @Override
    public AppInfo findAPKName(String apkName) {
        return appInfoMapper.findAPKName(apkName);
    }

    @Override
    public int insertApp(AppInfo appinfo) {
        return appInfoMapper.insertApp(appinfo);
    }

    @Override
    public Integer delApp(Long id) {
        return appInfoMapper.delApp(id);
    }

    @Override
    public Integer updateApp(Long id) {
        return appInfoMapper.updateApp(id);
    }
}
