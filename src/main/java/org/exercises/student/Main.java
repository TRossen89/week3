package org.exercises.student;

import jakarta.persistence.EntityManagerFactory;
import org.exercises.unicorn.Unicorn;

import java.util.List;

public class Main {

    static EntityManagerFactory entityManagerFactory;
    public static void main(String[] args) {

        entityManagerFactory = HibernateConfig.getEntityManagerFactoryConfig();

        Student student = new Student("Tobias", "Rossen", 34, "tobiasTest@)hotmail.com");
        createStudent(student);

        Student student1 = new Student("Clarence", "Seedorf", 47, "seedorfTest@)hotmail.com");
        createStudent(student1);

        Student student2 = new Student("Nana", "Juhlin", 16, "nanaTest@)hotmail.com");
        createStudent(student2);

        updateStudent(1,student1);

        Student studentRead1 = readStudent(1);
        Student studentRead2 = readStudent(2);

        System.out.println("Students read (they are supposed to be the same): \n" + studentRead1 +"\n"+studentRead2);

        deleteStudent(1);

        List<Student> listOfStudents = readAllStudents();

        System.out.println("All students: \n");
        listOfStudents.forEach(System.out::println);


    }

    public static void createStudent(Student student){

        try (var em = entityManagerFactory.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(student);
            em.getTransaction().commit();
        }
    }


    public static Student readStudent(int id){

        try (var em = entityManagerFactory.createEntityManager()) {
            return em.find(Student.class, id);
        }
    }


    public static void updateStudent(int id, Student student){

        try (var em = entityManagerFactory.createEntityManager()) {
            em.getTransaction().begin();

            var studentInDB = em.find(Student.class, id);
            studentInDB.setFirstName(student.getFirstName());
            studentInDB.setLastName(student.getLastName());
            studentInDB.setAge(student.getAge());
            studentInDB.setEmail(student.getEmail());

            em.merge(studentInDB);
            em.getTransaction().commit();
        }
    }

    public static void deleteStudent(int id){

        try (var em = entityManagerFactory.createEntityManager()) {
            em.getTransaction().begin();
            var student = em.find(Student.class, id);
            em.remove(student);
            em.getTransaction().commit();
        }
    }

    public static List<Student> readAllStudents(){

        try (var em = entityManagerFactory.createEntityManager()) {
            var query = em.createQuery("SELECT a FROM Student a", Student.class);
            return query.getResultList();
        }
    }

}
