package stu.love.entity;


/**
 * 
 *  文章列表实体
 * */
public class ArticleList {

	private String id ;// 文章id
	private String title;//  文章标题
	private String senddate;// 文章发布时间
	private String shorttitle;//文章短标题
	private String typeid;//文章 分类id
	private String typename;//文章分类名称
	private String litpic;//文章的缩略地址
	private String feedback;//文章的评论数
	private String arcurl;//文章网页的地址
	
	public ArticleList() {
		// TODO Auto-generated constructor stub
	}
	
	public ArticleList(String id, String title, String senddate,
			String shorttitle, String typeid, String typename, String litpic,
			String feedback, String arcurl) {
		super();
		this.id = id;
		this.title = title;
		this.senddate = senddate;
		this.shorttitle = shorttitle;
		this.typeid = typeid;
		this.typename = typename;
		this.litpic = litpic;
		this.feedback = feedback;
		this.arcurl = arcurl;
	}

	@Override
	public String toString() {
		return "ArticleList [id=" + id + ", title=" + title + ", senddate="
				+ senddate + ", shorttitle=" + shorttitle + ", typeid="
				+ typeid + ", typename=" + typename + ", litpic=" + litpic
				+ ", feedback=" + feedback + ", arcurl=" + arcurl + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSenddate() {
		return senddate;
	}

	public void setSenddate(String senddate) {
		this.senddate = senddate;
	}

	public String getShorttitle() {
		return shorttitle;
	}

	public void setShorttitle(String shorttitle) {
		this.shorttitle = shorttitle;
	}

	public String getTypeid() {
		return typeid;
	}

	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	public String getLitpic() {
		return litpic;
	}

	public void setLitpic(String litpic) {
		this.litpic = litpic;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public String getArcurl() {
		return arcurl;
	}

	public void setArcurl(String arcurl) {
		this.arcurl = arcurl;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((arcurl == null) ? 0 : arcurl.hashCode());
		result = prime * result
				+ ((feedback == null) ? 0 : feedback.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((litpic == null) ? 0 : litpic.hashCode());
		result = prime * result
				+ ((senddate == null) ? 0 : senddate.hashCode());
		result = prime * result
				+ ((shorttitle == null) ? 0 : shorttitle.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((typeid == null) ? 0 : typeid.hashCode());
		result = prime * result
				+ ((typename == null) ? 0 : typename.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ArticleList other = (ArticleList) obj;
		if (arcurl == null) {
			if (other.arcurl != null)
				return false;
		} else if (!arcurl.equals(other.arcurl))
			return false;
		if (feedback == null) {
			if (other.feedback != null)
				return false;
		} else if (!feedback.equals(other.feedback))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (litpic == null) {
			if (other.litpic != null)
				return false;
		} else if (!litpic.equals(other.litpic))
			return false;
		if (senddate == null) {
			if (other.senddate != null)
				return false;
		} else if (!senddate.equals(other.senddate))
			return false;
		if (shorttitle == null) {
			if (other.shorttitle != null)
				return false;
		} else if (!shorttitle.equals(other.shorttitle))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (typeid == null) {
			if (other.typeid != null)
				return false;
		} else if (!typeid.equals(other.typeid))
			return false;
		if (typename == null) {
			if (other.typename != null)
				return false;
		} else if (!typename.equals(other.typename))
			return false;
		return true;
	}

	
}
