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

public class RegistInfo extends Activity{

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Button btnNext=null;
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.regist_info);
		btnNext=(Button)findViewById(R.id.btn_next);
		btnNext.setOnClickListener(new OnClickListener(){
	        	public void onClick(View v){
	        		Intent intent=new Intent();
	        		intent.setClass(RegistInfo.this, RegistOrgan.class);
	        		startActivity(intent);
	
	}
	        });
	}
}