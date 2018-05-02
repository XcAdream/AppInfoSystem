package com.jbit.service;

import com.jbit.entity.BackendUser;

/**
 * Created by Xc on 2018/4/19.
 */
public interface BackendUserService {

    BackendUser findlogin(String usercode,String userpassword);
}
