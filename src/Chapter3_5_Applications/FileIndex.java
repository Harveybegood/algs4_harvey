package Chapter3_5_Applications;

import edu.princeton.cs.algs4.*;

import java.io.File;

public class FileIndex {
    public static void main(String[] args) {
        // key = word, value = set of files containing that word
        ST<String, SET<File>> st = new ST<>();
        // create inverted index of all files
        StdOut.println("Indexing files");
        for (String filename : args){
            File file = new File(filename);
            In in = new In(file);
            while (!in.isEmpty()){
                String word = in.readString();
                if (!st.contains(word)){
                    st.put(word, new SET<>());
                }
                SET<File> set = st.get(word);
                set.add(file);
                //st.get(word).add(file);
            }
        }
        // read queries from standard input, one per line
        while (!StdIn.isEmpty()){
            String query = StdIn.readString();
            if (st.contains(query)){
                SET<File> set = st.get(query);
                for (File file : set){
                    StdOut.println(" " + file.getName());
                }
            }
        }
    }
}
