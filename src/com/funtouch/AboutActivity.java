package com.funtouch;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class AboutActivity extends Activity {
	public Cookie application ; 

	private Button btnCreateAct = null;
	private Button btnSeeAct = null;
	private SimpleAdapter adapter;
	private SimpleAdapter adapter1;
	private List<Map<String, Object>> listData = new ArrayList<Map<String, Object>>();
	private List<Map<String, Object>> ActDetail = new ArrayList<Map<String, Object>>();
	private List<Speaker> listSpeaker;
	private DataRetriever dataRetriever = new DataRetriever();
	String cookie = application.getInstance().getCookie();
	Map<String, Object> tmp = new HashMap<String, Object>();
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//android4.0之后不允许用主线程访问网络
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
     
		setContentView(R.layout.about_activity);
		
		ListView lsvActInfo = (ListView)findViewById(R.id.lsv_act_info);
				
		init();
		int flag = dataRetriever.seeActinfo(cookie);
		if(flag == 200)
		{
			showToast("获取活动信息成功");
		}
		else if(flag == 420)
		{
			showToast("获取活动信息失败");
		}
		else if(flag == 404)
		{
			showToast("请先登陆");
			Intent intent = new Intent();
			intent.setClass(AboutActivity.this, Login.class);
			startActivity(intent);
			finish();
		}
		
		listSpeaker = dataRetriever.retrieveAllSpeakers(cookie);
		getData();
		adapter = new SimpleAdapter(this, listData, R.layout.lsv_act_info_raw,
				new String[] {"name", "info", "time"},
				new int[] {R.id.act_name, R.id.act_info, R.id.act_time});
		lsvActInfo.setAdapter(adapter);
		
		lsvActInfo.setOnItemClickListener(new OnItemClickListener(){
			public void onItemClick(AdapterView<?> parent, View view,  
				     int position, long id) {
				ListView listView = (ListView)parent; 
				ActDetail.clear();
			    HashMap<String, String> map = (HashMap<String, String>) listView.getItemAtPosition(position);
			    String name = map.get("name");
			    for (Iterator<Speaker> it=listSpeaker.iterator(); it.hasNext(); )
			    {
			    	
			    	Speaker spk = it.next();
			    	if(name.equals(spk.getName()))
			    	{
			    		tmp.put("name", spk.getName());
						tmp.put("info", spk.getInfo());
						tmp.put("time", spk.getTime());
						tmp.put("place", spk.getPlace());
						tmp.put("type", spk.getType());
						tmp.put("org", spk.getOrg());
						tmp.put("actor", spk.getActor());
						tmp.put("limit", spk.getLimit());					
						ActDetail.add(tmp);
						//ShowDetails();
						ActDetailsInfo info = new ActDetailsInfo(ActDetail);
						List<ActDetailsInfo> objectList = new ArrayList<ActDetailsInfo>();
						objectList.add(info);		
						//showToast(spk.getOrg());
						Intent intent = new Intent();
						intent.setClass(AboutActivity.this, ActDetails.class);
						intent.putExtra("ListObject", (Serializable) objectList);
						startActivity(intent);		
			    	}			    	
			    }
			}		
		}); 
			
		btnCreateAct.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent();
				intent.setClass(AboutActivity.this, CreateActivity.class);
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
	
	
	private void ShowDetails(){
		ListView lsvActDetails = (ListView)findViewById(R.id.lsv_act_details);
		adapter1 = new SimpleAdapter(this, ActDetail, R.layout.lsv_act_detail_raw,
				new String[] {"name", "info", "time","place","type","org","actor","limit"},
				new int[] {R.id.act_detail_name, R.id.act_detail_info, R.id.act_detail_time,R.id.act_detail_place,R.id.act_detail_type,R.id.act_detail_org,R.id.act_detail_actor,R.id.act_detail_limit});
		//lsvActDetails.setAdapter(adapter1);
		//Intent intent = new Intent();
		//intent.setClass(AboutActivity.this, ActDetails.class);
		//startActivity(intent);		
	}

	private void init() {
		btnCreateAct = (Button) findViewById(R.id.btn_create_activity);
		btnSeeAct = (Button) findViewById(R.id.btn_see_activity);
	}
	
	//获取活动信息列表
	private void getData() {
		listData.clear();
		for (Iterator<Speaker> it=listSpeaker.iterator(); it.hasNext(); ){
			Map<String, Object> tmp = new HashMap<String, Object>();
			Speaker spk = it.next();		
			tmp.put("name", spk.getName());
			tmp.put("info", spk.getInfo());
			tmp.put("time", spk.getTime());
			listData.add(tmp);
		}
	}
	//提示类
	private void showToast(CharSequence msg) {
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
	}
	
	
	public void onBackPressed() { 
	        super.onBackPressed(); 
	        Intent intent = new Intent();
			intent.setClass(AboutActivity.this, MainActivity.class);
			startActivity(intent);
			this.finish();       
	   } 

}
