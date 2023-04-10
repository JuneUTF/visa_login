package com.bteam.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bteam.mapper.UserRegisterMapper;
import com.bteam.model.UserRegisterModel;
import com.bteam.service.UserRegisterService;
@Service
public class UserRegisterImpl implements UserRegisterService {
@Resource
UserRegisterMapper mapper;
@Override
		public int insert(UserRegisterModel userRegisterModel) {
				return mapper.insert(userRegisterModel);
		}
		public List<UserRegisterModel> checkuser(UserRegisterModel userRegisterModel) {
				return mapper.checkuser(userRegisterModel);
		}
}
