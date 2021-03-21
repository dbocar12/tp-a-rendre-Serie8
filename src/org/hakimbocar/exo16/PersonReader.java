package org.hakimbocar.exo16;
import org.hakimbocar.model.Person;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.Collections;
class PersonReader extends exo16 {

    public List<Person> read(String fileName) {

        File textPersons = new File(fileName);
        System.out.println(fileName+ " exists ? " + textPersons.exists());
        System.out.println("==============================================");
        try (Reader reader = new FileReader(textPersons);
             BufferedReader bufferedReader = new BufferedReader(reader)) {

            /*  Comments : Question 1 about Stream Interface and AutoCloseable Interface
             * To be able to open a resource in a try with resource pattern,
             * Our resource needs to implement a special interface called AutoCloseable
             * if we check BufferedReader class it extends Reader and we can see that indeed
             * it does implement AutoCloseable. It turns out that the stream interface
             * is an extension of BaseStream also implements AutoCloseable.
             * So in fact we can put this stream bellow (Question a)  directly in this try resource pattern
             * and thus our reader created by stream will be properly closed
             */

            // Question a
            Stream<String> myLines = bufferedReader.lines();
            // Question b
            Stream<String> linesWithoutComments= myLines.filter(line -> !line.startsWith("#"));

            // Question c

            /* Comments: if there is a buggy line on my file (ex: no separator)
             * if a catch an exception I'm going to return nothing (Stream.empty)
             * to avoid buggy stuff...
             * If it contains one person, that person will be added to the stream.
             * If the stream is empty nothing will be added to the stream
             */
            return linesWithoutComments
                    .flatMap(lineToP -> {
                        try {
                            return Stream.of(lineToPerson.apply(lineToP));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return Stream.empty();
                    })
                    .collect(Collectors.toList());

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return Collections.emptyList();

    }

}
