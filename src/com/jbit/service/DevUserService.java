package com.jbit.service;

import com.jbit.entity.DevUser;

/**
 * Created by Administrator on 2018/4/19.
 */
public interface DevUserService {
    DevUser findlogin(String devcode,String devpassword);
}
