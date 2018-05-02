package com.jbit.service.impl;

import com.jbit.dao.DataDictionaryMapper;
import com.jbit.entity.DataDictionary;
import com.jbit.service.DataDictionaryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Xc on 2018/4/19.
 */
@Service("dataDictionaryService")
public class DataDictionaryServiceImpl implements DataDictionaryService {

    @Resource
    private DataDictionaryMapper dataDictionaryMapper;
    @Override
    public List<DataDictionary> findtypeCode(String typecode) {
        return dataDictionaryMapper.findtypeCode(typecode);
    }
}
