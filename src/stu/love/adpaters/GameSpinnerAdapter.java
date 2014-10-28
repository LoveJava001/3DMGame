package stu.love.adpaters;

import java.util.List;

import stu.love.game.GameType;
import stu.love.game.R;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class GameSpinnerAdapter extends BaseAdapter {

	private Context context;
	private LayoutInflater inflater;
	private List<GameType> lists;
	
	
	public GameSpinnerAdapter() {
		// TODO Auto-generated constructor stub
	}
	
	public GameSpinnerAdapter(Context context,LayoutInflater inflater) {
		this.context =context;
		this.inflater = inflater;
	}
	
	public void bindData(List<GameType> lists)
	{
		this.lists = lists;
	}

	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return lists.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return lists.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View view = null;
		if(convertView == null)
		{
			view = inflater.inflate(R.layout.spinner_item, null);
		}else
		{
			view = convertView;
		}
		
		TextView textView = (TextView)view.findViewById(R.id.spinner_list_item);
		textView.setText(lists.get(position).getName());
		textView.setGravity(Gravity.CENTER_VERTICAL);
		textView.setTextSize(17);
		
		return view;
	}

}
