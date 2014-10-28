package stu.love.asynctask;

import java.util.List;

import stu.love.entity.ArticleList;
import android.os.AsyncTask;


/**
 *  异步任务 开启下载 文章内容
 * 
 * 
 * */
/**
 * @author aaa
 * 
 * String path  下载的路径！
 * void 显示的进度条
 * List<ArticleList>
 *
 */
public class ArticalAsyntask extends AsyncTask<String , Void, List<ArticleList>> {

	/**
	 * 
	 *  后台获取的数据
	 *  使用 Volley 获取数据！
	 *  
	 * */
	@Override
	protected List<ArticleList> doInBackground(String... params) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 *  更新 Ui
	 * 
	 * */
	@Override
	protected void onPostExecute(List<ArticleList> result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);

		
	}
}
