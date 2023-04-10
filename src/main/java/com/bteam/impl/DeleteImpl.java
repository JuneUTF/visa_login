package com.bteam.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bteam.mapper.DeleteMapper;
import com.bteam.model.DeleteModel;
import com.bteam.service.DeleteService;
@Service
public class DeleteImpl implements DeleteService {
	@Resource
	DeleteMapper mapper;
	@Override
	public List<DeleteModel> SelectDeleteId(DeleteModel deleteModel) {
		return mapper.SelectDeleteId(deleteModel);
	 }
	public int DeleteId(DeleteModel deleteModel) {
		return mapper.DeleteId(deleteModel);
	 }
}
