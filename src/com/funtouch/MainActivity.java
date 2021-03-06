package com.funtouch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	private static boolean isSocialFirstUse = true;
	
	private Button btnFunTouch = null;
	private Button btnSocial = null;
	private Button btnAct = null;
	private Button btnSetting = null;
	private Button btnGame = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		init();
		
		btnFunTouch.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, FunTouchProfile.class);
				startActivity(intent);
			}
		});
		
		btnAct.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, AboutActivity.class);
				startActivity(intent);
			}
		});
		
		btnGame.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, GameList.class);
				startActivity(intent);
			}
		});
		
		btnSocial.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent();
				
				if (isSocialFirstUse)
					intent.setClass(MainActivity.this, SocialFirstUse.class);
				else
					intent.setClass(MainActivity.this, SocialMyCard.class);
				
				startActivity(intent);
			}
		});
		
	}

	private void init() {
		btnFunTouch = (Button) findViewById(R.id.btn_funtouch);
		btnAct = (Button) findViewById(R.id.btn_about_activity);
		btnSocial = (Button) findViewById(R.id.btn_social);
		btnSetting = (Button) findViewById(R.id.btn_setting);
		btnGame = (Button) findViewById(R.id.btn_game_help);

	}
	
	public static void setSocialStatus(boolean status)
	{
		isSocialFirstUse = status;
	}

}
