package Chapter3_5_Applications;

import Chapter3_3_BalancedSearchTrees.RedBlackBST;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/*
*   Modify LookupCSV to make a program RangeLookupCSV that takes two key values from the standard input and prints all key-value
*   pairs in the .csv file such that the key falls within the range specified.
*
* */
public class Ex13_RangeLookupCSV {
    public static void main(String[] args) {
        RedBlackBST<String, String> st = new RedBlackBST<>();
        In in = new In(args[0]);
        int keyField = Integer.parseInt(args[1]);
        int valField = Integer.parseInt(args[2]);
        while (in.hasNextLine()){
            String line = in.readLine();
            String[] tokens = line.split(",");
            String key = tokens[keyField];
            String val = tokens[valField];
            st.put(key, val);
        }
        while (!StdIn.isEmpty()){
            String query = StdIn.readString();
            String query1 = StdIn.readString();
            for (String key : st.keys(query, query1)){
                StdOut.println(key + " " + st.get(key));
            }
        }
    }
}

/*              key1    key2
*               AAA     ATA
*               key                 value

                AAA                 Lys
                AAC                 Ala
                AAG                 Lys
                AAT                 Ala
                ACA                 Thr
                ACC                 Thr
                ACG                 Thr
                ACT                 Thr
                AGA                 Arg
                AGC                 Ser
                AGG                 Arg
                AGT                 Ser
                ATA                 Ile
*
*
* */