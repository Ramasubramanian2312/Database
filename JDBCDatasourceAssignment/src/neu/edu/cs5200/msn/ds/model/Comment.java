package neu.edu.cs5200.msn.ds.model;

import java.sql.Date;

public class Comment {
	private int id;
	private String comment;
	private Date date;
	private String userid;
	private int movieid;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public int getMovieid() {
		return movieid;
	}

	public void setMovieid(int movieid) {
		this.movieid = movieid;
	}

	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Comment(String comment, Date date) {
		super();
		this.comment = comment;
		this.date = date;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
