package stu.love.artical;

import stu.love.game.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 
 * ArticalListFragment 
 *  文章的主 Fragment 
 * 
 * */
public class ArticalListFragment extends Fragment {

	private FragmentManager manager;
	private FragmentTransaction transaction;
	private ArticalDetailFragment detailFragment;
	
	public ArticalListFragment() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.artical_list_fragment, null);
		
//		非常重要！ 解决了 Fragment 不显示的问题！
		manager = getChildFragmentManager();
		transaction = manager.beginTransaction();
		if(detailFragment==null)
			detailFragment = new ArticalDetailFragment();
		transaction.replace(R.id.titlefragment, detailFragment, "detailFragment");
		transaction.addToBackStack("detailFragment");
//		有忘记了 提交！
		transaction.commit();
		return view;
	}
	
	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

}
