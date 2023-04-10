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
import org.springframework.web.bind.annotation.PostMapping;

import com.bteam.model.UserRegisterModel;
import com.bteam.service.UserRegisterService;


@Controller
public class MainController {
//	userRegisterService　読み込
	@Resource
	UserRegisterService userRegisterService;
	
	@GetMapping("/")
	public String index(  ) {
		
	    return "index";
	}
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	@GetMapping
	public String about() {
		return "about";
	}
//	userRegister Controller
//	登録表示ページ設定
	@GetMapping("/register")
	public String register() {
		return "register";
	}
//	ボータンをmethod　POSTにクリックした
	@PostMapping("/register")
	public String registed(@Validated @ModelAttribute UserRegisterModel userRegisterModel, BindingResult result, Model model) {
//		エラーチェック
		 if (result.hasErrors()) {
	            List<String> errorList = new ArrayList<String>();
	            for (ObjectError error : result.getAllErrors()) {
//	            	エラー内容を追加
	                errorList.add(error.getDefaultMessage());
	            }
//	            エラー一名を表示します
	            model.addAttribute("warning", errorList.get(0));
	            return "register";
	        }
//		 エラーがない時
//		 ユーザー名がチェック
		 	List<UserRegisterModel> check = userRegisterService.checkuser(userRegisterModel);
		 	if(check.size()!=0) {
//		 		ユーザー名がある時は登録出来ません
		 		model.addAttribute("warning", "ユーザー名が存在しました。");
		 			return "register";
		 	}else {
//		 		ユーザー名がないとき
//		 		入力したビザ期限をString型からDateSQL型を変更
		 			String inputDate = userRegisterModel.getVisaDay();
		 			try {
		 					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 						java.util.Date date = sdf.parse(inputDate);
		 						Date sqlDate = new Date(date.getTime());
//		 						変更したビザ期限をuserRegisterModelに設定
		 						userRegisterModel.setVisa(sqlDate);
		 			} catch (Exception e) {
//		 				変更出来ないは登録できませんとエラー表示
		 				model.addAttribute("warning", "登録が失敗しました。");
		 				return "register";
		 			}
//		 			パスワードを変更
		 			userRegisterModel.setPassword(new BCryptPasswordEncoder().encode(userRegisterModel.getPassword()));
//		 			データベースをinsert
		 			int cont = userRegisterService.insert(userRegisterModel);
//		 			insertをチェック
		 			if(cont ==1) {
//		 				insert　OKは表示する画面を移動します
		 			return "redirect:/home";
		 			}else {
//		 				insertできませんはエラー表示
		 				model.addAttribute("warning", "登録が失敗しました。");
		 				return "register";
		 			}
		 	}
		 }
	
}
