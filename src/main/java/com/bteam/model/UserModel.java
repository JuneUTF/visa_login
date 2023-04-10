package com.bteam.model;

import java.io.Serializable;

import lombok.Data;
@Data
public class UserModel implements Serializable {
    private static final long serialVersionUID = 1L;
//  id,username,status,name,visa,visatypy データベースから読んでセットします
    private int id;
    private String username;
    private String status;
    private String name;
    private String visa;
    private String visatype;
//    remdayは残り日に計算して、セットします
    private String remday;
//    colorのため
    private int color;
//    HTMLから
    private String search;
    private String searchkey;
    private int searchid;
    private String searchuser;
    

    public UserModel() {
    }
}