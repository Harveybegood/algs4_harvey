package Chapter3_5_Applications;

import edu.princeton.cs.algs4.*;

/*
*   Indexing. Study a client like LookupIndex in a scenario where performance matters. Specifically, design a query-generation scenario instead
*   of taking commands from standard input, and run performance tests for large inputs and large numbers of queries.
*
* */
public class Ex33_Indexing {

    public void lookupIndex(String inputFiles, int largeNumberOfQueries){
        LinearProbingHashST<String, Queue<String>> st = new LinearProbingHashST<>();
        LinearProbingHashST<String, Queue<String>> ts = new LinearProbingHashST<>();
        In in = new In(inputFiles);
        while (in.hasNextLine()){
            String[] words = in.readLine().split(",");
            String key = words[0];
            for (int i = 1; i < words.length; i++){
                String val = words[i];
                if (!st.contains(key)){
                    st.put(key, new Queue<>());
                }
                if (!ts.contains(key)){
                    ts.put(val, new Queue<>());
                }
                st.get(key).enqueue(val);
                ts.get(val).enqueue(key);
            }
        }
        for (int i = 0; i < largeNumberOfQueries; i++){
            for (String key : st.keys()){
                if (st.contains(key)){
                    for (String s : st.get(key)){

                    }
                }
            }
            for (String val : ts.keys()){
                if (ts.contains(val)){
                    for (String s : ts.get(val)){

                    }
                }
            }
        }
    }
    public void runPerformanceTest(String inputFiles){
        StdOut.printf("%19s %8s\n", "largeNumberOfQueries", "timeCost");
        int[] largeNumberOfQueries = {1000, 10000, 100000, 1000000, 10000000, 100000000};
        for (int i = 0; i < 7; i++){
            Stopwatch timer = new Stopwatch();
            lookupIndex(inputFiles, largeNumberOfQueries[i]);
            StdOut.printf("%19d %8.2f\n", largeNumberOfQueries[i], timer.elapsedTime());
        }
    }

    public static void main(String[] args) {
        Ex33_Indexing indexing = new Ex33_Indexing();
        String inputFiles = args[0];
        indexing.runPerformanceTest(inputFiles);
    }
}


/*
*           largeNumberOfQueries timeCost
                           1000     0.10
                          10000     0.10
                         100000     0.73
                        1000000     4.10
                       10000000    48.23
                      100000000   415.67
*
*
* */