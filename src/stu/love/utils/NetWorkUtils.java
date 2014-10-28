package stu.love.utils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.ByteArrayBuffer;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import stu.love.game.GameType;
import android.util.Log;

/**
 * 网络工具类
 * @author Davie
 *
 */
public class NetWorkUtils {
	
	private static final String Tag = "NetWorkUtils";
	
	/**
	 *  获取Json 数据
	 * 
	 * */
	public static JSONObject requestJson(String url){
		
		HttpParams mHttpParams=new BasicHttpParams();
        //设置网络链接超时
        HttpConnectionParams.setConnectionTimeout(mHttpParams, 200*1000);
        //设置socket响应超时
        HttpConnectionParams.setSoTimeout(mHttpParams, 200*1000);
        //设置socket缓存大小
        HttpConnectionParams.setSocketBufferSize(mHttpParams, 8*1024);
         
        HttpClient client=new DefaultHttpClient(mHttpParams);
		
		HttpGet get=new HttpGet(url);  //生成GET请求对象
		HttpResponse resp = null;
		try{
			resp=client.execute(get); //客户端执行Get请求，并得到响应
			if(resp.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
				//获取响应的数据
				
				String str = EntityUtils.toString(resp.getEntity(),"UTF-8");
				JSONObject obj = new JSONObject(str);
				Log.i(Tag, "---entity="+obj.getString("body"));
				return obj;
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
		return null;
	}
	
	
	
	
	/**
	 * //下载网络资源的功能方法
	 *   图片
	 * 
	 * @param url  下载图片的 地址！
	 * @return
	 */
	public static byte[] download(String url){
		//字节数组缓存
		ByteArrayBuffer byteArrayBuffer=new ByteArrayBuffer(0);
		HttpClient client=new DefaultHttpClient(); //生成客户端对象
		HttpGet get=new HttpGet(url);  //生成GET请求对象
		try{
			HttpResponse resp=client.execute(get); //客户端执行Get请求，并得到响应
			if(resp.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
				//获取响应的数据
				HttpEntity entity=resp.getEntity();
				InputStream is=entity.getContent();
				byte[] buffer=new byte[1024*10];
				int len=-1;
				while((len=is.read(buffer))!=-1){
					byteArrayBuffer.append(buffer, 0, len);
				}
				is.close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return byteArrayBuffer.toByteArray();
	}
	
	
/**
 * 
 *  使用网络下载游戏数据
 * 只需要将 JSOn数据返回 即可！
 * 
 * */
	public static String downloadGameInfo(String path)
	{
		HttpParams mHttpParams=new BasicHttpParams();
        //设置网络链接超时
        HttpConnectionParams.setConnectionTimeout(mHttpParams, 10*2000*1000);
        //设置socket响应超时
        HttpConnectionParams.setSoTimeout(mHttpParams, 10*2000*1000);
        //设置socket缓存大小
        HttpConnectionParams.setSocketBufferSize(mHttpParams, 1024*1024*10);
        //设置是否可以重定向
        HttpClientParams.setRedirecting(mHttpParams, true);
         
        HttpClient client=new DefaultHttpClient(mHttpParams);
		//字节数组缓存
		
		HttpGet get=new HttpGet(path);  //生成GET请求对象
		HttpResponse resp = null;
		try{
			Log.i(Tag, "---start execute(Get) ");
			resp=client.execute(get); //客户端执行Get请求，并得到响应
			if(resp.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
				//获取响应的数据
				HttpEntity entity = resp.getEntity();
//				缓冲区
				byte[] buf = EntityUtils.toByteArray(entity);
				Log.i(Tag, "--"+buf.length);
				String gameInfo = new String(buf,0,buf.length);
				Log.i(Tag, "--gameInfo="+gameInfo);
				String info = gameInfo.substring(gameInfo.indexOf("{"));
				return info;
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
		
		return null;
	}
	

}
