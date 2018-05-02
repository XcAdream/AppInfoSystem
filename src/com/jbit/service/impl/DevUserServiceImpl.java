package com.jbit.service.impl;

import com.jbit.dao.DevUserMapper;
import com.jbit.entity.DevUser;
import com.jbit.service.DevUserService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Service("devUserService")
public class DevUserServiceImpl implements DevUserService {

    @Resource
    private DevUserMapper devUserMapper;
    @Override
    public DevUser findlogin(String devcode, String devpassword) {
        return devUserMapper.findlogin(devcode,devpassword);
    }
}
