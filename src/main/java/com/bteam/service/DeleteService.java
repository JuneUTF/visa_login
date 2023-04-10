package com.bteam.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bteam.model.DeleteModel;

@Service
public interface DeleteService {
	List<DeleteModel> SelectDeleteId(DeleteModel deleteModel);
	int DeleteId(DeleteModel deleteModel);
}
