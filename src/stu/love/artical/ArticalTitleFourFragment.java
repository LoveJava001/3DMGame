package stu.love.artical;


import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import stu.love.adpaters.AricalTitltContentAdapter;
import stu.love.entity.ArticleList;
import stu.love.game.R;
import stu.love.utils.JsonUtils;
import stu.love.utils.JsonUtils.JsonObjectCallBack;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader.ImageCache;

public class ArticalTitleFourFragment extends Fragment
{
	private String baseUrl;
	private static  String artoicalPathFour ="http://www.3dmgame.com/sitemap/api.php?row=20&typeid=199&paging=1&page=1" ;
	private static String Tag = "ArticalTitleOneFragment";
	private ListView titleOneContentListView;
	private AricalTitltContentAdapter aritcalAdapter; 
	private RequestQueue ariRequestQueue;
	private ImageCache imageCache;
	private List<ArticleList> articalLists;
	private LayoutInflater layoutInflater;
	private LruCache<String, Bitmap> lruCache;
	private Context context;
	
	public ArticalTitleFourFragment()
	{
		super();
	}
	
	public ArticalTitleFourFragment(
			RequestQueue ariRequestQueue,
			ImageCache imageCache,
			LayoutInflater layoutInflater,
			LruCache<String, Bitmap> lruCache,String baseUrl,
			Context context)
	{
		this.ariRequestQueue = ariRequestQueue;
		this.imageCache = imageCache;
		this.layoutInflater = layoutInflater;
		this.lruCache = lruCache;
		this.baseUrl = baseUrl;
		this.context = context;
		articalLists = new ArrayList<ArticleList>();
	}
	
	/* (non-Javadoc)
	 * @see android.support.v4.app.Fragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
	 */
	/* (non-Javadoc)
	 * @see android.support.v4.app.Fragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.actical_title_one_fragment, null);
		
			

//		2 获取 显示数据的ListView
		titleOneContentListView = (ListView) view.findViewById(R.id.title_one_content);

//		3开始获取 Json数据
		JsonUtils.parseJsonObject(context, artoicalPathFour, new JsonObjectCallBack() {

			public void jsonObjectCallBack(JSONObject jsonObject) {
				// TODO Auto-generated method stub
				try {
//					解析获取的 JsonObject数据
//					Log.i(Tag, "--jsonObject="+jsonObject);
					JSONObject artListDatas = jsonObject.getJSONObject("data");
					
//					一次我只取20条数据
					for(int i=0;i<20;i++)
					{
						JSONObject obj = artListDatas.getJSONObject(""+i);
						ArticleList article = new ArticleList();
						article.setId(obj.getString("id"));
						article.setTypeid(obj.getString("typeid"));
						article.setTypename(obj.getString("typename"));
						article.setTitle(obj.getString("title"));
						article.setShorttitle(obj.getString("shorttitle"));
						article.setSenddate(obj.getString("senddate"));
						article.setLitpic(baseUrl+obj.getString("litpic"));
						article.setFeedback(obj.getString("feedback"));
						article.setArcurl(obj.getString("arcurl"));
//						Log.i(Tag, "--"+article.toString());
//						已经获取数据了！
						articalLists.add(article);
					}
//					Log.i(Tag, "--jsonObjects="+artListDatas.getString("0"));
					
//					初始化适配器器
					aritcalAdapter = new AricalTitltContentAdapter(getActivity(), layoutInflater,ariRequestQueue , imageCache);	
//					适配器 绑定数据
					aritcalAdapter.bindData(articalLists);
					
//					获取数据之后才能设置 适配器！
					titleOneContentListView.setAdapter(aritcalAdapter);
					aritcalAdapter.notifyDataSetChanged();
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}, ariRequestQueue);
		
		

//		设置 ListView 点击事件 : 跳转到 详情界面！
		titleOneContentListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
//				获取你点击的位置
				ArticleList articleinfo = articalLists.get(position);
				String artId = articleinfo.getId();
				String artTypeId = articleinfo.getTypeid();
				Bundle bundle = new Bundle();
				bundle.putString("art_id", artId);
				bundle.putString("art_typeid", artTypeId);
				Intent intent = new Intent(getActivity(), ArticalContent.class);
				intent.putExtra("detail", bundle);
				startActivity(intent);
			}
		});
//		返回试图。
		return view;
	}
}
