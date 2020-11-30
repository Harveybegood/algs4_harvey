package Chapter2_1_ElementarySorts;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;
@SuppressWarnings("unchecked")
public class SortCompare {
    public static class Insertion{
        public static void sort(Comparable[] a){
            for (int i = 1; i < a.length; i++){
                for (int j = i; j > 0 && (a[j].compareTo(a[j-1]) < 0); j--){
                    Comparable temp = a[j];
                    a[j] = a[j-1];
                    a[j-1] = temp;
                }
            }
        }
    }
    public static class Selection{
        public static void sort(Comparable[] a){
            for (int i = 0; i < a.length; i++){
                int min = i;
                for (int j = i + 1; j < a.length; j++){
                    if (a[j].compareTo(a[min]) < 0){
                        min = j;
                    }
                }
                Comparable temp =  a[i];
                a[i] = a[min];
                a[min] = temp;
            }
        }
    }
/*    public static class Shell{
        public static void sort(Comparable[] a){
            int N = a.length;
            int h = 1;
            while (h < N/3) h = 3 * h + 1;
            while (h >=1){
                for (int i = h; i < N; i++){
                    for (int j = i; i >= h && a[j].compareTo(a[j-h]) < 0; j -= h){
                        Comparable temp = a[j];
                        a[j] = a[j-h];
                        a[j-h] = temp;
                    }
                }
                h = h/3;
            }
        }
    }*/
    public static double time(String alg, Double[] a){
        Stopwatch timer = new Stopwatch();
        if (alg.equals("Insertion")) Insertion.sort(a);
        if (alg.equals("Selection")) Selection.sort(a);
        //if (alg.equals("Shell")) Shell.sort(a);
        return timer.elapsedTime();
    }
    public static double timeRandomInput(String alg, int N, int T){
        double total = 0.0;
        Double[] a = new Double[N];
        for (int i = 0; i < T; i++){
            for (int j = 0; j < N; j++){
                a[j] = StdRandom.uniform();
            }
            total += time(alg, a);
        }
        return total;
    }

    public static void main(String[] args) {
        String alg1 = args[0];
        String alg2 = args[1];
        //String alg3 = args[2];
        int N = Integer.parseInt(args[2]);
        int T = Integer.parseInt(args[3]);
        double t1 = timeRandomInput(alg1, N, T);
        double t2 = timeRandomInput(alg2, N, T);
        //double t3 = timeRandomInput(alg3, N, T);
        StdOut.printf("From %d random Double %s is", N, alg1);
        StdOut.printf("%.1f times faster than %s\n", t1/t2, alg2);
        /*StdOut.printf("From %d random Double %s is", N, alg3);
        StdOut.printf("%.1f times faster than $s\n", t3/t1, alg1);*/
    }
}
