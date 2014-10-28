package stu.love.entity;

public class GameDetailEntity extends GameEntity {

	private String tips;  //游戏攻略
	
	public GameDetailEntity() {
		// TODO Auto-generated constructor stub
	}

	public GameDetailEntity(String id, String title, String senddate,
			String shorttitle, String typeid, String typename, String litpic,
			String feedback, String arcurl, String release_date,
			String made_company, String release_company, String website,
			String terrace) {
		super(id, title, senddate, shorttitle, typeid, typename, litpic,
				feedback, arcurl, release_date, made_company,
				release_company, website, terrace);
		// TODO Auto-generated constructor stub
	}

	public String getTips() {
		return tips;
	}

	public void setTips(String tips) {
		this.tips = tips;
	}

	public GameDetailEntity(String id, String title, String senddate,
			String shorttitle, String typeid, String typename, String litpic,
			String feedback, String arcurl, String body, String release_date,
			String made_company, String release_company, String website,
			String terrace, String tips) {
		
		super(id, title, senddate, shorttitle, typeid, typename, litpic, feedback, arcurl, release_date, made_company, release_company, website, terrace);
		this.tips = tips;
	}

	
	
	
}
