package com.funtouch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.funtouch.Speaker;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

public class DataRetriever {
	/*public List<Speaker> retrieveAllSpeakers() {

		String url = "http://pyfun.sinaapp.com/test/get";
		List<Speaker> speakerArrayList = new ArrayList<Speaker>();
		HttpGet httpGet = new HttpGet(url);
		HttpClient httpClient = new DefaultHttpClient();

		try {
			HttpResponse httpResponse = httpClient.execute(httpGet);
			HttpEntity httpEntity = httpResponse.getEntity();
			String jsonString = EntityUtils.toString(httpEntity);
			JSONArray jsonArray = new JSONArray(jsonString);
			Speaker speaker;
			
			for (int i = 0; i < jsonArray.length(); i++) {

				JSONObject jsonObj = jsonArray.getJSONObject(i);
				speaker = new Speaker();

				speaker.setName(jsonObj.getString("name"));
				speaker.setPhone(jsonObj.getString("phone"));
				speaker.setValue(jsonObj.getInt("value"));

				speakerArrayList.add(speaker);

			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return speakerArrayList;
	}*/
	
	//×¢²á
	public int regist(String name, String password, String mailbox, String userclass,String phone){
		String url = "http://pyfun.sinaapp.com/regist";
		
		List <NameValuePair> params = new ArrayList <NameValuePair>();
		params.add(new BasicNameValuePair("name", name));
		params.add(new BasicNameValuePair("password", password));
		params.add(new BasicNameValuePair("mailbox", mailbox));
		params.add(new BasicNameValuePair("grade", userclass));
		params.add(new BasicNameValuePair("tel", phone));
		
		HttpPost httpPost = new HttpPost(url);
		
		HttpClient httpClient = new DefaultHttpClient();
		try {

			httpPost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
			HttpResponse httpResponse = httpClient.execute(httpPost);
			HttpEntity httpEntity = httpResponse.getEntity();

			String jsonString = EntityUtils.toString(httpEntity);
			JSONObject result = new JSONObject(jsonString);
			String res = result.getString("code");
			
			//JSONObject jsonObj = jsonArray.getJSONObject(1);
			//String res = jsonArray.getString(0);
			Log.i("b", res);	
			
			if (res.equals("200"))
				return 200;
			if (res.equals("401"))
				return 401;
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}
	
	//µÇÂ½
	public int login(String name, String password){
		String url = "http://pyfun.sinaapp.com/login";
		
		List <NameValuePair> params = new ArrayList <NameValuePair>();
		params.add(new BasicNameValuePair("name", name));
		params.add(new BasicNameValuePair("password", password));
		
		HttpPost httpPost = new HttpPost(url);
		
		HttpClient httpClient = new DefaultHttpClient();
		try {

			httpPost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
			HttpResponse httpResponse = httpClient.execute(httpPost);
			HttpEntity httpEntity = httpResponse.getEntity();

			String jsonString = EntityUtils.toString(httpEntity);
			JSONObject result = new JSONObject(jsonString);
			String res = result.getString("code");
			
			//JSONObject jsonObj = jsonArray.getJSONObject(1);
			//String res = jsonArray.getString(0);
			Log.i("b", res);	
			
			if (res.equals("200"))
				return 200;
			if (res.equals("410"))
				return 410;
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}

	// check the Internet connection
	public boolean isNetworkConnected(Context context) {
		if (context != null) {
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mNetworkInfo = mConnectivityManager
					.getActiveNetworkInfo();
			if (mNetworkInfo != null) {
				return mNetworkInfo.isAvailable();
			}
		}
		return false;
	}
	

}
