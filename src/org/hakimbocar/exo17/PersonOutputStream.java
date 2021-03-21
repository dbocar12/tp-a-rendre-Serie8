package org.hakimbocar.exo17;

import org.hakimbocar.model.Person;

import java.io.*;
import java.util.List;

public class PersonOutputStream extends FileOutputStream {

    String name;
    public PersonOutputStream(String name) throws FileNotFoundException {
        super(name);
        this.name = name;
    }

    public void writeFields(List<Person> people) {

        File file = new File(this.name);
        int size = people.size();

        try (FileOutputStream fos = new FileOutputStream(file);
             DataOutputStream dos = new DataOutputStream(fos)) {

            dos.writeInt(size); // write the size first
            for (Person person : people) {
                dos.write(exo17.personToBytes.apply(person));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("[OK] File written successfully");
    }

    public  void writePeople(List<Person> people) {

        File file = new File(this.name);
        try (OutputStream os = new FileOutputStream(file);
             ObjectOutputStream oos = new ObjectOutputStream(os)) {

            oos.writeObject(people);

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("[OK] File written successfully");
    }
}
