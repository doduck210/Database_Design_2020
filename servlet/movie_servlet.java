package movies;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import movies.moviesDTO;
import movies.moviesDAO;


@WebServlet("/movie_servlet")
public class movie_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("DAO doget()호출!");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		moviesDAO dao=new moviesDAO();
		
		String mode = request.getParameter("mode");
		
		switch(mode) {
			case "Read_Movie":
				List<moviesDTO> movieList=dao.readMovie(request.getParameter("title"));
				int count=0;
				for(moviesDTO listMovie : movieList) {
					if(count==1) {
						out.println("<br>Actor2's name : " + listMovie.getActor_fname()+ " "+listMovie.getActor_lname() 
								+ " </h2>");
					}
					else {
						out.println("<h1>Info of " + request.getParameter("title") + "</h1><br>"
								+ "<h2> Director's name : "+listMovie.getDir_fname()+" "+ listMovie.getDir_lname() + 
									"<br> Actor1's name : " + listMovie.getActor_fname()+" "+ listMovie.getActor_lname());
						count+=1;
					}
					}
						break;
			case "Read_Actor":
				List<moviesDTO> actorList=dao.readActor(request.getParameter("title"));
				out.println("<h1>Actors in " + request.getParameter("title") + "</h1><br>");
				for(moviesDTO listMovie : actorList) {
	
						out.println("<h2>Actor's name : " + listMovie.getActor_fname()+ " "+listMovie.getActor_lname() 
								+ " </h2>");
					}
						break;
			case "Read_Producers":
				List<moviesDTO> proList=dao.readProducers(request.getParameter("title"));
				out.println("<h1>Producer(s) in " + request.getParameter("title") + "</h1><br>");
				for(moviesDTO listMovie : proList) {
	
						out.println("<h2>Producer's name : " + listMovie.getPro_fname()+ " "+listMovie.getPro_lname() 
								+ " </h2>");
					}
						break;
			case "Read_Diract":
				List<moviesDTO> diractList=dao.readDiract();
				for(moviesDTO listMovie : diractList) {
	
						out.println("<h2>Name : " + listMovie.getActor_fname()+ " "+listMovie.getActor_lname() 
								+ " </h2>");
					}
						break;
						
			case "Read_Proact":
				List<moviesDTO> proactList=dao.readProact();
				for(moviesDTO listMovie : proactList) {
	
						out.println("<h2>Name : " + listMovie.getActor_fname()+ " "+listMovie.getActor_lname() 
								+ " </h2>");
					}
						break;
			case "Create_Actor":
				moviesDTO cactor=dao.createActor(request.getParameter("actorCode"),request.getParameter("fName")
						, request.getParameter("lName"), request.getParameter("movieCode"));

				out.println("<h2>" + cactor.getActor_fname()+ " "+cactor.getActor_lname() 
								+ " has been completely inserted to database! </h2>");

						break;
			case "Create_Producer":
				moviesDTO cpro=dao.createProducer(request.getParameter("proCode"),request.getParameter("fName")
						, request.getParameter("lName"), request.getParameter("movieCode"),request.getParameter("actCode"));

				out.println("<h2>" + cpro.getPro_fname()+ " "+cpro.getPro_lname() 
								+ " has been completely inserted to database! </h2>");

						break;
			case "Create_Movie":
				moviesDTO cmovie=dao.createMovie(request.getParameter("act1Code"), request.getParameter("act1fname"),
						request.getParameter("act1lname"), request.getParameter("act2Code"), request.getParameter("act2fname")
						, request.getParameter("act2lname"), request.getParameter("movieCode"), request.getParameter("title"), 
						request.getParameter("dirCode"), request.getParameter("dirfname"), request.getParameter("dirlname"), 
						request.getParameter("proCode"), request.getParameter("profname"), request.getParameter("prolname"));

				out.println("<h2>" + cmovie.getMovie_title()
								+ " has been completely inserted to database! </h2>");

						break;
			case "Update_Diract":
				moviesDTO udiract=dao.updateDiract(request.getParameter("dirCode"), request.getParameter("actCode"));

				out.println("<h2>" + udiract.getActor_code()+ " is now connected with "+udiract.getDir_code()
								+ " ! </h2>");

						break;
			case "Update_Proact":
				moviesDTO uproact=dao.updateProact(request.getParameter("proCode"), request.getParameter("actCode"));

				out.println("<h2>" + uproact.getActor_code()+ " is now connected with "+uproact.getPro_code()
								+ " ! </h2>");
			
						break;
			case "Delete_Actor":
				moviesDTO dactor=dao.deleteActor(request.getParameter("actCode"));
				out.println("<h2>" + dactor.getActor_code()+ " is succesfully deleted! </h2>");
			
						break;
			case "Delete_Producer":
				moviesDTO dpro=dao.deleteProducer(request.getParameter("proCode"));
				out.println("<h2>" + dpro.getPro_code()+ " is succesfully deleted! </h2>");
				
						break;
			default:
				break;
		}
		out.close();
	}

}
