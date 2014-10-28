package stu.love.utils;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

public class JsonUtils {

	public JsonUtils() {
		// TODO Auto-generated constructor stub
	}
	
    static{
    	     
    }
	
	/**
	 * 解析json数据的工具类
	 * @param context
	 * @param json_path
	 * @param callBack
	 * @param queue
	 */
	public static void parseJsonString(Context context, String json_path,
			final JsonStringCallBack callBack, RequestQueue queue) {
//		string  请求
		StringRequest request = new StringRequest(json_path,
				new Listener<String>() {
			@Override
			public void onResponse(String response) {
				// TODO Auto-generated method stub
				callBack.jsonStringCallBack(response);
			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				// TODO Auto-generated method stub
			}
		});
		//添加到队列中
		queue.add(request);
		queue.start();
		
	}

	/**
	 * 解析数组开始的json字符串
	 * 
	 * @param context
	 * @param json_path
	 * @param callBack
	 * @param queue
	 */
	public static void parseJsonArray(Context context, String json_path,
			final JsonArrayCallBack callBack, RequestQueue queue) {
		JsonArrayRequest request = new JsonArrayRequest(json_path,
				new Listener<JSONArray>() {
					@Override
					public void onResponse(JSONArray response) {
						// TODO Auto-generated method stub
						callBack.jsonArrayCallBack(response);
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						// TODO Auto-generated method stub
						Log.i("TAG", error.getMessage());
					}
				});
		// 添加到队列中
		queue.add(request);
	}

	/**
	 * 
	 * @param context
	 * @param json_path
	 * @param callBack
	 */
	public static void parseJsonObject(Context context, String json_path,
			final JsonObjectCallBack callBack, RequestQueue queue) {
		JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
				Request.Method.POST, json_path, null,
				new Response.Listener<JSONObject>() {
					@Override
					public void onResponse(JSONObject response) {
						// TODO Auto-generated method stub
						// System.out.println("----->>response=" + response);
						callBack.jsonObjectCallBack(response);
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						// TODO Auto-generated method stub

					}
				});
		queue.add(jsonObjectRequest);
	}

	// 解析json对象的回调接口
	public interface JsonObjectCallBack {
		public void jsonObjectCallBack(JSONObject jsonObject);

	}

	// 解析json数组的回调接口
	public interface JsonArrayCallBack {
		public void jsonArrayCallBack(JSONArray jsonArray);
	}

	// 解析json字符串的回调接口
	public interface JsonStringCallBack {
		public void jsonStringCallBack(String jsonString);
	}
	
}
