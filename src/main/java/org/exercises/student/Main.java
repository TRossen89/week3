package org.exercises.student;

import jakarta.persistence.EntityManagerFactory;
import org.exercises.unicorn.Unicorn;

import java.util.List;

public class Main {

    static EntityManagerFactory entityManagerFactory;
    public static void main(String[] args) {

        entityManagerFactory = HibernateConfig.getEntityManagerFactoryConfig();

        // Transient state
        Student student = new Student("Tobias", "Rossen", 34, "tobiasTest@)hotmail.com");
        // Managed and then detached state
        createStudent(student);

        // Transient state
        Student student1 = new Student("Clarence", "Seedorf", 47, "seedorfTest@)hotmail.com");
        // Managed and then detached state
        createStudent(student1);

        // Transient state
        Student student2 = new Student("Nana", "Juhlin", 16, "nanaTest@)hotmail.com");
        // Managed and then detached state
        createStudent(student2);

        // Managed and then detached state
        updateStudent(1,student1);

        // DB, managed, detached, transient ??
        Student studentRead1 = readStudent(1);
        Student studentRead2 = readStudent(2);

        System.out.println("Students read (they are supposed to be the same): \n" + studentRead1 +"\n"+studentRead2);

        // Managed and then removed
        deleteStudent(1);

        // DB, managed, detached, transient
        List<Student> listOfStudents = readAllStudents();

        System.out.println("All students: \n");
        // Transient entities
        listOfStudents.forEach(System.out::println);


    }

    public static void createStudent(Student student){

        try (var em = entityManagerFactory.createEntityManager()) {
            em.getTransaction().begin();
            // Managed state
            em.persist(student);

            // Detached state
            em.getTransaction().commit();
        }
    }


    public static Student readStudent(int id){


        try (var em = entityManagerFactory.createEntityManager()) {

            // DB, managed, detached?
            return em.find(Student.class, id);
        }
    }


    public static void updateStudent(int id, Student student){

        try (var em = entityManagerFactory.createEntityManager()) {
            em.getTransaction().begin();

            // DB and then managed
            var studentInDB = em.find(Student.class, id);

            //Managed
            studentInDB.setFirstName(student.getFirstName());
            studentInDB.setLastName(student.getLastName());
            studentInDB.setAge(student.getAge());
            studentInDB.setEmail(student.getEmail());

            // Managed, DB
            em.merge(studentInDB);

            // Detached
            em.getTransaction().commit();
        }
    }

    public static void deleteStudent(int id){

        try (var em = entityManagerFactory.createEntityManager()) {
            em.getTransaction().begin();
            // DB and then managed
            var student = em.find(Student.class, id);

            // Managed
            em.remove(student);

            // Removed
            em.getTransaction().commit();
        }
    }

    public static List<Student> readAllStudents(){

        try (var em = entityManagerFactory.createEntityManager()) {
            // DB, managed
            var query = em.createQuery("SELECT a FROM Student a", Student.class);

            // Managed and detached
            return query.getResultList();
        }
    }

}
