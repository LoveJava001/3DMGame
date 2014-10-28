package stu.love.artical;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import stu.love.entity.ArticleEntity;
import stu.love.game.R;
import stu.love.utils.JsonUtils;
import stu.love.utils.JsonUtils.JsonObjectCallBack;
import stu.love.utils.MyWebChromeClient;
import stu.love.utils.MyWebViewClient;
import stu.love.utils.NetWorkUtils;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.Window;
import android.webkit.WebView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader.ImageCache;
import com.android.volley.toolbox.Volley;

public class ArticalContent extends Activity {

	private static final String Tag = "ArticalContent" ;
	private WebView webView;
	
	
//	private static String articalContentUrl = "http://www.3dmgame.com/sitemap/api.php?id=3372964&typeid=2";
	private static String baseUrl = "http://www.3dmgame.com/sitemap/api.php?";
	private static String base="http://www.3dmgame.com/";
	
	private RequestQueue ariQueue;
	private ImageCache imageCache;
	private static LruCache<String, Bitmap> lruCache;
	
//	文章详情对象
	private ArticleEntity article;
	
	public ArticalContent() {
		// TODO Auto-generated constructor stub
		Log.i(Tag, "--- ArticalContent 构造函数");
	}
	
	private Handler handler = new Handler()
	{
		public void handleMessage(Message msg) {
			if(msg.what == 1)
			{
				article = (ArticleEntity) msg.obj;
				// TODO Auto-generated method stub
//		        1  解码：
		        String decoder;
		        try {
//		        		解码  将获取的 Data 解码！
					decoder = URLDecoder.decode(article.getBody().toString(),"UTF-8");
//					2 设置 BaseUrl 来解决新的data中的 先谷底路径的错误！
					webView.loadDataWithBaseURL(base, decoder, "text/html", "UTF-8", null);
		        } catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_PROGRESS);
		setContentView(R.layout.artical_content_fragment);
		
		
//		1 获取到 需要的请求 文件的信息！
		Intent intent = getIntent();
		Bundle bundle = intent.getBundleExtra("detail");
		String artId = bundle.getString("art_id");
		String artTypeId = bundle.getString("art_typeid");
		
		/**
		 *3		开启异步任务 去下载数据！
		 * 			使用Volley框架！　　不建议使用Volley 因为数据太少了！
		 *    		内部封装好了 异步任务！
		 *  		获取到数据之后 才能 设置 适配器！
					自定义Adapter
		 * */
				
//		2   设置 WebView 获得数据之后！
		webView = (WebView) findViewById(R.id.artical_content_webview);
		webView.setWebChromeClient(new MyWebChromeClient(this));		
		webView.setWebViewClient(new MyWebViewClient());
				
//		3   开启异步任务 下载 数据！ WebView  获取数据！
		new DetailAsycnTask().execute(baseUrl+"id="+artId+"&typeid="+artTypeId);
				
	}
	
	
	
//	3	 异步任务
	class DetailAsycnTask extends AsyncTask<String, Void,ArticleEntity>
	{
		public DetailAsycnTask()
		{}

//		获取数据
		@Override
		protected ArticleEntity doInBackground(String... params) {
			// TODO Auto-generated method stub
//			获取 文章的详情！
			Log.i(Tag, "--params="+params[0]);
			
			JSONObject obj =  NetWorkUtils.requestJson(params[0]);
			
			try {
				article = new ArticleEntity();
				article.setId(obj.getString("id"));
				article.setTypeid(obj.getString("typeid"));
				article.setTypename(obj.getString("typename"));
				article.setTitle(obj.getString("title"));
				article.setShorttitle(obj.getString("shorttitle"));
				article.setSenddate(obj.getString("senddate"));
				article.setLitpic(base+obj.getString("litpic"));
				article.setFeedback(obj.getString("feedback"));
				article.setArcurl(obj.getString("arcurl"));
				article.setBody(obj.getString("body"));
//				已经获取数据了！
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
//			將 获取的数据  发送给 handler
			Message msg = Message.obtain();
			msg.what = 1;
			msg.obj = article;
			handler.sendMessage(msg);
			
			return article;
		}
	}
	
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}
	
}
