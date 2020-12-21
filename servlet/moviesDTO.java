package movies;

public class moviesDTO {
	public moviesDTO(String actor_code, String actor_fname, String actor_lname, String movie_code, String movie_title,
			String dir_code, String dir_fname, String dir_lname, String pro_code, String pro_fname, String pro_lname) {
		super();
		this.actor_code = actor_code;
		this.actor_fname = actor_fname;
		this.actor_lname = actor_lname;
		this.movie_code = movie_code;
		this.movie_title = movie_title;
		this.dir_code = dir_code;
		this.dir_fname = dir_fname;
		this.dir_lname = dir_lname;
		this.pro_code = pro_code;
		this.pro_fname = pro_fname;
		this.pro_lname = pro_lname;
	}
	private String actor_code;
	private String actor_fname;
	private String actor_lname;
	
	private String movie_code;
	private String movie_title;
	
	private String dir_code;
	private String dir_fname;
	private String dir_lname;
	
	private String pro_code;
	private String pro_fname;
	private String pro_lname;
	
	public String getActor_code() {
		return actor_code;
	}
	public void setActor_code(String actor_code) {
		this.actor_code = actor_code;
	}
	public String getActor_fname() {
		return actor_fname;
	}
	public void setActor_fname(String actor_fname) {
		this.actor_fname = actor_fname;
	}
	public String getActor_lname() {
		return actor_lname;
	}
	public void setActor_lname(String actor_lname) {
		this.actor_lname = actor_lname;
	}
	public String getMovie_code() {
		return movie_code;
	}
	public void setMovie_code(String movie_code) {
		this.movie_code = movie_code;
	}
	public String getMovie_title() {
		return movie_title;
	}
	public void setMovie_title(String movie_title) {
		this.movie_title = movie_title;
	}
	public String getDir_code() {
		return dir_code;
	}
	public void setDir_code(String dir_code) {
		this.dir_code = dir_code;
	}
	public String getDir_fname() {
		return dir_fname;
	}
	public void setDir_fname(String dir_fname) {
		this.dir_fname = dir_fname;
	}
	public String getDir_lname() {
		return dir_lname;
	}
	public void setDir_lname(String dir_lname) {
		this.dir_lname = dir_lname;
	}
	public String getPro_code() {
		return pro_code;
	}
	public void setPro_code(String pro_code) {
		this.pro_code = pro_code;
	}
	public String getPro_fname() {
		return pro_fname;
	}
	public void setPro_fname(String pro_fname) {
		this.pro_fname = pro_fname;
	}
	public String getPro_lname() {
		return pro_lname;
	}
	public void setPro_lname(String pro_lname) {
		this.pro_lname = pro_lname;
	}
	@Override
	public String toString() {
		return "moviesDTO [actor_code=" + actor_code + ", actor_fname=" + actor_fname + ", actor_lname=" + actor_lname
				+ ", movie_code=" + movie_code + ", movie_title=" + movie_title + ", dir_code=" + dir_code
				+ ", dir_fname=" + dir_fname + ", dir_lname=" + dir_lname + ", pro_code=" + pro_code + ", pro_fname="
				+ pro_fname + ", pro_lname=" + pro_lname + "]";
	}
}
