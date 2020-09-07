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
import org.junit.jupiter.api.Test;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class FacadeExampleTest {

    private static EntityManagerFactory emf;
    private static MovieFacade facade;

    public FacadeExampleTest() {
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
        try {
            em.getTransaction().begin();
            em.persist(new Movie(1995, "Fed Film 1", "Fed actor 1"));
            em.persist(new Movie(2005, "Fed Film 2", "Fed actor 2"));
            em.persist(new Movie(2015, "Fed Film 3", "Fed actor 3"));
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    // TODO: Delete or change this method 
//    @Test
//    public void testGetMovieByID() {
//        MovieFacade mf = MovieFacade.getFacadeExample(emf);
//        Long id = 1;
//        Long expResult = 1;
//        Long result = mf.getMovieById(id).getId();
//        
//        assertEquals(expResult, result);
//    }
    
        @Test
    public void testGetMovieByName() {
        MovieFacade cf = MovieFacade.getFacadeExample(emf);
        String name = "Fed Film 1";
        String expResult = "Fed Film 1";
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
