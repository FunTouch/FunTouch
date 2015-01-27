package com.funtouch;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class ActDetails extends Activity{
	private SimpleAdapter adapter1;
	private List<Map<String, Object>> ActDetail = new ArrayList<Map<String, Object>>();
	protected void onCreate(Bundle savedInstanceState) {
		  
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_details);
		List<ActDetailsInfo> objectList = (List<ActDetailsInfo>) getIntent().getSerializableExtra("ListObject");
		ActDetail = objectList.get(0).getActDetail();
		ListView lsvActDetails = (ListView)findViewById(R.id.lsv_act_details);
		adapter1 = new SimpleAdapter(this, ActDetail, R.layout.lsv_act_detail_raw,
				new String[] {"name", "info", "time","place","type","org","actor","limit"},
				new int[] {R.id.act_detail_name, R.id.act_detail_info, R.id.act_detail_time,R.id.act_detail_place,R.id.act_detail_type,R.id.act_detail_org,R.id.act_detail_actor,R.id.act_detail_limit});
		lsvActDetails.setAdapter(adapter1);
	
		
		}
}
