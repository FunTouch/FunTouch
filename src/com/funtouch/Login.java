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
					showToast("�������˻�������");
				}
				else{
					int flag = dataRetriever.login(userName.getText().toString(),MD5(password.getText().toString()));
					if(flag == 200)
					{
						showToast("��¼�ɹ�");
						Intent intent = new Intent();
						intent.setClass(Login.this, UserMenu.class);
						startActivity(intent);
					}
					else if(flag == 410)
					{
						showToast("��¼ʧ��");
					}
					/*if( userLogin!=null || userLogin.size()>0 )	
					{
						int flag = 0;
						for(int i=0;i<userLogin.size();i++)	
						{
							if(userName.getText().toString().equals(userLogin.get(i).getName())&& MD5(password.getText().toString()).equals(userLogin.get(i).getPassword()))
							{
								showToast("��¼�ɹ�");
								Intent intent = new Intent();
								intent.setClass(Login.this, UserMenu.class);
								startActivity(intent);
								flag = 1;
							}
						
						}
						if(flag == 0)
							showToast("�û������������,����������");
					}
					else
						showToast("�û������������,����������");
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
	
	//��ʾ��
	private void showToast(CharSequence msg) {
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
	}
	
	/*public List<UserInfo> getSPUser()
	{
		List<UserInfo> userInfos = new ArrayList<UserInfo>();// ���ڱ����û��б���Ϣ
		String userinfos = read.getString("member", "");// ȡ�������û���Ϣ
		// ����û��ִ�
		if (userinfos != "")// ������
		{
			// name1/pwd1,name2/pwd2
			if (userinfos.contains(","))// �ж�����, ���Ŵ����û�ÿ���û��ָ��
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
				// û��, ����ֻ��һ���û�
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


     
        




