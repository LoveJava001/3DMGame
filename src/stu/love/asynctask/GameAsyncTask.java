package stu.love.asynctask;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import stu.loce.interfcejav.GameCallBackInterface;
import stu.love.entity.GameEntity;
import stu.love.utils.NetWorkUtils;
import android.os.AsyncTask;
import android.util.Log;

public class GameAsyncTask extends AsyncTask<String, Void, List<GameEntity>> {

	private String TagAsy = "GameAsyncTask";
	private List<GameEntity> lists;
	private GameCallBackInterface callBack;
	
	public GameAsyncTask(GameCallBackInterface gameCallBack )
	{
		this.callBack = gameCallBack;
		lists = new ArrayList<GameEntity>();
	}

	@Override
	protected List<GameEntity> doInBackground(String... params) {
		// TODO Auto-generated method stub

		String str = NetWorkUtils.downloadGameInfo(params[0]);
		Log.i(TagAsy, "----GameAsyncTask="+str);
		
		try {
//			将获取的 Gameinfo 数据解析！
			JSONObject jsonObject = new JSONObject(str);
			JSONObject objects = jsonObject.getJSONObject("data");
			for(int i=0;i<12;i++)
			{
				JSONObject obj = objects.getJSONObject(""+i);
				GameEntity game = new GameEntity();
				game.setId(obj.getString("id"));
				game.setTitle(obj.getString("title"));
				game.setTypeid(obj.getString("typeid"));
				game.setTypename(obj.getString("typename"));
				game.setLitpic(obj.getString("litpic"));
				game.setRelease_date(obj.getString("release_date"));
				game.setRelease_company(obj.getString("release_company"));
				game.setMade_company(obj.getString("made_company"));
				game.setWebsite(obj.getString("websit"));
				game.setTerrace(obj.getString("terrace"));
//				将数据 添加到 Lists 
				lists.add(game);
			}
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		使用毁掉函数 加载数据!
		callBack.getResult(lists);
		
		return lists;
	}
	
	@Override
	protected void onPostExecute(List<GameEntity> result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
	}
	
	
}

