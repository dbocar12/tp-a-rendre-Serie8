package org.hakimbocar.exo16;
import org.hakimbocar.model.Person;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class PersonWriter extends exo16{

    public void write(List<Person> people, String fileName) {
        File file = new File(fileName);
        try (Writer writer = new FileWriter(file);
             BufferedWriter bufferedWriter = new BufferedWriter(writer)) {
            bufferedWriter.write("# LastName, firstName, age"+"\n");
            for (Person p : people) {
                bufferedWriter.write(personToLine.apply(p));
                bufferedWriter.write("\r\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("[OK] Written successfully. \n");
    }
}
