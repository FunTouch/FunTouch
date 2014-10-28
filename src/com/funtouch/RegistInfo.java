package com.funtouch;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import com.funtouch.UserInfo;
import java.util.*;
import com.funtouch.Data;


public class RegistInfo extends Activity{
	private EditText userName, password,passwordAgain, userMailbox, userClass, userPhone;
	public Data application;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Button btnNext=null;
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.regist_info);
		userName = (EditText) findViewById(R.id.edit_UserName1);
		password = (EditText) findViewById(R.id.edit_Password);
		passwordAgain = (EditText) findViewById(R.id.edit_PasswordAgain);
		userMailbox = (EditText) findViewById(R.id.edit_Mailbox);
		userClass = (EditText) findViewById(R.id.edit_Class);
		userPhone = (EditText) findViewById(R.id.edit_Phone);
		btnNext=(Button)findViewById(R.id.btn_next);
		application = (Data) this.getApplicationContext(); 
		btnNext.setOnClickListener(new OnClickListener(){
	        	public void onClick(View v){
	        		if(userName.getText().toString().trim().equals("") || password.getText().toString().trim().equals("")
	        				||passwordAgain.getText().toString().trim().equals("")||userMailbox.getText().toString().trim().equals("")
	        				||userClass.getText().toString().trim().equals("")||userPhone.getText().toString().trim().equals("")){
						showToast("请输入全部信息..");
					}
	        		else if(!password.getText().toString().equals(passwordAgain.getText().toString())){
	        			showToast("两次输入的密码不一致,请重新输入");
	        		}
	        		else if(password.getText().length()<6){
	        			showToast("密码不符合规则,请重新输入");
	        		}
	        		else if(!userMailbox.getText().toString().contains("@")){
	        			showToast("邮箱格式不符合规则,请重新输入");
	        		}
	        		else if(!userClass.getText().toString().contains("-")){
	        			showToast("班级格式不符合规则,请重新输入");
	        		}
	        		else if(userPhone.getText().length()<11){
	        			showToast("电话号码不符合规则,请重新输入");
	        		}
	        		else{
	        			application.setUserName(userName.getText().toString());
	        			application.setPassword(password.getText().toString());
	        			application.setUserMailbox(userMailbox.getText().toString());
	        			application.setUserClass(userClass.getText().toString());
	        			application.setUserPhone(userPhone.getText().toString());
	        			Intent intent=new Intent();
	        			intent.setClass(RegistInfo.this, RegistOrgan.class);
	        			startActivity(intent);
	        			}
	        		
	
	}
	        });
		
	}
	private void showToast(CharSequence msg) {
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
	}
	
}
