package stu.love.entity;

public class GameEntity extends ArticleList{

	private String release_date;//发售日期
	private String made_company;//开发厂商
	private String release_company;//发行厂商
	private String website;//官方网站
	private String terrace;//游戏平台
	
	
	public GameEntity() {
		// TODO Auto-generated constructor stub
	}
	
	

	public GameEntity(String id, String title, String senddate,
			String shorttitle, String typeid, String typename, String litpic,
			String feedback, String arcurl, String release_date,
			String made_company, String release_company, String website,
			String terrace) {
		super(id, title, senddate, shorttitle, typeid, typename, litpic,
				feedback, arcurl);
		this.release_date = release_date;
		this.made_company = made_company;
		this.release_company = release_company;
		this.website = website;
		this.terrace = terrace;
	}



	public String getRelease_date() {
		return release_date;
	}

	public void setRelease_date(String release_date) {
		this.release_date = release_date;
	}

	public String getMade_company() {
		return made_company;
	}

	public void setMade_company(String made_company) {
		this.made_company = made_company;
	}

	public String getRelease_company() {
		return release_company;
	}

	public void setRelease_company(String release_company) {
		this.release_company = release_company;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getTerrace() {
		return terrace;
	}

	public void setTerrace(String terrace) {
		this.terrace = terrace;
	}



	@Override
	public String toString() {
		return "GameEntity ["+ ", release_date=" + release_date
				+ ", made_company=" + made_company + ", release_company="
				+ release_company + ", website=" + website + ", terrace="
				+ terrace + "]";
	}




	
	
}
