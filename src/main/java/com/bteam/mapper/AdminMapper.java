package com.bteam.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.bteam.model.AdminModel;


@Repository
@Mapper
public interface AdminMapper {
		 int AdminRegisterUser(AdminModel adminModel);
		 List<AdminModel> AdminCheckUser(AdminModel adminModel);
		 List<AdminModel> AdminSelectPending(AdminModel adminModel);
		List<AdminModel> AdminSelectDeleted(AdminModel adminModel);
		List<AdminModel> AdminSearchUser(AdminModel adminModel);
		List<AdminModel> AdminSearchId(AdminModel adminModel);
}
