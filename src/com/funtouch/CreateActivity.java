package com.funtouch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.funtouch.Cookie;

public class CreateActivity extends Activity{
	public Cookie application ; 
	
	private EditText name, time, place, type, org, actor, limit, info;
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
	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.create_activity);
		
		//init();
		Button btnCreateAct = null;
		
		name = (EditText) findViewById(R.id.edt_ActName);
		time = (EditText) findViewById(R.id.edt_ActTime);
		place = (EditText) findViewById(R.id.edt_ActPlace);
		type = (EditText) findViewById(R.id.edt_ActType);
		org = (EditText) findViewById(R.id.edt_ActOrg);
		actor = (EditText) findViewById(R.id.edt_ActActor);
		limit = (EditText) findViewById(R.id.edt_ActLimit);
		info = (EditText) findViewById(R.id.edt_ActInfo);
		btnCreateAct = (Button) findViewById(R.id.btn_CreateAct);	
		
		btnCreateAct.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if(name.getText().toString().trim().equals("")||time.getText().toString().trim().equals("")||
					place.getText().toString().trim().equals("")||type.getText().toString().trim().equals("")||
					org.getText().toString().trim().equals("")||actor.getText().toString().trim().equals("")||
					limit.getText().toString().trim().equals("")||info.getText().toString().trim().equals("")){
					showToast("��������������Ϣ!");
				}
				else{
					int flag = dataRetriever.createAct(cookie,name.getText().toString(),info.getText().toString(),time.getText().toString(),
							place.getText().toString(),type.getText().toString(),org.getText().toString(),actor.getText().toString(),
							limit.getText().toString());
					if(flag == 200)
					{
						showToast("������ɹ�");
						Intent intent = new Intent();
						intent.setClass(CreateActivity.this, AboutActivity.class);
						startActivity(intent);
					}
					else if(flag == 420)
					{
						showToast("����ʧ��");
					}
					else if(flag == 404)
					{
						showToast("���ȵ�½");
						Intent intent = new Intent();
						intent.setClass(CreateActivity.this, Login.class);
						startActivity(intent);
					}
				}
			}
		});
		
		
		
	}
	
	
	
	
	//��ʾ��
		private void showToast(CharSequence msg) {
			Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
		}
		
		
		
		
}