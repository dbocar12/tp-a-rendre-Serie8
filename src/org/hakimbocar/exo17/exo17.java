package org.hakimbocar.exo17;
import org.hakimbocar.model.Person;

import java.io.*;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class exo17 {
    static Function<Person,byte[]> personToBytes = (person) -> {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             DataOutputStream dos = new DataOutputStream(bos)) {

            dos.writeUTF(person.getLastName());
            dos.writeUTF(person.getFirstName());
            dos.writeInt(person.getAge());
            return bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new byte[0];
    };

    public static void main(String[] args) throws IOException {
        /*
        * Question : 1.a
        * ByteArrayOutputStream class stream creates a buffer in memory
        * and all the data sent to the stream is stored in the buffer.
        * Question : 1.b
        * DataOutputStream lets us write the primitives types to an output source
        *
         */
        // Let's have a look how personToBytes works by making a simple Test
        Person p = new Person("Bocar","DIALLO",22);
        byte[] personBytes = personToBytes.apply(p);
        String fileName = "files/exo17PersonBytes.bin";
        System.out.println("Print the written bytes:");
        System.out.println(MessageFormat.format("personBytes= {0}", personBytes));
        InputStream is = new ByteArrayInputStream(personBytes);
        DataInputStream dis = new DataInputStream(is);
        String firstName = dis.readUTF();
        String  lastName= dis.readUTF();
        int  age= dis.readInt();
        System.out.println("Print the conversion of the bytes:");
        System.out.println(firstName+" "+lastName+" "+age);
        System.out.println("===============================================");

        // create a List of Person to test the method writeFields from PersonOutputStream
        List<Person> people = Arrays.asList(
                new Person("Diallo","Bocar",22),
                new Person("Ben Taarit","Hakim",23),
                new Person("Micheal","Jackson",43),
                new Person("Ariane","Jackson",22)
                );

        try (PersonOutputStream personOutput = new PersonOutputStream(fileName)) {
            personOutput.writeFields(people);
        }
        List<Person> people1;
        System.out.println("===============================================");
        try (PersonInputStream personInput = new PersonInputStream(fileName)) {
            people1 = personInput.readFields();
        }
        people1.forEach(System.out::println);

    }

}
