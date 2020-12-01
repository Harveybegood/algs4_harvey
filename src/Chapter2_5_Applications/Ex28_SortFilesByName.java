package Chapter2_5_Applications;

import edu.princeton.cs.algs4.StdOut;

import java.io.File;
import java.util.Arrays;

/*
*   Sort files by name. Write a program FileSorter that takes the name of a directory as a command-line argument and
*   prints out all of the files in the current directory, sorted by file name. Hint: Use the File data type
*
* */
public class Ex28_SortFilesByName {
    public static void main(String[] args) {
        File directory = new File(args[0]);
        if (!directory.isDirectory()){
            StdOut.println(args[0] + " is not a directory");
        }
        if (!directory.exists()){
            StdOut.println(args[0] + " does not exist");
        }
        File[] files = directory.listFiles();
        if (files == null){
            StdOut.println("Could not read files");
        }
        Arrays.sort(files);
        for (int i = 0; i < files.length; i++){
            StdOut.println(files[i].getName());
        }
    }
}
