package test;

import edu.princeton.cs.algs4.StdOut;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FilesNameAsArgument {
    public static final String filepath = "/usr/local/algs4/src/algs4-data/tinyTale.txt";
    public static void main(String[] args) {
        //Files.readAllLines(Paths.get("/usr/local/algs4/src/algs4-data/tinyTale.txt"))
        /*List<String> lines = new ArrayList<>();
        try{
            lines = Files.readAllLines(Paths.get("/usr/local/algs4/src/algs4-data/tinyTale.txt"));
        }catch (IOException e){
            StdOut.println(e.getMessage());
        }
        String[] words = new String[80];
        for (String line : lines){
            String[] wordsInCurrentLine = line.split("\\s+");
            int i = 0;
            for (String wordInCurrentLine : wordsInCurrentLine){
                words[i] = wordInCurrentLine;
                i++;
            }
        }
        for (int i = 0; i < 80; i++){
            StdOut.println(words[i]);
        }*/

        List<String> lines = new ArrayList<>();
        try{
            lines = Files.readAllLines(Paths.get(filepath));
        }catch (IOException e){
            StdOut.println(e.getMessage());
        }
        List<String> wordsList = new ArrayList<>();
        for (String line : lines){
            String[] wordsInCurrentLine = line.split(" ");
            for (String wordInCurrentLine : wordsInCurrentLine){
                if (wordInCurrentLine.equals("")){
                    continue;
                }
                wordsList.add(wordInCurrentLine);
            }
        }
        String[] words = new String[wordsList.size()];
        wordsList.toArray(words);

        for (String s : words){
            StdOut.println(s);
        }
        StdOut.println(words.length);
        StdOut.println();
        StdOut.println();
        for (String s : wordsList){
            StdOut.println(s);
        }
        StdOut.println(wordsList.size());
        for (int i = 0; i < words.length; i++){
            if (!words[i].equals(wordsList.get(i))){
                StdOut.println("Not equal");
                return;
            }
        }
        StdOut.println("Equal");
    }
}
