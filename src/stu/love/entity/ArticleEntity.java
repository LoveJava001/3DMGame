package stu.love.entity;

public class ArticleEntity extends ArticleList{

	private String body;//文章的 实体内容部分！
	
	public ArticleEntity() {
		// TODO Auto-generated constructor stub
	}
	
//	文章详情的构造函数
	public ArticleEntity(String id, String title, String senddate,
			String shorttitle, String typeid, String typename, String litpic,
			String feedback, String arcurl, String body) {
		super(id, title, senddate, shorttitle, typeid, typename, litpic,
				feedback, arcurl);
		this.body = body;
	}
	
	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	@Override
	public String toString() {
		return "ArticleEntity [body=" + body + ", toString()="
				+ super.toString() + ", getId()=" + getId() + ", getTitle()="
				+ getTitle() + ", getSenddate()=" + getSenddate()
				+ ", getShorttitle()=" + getShorttitle() + ", getTypeid()="
				+ getTypeid() + ", getTypename()=" + getTypename()
				+ ", getLitpic()=" + getLitpic() + ", getFeedback()="
				+ getFeedback() + ", getArcurl()=" + getArcurl() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((body == null) ? 0 : body.hashCode());
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
		ArticleEntity other = (ArticleEntity) obj;
		if (body == null) {
			if (other.body != null)
				return false;
		} else if (!body.equals(other.body))
			return false;
		return true;
	}

}
