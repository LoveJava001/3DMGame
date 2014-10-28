package stu.love.game;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;


/** 
 * 
 * 
 * 登陆界面！  开启动画！
 * 
 * 动画的加载 需要 开启另一个线程！
 * 不够再主线程的话，会占用 主线程！
 * 
 * */
public class ActivityStart extends Activity{

	private final Handler handle = new Handler()
	{
		public void handleMessage(Message msg) {
			
			Intent intent = new Intent(ActivityStart.this,MianActivity.class);
			startActivity(intent);
//			关闭Activity 资源
			finish();
		};
	};
	
	public ActivityStart() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);

//		开启线程 显示 图片！
		new Start().start();
		
	}
	
	class Start extends Thread
	{
		public void run()
		{
			try {
//				睡一会在加载！
				Thread.sleep(1000);
				
				Message message = Message.obtain();
				handle.sendMessage(message);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
}
