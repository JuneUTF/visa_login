package com.bteam.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bteam.model.UserRegisterModel;

@Service
public interface UserRegisterService {
	 int insert(UserRegisterModel userRegisterModel);
	 List<UserRegisterModel> checkuser(UserRegisterModel userRegisterModel);
}
