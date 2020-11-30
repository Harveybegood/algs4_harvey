package Chapter2_1_ElementarySorts;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

import java.awt.*;


/*
*   Distribution. Write a client that enters into an infinite loop running sort() on arrays of the size given as
*   the third command-line argument, measures the time taken for each run, and uses StdDraw to plot the average running
*   times. A picture of the distribution of the running times should emerge.
*
* */
@SuppressWarnings("unchecked")
public class Ex2_1_33 {
   /* public enum SortType{
        shellSortType, SelectionSortType
    }*/
    public static void shell(Comparable[] a){
        int N = a.length;
        int increment = 1;
        while (increment < N / 3){
            increment = increment * 3 + 1;
        }
        while (increment >= 1){
            for (int i = increment; i < N; i++){
                for (int j = i; j >= increment && a[j].compareTo(a[j-increment]) < 0; j -= increment){
                    Comparable temp = a[j];
                    a[j] = a[j-increment];
                    a[j-increment] = temp;
                }
            }
            increment = increment / 3;
        }
    }
    public static void selection(Comparable[] a){
        int N = a.length;
        for (int i = 0; i < N; i++){
            int min = i;
            for (int j = i + 1; j < N; j++){
                if (a[j].compareTo(a[min]) < 0){
                    min = j;
                }
            }
            Comparable temp = a[i];
            a[i] = a[min];
            a[min] = temp;
        }
    }
    public static double time(Comparable[] a, String sortType){
        Stopwatch timer = new Stopwatch();
        if (sortType.equals("Selection")){
            selection(a);
        }else if (sortType.equals("Shell")){
            shell(a);
        }
        return timer.elapsedTime();
    }
    public static double timeRandomInput(String sortType, int arrayLength){
        //double timeEachRun = 0.0;
        Comparable[] array = new Comparable[arrayLength];
        for (int i = 0; i < arrayLength; i++){
            array[i] = StdRandom.uniform();
        }
        return time(array, sortType);
    }
    public static void main(String[] args) {
        String alg1 = args[0];
        String alg2 = args[1];
        StdDraw.setXscale(0, 500);
        StdDraw.setYscale(-1, 1);
        int N = Integer.parseInt(args[2]);
        for (int experiment = 1; true; experiment++){
            double TimeOfAlg1 = timeRandomInput(alg1, N);
            double TimeOfAlg2 = timeRandomInput(alg2, N);
            StdDraw.setPenRadius(.003);
            StdOut.printf("Round %d : Time Cost %.2f --- ", experiment, TimeOfAlg1);
            StdOut.printf("Round %d : Time Cost %.2f\n", experiment, TimeOfAlg2);
            StdDraw.setPenColor(Color.black);
            StdDraw.point(experiment, TimeOfAlg1 / experiment);
            StdDraw.point(experiment, TimeOfAlg2 / experiment);
            StdDraw.setPenRadius(.003);
            StdDraw.setPenColor(Color.BLUE);
            StdDraw.point(experiment, TimeOfAlg1);
        }
    }
}
