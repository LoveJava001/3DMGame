package stu.love.utils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

public class MyWebChromeClient extends WebChromeClient
{

	
	public MyWebChromeClient() {
		// TODO Auto-generated constructor stub
	}
	
	private Activity activity;

    public MyWebChromeClient(Activity activity) {
        this.activity = activity;
    }

    public void onProgressChanged(WebView view, int newProgress) {
        if (activity != null) {
            activity.setProgress(newProgress * 100);
        }
    }

    public void onReceivedTitle(WebView view, String title) {
        if (activity != null) {
            activity.setTitle(title);
        }
    }

    public void onReceivedIcon(WebView view, Bitmap icon) {
        if (activity != null) {

        }
    }

}
