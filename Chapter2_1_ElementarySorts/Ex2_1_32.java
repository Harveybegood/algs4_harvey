package Chapter2_1_ElementarySorts;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

import java.awt.*;
import java.util.Random;

/*
*   Plot running times. Writes a client that uses StdDraw to plot the average running times of the algorithm for random
*   inputs and various of the array size. You may add one or two more command-line arguments. Strive to design a useful
*   tool.
*
* */
@SuppressWarnings("unchecked")
public class Ex2_1_32 {
    public enum SortType{
        TypeOfSelection, TypeOfInsertion, TypeOfShell
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
    public static void insertion(Comparable[] a){
        int N = a.length;
        for (int i = 1; i < N; i++){
            for (int j = i; j > 0 && a[j].compareTo(a[j-1]) < 0; j--){
                Comparable temp = a[j];
                a[j] = a[j-1];
                a[j-1] = temp;
            }
        }
    }
    public static void shell(Comparable[] a){
        int N = a.length;
        int increment = 1;
        while (increment < N / 3){increment = increment * 3 + 1;}
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
    public static double time(Comparable[] a, SortType sortType){
        Stopwatch timer = new Stopwatch();
        if (sortType == SortType.TypeOfSelection){
            selection(a);
        }else if (sortType == SortType.TypeOfInsertion){
            insertion(a);
        }else if (sortType == SortType.TypeOfShell){
            shell(a);
        }
        return timer.elapsedTime();
    }
    public static double timeRandomInput(SortType sortType, int arrayLength){
        double total = 0.0;
        Comparable[] array = new Comparable[arrayLength];
        for (int experiment = 0; experiment < 10; experiment++){
            for (int i = 0; i < arrayLength; i++){
                array[i] = StdRandom.uniform();
            }
            total += time(array, sortType);
        }
        return total;
    }
    public static void plotRunningTime(SortType sortType){
        StdDraw.setXscale(-.5, 10000);
        StdDraw.setYscale(-.5, 10);
        StdDraw.setPenRadius(.003);
        Random random = new Random();
        for (int i = 0; true; i++){
            int arrayLength = random.nextInt(10000) + 1000;
            if (sortType == SortType.TypeOfSelection){
                StdDraw.setPenColor(Color.GREEN);
                double total = timeRandomInput(SortType.TypeOfSelection, arrayLength);
                StdDraw.point(i, total / arrayLength);
            }else if (sortType == SortType.TypeOfInsertion){
                StdDraw.setPenColor(Color.black);
                double total = timeRandomInput(SortType.TypeOfInsertion, arrayLength);
                StdDraw.point(i, total / arrayLength);
            }else if (sortType == SortType.TypeOfShell){
                StdDraw.setPenColor(Color.red);
                double total = timeRandomInput(SortType.TypeOfShell, arrayLength);
                StdDraw.point(i, total / arrayLength);
            }
        }

    }

    public static void main(String[] args) {
        plotRunningTime(SortType.TypeOfSelection);
        plotRunningTime(SortType.TypeOfInsertion);
        plotRunningTime(SortType.TypeOfShell);
    }
}
