package com.funtouch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AboutActivity extends Activity {

	private Button btnCreateAct = null;
	private Button btnSeeAct = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about_activity);
		
		init();
		
		btnCreateAct.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent();
				intent.setClass(AboutActivity.this, Login.class);
				startActivity(intent);
			}
		});
		
		btnSeeAct.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent();
				intent.setClass(AboutActivity.this, SeeActivity.class);
				startActivity(intent);
			}
		});
	}

	private void init() {
		btnCreateAct = (Button) findViewById(R.id.btn_create_activity);
		btnSeeAct = (Button) findViewById(R.id.btn_see_activity);
	}

}
