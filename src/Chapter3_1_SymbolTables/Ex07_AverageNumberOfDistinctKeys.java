package Chapter3_1_SymbolTables;


import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/*
*   What is the average number of distinct keys that FrequencyCounter will find among N random nonnegative integer
*   less than 1,000, for N = 10, 10^2, 10^3, 10^4, 10^5 and 10^6?
*
* */
public class Ex07_AverageNumberOfDistinctKeys {
    public static void main(String[] args) {
        int totalDistinct = 0;
        int distinct = 0;
        ST<Integer, Integer> st = new ST<>();
        Ex07_AverageNumberOfDistinctKeys averageNumberOfDistinctKeys = new Ex07_AverageNumberOfDistinctKeys();
        for (int m = 1; m < 7; m++){
            int[] keys = averageNumberOfDistinctKeys.generateRandomArray(m);
           /* for (int n = 0; n < Math.pow(10, m); n++){
                st.put(keys[n], n);
            }*/
            for (int i = 0; i < Math.pow(10, m); i++){
                if (!st.contains(keys[i])){
                    st.put(keys[i], 1);
                    distinct++;
                }
                else {
                    st.put(keys[i], st.get(keys[i]) + 1);
                }
            }
            totalDistinct += distinct;
        }
        StdOut.println("The average number: " + totalDistinct / (10 + Math.pow(10, 2) + Math.pow(10, 3) + Math.pow(10, 4)
         + Math.pow(10, 5) + Math.pow(10, 6)));
    }
    public int[] generateRandomArray(int N){
        int max =(int)Math.pow(10, N);
        int[] keys = new int[max];
        for (int i = 0; i < max; i++){
            keys[i] = StdRandom.uniform(1, 1000);
        }
        return keys;
    }
}
