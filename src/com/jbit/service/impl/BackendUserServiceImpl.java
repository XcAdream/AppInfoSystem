package com.jbit.service.impl;

import com.jbit.dao.BackendUserMapper;
import com.jbit.entity.BackendUser;
import com.jbit.service.BackendUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Xc on 2018/4/19.
 */
@Service("backendUserService")
public class BackendUserServiceImpl implements BackendUserService {
    @Resource
    private BackendUserMapper BackendUserMapper;
    @Override
    public BackendUser findlogin(String usercode, String userpassword) {
        return BackendUserMapper.findlogin(usercode,userpassword);
    }
}
