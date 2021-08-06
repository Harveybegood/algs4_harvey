package Chapter3_5_Applications;

import edu.princeton.cs.algs4.*;

import java.util.*;

/*
*   Fully indexed CSV. Implement an ST client FUllLookupCSV that builds an array of ST objects(one for each field),
*   with a test client that allows the user to specify the key and value fields in each query.
*
*       What is "Fully indexed CSV"??
*
*       the implement below that I copy from
*       https://github.com/Harveybegood/algorithms-sedgewick-wayne/
*       blob/master/src/chapter3/section5/Exercise22_FullyIndexedCSV.java
*
* */
@SuppressWarnings("unchecked")
public class Ex22_FullLookupCSV {
    ST<String, List<String>>[] st;

    public void buildArrayOfST(String inputFiles){
        In in = new In(inputFiles);
        boolean isFirstLine = true;
        while (in.hasNextLine()){
            String[] fields = in.readLine().split(",");
            int arrayLength = fields.length;
            if (isFirstLine){
                st = (ST<String, List<String>>[]) new ST[arrayLength];
                // build an array of ST objects
                for (int i = 0; i < st.length; i++){
                    st[i] = new ST<>();
                }
                isFirstLine = false;
            }
            for (int key1 = 0; key1 < fields.length; key1++){
                List<String> values = new ArrayList<>();
                for (int key2 = 0; key2 < fields.length; key2++){
                    if (key1 != key2){
                        values.add(fields[key2]);
                    }
                }
                st[key1].put(fields[key1], values);
            }
        }
    }
    public String testClient(int key1, int key2, String query){
        if (key1 < 0 || key2 < 0){
            throw new IllegalArgumentException("Argument must be equal or larger than 0");
        }
        if (key1 == key2){
            return query;
        }
        else if (key1 < key2){
            key2--;
        }

        return st[key1].get(query).get(key2);

    }
    public static void main(String[] args) {
        Ex22_FullLookupCSV fullLookupCSV = new Ex22_FullLookupCSV();
        fullLookupCSV.buildArrayOfST(args[0]);
        while (StdIn.hasNextLine()){
            String line = StdIn.readLine();
            String[] words = line.split(" ");
            int key1 = Integer.parseInt(words[0]);
            int key2 = Integer.parseInt(words[1]);
            String query = words[2];
            StdOut.println(fullLookupCSV.testClient(key1, key2, query));
        }
    }
}
