package com.funtouch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Login extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Button btnLogin = null;
		Button btnRegist = null;
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		btnLogin = (Button) findViewById(R.id.btn_login);
		btnLogin.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(Login.this, UserMenu.class);
				startActivity(intent);

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
}
     
        




