package com.jbit.service;

import com.jbit.entity.AppCategory;

import java.util.List;

/**
 * Created by Xc on 2018/4/19.
 */
public interface AppCategoryService {


    List<AppCategory> findcategoryLevel(Integer pid);

}
