package facades;

import dto.MovieDTO;
import entities.Movie;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class MovieFacade {

    private static MovieFacade instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private MovieFacade() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static MovieFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new MovieFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    
        public MovieDTO getMovieById(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            Query query2 = em.createQuery("Select e FROM Movie e WHERE e.id = :id");
            query2.setParameter("id", id);
            Movie movie = (Movie) query2.getSingleResult();
            MovieDTO movDTO = new MovieDTO(movie);
            return movDTO;
        } finally {
            em.close();
        }
    }
    
        public MovieDTO getMovieByName(String title) {
        EntityManager em = emf.createEntityManager();
        try {
            Query query2 = em.createQuery("Select e FROM Movie e WHERE e.title = :title");
            query2.setParameter("title", title);
            Movie movie = (Movie) query2.getSingleResult();
            MovieDTO movDTO = new MovieDTO(movie);
            return movDTO;
        } finally {
            em.close();
        }
    }

    public List<MovieDTO> getAllMovies() {
        EntityManager em = emf.createEntityManager();
        try {
            Query query2 = em.createQuery("Select e from Movie e");
            List<Movie> movieList = query2.getResultList();
            List<MovieDTO> movieDTOList = new ArrayList();
            for (Movie movie : movieList) {
                MovieDTO movDTO = new MovieDTO(movie);
                movieDTOList.add(movDTO);    
            }
            return movieDTOList;
            
        } finally {
            em.close();
        }
    }
        

}
