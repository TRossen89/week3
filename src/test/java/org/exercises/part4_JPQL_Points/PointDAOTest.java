package org.exercises.part4_JPQL_Points;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PointDAOTest {


    EntityManagerFactory emf;
    EntityManager em;
    PointDAO pointDAO;

    @BeforeAll
    void beforeAll(){
        emf = HibernateConfig.getEntityManagerFactoryConfig();
        em = emf.createEntityManager();
        pointDAO = new PointDAO(emf);
    }


    @Test
    void getAllPointsTest(){

        // Arrange
        int numberOfPoints = 10;

        // If no points has been stored in DB yet:
        pointDAO.storePoints(numberOfPoints);

        List<Point> expected = new ArrayList<>();
        for (int i = 0; i < numberOfPoints; i++) {
            Point p = new Point(i+1, i, i);
            expected.add(p);
        }

        // Act
        List<Point> actual = pointDAO.getAllPoints();

        // Assert
        assertEquals(expected.get(0).getY(), actual.get(0).getY());
        assertEquals(expected.get(1).getX(), actual.get(1).getX());
        assertEquals(expected.get(2).getX(), actual.get(2).getX());
        assertEquals(expected.get(3).getY(), actual.get(3).getY());

    }

    @AfterAll
    void closing(){
        em.close();
        emf.close();
    }

}
