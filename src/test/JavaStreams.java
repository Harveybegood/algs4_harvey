package test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class JavaStreams {
    public static void main(String[] args) throws IOException{
        Stream<String> rows1 = Files.lines(Paths.get("src/test/data1.txt"));
        int rowCount = (int)rows1.
                map(x -> x.split(",")).
                filter(x -> x.length == 3).
                count();
        System.out.println(rowCount + " rows. ");
        rows1.close();
    }
}
