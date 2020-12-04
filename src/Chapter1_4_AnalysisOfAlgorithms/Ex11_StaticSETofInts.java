package Chapter1_4AnalysisOfAlgorithms;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/*
*   Add an instance method howMany() to StaticSETofInts(page 99)that finds the number of occurrence of a given key in
*   time proportional to logN in the worst case.
*
* */
public class Ex11_StaticSETofInts {
    public class StaticSETofInts{
        private int[] a;
        private int cnt;
        public StaticSETofInts(int[] keys){
            a = new int[keys.length];
            for (int i = 0; i < keys.length; i++){
                a[i] = keys[i];
            }
            Arrays.sort(a);
        }
        public StaticSETofInts(){}
        private int rank(int key){
            int lo = 0;
            int hi = a.length - 1;
            while (lo <= hi){
                int mid = lo + (hi - lo)/2;
                if (key < a[mid]){
                    hi = mid - 1;
                }
                else if (key > a[mid]){
                    lo = mid + 1;
                }
                else {
                    cnt++;
                    return mid;
                }
            }
            return -1;
        }
        public boolean contains(int key){
            return rank(key) != -1;
        }
        private int howMany(int key){
            //int cnt = 0;
            int[] w = StdIn.readAllInts();
            StaticSETofInts set = new StaticSETofInts(w);
            //int key = StdIn.readInt();
            return set.rank(key);
        }
    }

    public static void main(String[] args) {
        Ex11_StaticSETofInts staticSETofInts = new Ex11_StaticSETofInts();
        StaticSETofInts set = staticSETofInts.new StaticSETofInts();
        StdOut.println(set.howMany(100));
    }
}
