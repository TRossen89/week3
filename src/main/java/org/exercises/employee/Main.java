package org.exercises.employee;

import jakarta.persistence.*;
import org.exercises.employee.HibernateConfig;

import java.util.List;

public class Main {
    static EntityManagerFactory entityManagerFactory;
    public static void main(String[] args) {
        entityManagerFactory = HibernateConfig.getEntityManagerFactoryConfig();

        EntityManager em = entityManagerFactory.createEntityManager();


        TypedQuery<Employee> query = em.createQuery("SELECT a FROM Employee a", Employee.class);
        List<Employee> listOfEmployees = query.getResultList();
        listOfEmployees.forEach(System.out::println);



    }
}
