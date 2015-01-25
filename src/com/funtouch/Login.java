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
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import com.funtouch.UserInfo;
import com.funtouch.Data;

public class Login extends Activity {
	private SharedPreferences read ;
	private EditText userName, password;
	//public Data application;
	List<UserInfo> userLogin= new ArrayList<UserInfo>();
	private DataRetriever dataRetriever = new DataRetriever();
	

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
		//read = getSharedPreferences("user",Context.MODE_PRIVATE);
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		userName = (EditText) findViewById(R.id.edit_UserName);
		password = (EditText) findViewById(R.id.edit_UserPassword);
		btnLogin = (Button) findViewById(R.id.btn_login);
		//application = (Data) this.getApplicationContext(); 
		//userLogin = getSPUser();
		
		btnLogin.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if(userName.getText().toString().trim().equals("")||password.getText().toString().trim().equals("")){
					showToast("请输入账户和密码");
				}
				else{
					int flag = dataRetriever.login(userName.getText().toString(),MD5(password.getText().toString()));
					if(flag == 200)
					{
						showToast("登录成功");
						Intent intent = new Intent();
						intent.setClass(Login.this, UserMenu.class);
						startActivity(intent);
					}
					else if(flag == 410)
					{
						showToast("登录失败");
					}
					/*if( userLogin!=null || userLogin.size()>0 )	
					{
						int flag = 0;
						for(int i=0;i<userLogin.size();i++)	
						{
							if(userName.getText().toString().equals(userLogin.get(i).getName())&& MD5(password.getText().toString()).equals(userLogin.get(i).getPassword()))
							{
								showToast("登录成功");
								Intent intent = new Intent();
								intent.setClass(Login.this, UserMenu.class);
								startActivity(intent);
								flag = 1;
							}
						
						}
						if(flag == 0)
							showToast("用户名或密码错误,请重新输入");
					}
					else
						showToast("用户名或密码错误,请重新输入");
						*/
					
					
				}
				

			}
		});
		btnRegist = (Button) findViewById(R.id.btn_regist);
		btnRegist.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(Login.this, RegistInfo.class);
				startActivity(intent);

			}
		});
	}
	
	// MD5加密，32位 
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
	
	//提示类
	private void showToast(CharSequence msg) {
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
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


     
        




