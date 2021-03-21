package org.hakimbocar.exo17;

import org.hakimbocar.model.Person;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PersonInputStream extends FileInputStream {
    String name;
    public PersonInputStream(String name) throws FileNotFoundException {
        super(name);
        this.name = name;
    }

   public List<Person> readFields() {
       File file = new File(this.name);

        try (FileInputStream fis = new FileInputStream(file);
             InputStream is = new ByteArrayInputStream(fis.readAllBytes());
             DataInputStream dis = new DataInputStream(is)){

            List<Person> people = new ArrayList<>();
            int  size= dis.readInt();
            for (int i = 0;i<size;i++) {
                people.add(new Person(dis.readUTF(),dis.readUTF(),dis.readInt()));
            }
            return people;
       } catch (IOException e) {
           e.printStackTrace();
       }

        return Collections.emptyList();
   }
    public List<Person> readPeople() {

        File file = new File(this.name);
        try (InputStream is = new FileInputStream(file);
             ObjectInputStream ois = new ObjectInputStream(is)) {

            @SuppressWarnings("unchecked")
            List<Person> people = (List<Person>)ois.readObject();
            return people;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }


}
