package com.bteam.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.bteam.model.DeleteModel;

@Repository
@Mapper
public interface DeleteMapper {
	List<DeleteModel> SelectDeleteId(DeleteModel deleteModel);
	int DeleteId(DeleteModel deleteModel);
}
