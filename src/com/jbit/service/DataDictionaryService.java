package com.jbit.service;

import com.jbit.entity.DataDictionary;

import java.util.List;

/**
 * Created by Xc on 2018/4/19.
 */
public interface DataDictionaryService {

    List<DataDictionary> findtypeCode(String typecode);
}
