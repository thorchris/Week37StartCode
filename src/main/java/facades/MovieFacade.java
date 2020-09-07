package facades;

import entities.Movie;
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
    
    public Movie getMovieById(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            Query query2 = em.createQuery("Select e FROM Movie e WHERE e.id = :id");
            query2.setParameter("id", id);
            Movie emp = (Movie) query2.getSingleResult();
            return emp;
        } finally {
            em.close();
        }
    }
    
        public Movie getMovieByName(String title) {
        EntityManager em = emf.createEntityManager();
        try {
            Query query2 = em.createQuery("Select e FROM Movie e WHERE e.title = :title");
            query2.setParameter("title", title);
            Movie emp = (Movie) query2.getSingleResult();
            return emp;
        } finally {
            em.close();
        }
    }
        
        public List<Movie> getAllMovies(){
        EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<Movie> query = em.createQuery("Select movie from Movie movie", Movie.class);
            return query.getResultList();
        } finally{
            em.close();
        }
    }
        
        public Movie createMovie(int year, String title, String actors){
        Movie employee = new Movie(year, title, actors);
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(employee);
            em.getTransaction().commit();
            return employee;
        } finally {
            em.close();
        }
    }

}
