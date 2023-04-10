package com.bteam.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bteam.mapper.AdminMapper;
import com.bteam.model.AdminModel;
import com.bteam.service.AdminService;
@Service
public class AdminImpl implements AdminService {
@Resource
AdminMapper mapper;
@Override
		public int AdminRegisterUser(AdminModel AdminModel) {
				return mapper.AdminRegisterUser(AdminModel);
		}
		public List<AdminModel> AdminCheckUser(AdminModel AdminModel) {
				return mapper.AdminCheckUser(AdminModel);
		}
		public List<AdminModel> AdminSelectPending(AdminModel AdminModel) {
			return mapper.AdminSelectPending(AdminModel);
	}
		public List<AdminModel> AdminSelectDeleted(AdminModel AdminModel) {
			return mapper.AdminSelectDeleted(AdminModel);
	}
		public List<AdminModel> AdminSearchUser(AdminModel AdminModel) {
			return mapper.AdminSearchUser(AdminModel);
	}
		public List<AdminModel> AdminSearchId(AdminModel AdminModel) {
			return mapper.AdminSearchId(AdminModel);
	}
}
