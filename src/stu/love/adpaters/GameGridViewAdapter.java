package stu.love.adpaters;

import java.util.List;

import stu.love.entity.GameEntity;
import stu.love.game.R;
import stu.love.utils.ImageDownLoad;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader.ImageCache;


public class GameGridViewAdapter extends BaseAdapter {

	private static String Tag = "GameGridViewAdapter";
	
	private List<GameEntity> gameEntitys;
	private Context context;
	private LayoutInflater inflater;
	private ViewHolderGameList viewHolder;
	private String base = "http://www.3dmgame.com";
	
	private RequestQueue queue;
	private ImageCache imageCache;
	
	
	public GameGridViewAdapter(Context context,
			LayoutInflater inflater,
			RequestQueue queue,
			ImageCache imageCache) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.inflater = inflater;
		this.queue = queue;
		this.imageCache = imageCache;
	}
	
	public void BindData(List<GameEntity> gameEntitys)
	{
		this.gameEntitys = gameEntitys;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return gameEntitys.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return gameEntitys.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

//	这里处理 gridView的 视图显示！
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
//		适配器！
		viewHolder = new ViewHolderGameList();
		if(convertView == null)
		{
			convertView = inflater.inflate(R.layout.game_gridview_item, null);
			viewHolder.iamgeView = (ImageView) convertView.findViewById(R.id.gridview_item);
			viewHolder.textView = (TextView)convertView.findViewById(R.id.gridview_item_name);
			convertView.setTag(viewHolder);
		}else
		{
			viewHolder = (ViewHolderGameList) convertView.getTag();
		}
		
//		获取数据添加到 item
//		在这里先不用 三级缓存
		viewHolder.textView.setText(gameEntitys.get(position).getTitle());
		
		
//		获取Bitmap图片！
		String urlImage = base+gameEntitys.get(position).getLitpic();
		Log.i(Tag, "--iamge url="+urlImage);
		ImageDownLoad.downLoadImage(context, viewHolder.iamgeView, 
				urlImage, queue, imageCache);
		
		return convertView;
	}

}
