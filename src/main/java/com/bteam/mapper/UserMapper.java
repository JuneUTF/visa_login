package com.bteam.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.bteam.model.UserModel;

@Repository
@Mapper
public interface UserMapper {
	List<UserModel> checkpendding(String username);
	List<UserModel> selectAll(UserModel userModel);
	List<UserModel> searchuser(UserModel userModel);
	List<UserModel> searchid(UserModel userModel);
}
