package org.hakimbocar.exo18;
import org.hakimbocar.exo17.PersonInputStream;
import org.hakimbocar.exo17.PersonOutputStream;
import org.hakimbocar.model.Person;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class exo18 {

    public static void main(String[] args) {

        List<Person> persons = Arrays.asList(
                new Person("Diallo", "Bocar", 22),
                new Person("Ben Taarit", "Hakim", 23),
                new Person("Michael", "Jackson", 45),
                new Person("Stephane", "Hawking", 54)
        );
        /* Comments: Serialization
        * Question 18.a
        * We need to <serialize> objects
        * it is about creating a portable representation of an object
        * a portable representation (i.e: XML, JSON, binary Representation)
        *
        *      ObjectOutputStream is used to write Java objects in binary files
        *
        * Question 18.b
        * We need to make the class Person serializable by implementing
        * Serializable Interface into Person class
        *       public class Person implements Serializable
        * Optional : We could add a serialVersionUID
         */
        String fileName = "files/exo18Persons.bin";

        try (PersonOutputStream personOutputStream1 = new PersonOutputStream(fileName)) {
            personOutputStream1.writePeople(persons);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("========================================");
        try (PersonInputStream personInputStream1 = new PersonInputStream(fileName)) {
            List<Person> people = personInputStream1.readPeople();
            System.out.println("Print the people list:");
            people.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("========================================");

    }


}

