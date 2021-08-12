package Chapter3_5_Applications;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.HashSet;


public class DeDup {
    public static void main(String[] args) {
        DeDup deDup = new DeDup();
        deDup.experiment();
    }
    public void experiment(){
        for (int t = 0; t < 10; t++){
            int count1 = theNumberOfDuplicates(generateValues((int)Math.pow(10,3), (int)Math.pow(10, 1.5)));
            StdOut.printf("%6d %6.2f %6.2f\n", count1, count1 * 1.0/(int)Math.pow(10, 3), 1 - Math.pow(Math.E, -2));
            int count2 = theNumberOfDuplicates(generateValues((int)Math.pow(10,3), (int)Math.pow(10, 3)));
            StdOut.printf("%6d %6.2f %6.2f\n", count2, count2 * 1.0/(int)Math.pow(10, 3), 1 - Math.pow(Math.E, -1));
            int count3 = theNumberOfDuplicates(generateValues((int)Math.pow(10,3), (int)Math.pow(10, 6)));
            StdOut.printf("%6d %6.2f %6.2f\n", count3, count3 * 1.0/(int)Math.pow(10, 3), 1 - Math.pow(Math.E, -1/2));
            int count4 = theNumberOfDuplicates(generateValues((int)Math.pow(10,4), (int)Math.pow(10, 2)));
            StdOut.printf("%6d %6.2f %6.2f\n", count4, count4 * 1.0/(int)Math.pow(10, 4), 1 - Math.pow(Math.E, -2));
            int count5 = theNumberOfDuplicates(generateValues((int)Math.pow(10,4), (int)Math.pow(10, 4)));
            StdOut.printf("%6d %6.2f %6.2f\n", count5, count5 * 1.0/(int)Math.pow(10, 4), 1 - Math.pow(Math.E, -1));
            int count6 = theNumberOfDuplicates(generateValues((int)Math.pow(10,4), (int)Math.pow(10, 8)));
            StdOut.printf("%6d %6.2f %6.2f\n", count6, count6 * 1.0/(int)Math.pow(10, 4), 1 - Math.pow(Math.E, -1/2));
            int count7 = theNumberOfDuplicates(generateValues((int)Math.pow(10,5), (int)Math.pow(10, 2.5)));
            StdOut.printf("%6d %6.2f %6.2f\n", count7, count7 * 1.0/(int)Math.pow(10, 5), 1 - Math.pow(Math.E, -2));
            int count8 = theNumberOfDuplicates(generateValues((int)Math.pow(10,5), (int)Math.pow(10, 5)));
            StdOut.printf("%6d %6.2f %6.2f\n", count8, count8 * 1.0/(int)Math.pow(10, 5), 1 - Math.pow(Math.E, -1));
            int count9 = theNumberOfDuplicates(generateValues((int)Math.pow(10,5), (int)Math.pow(10, 10)));
            StdOut.printf("%6d %6.2f %6.2f\n", count9, count9 * 1.0/(int)Math.pow(10, 5), 1 - Math.pow(Math.E, -1/2));
            int count10 = theNumberOfDuplicates(generateValues((int)Math.pow(10,6), (int)Math.pow(10, 3)));
            StdOut.printf("%6d %6.2f %6.2f\n", count10, count10 * 1.0/(int)Math.pow(10, 6), 1 - Math.pow(Math.E, -2));
            int count11 = theNumberOfDuplicates(generateValues((int)Math.pow(10,6), (int)Math.pow(10, 6)));
            StdOut.printf("%6d %6.2f %6.2f\n", count11, count11 * 1.0/(int)Math.pow(10, 6), 1 - Math.pow(Math.E, -1));
            int count12 = theNumberOfDuplicates(generateValues((int)Math.pow(10,6), (int)Math.pow(10, 12)));
            StdOut.printf("%6d %6.2f %6.2f\n", count12, count12 * 1.0/(int)Math.pow(10, 6), 1 - Math.pow(Math.E, -1/2));
            StdOut.println();
            StdOut.println();
        }
    }
    HashSet<Integer> set = new HashSet<>();
    // find the number of duplicates
    public int theNumberOfDuplicates(int[] a){
        //Quick.sort(a);
        int count = 0;
        for (int i = 0; i < a.length; i++){
            if (set.contains(a[i])){
                count++;
            }
            else {
                set.add(a[i]);
            }
        }
        return count;
    }
    // generate random values
    public int[] generateValues(int N, int M){
        int[] a = new int[N];
        for (int i = 0; i < N; i++){
            a[i] = StdRandom.uniform(0, M - 1);
        }
        return a;
    }
}
