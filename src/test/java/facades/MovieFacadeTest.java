package facades;

import utils.EMF_Creator;
import entities.Movie;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class MovieFacadeTest {

    private static EntityManagerFactory emf;
    private static MovieFacade facade;

    public MovieFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
       emf = EMF_Creator.createEntityManagerFactoryForTest();
       facade = MovieFacade.getFacadeExample(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the script below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        Movie m1 = new Movie(1995, "Harry Potter", "J.K Rowling");
        Movie m2 = new Movie(2005, "Star Wars", "George Lucas");
        Movie m3 = new Movie(2015, "Once apon a time in Holly Wood", "Tarentino");
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Movie.deleteAllRows").executeUpdate();
            em.persist(m1);
            em.persist(m2);
            em.persist(m3);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }
    
        @Test
        public void testGetMovieByName() {
        MovieFacade cf = MovieFacade.getFacadeExample(emf);
        String name = "Harry Potter";
        String expResult = "Harry Potter";
        String result = cf.getMovieByName(name).getTitle();

        assertEquals(expResult, result);
    }
        
    
        @Test
        public void testgetAllMovies() {
        MovieFacade cf = MovieFacade.getFacadeExample(emf);
        int expResult = 3;
        int result = cf.getAllMovies().size();
        assertEquals(expResult, result);
    }
        
        

}
