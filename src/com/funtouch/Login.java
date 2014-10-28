package com.funtouch;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
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
import com.funtouch.Data;

public class Login extends Activity {
	private SharedPreferences read ;
	private EditText userName, password;
	public Data application;
	List<UserInfo> userLogin= new ArrayList<UserInfo>();
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Button btnLogin = null;
		Button btnRegist = null;
		read = getSharedPreferences("user",Context.MODE_PRIVATE);
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		userName = (EditText) findViewById(R.id.edit_UserName);
		password = (EditText) findViewById(R.id.edit_UserPassword);
		btnLogin = (Button) findViewById(R.id.btn_login);
		application = (Data) this.getApplicationContext(); 
		userLogin = getSPUser();
		//showToast(read.getString("member", ""));
		btnLogin.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if(userName.getText().toString().trim().equals("")||password.getText().toString().trim().equals("")){
					showToast("请输入账户和密码..");
				}
				else{
					if( userLogin!=null || userLogin.size()>0 )	
					{
						int flag = 0;
						for(int i=0;i<userLogin.size();i++)	
						{
							if(userName.getText().toString().equals(userLogin.get(i).getName())&& password.getText().toString().equals(userLogin.get(i).getPassword()))
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
	
	//提示类
	private void showToast(CharSequence msg) {
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
	}
	
	public List<UserInfo> getSPUser()
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
					userinfo.initialize(user[0],user[1],user[2],user[3],user[4],user[5],user[6]);// 
					userInfos.add(userinfo);
				}
			} else
				// 没有, 代表只有一个用户
			{
				UserInfo userinfo = new UserInfo();
				String[] user = userinfos.split("/");
				userinfo.initialize(user[0],user[1],user[2],user[3],user[4],user[5],user[6]);// 
				userInfos.add(userinfo);
			}
			return userInfos;
	  } else
	  {
		  return userInfos;
	  }
	}
}


     
        




