package com.bteam.model;


import java.sql.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UserRegisterModel {
	@NotEmpty(message = "ユーザー名を入力してください")
    @Size(max = 50 , message = "ユーザー名は6～50桁以内で入力してください")
	@Size(min=6, message = "ユーザー名は6～50桁以内で入力してください")
	private String username;
	@NotEmpty(message = "パスワードを入力してください")
    @Size(max =16, message = "パスワードは8～16桁以内で入力してください")
    @Size(min=8, message = "パスワードは8～16桁以内で入力してください")
	private String password;
	@NotEmpty(message = "名前を入力してください")
    @Size(max =  255, message = "名前は255桁以内で入力してください")
	private String name;
	@NotEmpty(message = "ビザ期限を入力してください")
	private String visaDay;
	private Date visa;
	@NotEmpty(message = "在留資格を入力してください")
	private String visatype;
}
