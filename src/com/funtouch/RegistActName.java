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

public class RegistActName extends Activity{

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Button btnOk = null;
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.regist_act_name);
		btnOk = (Button)findViewById(R.id.btn_ok);
		btnOk.setOnClickListener(new OnClickListener(){
	        	public void onClick(View v){
	        		Intent intent=new Intent();
	        		intent.setClass(RegistActName.this, UserMenu.class);
	        		startActivity(intent);
		
	        	}
	        });
	}
}



