package stu.love.utils;

import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import stu.love.game.R;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageCache;
import com.android.volley.toolbox.ImageLoader.ImageContainer;
import com.android.volley.toolbox.ImageLoader.ImageListener;

public class ImageDownLoad {

	private static String Tag= "ImageDownLoad";
	
	public ImageDownLoad() {
		// TODO Auto-generated constructor stub
	}

	
	/**
	 * 设计图片下载
	 * @param context
	 * @param imageView
	 * @param path
	 * @param queue
	 * @param imageCache
	 */
	public static void downLoadImage(Context context, ImageView imageView,
			String path, RequestQueue queue, ImageCache imageCache) {
		
		Log.i(Tag,"----start downloadImage ");
		ImageLoader imageLoader = new ImageLoader(queue, imageCache);
		ImageListener imageListener = ImageLoader.getImageListener(imageView,
				R.drawable.ic_launcher, R.drawable.ic_launcher);
//		1 imageLoader 将图片加载仅投入缓存中！
		ImageContainer container = imageLoader.get(path, imageListener);
	}
	
	
	public static void downloadImageToSDCard(Context context, ImageView imageView,
			String path, RequestQueue queue, ImageCache imageCache)
	{
		HttpParams mHttpParams=new BasicHttpParams();
        //设置网络链接超时
        HttpConnectionParams.setConnectionTimeout(mHttpParams, 200*1000);
        //设置socket响应超时
        HttpConnectionParams.setSoTimeout(mHttpParams, 200*1000);
        //设置socket缓存大小
        HttpConnectionParams.setSocketBufferSize(mHttpParams, 8*1024);
        //设置是否可以重定向
        HttpClientParams.setRedirecting(mHttpParams, true);
         
        HttpClient client=new DefaultHttpClient(mHttpParams);
		//字节数组缓存
		
		HttpGet get=new HttpGet(path);  //生成GET请求对象
		HttpResponse resp = null;
		try{
			resp=client.execute(get); //客户端执行Get请求，并得到响应
			if(resp.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
				//获取响应的数据
				byte[] data = new byte[1024*1024*10];
				HttpEntity entity = resp.getEntity();
				data = EntityUtils.toByteArray(entity);
				
//				1 一级缓存
				ImageLoader imageLoader = new ImageLoader(queue, imageCache);
				ImageListener imageListener = ImageLoader.getImageListener(imageView,
						R.drawable.ic_launcher, R.drawable.ic_launcher);
//				1 imageLoader 将图片加载仅投入缓存中！
				ImageContainer container = imageLoader.get(path, imageListener);
				Log.i(Tag, "---container.getBitmap().getByteCount()="+container.getBitmap().getByteCount());
				
				
//				2 二级缓存 将图片  写入 SDCard中!
				ImageCacheSDUtils.getInstance().putBitmapData(path, data);
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally
		{
			if(client != null)
			{
				client.getConnectionManager().shutdown();
			}
		}
	}
}
