package Chapter3_5_Applications;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/*
*   Modify LookupCSV to associate with each key all values that appear in a key-value pair with that key in the input
*   (not just the most recent, as in the associative-array abstraction).
*
* */
public class Ex12_ModifiedLookupCSV {
    public static void main(String[] args) {
        In in = new In(args[0]);
        int keyField = Integer.parseInt(args[1]);
        //int valField = 0;
        Ex09_BSTWithDuplicateKeys<String, String > st = new Ex09_BSTWithDuplicateKeys<>();
        String[] val;
        while (in.hasNextLine()){
            String line = in.readLine();
            String[] tokens = line.split(",");
            int lengthOfArrays = tokens.length;
            val = new String[lengthOfArrays];
            String key = tokens[keyField];
            //StdOut.println("The length of tokens " + lengthOfArrays);
            for (int i = 1; i < lengthOfArrays; i++){
                val[i] = tokens[i];
                st.put(;
            }
        }
        while (!StdIn.isEmpty()){
            String query = StdIn.readString();
            if (st.contains(query)){
                StdOut.println(query);
                StdOut.println("  " + st.get(query));
            }
        }
    }
}
