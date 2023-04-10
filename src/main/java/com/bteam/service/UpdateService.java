package com.bteam.service;


import java.util.List;

import com.bteam.model.UpdateModel;

public interface UpdateService {

	public List<UpdateModel> SelectUpdateId(UpdateModel updateModel);
	List<UpdateModel> CheckUser(UpdateModel updateModel);
    public int InsertUpdateUser(UpdateModel updateModel);

}
