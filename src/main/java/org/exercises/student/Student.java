package org.exercises.student;


import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.hibernate.type.descriptor.java.CharacterArrayJavaType;

import java.io.IOException;

@Entity
@Table(name = "student")
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_mame", nullable = false)
    private String lastName;

    @Column(name = "age")
    private int age;

    @Column(name = "email")
    private String email;

    public Student(String firstName, String lastName, int age, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
    }


    @PrePersist
    public void verifyEmail() throws RuntimeException{


        try{
            String[] partToVerify = this.email.split("@");

            if(partToVerify[partToVerify.length-1].equals("hotmail.com")||partToVerify[partToVerify.length-1].equals("gmail.com")){
                System.out.println("Email is verified");
            }
            else {
                throw new RuntimeException("Email not verified");
            }

        }
        catch (Exception e){
            throw new RuntimeException("Email not verified");
        }


    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "\nFirst name:" + firstName +
                "\nLast name:" + lastName;
    }
}
