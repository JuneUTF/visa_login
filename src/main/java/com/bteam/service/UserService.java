package com.bteam.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bteam.model.UserModel;

@Service
public interface UserService {
	List<UserModel> checkpendding(String username);
	 List<UserModel> selectAll(UserModel userModel);
	 List<UserModel> searchuser(UserModel userModel);
	 List<UserModel> searchid(UserModel userModel);
	 List<UserModel> selectid(UserModel userModel);
}
