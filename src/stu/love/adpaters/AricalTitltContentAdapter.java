package stu.love.adpaters;

import java.text.SimpleDateFormat;
import java.util.List;

import stu.love.entity.ArticleList;
import stu.love.game.R;
import stu.love.utils.ImageCacheSDUtils;
import stu.love.utils.ImageDownLoad;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader.ImageCache;


/**
 * 
 * 文章列表的ListView
 * ListAdapter 
 * ListView  的自定义组件！
 * 
 * */
public class AricalTitltContentAdapter extends BaseAdapter {

	private static final String Tag = "AricalTitltContentAdapter";
	
	private Context context;
	private LayoutInflater layoutInflater;
	private List<ArticleList> articalList;
	private ImageCache imageCache;
	private RequestQueue queue;
	private SimpleDateFormat formatter;

	public AricalTitltContentAdapter(Context context,
			LayoutInflater layoutInflater,
			RequestQueue queue,ImageCache imageCache) {// TODO Auto-generated constructor
		this.context = context;
		this.layoutInflater = layoutInflater;
		this.queue = queue;
		this.imageCache = imageCache;
		formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	}

	public void bindData(List<ArticleList> list) {
		this.articalList = list;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return articalList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return articalList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	// 设置 ListView Item 的 显示
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolderArtical holder = null;
		if (convertView == null) {
			holder = new ViewHolderArtical();
			convertView = layoutInflater.inflate(R.layout.list_item_title_one,
					null);
			holder.articalImageView = (ImageView) convertView
					.findViewById(R.id.title_item_preimage);
			holder.articalItemTitle = (TextView) convertView
					.findViewById(R.id.list_item_title);
			holder.articalItemComment = (TextView) convertView
					.findViewById(R.id.list_item_comment);
			holder.articalItemDate = (TextView) convertView
					.findViewById(R.id.list_item_date);
			convertView.setTag(holder);
		} else {
			// ViewHolder 重用
			holder = (ViewHolderArtical) convertView.getTag();
		}
		// 2 将获取的信息 设置到 ListView 中的item 选项中！
		holder.articalItemTitle.setText(articalList.get(position).getShorttitle());
		long data = Long.parseLong(articalList.get(position).getSenddate());
		holder.articalItemDate.setText("日期"+formatter.format(data));
		holder.articalItemComment.setText("评论数"+articalList.get(position).getFeedback());

		Log.i(Tag, "--data time = "+data);
		
		//		图片 还需要下载！
		/**
		 *	需要用到 缓存数据数，要不然就 会浪费 流量！
		 *	而且用户的体验不好！
		 *
		 * */
		
//		1 获取 图片的Key值
		String url = articalList.get(position).getLitpic();
		
//		2  一级缓存  内存中
		Bitmap bitmap = imageCache.getBitmap(url);
		if(bitmap != null)
		{
			Log.i(Tag, "--  一级缓存启动！");
//			加载图片
			holder.articalImageView.setImageBitmap(bitmap);
		}else{
//			3  二级 缓存 文件中
//				从文件中读取 数据 并解析成 Bitmap
				byte[] dataImage = ImageCacheSDUtils.getInstance().getBitmapData(url);
				if(dataImage != null)
				{
//					3 解码 文件的中的图片！
					Log.i(Tag, "--- 二级缓存启动");
					bitmap = BitmapFactory.decodeByteArray(dataImage, 0, dataImage.length);
					holder.articalImageView.setImageBitmap(bitmap);
				}else
				{
	//				4 三级缓存 网络中取数据
					Log.i(Tag, "--- 三级缓存启动");
					ImageDownLoad.downLoadImage(context, holder.articalImageView, url, queue, imageCache);
				}
		}
		return convertView;
	}
}
