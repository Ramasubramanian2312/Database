package neu.edu.cs5200.msn.ds.model;

public class Cast {
	private int id;
	private String characterName;
	private int actorid;
	private int movieid;

	public Cast() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cast(String characterName) {
		super();
		this.characterName = characterName;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getActorid() {
		return actorid;
	}

	public void setActorid(int actorid) {
		this.actorid = actorid;
	}

	public int getMovieid() {
		return movieid;
	}

	public void setMovieid(int movieid) {
		this.movieid = movieid;
	}

	public String getCharacterName() {
		return characterName;
	}

	public void setCharacterName(String characterName) {
		this.characterName = characterName;
	}

}
