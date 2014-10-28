package stu.love.forum;

import stu.love.game.R;
import stu.love.utils.MyWebViewClient;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class ForumFragment extends Fragment {

	private WebView webview;
	private Activity context;
	
	public ForumFragment() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// Let's display the progress in the activity title bar, like the
		// browser app does.
		context = getActivity();
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.forum_fragment, null);
		
		webview = (WebView) view.findViewById(R.id.forumcontent);
	  //1 直接加载网址
//		webView.loadUrl("http://www.baidu.com/");
      //2 加载静态的网页，显示出来，网页内容使用 html
//		webView.loadData("<html><body><h1>Android</h1></body></html>", "text/html", null);
//		3 自定义
		 webview.getSettings().setJavaScriptEnabled(true);
		 webview.setWebChromeClient(new WebChromeClient() {
			   public void onProgressChanged(WebView view, int progress) {
			     // Activities and WebViews measure progress with different scales.
			     // The progress meter will automatically disappear when we reach 100%
			     context.setProgress(progress * 1000);
			   }
			 });
			 webview.setWebViewClient(new WebViewClient() {
			   public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
			     Toast.makeText(context, "Oh no! " + description, Toast.LENGTH_SHORT).show();
			   }
			 });
//			 加载 页面！
			 webview.loadUrl("http://www.baidu.com/");
		
		return view;
		 
	}
	
	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

}
