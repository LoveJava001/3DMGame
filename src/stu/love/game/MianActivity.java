package stu.love.game;

import stu.love.artical.ArticalListFragment;
import stu.love.forum.ForumFragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * 3DM game 程序的 入口 测试类！
 * 
 * */

public class MianActivity extends FragmentActivity implements OnClickListener {

	// 菜单选项
	private Button articlemenu;
	private Button forummenu;
	private Button gamemenu;
	
	// fragment
	private FragmentManager manager;
	private FragmentTransaction transaction;
	private ArticalListFragment articalListFragment;
	private ForumFragment forumFrgment;
	private GameFragment gameFragment;

	public MianActivity() {
		super();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_PROGRESS);
		setContentView(R.layout.activity_mian);

//		1 初始化组件
		initComponet();
//		2  初始化 frgment
		manager = getSupportFragmentManager();
		initArticalListFragment();
		
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	/**
	 * 1 初始化组件
	 * 
	 * */
	private void initComponet() {
		articlemenu = (Button) this.findViewById(R.id.articalmenu);
		forummenu = (Button) this.findViewById(R.id.forummenu);
		gamemenu = (Button) this.findViewById(R.id.gamemenu);
		articlemenu.setOnClickListener(this);
		forummenu.setOnClickListener(this);
		gamemenu.setOnClickListener(this);
	}

	/**
	 * 2 事件的响应 替换 Fragment2 事件的响应 替换 Fragment
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		transaction = manager.beginTransaction();
		switch (v.getId()) {
		case R.id.articalmenu:
			if (articalListFragment == null)
				articalListFragment = new ArticalListFragment();
				transaction.replace(R.id.contentfragment, articalListFragment,"articalListFragment");
				transaction.addToBackStack("articalListFragment");
			break;

		case R.id.forummenu:
			if (forumFrgment == null)
				forumFrgment = new ForumFragment();
				transaction.replace(R.id.contentfragment, forumFrgment,"forumFrgment");
				transaction.addToBackStack("forumFrgment");
			break;
			
		case R.id.gamemenu:
			if (gameFragment == null)
				gameFragment = new GameFragment();
				transaction.replace(R.id.contentfragment, gameFragment,"gameFragment");
				transaction.addToBackStack("gameFragment");
			break;
		}
//		fragment 的替换！
		transaction.commit();
	}
	
//	3 初始化 Fragment
	public void initArticalListFragment()
	{
		transaction = manager.beginTransaction();
		articalListFragment = new ArticalListFragment();
		transaction.add(R.id.contentfragment, articalListFragment,"articalListFragment");
		transaction.addToBackStack("articalListFragment");
//		因为这里 ，没有 commit 所以 不能show data!
		transaction.commit();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.mian, menu);
		return true;
	}

}
