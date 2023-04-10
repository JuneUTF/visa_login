package com.bteam.controller;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bteam.model.UserModel;
import com.bteam.service.DateConverter;
import com.bteam.service.UserService;

@Controller
@RequestMapping("user")
public class UserController {
	@Resource
	UserService userService;
	@GetMapping()
	public String index(Model model, Authentication authentication,UserModel userModel ) {
	    String loginuser = authentication.getName();
	    List<UserModel> count = userService.checkpendding(loginuser);
	    if(count.get(0).getStatus().equals("PENDING")   ) {
	    	return "user/pending";
	    }if (count.get(0).getStatus().equals("DELETED")) {
	    	return "user/userdeleted";
		}else {
	    	List<UserModel> list = userService.selectAll(userModel);
	    	DateConverter changeDate = new DateConverter();
//	    	Listの中に一つずつ　取りやる
	    	list.forEach(e -> {
//	    		changeDateの中にreamdateを取り出し ,何日をセットします
	    		long remday = changeDate.reamdate(e.getVisa());
//	    		changeDateの中にchangedateを取り出し　、Listにセットします
//	    		例　	 2023-05-21 変更　2023年05月21日
	    		e.setVisa(changeDate.changedate(e.getVisa()));
//	    		text 
	    		String outString = "";
//	    		期限切れはColor=0
//	    		本日に期限切れColor=1
//	    		30日以内切れColor=2
//	    		30日以上切れColor=３
	    		
//	    		remday=0本日に期限切れ
	    		if(remday==0) {
	    			outString+="本日に期限切れ";
	    			e.setColor(1);
	    		}else {

//		    		LONG型　To　INT　型　とマイナス消し
		    		int rem = (int) Math.abs(remday);
//		    		JavaのPeriod　読み込
		    		 LocalDate today = LocalDate.now();
		    	     LocalDate futureDate = today.plusDays(rem);
		    		Period period = Period.between(today, futureDate);
//		    		何年計算
		    		int years = period.getYears();
//		    		何月計算
		    		int months = period.getMonths();
//		    		何日計算
		    		int days = period.getDays();
//		    		チェック
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
				}
//	    		String　セットします
	    		 e.setRemday(outString);
	    	});
	    	model.addAttribute("user",list);
		return "user/index";
	}
	}
	
	@GetMapping("/search")
	 public String search(Model model, UserModel userModel) {
		List<UserModel> list = new ArrayList<>();
		if(userModel.getSearch().equals("id")) {
			try {
				int newid;
				newid = Integer.parseInt(userModel.getSearchkey());
				userModel.setSearchid(newid);
				list.addAll(userService.searchid(userModel));
				if(list.size()==0) {
					model.addAttribute("warning","情報がありません。");
				}
			} catch (NumberFormatException e) {
				model.addAttribute("warning","番号として入力してください。");
			}
		}else {
			userModel.setSearchuser(userModel.getSearchkey());
			list.addAll(userService.searchuser(userModel));
			if(list.size()==0) {
				model.addAttribute("warning","情報がありません。");
			}
		}
		
		DateConverter changeDate = new DateConverter();
//    	Listの中に一つずつ　取りやる
    	list.forEach(e -> {
//    		changeDateの中にreamdateを取り出し ,何日をセットします
    		long remday = changeDate.reamdate(e.getVisa());
//    		changeDateの中にchangedateを取り出し　、Listにセットします
//    		例　	 2023-05-21 変更　2023年05月21日
    		e.setVisa(changeDate.changedate(e.getVisa()));
//    		text 
    		String outString = "";
//    		期限切れはColor=0
//    		本日に期限切れColor=1
//    		30日以内切れColor=2
//    		30日以上切れColor=３
    		
//    		remday=0本日に期限切れ
    		if(remday==0) {
    			outString+="本日に期限切れ";
    			e.setColor(1);
    		}else {

//	    		LONG型　To　INT　型　とマイナス消し
	    		int rem = (int) Math.abs(remday);
//	    		JavaのPeriod　読み込
	    		Period period = Period.ofDays(rem);
//	    		何年計算
	    		int years = period.getYears();
//	    		何月計算
	    		int months = period.getMonths();
//	    		何日計算
	    		int days = period.getDays();
//	    		チェック
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
			}
//    		String　セットします
    		 e.setRemday(outString);
    	});

		model.addAttribute("user",list);
		return "user/index";
	}
	
}
