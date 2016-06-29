package de.cachaca.learn.java.java8extensions;

import static org.junit.Assert.assertEquals;

import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;


public class LambdaTest {

    @Test
    public void testLambdaAsPredicate() {
        List<String> list = Arrays.asList("Pierre", "Silke", "Jonas", "Robin", "Nora");
        List<String> girls = list.stream()
            .filter(entry -> "Silke".equals(entry) || "Nora".equals(entry))
            .collect(Collectors.toList());
        assertEquals(2, girls.size());

        List<String> girlsOldFashioned = new ArrayList<>();
        for (String entry : girls) {
            if ("Silke".equals(entry) || "Nora".equals(entry)) {
                girlsOldFashioned.add(entry);
            }
        }
        assertEquals(2, girlsOldFashioned.size());
    }

    @Test
    public void testLambdaAsConsumer() {
        List<String> list = Arrays.asList("Pierre", "Silke", "Jonas", "Robin", "Nora");
        list.forEach(entry -> System.out.println(entry));
    }

    @Test
    public void testLambdaWithException() throws Exception {
        Collections.emptyList().forEach(entry -> { 
            try {
                Files.size(null);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
    }

    @Test(expected = IllegalStateException.class)
    public void testStreamsTerminalfunctionMakesTheStreamUnusableForIntermediateFunctions() {
        List<String> list = Arrays.asList("Pierre", "Silke", "Jonas", "Robin", "Nora");
        Stream<String> listAsStream = list.stream();
        List<String> girls = listAsStream
            .filter(entry -> "Silke".equals(entry) || "Nora".equals(entry))
            .collect(Collectors.toList());
        listAsStream.filter(entry -> "Pierre".equals(entry));
    }
    
}
