package com.funtouch;

import java.util.ArrayList;
import java.util.List;

import android.app.Application;

public class Cookie extends Application{ 
	private
	 static Cookie instance;

	public
	 static Cookie getInstance() {
	    return instance;
	}

	private String cookie;
	public List<UserInfo> info = new ArrayList<UserInfo>(); 
	public void onCreate() { 
        super.onCreate(); 
        instance = this;
    } 
	
	public void setInfo(List<UserInfo> i){
		this.info = i;
	}
	
	public void setCookie(String s){
		this.cookie=s;
	}
	
	public List<UserInfo> getInfo(){
		return info;
	}
	public String getCookie(){
		return cookie;
	}
}

