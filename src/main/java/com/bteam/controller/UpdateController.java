package com.bteam.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bteam.model.UpdateModel;
import com.bteam.service.UpdateService;


@Controller
@RequestMapping
public class UpdateController {
@Resource
UpdateService updateSevice;

@GetMapping("/update")
public String showupdate() {
	return "redirect:/home";
}
@GetMapping("update/{id}")
public String Update(@PathVariable("id") int id,UpdateModel updateModel,Model model ) {
	List<UpdateModel> user = updateSevice.SelectUpdateId(updateModel);
	if(user.size()>0) {
		System.out.println(user);
    	model.addAttribute("user",user);
	}else {
    	model.addAttribute("warning","情報がありません");
	}
	return "admin/update";
}

//ボータンをmethod　POSTにクリックした
@PostMapping("update/{id}")
public String registed(@Validated @ModelAttribute UpdateModel updateModel, BindingResult result, Model model) {
//	エラーチェック
	 if (result.hasErrors()) {
            List<String> errorList = new ArrayList<String>();
            for (ObjectError error : result.getAllErrors()) {
//            	エラー内容を追加
                errorList.add(error.getDefaultMessage());
            }
//            エラー一名を表示します
            model.addAttribute("warning", errorList.get(0));
            List<UpdateModel> user = updateSevice.SelectUpdateId(updateModel);
        	if(user.size()>0) {
        		System.out.println(user);
            	model.addAttribute("user",user);
        	}else {
            	model.addAttribute("warning","情報がありません");
        	}
            return "admin/update";
        }
//	 エラーがない時
//	 ユーザー名がチェック
	 	List<UpdateModel> check = updateSevice.CheckUser(updateModel);
	 	if(check.size()!=0) {
//	 		ユーザー名がある時は登録出来ません
	 		model.addAttribute("warning", "ユーザー名が存在しました。");
	 		List<UpdateModel> user = updateSevice.SelectUpdateId(updateModel);
	 		if(user.size()>0) {
	 			System.out.println(user);
	 	    	model.addAttribute("user",user);
	 		}else {
	 	    	model.addAttribute("warning","情報がありません");
	 		}
	 			return "admin/update";
	 	}else {
//	 		ユーザー名がないとき
//	 		入力したビザ期限をString型からDateSQL型を変更
	 			String inputDate = updateModel.getVisa();
	 			try {
	 					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	 						java.util.Date date = sdf.parse(inputDate);
	 						Date sqlDate = new Date(date.getTime());
//	 						変更したビザ期限をuserRegisterModelに設定
	 						updateModel.setVisaday(sqlDate);
	 			} catch (Exception e) {
//	 				変更出来ないは登録できませんとエラー表示
	 				model.addAttribute("warning", "登録が失敗しました。");
	 				List<UpdateModel> user = updateSevice.SelectUpdateId(updateModel);
	 				if(user.size()>0) {
	 					System.out.println(user);
	 			    	model.addAttribute("user",user);
	 				}else {
	 			    	model.addAttribute("warning","情報がありません");
	 				}
	 				return "admin/update";
	 			}
	 			if(updateModel.getPassword()!=null) {
//	 			パスワードを変更
	 			updateModel.setPassword(new BCryptPasswordEncoder().encode(updateModel.getPassword()));
	 			}
//	 			データベースをinsert
	 			int cont = updateSevice.InsertUpdateUser(updateModel);
//	 			insertをチェック
	 			if(cont ==1) {
//	 				insert　OKは表示する画面を移動します
	 				return "redirect:/user/"+updateModel.getUsername();		 			
	 			}else {
//	 				insertできませんはエラー表示
	 				model.addAttribute("warning", "登録が失敗しました。");
	 				List<UpdateModel> user = updateSevice.SelectUpdateId(updateModel);
	 				if(user.size()>0) {
	 					System.out.println(user);
	 			    	model.addAttribute("user",user);
	 				}else {
	 			    	model.addAttribute("warning","情報がありません");
	 				}
	 				return "admin/update";
	 			}
	 	}
	 }
}
