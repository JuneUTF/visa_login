package com.bteam.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.bteam.model.UserRegisterModel;

@Repository
@Mapper
public interface UserRegisterMapper {
		 int insert(UserRegisterModel userRegisterModel);
		 List<UserRegisterModel> checkuser(UserRegisterModel userRegisterModel);
}
