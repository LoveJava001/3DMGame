package stu.love.game;

import java.util.ArrayList;
import java.util.List;

import stu.loce.interfcejav.GameCallBackInterface;
import stu.love.adpaters.GameGridViewAdapter;
import stu.love.adpaters.GameSpinnerAdapter;
import stu.love.asynctask.GameAsyncTask;
import stu.love.entity.GameEntity;
import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.android.volley.toolbox.ImageLoader.ImageCache;

public class GameFragment extends Fragment implements OnItemClickListener{

	private String Tag = "GameFragment";
	
	private String gameInfo;
	private Spinner spinner;
	private GridView gridView;
	private Context context;

	private GameSpinnerAdapter spinnerAdapter;
	private GameGridViewAdapter gridViewAdapter;
	private List<GameType> gameLists;
	private List<GameEntity> gameEntitys;
	
	private RequestQueue queue;
	private ImageCache imageCache;
	private LruCache<String, Bitmap> lruCache;
	
	// 游戏类型
	private static String gameTypeId = "179";
//	Json 数据的路径
	private String baseurl = "http://www.3dmgame.com/sitemap/api.php?";
	private String url;
	
//	
	private RequestQueue queueGame;

	public GameFragment() {
		// TODO Auto-generated constructor stub

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		gameLists = new ArrayList<GameType>();
		gameEntitys = new ArrayList<GameEntity>();
		initGaemTypes();
		
		/**
		 * 		1	开启异步任务 去下载数据！
		 * 			使用Volley框架！
		 *    		内部封装好了 异步任务！
		 *  		获取到数据之后 才能 设置 适配器！
					自定义Adapter
		 * */
//		获取请求队列
		queue = Volley.newRequestQueue(getActivity());
		//获得系统的动态的剩余内存空间
		int memClass = ((ActivityManager) getActivity().getSystemService(Context.ACTIVITY_SERVICE))
						.getMemoryClass();
		//获取剩余的内存空间的/8
		final int cacheSize = 1024 * 1024 * memClass / 8;
//		设置缓存的空间大小！
		lruCache = new LruCache<String, Bitmap>(cacheSize);
//		初始化 图片的缓存！
		imageCache = new ImageCache() {
			public void putBitmap(String url, Bitmap bitmap) {
					// TODO Auto-generated method stub
					//设置缓存的路径
					lruCache.put(url, bitmap);
			}
	
			public Bitmap getBitmap(String url) {
					// TODO Auto-generated method stub
					return lruCache.get(url);
			}
		};	
		
	}

	@Override
	public View onCreateView(final LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.game_fragment, null);

		// 组件
		spinner = (Spinner) view.findViewById(R.id.spinner);
		gridView = (GridView) view.findViewById(R.id.gridview_content);
		
		// 适配器
		context = getActivity();
		spinnerAdapter = new GameSpinnerAdapter(context, inflater);

		// 绑定数据
		spinnerAdapter.bindData(gameLists);
		spinner.setAdapter(spinnerAdapter);
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				// 获取游戏的 分类 ID 区 更新GridView
				gameTypeId = gameLists.get(position).getTypeId();
//				更新 gridView的界面！
//				4  开启异步任务
				url = baseurl+"row=12&typeid="+gameTypeId+"&paging=1&page=0";
//				获取数据
				new GameAsyncTask(new GameCallBackInterface() {
					
					public void getResult(List<GameEntity> lists) {
						// TODO Auto-generated method stub
						gameEntitys = lists;
					}
				}).execute(url);
				
//				获取 网格的 设配器 并 绑定数据
				gridViewAdapter = new GameGridViewAdapter(context, inflater, queue, imageCache);
				gridViewAdapter.BindData(gameEntitys);
				gridView.setAdapter(gridViewAdapter);
			}
			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
			}
		});

//		3  开启异步任务
		url = baseurl+"row=12&typeid="+gameTypeId+"&paging=1&page=0";
//		获取数据
		new GameAsyncTask(new GameCallBackInterface() {
			public void getResult(List<GameEntity> lists) {
				// TODO Auto-generated method stub
				gameEntitys = lists;
			}
		}).execute(url);
		
//		获取 网格的 设配器 并 绑定数据
		gridViewAdapter = new GameGridViewAdapter(context, inflater, queue, imageCache);
		gridViewAdapter.BindData(gameEntitys);
		gridView.setAdapter(gridViewAdapter);
		
		return view;
		
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	// 初始化 gameType List
	private void initGaemTypes() {
		gameLists.add(new GameType("179", "首页"));
		gameLists.add(new GameType("181", "动作"));
		gameLists.add(new GameType("182", "射击"));
		gameLists.add(new GameType("182", "角色扮演"));
		gameLists.add(new GameType("184", "养成"));
		gameLists.add(new GameType("185", "益智"));
		gameLists.add(new GameType("186", "即时战略"));
		gameLists.add(new GameType("187", "策略"));
		gameLists.add(new GameType("188", "体育"));
		gameLists.add(new GameType("189", "模拟经营"));
		gameLists.add(new GameType("190", "赛车"));
		gameLists.add(new GameType("191", "冒险"));
		gameLists.add(new GameType("192", "动作角色"));
	}

//	响应GridView 的点击事件！
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
//		获取游戏的详情！
		String gameId = gameEntitys.get(position).getId();
		String gameTypeId = gameEntitys.get(position).getTypeid();
		
		Toast.makeText(context, gameId+"-----"+gameTypeId, 1).show();
		
	}

}
