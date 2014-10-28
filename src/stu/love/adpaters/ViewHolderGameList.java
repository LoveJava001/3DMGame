package stu.love.adpaters;

import android.widget.ImageView;
import android.widget.TextView;

public class ViewHolderGameList {

	public static ImageView iamgeView;
	public static TextView textView;
	
	public ViewHolderGameList() {
		// TODO Auto-generated constructor stub
	}
	
	

	public ViewHolderGameList(ImageView iamgeView, TextView textView) {
		super();
		this.iamgeView = iamgeView;
		this.textView = textView;
	}


	public ImageView getIamgeView() {
		return iamgeView;
	}

	public void setIamgeView(ImageView iamgeView) {
		this.iamgeView = iamgeView;
	}

	public TextView getTextView() {
		return textView;
	}

	public void setTextView(TextView textView) {
		this.textView = textView;
	}
	
	

}
