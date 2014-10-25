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

public class VoteClickAdd extends Activity{

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Button btnClickAdd=null;
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.vote_click_add);
		btnClickAdd=(Button)findViewById(R.id.btn_click_add);
		btnClickAdd.setOnClickListener(new OnClickListener(){
	        	public void onClick(View v){
	        		Intent intent=new Intent();
	        		intent.setClass(VoteClickAdd.this, VoteEdit.class);
	        		startActivity(intent);
	         	}
	        });
		
	
	}
   
}



