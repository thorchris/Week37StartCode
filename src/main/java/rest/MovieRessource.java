package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.MovieDTO;
import entities.Movie;
import utils.EMF_Creator;
import facades.MovieFacade;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("movie")
public class MovieRessource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    
    
    private static final MovieFacade FACADE =  MovieFacade.getFacadeExample(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
            
 @Path("all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllMovies() {
        EntityManager em = EMF.createEntityManager();
        try {
            List<MovieDTO> dtoList = new ArrayList();
            List<Movie> emp = FACADE.getAllMovies();
            for (Movie movie : emp) {
                MovieDTO empDTO = new MovieDTO(movie);
                dtoList.add(empDTO);
            }
            return new Gson().toJson(dtoList);
        } finally {
            em.close();
        }
    }
    
    @Path("id/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getMovieById(@PathParam("id") Long id) {
        EntityManager em = EMF.createEntityManager();
        try {
            Movie emp = FACADE.getMovieById(id);
            MovieDTO dtoEmp = new MovieDTO(emp);
            return new Gson().toJson(dtoEmp);
        } finally {
            em.close();
        }
    }
    
    @Path("title/{title}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getMovieByName(@PathParam("title") String title) {
        EntityManager em = EMF.createEntityManager();
        try {
            Movie emp = FACADE.getMovieByName(title);
            MovieDTO dtoEmp = new MovieDTO(emp);
            return new Gson().toJson(dtoEmp);
        } finally {
            em.close();
        }
    }  
}
