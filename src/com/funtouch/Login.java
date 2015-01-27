package com.funtouch;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.StrictMode;
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
import com.funtouch.MD5;

public class Login extends Activity {
	//private SharedPreferences read ;
	private EditText userName, password;
	public Cookie application ; 
	//public Data application;
	//List<UserInfo> userLogin= new ArrayList<UserInfo>();
	private DataRetriever dataRetriever = new DataRetriever();
	String cookie = application.getInstance().getCookie();
	

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
     
     
		Button btnLogin = null;
		Button btnRegist = null;
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		userName = (EditText) findViewById(R.id.edit_UserName);
		password = (EditText) findViewById(R.id.edit_UserPassword);
		btnLogin = (Button) findViewById(R.id.btn_login);
		//application = (Data) this.getApplicationContext(); 
		//userLogin = getSPUser();
		
		if(cookie!=null)
		{
			Intent intent = new Intent();
			intent.setClass(Login.this, UserMenu.class);
			startActivity(intent);
			this.finish(); 
		}
		
		btnLogin.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if(userName.getText().toString().trim().equals("")||password.getText().toString().trim().equals("")){
					showToast("请输入账户和密码");
				}
				else{
					int flag = dataRetriever.login(userName.getText().toString(),MD5.Encode(password.getText().toString()));
					if(flag == 200)
					{
						showToast("登录成功");
						Intent intent = new Intent();
						intent.setClass(Login.this, UserMenu.class);
						startActivity(intent);
						finish();  
					}
					else if(flag == 410)
					{
						showToast("无效的用户名");
					}	
					else if(flag == 411)
					{
						showToast("密码错误,请重新输入密码");
					}
				}
			}
		});
		
		btnRegist = (Button) findViewById(R.id.btn_regist);
		btnRegist.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(Login.this, RegistInfo.class);
				startActivity(intent);
				finish();

			}
		});
	}
	
	//提示类
	private void showToast(CharSequence msg) {
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
	}
	
	public void onBackPressed() { 
        super.onBackPressed(); 
        Intent intent = new Intent();
		intent.setClass(Login.this, MainActivity.class);
		startActivity(intent);
		this.finish();       
    } 
	
	/*public List<UserInfo> getSPUser()
	{
		List<UserInfo> userInfos = new ArrayList<UserInfo>();// 用于保存用户列表信息
		String userinfos = read.getString("member", "");// 取得所有用户信息
		// 获得用户字串
		if (userinfos != "")// 有数据
		{
			// name1/pwd1,name2/pwd2
			if (userinfos.contains(","))// 判断有无, 逗号代表用户每个用户分割点
			{
				String[] users = userinfos.split(",");
				for (String str : users)
				{
					UserInfo userinfo = new UserInfo();
					String[] user = str.split("/");
					userinfo.initialize(user[0],(user[1]),user[2],user[3],user[4],user[5],user[6]);// 
					userInfos.add(userinfo);
				}
			} else
				// 没有, 代表只有一个用户
			{
				UserInfo userinfo = new UserInfo();
				String[] user = userinfos.split("/");
				userinfo.initialize(user[0],(user[1]),user[2],user[3],user[4],user[5],user[6]);// 
				userInfos.add(userinfo);
			}
			return userInfos;
	  } else
	  {
		  return userInfos;
	  }
	}*/
}


     
        




