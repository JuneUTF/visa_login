package com.bteam.controller;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.bteam.model.DeleteModel;
import com.bteam.service.DateConverter;
import com.bteam.service.DeleteService;

@Controller
public class DeleteController {
	@Resource
	DeleteService deleteService;
	@GetMapping("/delete/{id}")
	public String deleteid(@PathVariable("id") int id,DeleteModel deleteModel,Model model ) {
		List<DeleteModel> list = deleteService.SelectDeleteId(deleteModel);
		if(list.toString()=="[]") {
			model.addAttribute("warning","情報がございません");
			model.addAttribute("showa","");
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
	    		 LocalDate today = LocalDate.now();
	    	     LocalDate futureDate = today.plusDays(rem);
	    		Period period = Period.between(today, futureDate);
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
	return "user/delete";
	}
	
	@PostMapping("/delete/{id}")
	public String postdelete(@PathVariable("id") int id,DeleteModel deleteModel, Model model){
		List<DeleteModel> user = deleteService.SelectDeleteId(deleteModel);
		String deleteName = deleteModel.getName();

		int count = deleteService.DeleteId(deleteModel);
		if(count == 1) {
			model.addAttribute("success",deleteName+"の情報が削除しました");
			model.addAttribute("showa","");
			return "user/delete";
		}else {
			model.addAttribute("warning","情報がございません");
			model.addAttribute("showa","");
			return "user/delete";
		}
	}
}
