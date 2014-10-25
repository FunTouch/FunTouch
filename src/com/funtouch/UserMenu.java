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

public class UserMenu extends Activity{

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Button btnVote = null;
	    Button btnSignUp = null;
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_menu);
		 
		btnVote=(Button)findViewById(R.id.btn_vote);
		btnVote.setOnClickListener(new OnClickListener(){
	        	public void onClick(View v){
	        		Intent intent=new Intent();
	        		intent.setClass(UserMenu.this, VoteClickAdd.class);
	        		startActivity(intent);
		
	        	}
	        });
	    
		btnSignUp=(Button)findViewById(R.id.btn_sign_up);
		btnSignUp.setOnClickListener(new OnClickListener(){
	        	public void onClick(View v){
	        		Intent intent=new Intent();
	        		intent.setClass(UserMenu.this, SignUpClickAdd.class);
	        		startActivity(intent);
		
	        	}
	        });
	     
	}

}


