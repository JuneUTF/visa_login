
package com.bteam.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.bteam.model.UpdateModel;
@Repository
@Mapper
public interface UpdateMapper {	

     List<UpdateModel> SelectUpdateId(UpdateModel updateModel);
	int  InsertUpdateUser(UpdateModel updateModel);
	List<UpdateModel> CheckUser(UpdateModel updateModel);
    
 }