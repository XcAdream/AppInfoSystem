package com.jbit.service.impl;

import com.jbit.dao.AppVersionMapper;
import com.jbit.entity.AppVersion;
import com.jbit.service.AppVersionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Xc on 2018/4/20.
 */
@Service("appVersionService")
public class AppVersionServiceImpl implements AppVersionService {
    @Resource
    private AppVersionMapper appVersionMapper;
    @Override
    public AppVersion findAppVersionOne(Long vid) {
        return appVersionMapper.findAppVersionOne(vid);
    }


    @Override
    public List<AppVersion> findAppVersion(Long id) {return appVersionMapper.findAppVersion(id);}

    @Override
    public int insertAppVersion(AppVersion appVersion) {
        return appVersionMapper.insertAppVersion(appVersion);
    }

    @Override
    public int deleteAppversion(Long id) {
        return appVersionMapper.deleteAppversion(id);
    }

    @Override
    public AppVersion findAppVersionlast() {
        return appVersionMapper.findAppVersionlast();
    }

    @Override
    public int updateAppVersion(AppVersion appVersion) {
        return appVersionMapper.updateAppVersion(appVersion);
    }

    @Override
    public int updatedel(Long id) {
        return appVersionMapper.updatedel(id);
    }
}
