package com.funtouch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class UserMenu extends Activity{

	private Button btnVote = null;
	private Button btnSignUp = null;
	private Button btnAuthenticate = null;
	private Button btnFlyer = null;
	private Button btnLogoff = null;
	public Cookie application ; 
	String cookie = application.getInstance().getCookie();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_menu);
		 
		init();
		btnLogoff = (Button) findViewById(R.id.btn_logoff);
		
		btnLogoff.setOnClickListener(new OnClickListener(){
        	public void onClick(View v){

        		application.getInstance().setCookie(null);    		
        		Intent intent=new Intent();
        		intent.setClass(UserMenu.this, MainActivity.class);
        		startActivity(intent);
        		finish();
        	}
        });	
		
		btnVote.setOnClickListener(new OnClickListener(){
	        	public void onClick(View v){
	        		Intent intent=new Intent();
	        		intent.setClass(UserMenu.this, VoteClickAdd.class);
	        		startActivity(intent);
		
	        	}
	        });
	    
		
		btnSignUp.setOnClickListener(new OnClickListener(){
	        	public void onClick(View v){
	        		Intent intent=new Intent();
	        		intent.setClass(UserMenu.this, SignUpClickAdd.class);
	        		startActivity(intent);
	        		finish();
		
	        	}
	        });
		
		btnFlyer.setOnClickListener(new OnClickListener(){
        	public void onClick(View v){
        		Intent intent=new Intent();
        		intent.setClass(UserMenu.this, FlyerList.class);
        		startActivity(intent);
	
        	}
        });
		
		btnAuthenticate.setOnClickListener(new OnClickListener(){
        	public void onClick(View v){
        		Intent intent=new Intent();
        		intent.setClass(UserMenu.this, ActAuthenticate.class);
        		startActivity(intent);
	
        	}
        });
	     
	}

	private void init() {
		btnVote = (Button)findViewById(R.id.btn_vote);
		btnSignUp = (Button)findViewById(R.id.btn_sign_up);
		btnAuthenticate = (Button)findViewById(R.id.btn_act_authenticate);
		btnFlyer = (Button)findViewById(R.id.btn_nfc_flyer);
	}
	
	
	@Override 
    public void onBackPressed() { 
        super.onBackPressed(); 
        Intent intent = new Intent();
		intent.setClass(UserMenu.this, MainActivity.class);
		startActivity(intent);
		this.finish();       
    } 
	 
}


