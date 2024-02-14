package org.exercises.employee;

import jakarta.persistence.*;
import org.exercises.employee.HibernateConfig;

import java.util.List;

public class Main {
    static EntityManagerFactory entityManagerFactory;
    public static void main(String[] args) {
        entityManagerFactory = HibernateConfig.getEntityManagerFactoryConfig();

        EntityManager em = entityManagerFactory.createEntityManager();

        // Getting all employees
        TypedQuery<Employee> query = em.createQuery("SELECT a FROM Employee a", Employee.class);
        List<Employee> listOfEmployees = query.getResultList();
        System.out.println("\n----------------------------");
        System.out.println("\nAll Employees: ");
        listOfEmployees.forEach(System.out::println);




        // Getting employess with salary above 60000
        TypedQuery<Employee> query1 = em.createQuery("SELECT a FROM Employee a WHERE a.salary >60000", Employee.class);
        List<Employee> listOfEmployeesWithAbove60000 = query1.getResultList();
        System.out.println("\n----------------------------");
        System.out.println("\nEmployees with 60000+ salary: ");
        listOfEmployeesWithAbove60000.forEach(System.out::println);



        // Getting employees from the Marketing department
        TypedQuery<Employee> query2 = em.createQuery("SELECT a FROM Employee a WHERE a.department = 'Marketing'", Employee.class);
        List<Employee> listOfEmployeesFromMarketing = query2.getResultList();
        System.out.println("\n----------------------------");
        System.out.println("\nEmployees from Marketing: ");
        listOfEmployeesFromMarketing.forEach(System.out::println);




        // Getting employees who's name starts with J
        TypedQuery<Employee> query3 = em.createQuery("SELECT a FROM Employee a WHERE a.firstName LIKE 'J%'", Employee.class);
        List<Employee> listOfEmployeesStartWithJ = query3.getResultList();
        System.out.println("\n----------------------------");
        System.out.println("\nEmployees starting with J: ");
        listOfEmployeesStartWithJ.forEach(System.out::println);


        // Me trying something
        TypedQuery<Employee> query4 = em.createQuery("SELECT a FROM Employee a WHERE a.firstName LIKE '%en%'", Employee.class);
        List<Employee> listOfEmployeesWithEN = query4.getResultList();
        System.out.println("\n----------------------------");
        System.out.println("\nEmployees having 'en' in name: ");
        listOfEmployeesWithEN.forEach(System.out::println);





    }
}
