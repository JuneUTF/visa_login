package com.bteam.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
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
import org.springframework.web.bind.annotation.RequestMapping;

import com.bteam.model.AdminModel;
import com.bteam.service.AdminService;
import com.bteam.service.DateConverter;

@Controller
//URL　管理者の設定
@RequestMapping("/admin")
public class AdminController  {
	@Resource
	AdminService adminService;

	@GetMapping()
	public String showindex() {
		return "admin/index";
	}
//	admin register
	@GetMapping("register")
	public String showregister() {
		return "admin/register";
	}
//	ボータンをmethod　POSTにクリックした
	@PostMapping("register")
	public String registed(@Validated @ModelAttribute AdminModel adminModel, BindingResult result, Model model) {
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
		 	List<AdminModel> check = adminService.AdminCheckUser(adminModel);
		 	if(check.size()!=0) {
//		 		ユーザー名がある時は登録出来ません
		 		model.addAttribute("warning", "ユーザー名が存在しました。");
		 			return "register";
		 	}else {
//		 		ユーザー名がないとき
//		 		入力したビザ期限をString型からDateSQL型を変更
		 			String inputDate = adminModel.getVisa();
		 			try {
		 					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 						java.util.Date date = sdf.parse(inputDate);
		 						Date sqlDate = new Date(date.getTime());
//		 						変更したビザ期限をuserRegisterModelに設定
		 						adminModel.setVisaDay(sqlDate);
		 			} catch (Exception e) {
//		 				変更出来ないは登録できませんとエラー表示
		 				model.addAttribute("warning", "登録が失敗しました。");
		 				return "register";
		 			}
//		 			パスワードを変更
		 			adminModel.setPassword(new BCryptPasswordEncoder().encode(adminModel.getPassword()));
//		 			データベースをinsert
		 			int cont = adminService.AdminRegisterUser(adminModel);
//		 			insertをチェック
		 			if(cont ==1) {
//		 				insert　OKは表示する画面を移動します
		 				return "redirect:/user/search?search=username&searchkey="+adminModel.getUsername();		 			
		 			}else {
//		 				insertできませんはエラー表示
		 				model.addAttribute("warning", "登録が失敗しました。");
		 				return "register";
		 			}
		 	}
		 }
	
//	Admin home show pending
	@GetMapping("pending")
	public String showpending(AdminModel adminModel,Model model ) {
	List<AdminModel> list = adminService.AdminSelectPending(adminModel);
	System.out.println(list);
	DateConverter changeDate = new DateConverter();
//	Listの中に一つずつ　取りやる
	list.forEach(e -> {
//		changeDateの中にreamdateを取り出し ,何日をセットします
		long remday = changeDate.reamdate(e.getVisa());
//		changeDateの中にchangedateを取り出し　、Listにセットします
//		例　	 2023-05-21 変更　2023年05月21日
		e.setVisa(changeDate.changedate(e.getVisa()));
//		text 
		String outString = "";
//		期限切れはColor=0
//		本日に期限切れColor=1
//		30日以内切れColor=2
//		30日以上切れColor=３
		
//		remday=0本日に期限切れ
		if(remday==0) {
			outString+="本日に期限切れ";
			e.setColor(1);
		}else {

//    		LONG型　To　INT　型　とマイナス消し
    		int rem = (int) Math.abs(remday);
//    		JavaのPeriod　読み込
    		 LocalDate today = LocalDate.now();
    	     LocalDate futureDate = today.plusDays(rem);
    		Period period = Period.between(today, futureDate);
//    		何年計算
    		int years = period.getYears();
//    		何月計算
    		int months = period.getMonths();
//    		何日計算
    		int days = period.getDays();
//    		チェック
    		if(years>0) {
    			outString+=(years +"年");
    			e.setColor(3);
    		}
    		if(months>0) {
    			outString+=(months +"月");
    			e.setColor(3);
    		}
    		if(days>0) {
    			outString+=(days +"日");
    			e.setColor(2);
    		}
    		if(remday>0) {
    			outString+="前に切れ";
    			e.setColor(0);
    		}
		
//		String　セットします
		 e.setRemday(outString);
	}
	});
		model.addAttribute("user",list);
			return "admin/pending";
		}
//	admin show deleted
	@GetMapping("delete")
	public String showdeleted(AdminModel adminModel,Model model ) {
	List<AdminModel> list = adminService.AdminSelectDeleted(adminModel);
	System.out.println(list);
	DateConverter changeDate = new DateConverter();
//	Listの中に一つずつ　取りやる
	list.forEach(e -> {
//		changeDateの中にreamdateを取り出し ,何日をセットします
		long remday = changeDate.reamdate(e.getVisa());
//		changeDateの中にchangedateを取り出し　、Listにセットします
//		例　	 2023-05-21 変更　2023年05月21日
		e.setVisa(changeDate.changedate(e.getVisa()));
//		text 
		String outString = "";
//		期限切れはColor=0
//		本日に期限切れColor=1
//		30日以内切れColor=2
//		30日以上切れColor=３
		
//		remday=0本日に期限切れ
		if(remday==0) {
			outString+="本日に期限切れ";
			e.setColor(1);
		}else {

//    		LONG型　To　INT　型　とマイナス消し
    		int rem = (int) Math.abs(remday);
//    		JavaのPeriod　読み込
    		 LocalDate today = LocalDate.now();
    	     LocalDate futureDate = today.plusDays(rem);
    		Period period = Period.between(today, futureDate);
//    		何年計算
    		int years = period.getYears();
//    		何月計算
    		int months = period.getMonths();
//    		何日計算
    		int days = period.getDays();
//    		チェック
    		if(years>0) {
    			outString+=(years +"年");
    			e.setColor(3);
    		}
    		if(months>0) {
    			outString+=(months +"月");
    			e.setColor(3);
    		}
    		if(days>0) {
    			outString+=(days +"日");
    			e.setColor(2);
    		}
    		if(remday>0) {
    			outString+="前に切れ";
    			e.setColor(0);
    		}
		
//		String　セットします
		 e.setRemday(outString);
	}
	});
		model.addAttribute("user",list);
			return "admin/delete";
		}
}
