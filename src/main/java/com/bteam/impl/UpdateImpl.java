package com.bteam.impl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bteam.mapper.UpdateMapper;
import com.bteam.model.UpdateModel;
import com.bteam.service.UpdateService;


@Service
public class UpdateImpl implements UpdateService {

    @Resource
    private UpdateMapper mapper;

    /**
     * 新規作成
     *
     * @param user
     */

    @Override
    public List<UpdateModel> SelectUpdateId(UpdateModel updateModel){
        return mapper.SelectUpdateId(updateModel);
    }
    public List<UpdateModel> CheckUser(UpdateModel updateModel){
        return mapper.CheckUser(updateModel);
    }
    public int InsertUpdateUser(UpdateModel updateModel){
        return mapper.InsertUpdateUser(updateModel);
    }
}