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

    private static final MovieFacade FACADE = MovieFacade.getFacadeExample(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo(){
        return "{\"msg\":\"Hello World\"}";
    }
    
    @Path("count")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String count() {
        long count = FACADE.getMovieCount();
        return "{\"count\":"+count+"}";
    }

    
    @GET
    @Path("all")
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllMovies() {
        EntityManager em = EMF.createEntityManager();
        try {
            MovieFacade mf = MovieFacade.getFacadeExample(EMF);
            List<MovieDTO> movieList = mf.getAllMovies();
            return GSON.toJson(movieList);
        } finally {
            em.close();
        }
    }

    @GET
    @Path("id/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getMovieById(@PathParam("id") int id) {
        EntityManager em = EMF.createEntityManager();
        try {
            MovieFacade mf = MovieFacade.getFacadeExample(EMF);
            MovieDTO dtoMov = mf.getMovieById(id);
            return GSON.toJson(dtoMov);
        } finally {
            em.close();
        }
    }

    @Path("title/{title}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getMovieByName(@PathParam("title") String title) {
        EntityManager em = EMF.createEntityManager();
        try {
            MovieFacade mf = MovieFacade.getFacadeExample(EMF);
            MovieDTO dtoMov = mf.getMovieByName(title);
            return GSON.toJson(dtoMov);
        } finally {
            em.close();
        }
    }
    
    @GET
    @Path("/populate")
    @Produces({MediaType.APPLICATION_JSON})
    public String populate(){
        FACADE.populateDB();
        return "{\"msg\":\"3 rows added\"}";
    }
}
