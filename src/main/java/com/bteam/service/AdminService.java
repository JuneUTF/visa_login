package com.bteam.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bteam.model.AdminModel;

@Service
public interface AdminService {
	 int AdminRegisterUser(AdminModel adminModel);
	 List<AdminModel> AdminCheckUser(AdminModel adminModel);
	 List<AdminModel> AdminSelectPending(AdminModel adminModel);
		List<AdminModel> AdminSelectDeleted(AdminModel adminModel);
		List<AdminModel> AdminSearchUser(AdminModel adminModel);
		List<AdminModel> AdminSearchId(AdminModel adminModel);
}
