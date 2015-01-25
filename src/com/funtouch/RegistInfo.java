package com.funtouch;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


import android.os.StrictMode;
import com.funtouch.UserInfo;

import java.security.MessageDigest;
import java.util.*;

import com.funtouch.Data;


public class RegistInfo extends Activity{
	private EditText userName, password,passwordAgain, userMailbox, userClass, userPhone;
	public Data application;
	private List<Speaker> listSpeaker;
	private DataRetriever dataRetriever = new DataRetriever();
	private SimpleAdapter adapter;
	private List<Map<String, Object>> listData = new ArrayList<Map<String, Object>>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
        .detectDiskReads()
        .detectDiskWrites()
        .detectAll()   // or .detectAll() for all detectable problems
        .penaltyLog()
        .build());
     StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
        .detectLeakedSqlLiteObjects()
        .detectLeakedClosableObjects()
        .penaltyLog()
        .penaltyDeath()
        .build());
     
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
						showToast("������ȫ����Ϣ..");
					}
	        		else if(!password.getText().toString().equals(passwordAgain.getText().toString())){
	        			showToast("������������벻һ��,����������");
	        		}
	        		else if(password.getText().length()<6){
	        			showToast("���벻���Ϲ���,����������");
	        		}
	        		else if(!userMailbox.getText().toString().contains("@")){
	        			showToast("�����ʽ�����Ϲ���,����������");
	        		}
	        		else if(!userClass.getText().toString().contains("-")){
	        			showToast("�༶��ʽ�����Ϲ���,����������");
	        		}
	        		else if(userPhone.getText().length()<11){
	        			showToast("�绰���벻���Ϲ���,����������");
	        		}
	        		else{
	        			//application.setUserName(userName.getText().toString());
	        			//application.setPassword(password.getText().toString());
	        			//application.setUserMailbox(userMailbox.getText().toString());
	        			//application.setUserClass(userClass.getText().toString());
	        			//application.setUserPhone(userPhone.getText().toString());
	        			//Intent intent=new Intent();
	        			//intent.setClass(RegistInfo.this, RegistOrgan.class);
	        			//startActivity(intent);
	        			int flag = dataRetriever.regist(userName.getText().toString(),MD5(password.getText().toString()),
	        					userMailbox.getText().toString(),userClass.getText().toString(),userPhone.getText().toString());
	        			if(flag == 200)
	        			{
	        				showToast("ע��ɹ�,���ص�½ҳ��");
	        				Intent intent=new Intent();
	        				intent.setClass(RegistInfo.this, Login.class);
	        				startActivity(intent);
	        			}
	        			if(flag == 401)
	        			{
	        				showToast("���û����Ѵ���!������ע��!");
	        			}
	        			if(flag == 0)
	        			{
	        				showToast("�û�������Ϊ����!");
	        			}
	        			}
	     
	}
	        });
		
	}
	// ��ʾToast
	private void showToast(CharSequence msg) {
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
	}
	
	// MD5���ܣ�32λ 
		public static String MD5(String str) { 
			MessageDigest md5 = null; 
			try { 
				md5 = MessageDigest.getInstance("MD5"); 
			} catch (Exception e) { 
				e.printStackTrace(); 
				return ""; 
			} 
			char[] charArray = str.toCharArray(); 
			byte[] byteArray = new byte[charArray.length]; 
			for (int i = 0; i < charArray.length; i++) { 
				byteArray[i] = (byte) charArray[i]; 
			} 
			byte[] md5Bytes = md5.digest(byteArray); 
			StringBuffer hexValue = new StringBuffer(); 
			for (int i = 0; i < md5Bytes.length; i++) { 
				int val = ((int) md5Bytes[i]) & 0xff; 
				if (val < 16) { 
					hexValue.append("0"); 
				} 
				hexValue.append(Integer.toHexString(val)); 
		} 
			return hexValue.toString(); 
		}
	
}
