package stu.love.adpaters;

import android.widget.ImageView;
import android.widget.TextView;

public final class ViewHolderArtical {

	public ImageView articalImageView;
	public TextView articalItemTitle;
	public TextView articalItemDate;
	public TextView articalItemComment;
	
	public ViewHolderArtical() {
		// TODO Auto-generated constructor stub
	}

	public ViewHolderArtical(ImageView articalImageView,
			TextView articalItemTitle, TextView articalItemDate,
			TextView articalItemComment) {
		super();
		this.articalImageView = articalImageView;
		this.articalItemTitle = articalItemTitle;
		this.articalItemDate = articalItemDate;
		this.articalItemComment = articalItemComment;
	}

}
