package Chapter1_4_AnalysisOfAlgorithms;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/*
*   Add an instance method howMany() to StaticSETofInts that finds the number of occurrences of a given key
*   in time proportional to log N in the worst case.
*
* */
public class Ex11 {
    public class StaticSETofInts{
        private int[] a;       // instance variables
        // constructor
        public StaticSETofInts(int[] keys){
            a = new int[keys.length];
            for (int i = 0; i < keys.length; i++){
                a[i] = keys[i];
            }
            Arrays.sort(a);
        }
        public boolean contains(int key){
            return rank(key) != -1;
        }
        private int rank(int key){
            int lo = 0;
            int hi = a.length - 1;
            while (lo < hi){
                int mid = lo + (hi - lo) / 2;
                if (key < a[mid]) hi = mid - 1;
                else if (key > a[mid]) lo = mid + 1;
                else return mid;
            }
            return -1;
        }
        private int recursiveRank(int key, int lo, int hi){
            int mid = lo + (hi - lo) / 2;
            if (lo > hi) return -1;
            if (a[mid] > key) recursiveRank(key, lo, mid - 1);
            else if (a[mid] < key) recursiveRank(key, mid + 1, hi);
            else
            {
                return mid;
            }
            return -1;
        }
        private int howMany(int key){
           int indexFromRank = rank(key);
           //if (indexFromRank != -1) return 0;
           int count;
           int previousIndex = indexFromRank;
           int currentPreviousIndex = previousIndex;
           int nextIndex = indexFromRank;
           int currentNextIndex = nextIndex;
           // find the smallest index of an element
           while (currentPreviousIndex != -1){
               currentPreviousIndex = recursiveRank(key, 0, currentPreviousIndex - 1);
               if (currentPreviousIndex != -1) previousIndex = currentPreviousIndex;
           }
           // find the highest index of an element
           while (currentNextIndex != -1){
               currentNextIndex = recursiveRank(key, currentNextIndex + 1, a.length - 1);
               if (currentNextIndex != -1) nextIndex = currentNextIndex;
           }
           count = nextIndex - previousIndex + 1;
           return count;
        }
    }

    public static void main(String[] args) {
        int[] w = StdIn.readAllInts();
        Ex11 ex11 = new Ex11();
        StaticSETofInts set = ex11.new StaticSETofInts(w);
        while (!StdIn.isEmpty()){
            int key = StdIn.readInt();
            if (!set.contains(key)){
                StdOut.println(key);
            }
        }
        StdOut.println("How many 2: " + set.howMany(2));
        StdOut.println("How many 10: " + set.howMany(10));
        StdOut.println("How many 12: " + set.howMany(12));
    }
}
