package com.funtouch;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import android.app.Activity;
import android.app.Application;
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

import com.funtouch.Data;
import com.funtouch.UserInfo;

public class RegistActName extends Activity{
	private EditText registActName;
	private String name = null;
	public SharedPreferences user;
	private Data application;
	private String alluserinfos ;
	//Vector<UserInfo> userInfos = new Vector<UserInfo>();
	//List<UserInfo> userInfos= new ArrayList<UserInfo>();;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Button btnOk = null;
		user = this.getSharedPreferences("user", Context.MODE_PRIVATE);
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.regist_act_name);
		btnOk = (Button)findViewById(R.id.btn_ok);
		registActName = (EditText) findViewById(R.id.edit_act_name);
		application = (Data) this.getApplicationContext(); 
		btnOk.setOnClickListener(new OnClickListener(){
	        	public void onClick(View v){
	        		if(registActName.getText().toString().trim().equals("") )
	        			showToast("����������");
	        		else{
	        		name = registActName.getText().toString();
	        		saveSPUser(application.getUserName(),application.getPassword(),application.getUserMailbox(),application.getUserClass(),application.getUserPhone(),application.getTemp(),name);
	        		//showToast(application.getTemp());
	        		Intent intent=new Intent();
	        		intent.setClass(RegistActName.this, Login.class);
	        		startActivity(intent);
	        		}
		
	        	}
	        });
	}
	
	private void showToast(CharSequence msg) {
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
	}
	
	

	private void saveSPUser(String userName, String password, String mb, String userclass, String uphone, String organ, String actname)
	{
		if(application.info.isEmpty()||(!application.info.isEmpty()&& (alluserinfos == null || alluserinfos.length()<=0)))
		{
			String save = user.getString("member", "");
			if (save != "")
				alluserinfos = save;
		}
		checkUser(userName,password,mb,userclass,uphone,organ,actname);// ����Ƿ��Ѵ�����ͬ�û���Ϣ
		List<UserInfo> info = application.getInfo();
		for (UserInfo userinfos: info)
		{	
			String name = userinfos.getName();
			String userinfo = userinfos.getName() + "/" + userinfos.getPassword() + "/" + userinfos.getMailbox() + "/" + userinfos.getUserClass() + "/" + userinfos.getUserPhone() + "/" + userinfos.getRegistOrgan() + "/" + userinfos.getRegistActName();
			if (alluserinfos == null)
			{
				alluserinfos = userinfo;
			} 
			else if(name.equals(userName))
			{
				alluserinfos += "," + userinfo;
			}		
		}

		Editor editor = user.edit();// �༭����¼
		editor.putString("member", alluserinfos);
		editor.commit();// �༭���ύ����
	}

  	public void checkUser(String userName, String password, String mb, String userclass, String uphone, String organ, String actname)
  	{
  		int flag = 0;
  		if (alluserinfos == null || alluserinfos.length() <= 0)
  		{
  			UserInfo userInfo = new UserInfo();
  			userInfo.initialize(userName, password, mb, userclass, uphone, organ, actname);
  			application.getInfo().add(userInfo);
  			//showToast("hehe");
  		}
  		else // ������
		{	
			if (alluserinfos.contains(","))// �ж�����, ���Ŵ����û�ÿ���û��ָ��
			{
				String[] users = alluserinfos.split(",");
				for (int i=0; i < users.length; i++)
				{
					String str = users[i];
					String[] user = str.split("/");
					if (user[0].equals(userName))
		  			{	
		  				flag = 1;
		  				if (flag >= 0)
				  		{// �Ѵ���
							showToast("���û����Ѵ���!������ע��!");
							Intent intent=new Intent();
			        		intent.setClass(RegistActName.this, RegistInfo.class);
			        		startActivity(intent);
			        		showToast("���û����Ѵ���!������ע��!");
			        		System.exit(0);
				  		}
		  				break;
		  			}
					
					
				}
				if(flag == 0)
				{
					UserInfo userInfo = new UserInfo();
			    	userInfo.initialize(userName, password, mb, userclass, uphone, organ, actname);
			    	application.getInfo().add(userInfo);
				}
			}
			else if(!alluserinfos.contains(","))
				// û��, ����ֻ��һ���û�
			{
				UserInfo userinfo = new UserInfo();
				String[] user = alluserinfos.split("/"); 
				if (user[0].equals(userName))
		  		{// �Ѵ���
		  			//application.getInfo().remove(position);
					showToast("���û����Ѵ���!������ע��!");
					Intent intent=new Intent();
	        		intent.setClass(RegistActName.this, RegistInfo.class);
	        		startActivity(intent);
	        		showToast("���û����Ѵ���!������ע��!");
	        		System.exit(0);
		  		}
				else{
					UserInfo userInfo = new UserInfo();
					userInfo.initialize(userName, password, mb, userclass, uphone, organ, actname);
					application.getInfo().add(userInfo);
				}
			}
			
		}
  		
  	}
 }





