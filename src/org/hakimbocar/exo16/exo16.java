package org.hakimbocar.exo16;
import org.hakimbocar.model.Person;
import java.util.List;
import java.util.function.Function;

public class exo16 {

    Function<String, Person> lineToPerson = line -> {
        String[] elements = line.split(", ");
        String lastName = elements[0];
        String firstName = elements[1];
        int age = Integer.parseInt(elements[2]);
        return new Person(firstName, lastName, age);
    };

    Function<Person, String> personToLine = person -> person.getLastName() + ", " +
            person.getFirstName() + ", " +
            person.getAge();

    public static void  main(String[] args) {
        String fileName = "files/exo16TextPersons.txt";
        String fileNameWriter = "files/exo16TextPersonsCreated";
        PersonReader reader = new PersonReader();
        PersonWriter writer = new PersonWriter();
        List<Person> persons = reader.read(fileName);
        System.out.println("Print the List of persons: ");
        persons.forEach(System.out::println);
        System.out.println("==============================================");
        writer.write(persons,fileNameWriter);

    }

}
