package movies;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import movies.moviesDTO;

public class moviesDAO { 
	private static String dburl="jdbc:mysql://127.0.0.1:13306/MOVIES?allowPublicKeyRetrieval=true&serverTimezone=UTC";
	private static String dbUser="root";
	private static String dbpasswd="root";
	
	public List<moviesDTO> readMovie(String title) {
		List<moviesDTO> list = new ArrayList<>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		String sql = "Select DIRECTOR_FNAME, DIRECTOR_LNAME, ACTOR_FNAME, ACTOR_LNAME from MOVIE_INFO where TITLE = ? ";
	
		try(Connection conn=DriverManager.getConnection(dburl,dbUser,dbpasswd);PreparedStatement ps=conn.prepareStatement(sql)){
			ps.setString(1,title);
			try(ResultSet rs= ps.executeQuery()){
				while (rs.next()) {
					String dirfname = rs.getString(1);
					String dirlname =rs.getString(2);
					String actorfname=rs.getString(3);
					String actorlname=rs.getString(4); 
					moviesDTO movie = new moviesDTO("1",actorfname,actorlname," "," "," ",dirfname,dirlname," "," "," ");
					list.add(movie);
				}
				} catch(Exception e) {
				e.printStackTrace();
				}
			
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		return list;
		}
	
	public List<moviesDTO> readActor(String title) {
		List<moviesDTO> list = new ArrayList<>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		String sql = "Select FNAME, LNAME from ACTORS_IN_MOVIE where TITLE = ? ";
	
		try(Connection conn=DriverManager.getConnection(dburl,dbUser,dbpasswd);PreparedStatement ps=conn.prepareStatement(sql)){
			ps.setString(1,title);
			try(ResultSet rs= ps.executeQuery()){
				while (rs.next()) {
					String actorfname = rs.getString(1);
					String actorlname =rs.getString(2);
					moviesDTO movie = new moviesDTO("1",actorfname,actorlname," "," "," "," "," "," "," "," ");
					list.add(movie);
				}
				} catch(Exception e) {
				e.printStackTrace();
				}
			
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		return list;
		}
	
	public List<moviesDTO> readDiract() {
		List<moviesDTO> list = new ArrayList<>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		String sql = "Select FNAME, LNAME from ACTOR_DIRECTOR";
	
		try(Connection conn=DriverManager.getConnection(dburl,dbUser,dbpasswd);PreparedStatement ps=conn.prepareStatement(sql)){

			try(ResultSet rs= ps.executeQuery()){
				while (rs.next()) {
					String actfname = rs.getString(1);
					String actlname =rs.getString(2);
					moviesDTO movie = new moviesDTO(" ",actfname,actlname," "," "," "," "," "," "," "," ");
					list.add(movie);
				}
				} catch(Exception e) {
				e.printStackTrace();
				}
			
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		return list;
		}
	public List<moviesDTO> readProducers(String title) {
		List<moviesDTO> list = new ArrayList<>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		String sql = "Select FNAME, LNAME from PRODUCERS_IN_MOVIE where TITLE = ? ";
	
		try(Connection conn=DriverManager.getConnection(dburl,dbUser,dbpasswd);PreparedStatement ps=conn.prepareStatement(sql)){
			ps.setString(1,title);
			try(ResultSet rs= ps.executeQuery()){
				while (rs.next()) {
					String profname = rs.getString(1);
					String prolname =rs.getString(2);
					moviesDTO movie = new moviesDTO(" "," "," "," "," "," "," "," "," ",profname,prolname);
					list.add(movie);
				}
				} catch(Exception e) {
				e.printStackTrace();
				}
			
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		return list;
		}
	
	public List<moviesDTO> readProact() {
		List<moviesDTO> list = new ArrayList<>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		String sql = "Select FNAME, LNAME from ACTOR_PRODUCER";
	
		try(Connection conn=DriverManager.getConnection(dburl,dbUser,dbpasswd);PreparedStatement ps=conn.prepareStatement(sql)){

			try(ResultSet rs= ps.executeQuery()){
				while (rs.next()) {
					String actfname = rs.getString(1);
					String actlname =rs.getString(2);
					moviesDTO movie = new moviesDTO(" ",actfname,actlname," "," "," "," "," "," "," "," ");
					list.add(movie);
				}
				} catch(Exception e) {
				e.printStackTrace();
				}
			
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		return list;
		}
	
	public moviesDTO createActor(String actorcode,String fname,String lname,String moviecode) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		String sql = "Insert into ACTOR values (?,?,?) ";
		
		try(Connection conn=DriverManager.getConnection(dburl,dbUser,dbpasswd);PreparedStatement ps=conn.prepareStatement(sql)){
			
			ps.setString(1, actorcode);
			ps.setString(2,fname);
			ps.setString(3, lname);
			
			ps.executeUpdate();
			
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		
		sql = "Insert into PERFORMS_IN values (?,?) ";
		
		try(Connection conn=DriverManager.getConnection(dburl,dbUser,dbpasswd);PreparedStatement ps=conn.prepareStatement(sql)){
			
			ps.setString(1, moviecode);
			ps.setString(2, actorcode);
			
			ps.executeUpdate();
			
			} catch(Exception ex) {
				ex.printStackTrace();
				sql="delete from ACTOR where ACTOR_CODE=? ";
				try(Connection conn=DriverManager.getConnection(dburl,dbUser,dbpasswd);PreparedStatement ps=conn.prepareStatement(sql)){
					ps.setString(1,actorcode);
					ps.executeUpdate();
					moviesDTO blank = new moviesDTO(" ","No Movie Found","Error<!--"," "," "," "," "," "," "," "," ");
					return blank;
				}
				catch(Exception eex) {
					eex.printStackTrace();
				}
			}
		
		moviesDTO movie = new moviesDTO(actorcode,fname,lname,moviecode," "," "," "," "," "," "," ");
		return movie;
		}
	
	public moviesDTO createProducer(String procode,String fname,String lname,String moviecode,String actcode) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		String sql = "Insert into PRODUCER (PRODUCER_CODE,FNAME,LNAME,ACTOR_CODE) values (?,?,?,?) ";
		if(actcode.isEmpty()) {
			sql = "Insert into PRODUCER (PRODUCER_CODE,FNAME,LNAME) values (?,?,?) ";
		}
		try(Connection conn=DriverManager.getConnection(dburl,dbUser,dbpasswd);PreparedStatement ps=conn.prepareStatement(sql)){
			
			ps.setString(1, procode);
			ps.setString(2,fname);
			ps.setString(3, lname);
			if(!actcode.isEmpty()) {
			ps.setNString(4, actcode);
			}
			
			ps.executeUpdate();
			
			} catch(Exception ex) {
				ex.printStackTrace();
				moviesDTO blank = new moviesDTO(" "," "," "," "," "," "," "," "," ","Wrong Actor Code","Error<!--");
				return blank;
			}
		
		sql = "Insert into PRODUCES values (?,?) ";
		
		try(Connection conn=DriverManager.getConnection(dburl,dbUser,dbpasswd);PreparedStatement ps=conn.prepareStatement(sql)){
			
			ps.setString(1, moviecode);
			ps.setString(2, procode);
			
			ps.executeUpdate();
			
			} catch(Exception ex) {
				ex.printStackTrace();
				sql="delete from PRODUCER where PRODUCER_CODE=? ";
				try(Connection conn=DriverManager.getConnection(dburl,dbUser,dbpasswd);PreparedStatement ps=conn.prepareStatement(sql)){
					ps.setString(1,procode);
					ps.executeUpdate();
					moviesDTO blank = new moviesDTO(" "," "," "," "," "," "," "," "," ","No Movie Found","Error<!--");
					return blank;
				}
				catch(Exception eex) {
					eex.printStackTrace();
				}
			}
		
		moviesDTO movie = new moviesDTO(" "," "," ",moviecode," "," "," "," ",procode,fname,lname);
		return movie;
		}
	
	public moviesDTO createMovie(String act1Code,String act1fname,String act1lname, String act2Code, String act2fname, String act2lname
								,String movieCode,String title, String dirCode,String dirfname, String dirlname, String proCode, 
								String profname, String prolname) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		String sql = "Insert into ACTOR values (?,?,?) ";

		try(Connection conn=DriverManager.getConnection(dburl,dbUser,dbpasswd);PreparedStatement ps=conn.prepareStatement(sql)){
			
			ps.setString(1, act1Code);
			ps.setString(2,act1fname);
			ps.setString(3, act1lname);
			ps.executeUpdate();
			
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		
		try(Connection conn=DriverManager.getConnection(dburl,dbUser,dbpasswd);PreparedStatement ps=conn.prepareStatement(sql)){
			
			ps.setString(1, act2Code);
			ps.setString(2,act2fname);
			ps.setString(3, act2lname);
			ps.executeUpdate();
			
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		
		sql = "Insert into DIRECTOR (DIRECTOR_CODE,FNAME,LNAME) values (?,?,?) ";

		try(Connection conn=DriverManager.getConnection(dburl,dbUser,dbpasswd);PreparedStatement ps=conn.prepareStatement(sql)){
			
			ps.setString(1, dirCode);
			ps.setString(2, dirfname);
			ps.setString(3, dirlname);
			ps.executeUpdate();
			
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		
		sql = "Insert into PRODUCER (PRODUCER_CODE,FNAME,LNAME) values (?,?,?) ";

		try(Connection conn=DriverManager.getConnection(dburl,dbUser,dbpasswd);PreparedStatement ps=conn.prepareStatement(sql)){
			
			ps.setString(1, proCode);
			ps.setString(2, profname);
			ps.setString(3, prolname);
			ps.executeUpdate();
			
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		
		sql = "Insert into MOVIE values (?,?,?) ";

		try(Connection conn=DriverManager.getConnection(dburl,dbUser,dbpasswd);PreparedStatement ps=conn.prepareStatement(sql)){
			
			ps.setString(1, movieCode);
			ps.setString(2, title);
			ps.setString(3, dirCode);
			ps.executeUpdate();
			
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		
		sql = "Insert into PERFORMS_IN values (?,?) ";

		try(Connection conn=DriverManager.getConnection(dburl,dbUser,dbpasswd);PreparedStatement ps=conn.prepareStatement(sql)){
			
			ps.setString(1, movieCode);
			ps.setString(2, act1Code);
			ps.executeUpdate();
			
			} catch(Exception ex) {
				ex.printStackTrace();
			}

		try(Connection conn=DriverManager.getConnection(dburl,dbUser,dbpasswd);PreparedStatement ps=conn.prepareStatement(sql)){
			
			ps.setString(1, movieCode);
			ps.setString(2, act2Code);
			ps.executeUpdate();
			
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		
		sql = "Insert into LEAD_ROLE values (?,?) ";

		try(Connection conn=DriverManager.getConnection(dburl,dbUser,dbpasswd);PreparedStatement ps=conn.prepareStatement(sql)){
			
			ps.setString(1, movieCode);
			ps.setString(2, act1Code);
			ps.executeUpdate();
			
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		try(Connection conn=DriverManager.getConnection(dburl,dbUser,dbpasswd);PreparedStatement ps=conn.prepareStatement(sql)){
			
			ps.setString(1, movieCode);
			ps.setString(2, act2Code);
			ps.executeUpdate();
			
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		
		sql = "Insert into PRODUCES values (?,?) ";

		try(Connection conn=DriverManager.getConnection(dburl,dbUser,dbpasswd);PreparedStatement ps=conn.prepareStatement(sql)){
			
			ps.setString(1, movieCode);
			ps.setString(2, proCode);
			ps.executeUpdate();
			
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		
		
		moviesDTO movie = new moviesDTO(" "," "," "," ",title," "," "," "," "," "," ");
		return movie;
		}
	
	public moviesDTO updateDiract(String dirCode,String actCode) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		String sql = "Update DIRECTOR set ACTOR_Code = ?  where DIRECTOR_CODE = ?";

		try(Connection conn=DriverManager.getConnection(dburl,dbUser,dbpasswd);PreparedStatement ps=conn.prepareStatement(sql)){
			
			ps.setString(1, actCode);
			ps.setString(2, dirCode);
			
			ps.executeUpdate();
			
			} catch(Exception ex) {
				ex.printStackTrace();
				moviesDTO blank = new moviesDTO("Wrong Code Error<!--"," "," "," "," "," "," "," "," "," "," ");
				return blank;
			}
		
		moviesDTO movie = new moviesDTO(actCode," "," "," "," ",dirCode," "," "," "," "," ");
		return movie;
		}
	
	public moviesDTO updateProact(String proCode,String actCode) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		String sql = "Update PRODUCER set ACTOR_Code = ?  where PRODUCER_CODE = ?";

		try(Connection conn=DriverManager.getConnection(dburl,dbUser,dbpasswd);PreparedStatement ps=conn.prepareStatement(sql)){
			
			ps.setString(1, actCode);
			ps.setString(2, proCode);
			
			ps.executeUpdate();
			
			} catch(Exception ex) {
				ex.printStackTrace();
				moviesDTO blank = new moviesDTO("Wrong Code Error<!--"," "," "," "," "," "," "," "," "," "," ");
				return blank;
			}
		
		moviesDTO movie = new moviesDTO(actCode," "," "," "," "," "," "," ",proCode," "," ");
		return movie;
		}
	
	public moviesDTO deleteActor(String actCode) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		String sql = "select ACTOR_CODE from LEAD_ROLE where ACTOR_CODE = ?";

		try(Connection conn=DriverManager.getConnection(dburl,dbUser,dbpasswd);PreparedStatement ps=conn.prepareStatement(sql)){
			ps.setString(1,actCode);
			try(ResultSet rs= ps.executeQuery()){
					rs.next();
					String actorfname = rs.getString(1);
					if(!actorfname.isEmpty()) {
						moviesDTO error = new moviesDTO("Hey (s)he has lead role!<!--"," "," "," "," "," "," "," "," "," "," ");
						return error;
					}
				} catch(Exception e) {
				e.printStackTrace();
				}
			
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		
		sql = "Delete from ACTOR where ACTOR_CODE = ?";

		try(Connection conn=DriverManager.getConnection(dburl,dbUser,dbpasswd);PreparedStatement ps=conn.prepareStatement(sql)){
			
			ps.setString(1, actCode);
			
			ps.executeUpdate();
			
			} catch(Exception ex) {
				ex.printStackTrace();
				moviesDTO error = new moviesDTO("(S)he has another role!<!--"," "," "," "," "," "," "," "," "," "," ");
				return error;
			}
		
		moviesDTO movie = new moviesDTO(actCode," "," "," "," "," "," "," "," "," "," ");
		return movie;
		}
	
	public moviesDTO deleteProducer(String proCode) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		String sql = "select MOVIE_CODE from PRODUCES where PRODUCER_CODE=?;";

		try(Connection conn=DriverManager.getConnection(dburl,dbUser,dbpasswd);PreparedStatement ps=conn.prepareStatement(sql)){
			ps.setString(1,proCode);
			try(ResultSet rs= ps.executeQuery()){
				while (rs.next()) {
					String movCode = rs.getString(1);
					
					String sql1 = "select count(*) from PRODUCES where MOVIE_CODE= ? ";

					try(PreparedStatement pps=conn.prepareStatement(sql1)){
						
						pps.setString(1, movCode);
						try(ResultSet rrs= pps.executeQuery()){
							rrs.next();
								int count = rrs.getInt(1);
								if(count<2) {
									moviesDTO error = new moviesDTO(""," "," "," "," "," "," "," ","he is the only producer! <!--"," "," ");
									return error;
								}
							} catch(Exception e) {
							e.printStackTrace();
							}
						
						} catch(Exception ex) {
							ex.printStackTrace();
						}
					
				}
				} catch(Exception e) {
				e.printStackTrace();
				}
			
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		
		sql = "Delete from PRODUCER where PRODUCER_CODE = ? ";

		try(Connection conn=DriverManager.getConnection(dburl,dbUser,dbpasswd);PreparedStatement ps=conn.prepareStatement(sql)){
			
			ps.setString(1, proCode);
			
			ps.executeUpdate();
			
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		
		moviesDTO movie = new moviesDTO(" "," "," "," "," "," "," "," ",proCode," "," ");
		return movie;
		}
}