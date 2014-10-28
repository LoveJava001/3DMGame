/**
 * 
 */
package stu.love.artical;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.ls.LSException;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.android.volley.toolbox.ImageLoader.ImageCache;

import stu.love.adpaters.MyFragmentPagerAdapter;
import stu.love.entity.ArticleList;
import stu.love.game.R;
import android.app.ActivityManager;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * @author love ViewPager 与 Fragment 的实现！
 * 
 */
public class ArticalDetailFragment extends Fragment {

	Resources resources;
	private ViewPager mPager;
	private ArrayList<Fragment> fragmentsList;
	private ImageView ivBottomLine;
	private TextView tvTabNew, tvTabNew2,tvTabNew3,tvTabNew4;
	private Context context;//获取当前的 上下文
	
	private RequestQueue ariRequestQueue;
	private ImageCache imageCache;
	private List<ArticleList> articalLists;
	private LayoutInflater layoutInflater;
	private static LruCache<String, Bitmap> lruCache;
	private static final String baseUrl = "http://www.3dmgame.com/";
	
	private int currIndex = 0;
	private int bottomLineWidth;
	private int offset = 0;
	private int position_one;
	private int position_two;
	private int position_three;
	private int position_four;
	public final static int num = 4;
	ArticalTitleOneFragment home1;
	ArticalTitleTwoFrgament home2;
	ArticalTitleThreeFragment home3;
	ArticalTitleFourFragment home4;

	/**
	 * 
	 */
	public ArticalDetailFragment() {
		
		// TODO Auto-generated constructor stub
		
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.actical_list_body_fragment, null);
		layoutInflater = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		resources = getResources();
		

		/**
		 * 		1	开启异步任务 去下载数据！
		 * 			使用Volley框架！
		 *    		内部封装好了 异步任务！
		 *  		获取到数据之后 才能 设置 适配器！
					自定义Adapter
		 * */
//		获取请求队列
		ariRequestQueue = Volley.newRequestQueue(getActivity());
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
				
		// 初始化 图片的位置宽度
		InitWidth(view);
		InitTextView(view);
		InitViewPager(view);
		TranslateAnimation animation = new TranslateAnimation(position_one,
				offset, 0, 0);
		animation.setFillAfter(true);
		animation.setDuration(300);
		ivBottomLine.startAnimation(animation);
		
		return view;
	}

	private void InitTextView(View parentView) {
		tvTabNew = (TextView) parentView.findViewById(R.id.tv_tab_1);
		tvTabNew2 = (TextView) parentView.findViewById(R.id.tv_tab_2);
		tvTabNew3 = (TextView) parentView.findViewById(R.id.tv_tab_3);
		tvTabNew4 = (TextView) parentView.findViewById(R.id.tv_tab_4);

		tvTabNew.setOnClickListener(new MyOnClickListener(0));
		tvTabNew2.setOnClickListener(new MyOnClickListener(1));
		tvTabNew3.setOnClickListener(new MyOnClickListener(2));
		tvTabNew4.setOnClickListener(new MyOnClickListener(3));
	}

	/**
	 * 
	 * 初始化 ViewPager
	 * 
	 * */
	private void InitViewPager(View parentView) {
//		获取 ViewPager
		mPager = (ViewPager)parentView.findViewById(R.id.vPager);;
		fragmentsList = new ArrayList<Fragment>();

		home1 = new ArticalTitleOneFragment( ariRequestQueue, imageCache, layoutInflater, lruCache,baseUrl,context);
		home2 = new ArticalTitleTwoFrgament( ariRequestQueue, imageCache, layoutInflater, lruCache, baseUrl, context);
		home3 = new ArticalTitleThreeFragment( ariRequestQueue, imageCache, layoutInflater, lruCache, baseUrl, context);
		home4 = new ArticalTitleFourFragment( ariRequestQueue, imageCache, layoutInflater, lruCache, baseUrl, context);

		fragmentsList.add(home1);
		fragmentsList.add(home2);
		fragmentsList.add(home3);
		fragmentsList.add(home4);

		// 设置适配器
		mPager.setAdapter(new MyFragmentPagerAdapter(getChildFragmentManager(),
				fragmentsList));
		
		// 监听滑动事件
		mPager.setOnPageChangeListener(new MyOnPageChangeListener());
		
//		设置当前的显示的frgment
		mPager.setCurrentItem(0);

	}

	// 计算 下面的滑动 的位置
	private void InitWidth(View parentView) {
		ivBottomLine = (ImageView)parentView.findViewById(R.id.iv_bottom_line);
		bottomLineWidth = ivBottomLine.getLayoutParams().width;
		DisplayMetrics dm = new DisplayMetrics();
		getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
		int screenW = dm.widthPixels;
		offset = (int) ((screenW / num - bottomLineWidth) / 4);
		int avg = (int) (screenW / num);
		position_one = avg + offset;
		position_two = 2*avg + offset;
		position_three = 3*avg + offset;
	}

	// 点击事件 切换 Fregment
	public class MyOnClickListener implements View.OnClickListener {
		private int index = 0;

		public MyOnClickListener(int i) {
			index = i;
		}

		@Override
		public void onClick(View v) {
			mPager.setCurrentItem(index);
		}
	};

	public class MyOnPageChangeListener implements OnPageChangeListener {

		@Override
		public void onPageSelected(int arg0) {
			Animation animation = null;
			// 滑动效果的实现！
			switch (arg0) {
			case 0:
				if (currIndex == 1) {
					animation = new TranslateAnimation(position_one, offset, 0,
							0);
				} else if (currIndex == 2) {
					animation = new TranslateAnimation(position_two, offset, 0,
							0);
				} else if (currIndex == 3) {
					animation = new TranslateAnimation(position_three, offset,
							0, 0);
				} 
				break;
			case 1:
				if (currIndex == 0) {
					animation = new TranslateAnimation(offset, position_one, 0,
							0);
				} else if (currIndex == 2) {
					animation = new TranslateAnimation(position_two, position_one, 0,
							0);
				}else if (currIndex == 3) {
					animation = new TranslateAnimation(position_three, position_one, 0,
							0);
				}
				break;
			case 2:
				if (currIndex == 0) {
					animation = new TranslateAnimation(offset, position_two, 0,
							0);
				}else if (currIndex == 1) {
					animation = new TranslateAnimation(position_one, position_two, 0,
							0);
				}else if (currIndex == 3) {
					animation = new TranslateAnimation(position_three, position_two, 0,
							0);
				}
				break;
			case 3:
				if (currIndex == 0) {
					animation = new TranslateAnimation(offset, position_three, 0,
							0);
				}else if (currIndex == 1) {
					animation = new TranslateAnimation(position_one, position_three, 0,
							0);
				}else if (currIndex == 2) {
					animation = new TranslateAnimation(position_two, position_three, 0,
							0);
				}
				break;
			}

			currIndex = arg0;
			animation.setFillAfter(true);
			animation.setDuration(300);
			ivBottomLine.startAnimation(animation);
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		@Override
		public void onPageScrollStateChanged(int arg0) {
		}
	}

}
