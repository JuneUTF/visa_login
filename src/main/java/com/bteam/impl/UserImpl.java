package com.bteam.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bteam.mapper.UserMapper;
import com.bteam.model.UserModel;
import com.bteam.service.UserService;
@Service
public class UserImpl implements UserService {
	@Resource
	UserMapper mapper;
	@Override
	public List<UserModel> checkpendding(String username) {
		return mapper.checkpendding(username);
	}
	 public List<UserModel> selectAll(UserModel userModel){
		 return mapper.selectAll(userModel);
	 }
	 public  List<UserModel> searchuser(UserModel userModel){
		 return mapper.searchuser(userModel);
	 }
	 public  List<UserModel> searchid(UserModel userModel){
		 return mapper.searchid(userModel);
	 }
	 public  List<UserModel> selectid(UserModel userModel){
		 return mapper.selectAll(userModel);
	 }
}
