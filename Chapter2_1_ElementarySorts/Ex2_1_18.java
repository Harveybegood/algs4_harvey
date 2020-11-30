package Chapter2_1_ElementarySorts;


import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.awt.*;

/*
*   Visual trace. Modify your solution to the previous exercise to make Insertion and Selection produce visual traces
*   such as those depicted in this section. Hints: Judicious use of setYscale() makes this problem easy, Extra credit:
*   Add the code necessary to produce red and gray color accents such as those in our figures.
*
* */
public class Ex2_1_18 {
    @SuppressWarnings("unchecked")
    public static void Selection(Comparable[] a){
        int N = a.length;
        for (int i = 0; i < N; i++){
            int min = i;
            for (int j = 0; j < N; j++){
                if (a[j].compareTo(a[min]) < 0){
                    min = j;
                }
            }
            draw(a, i, min, Ex2_1_17_1.SortType.SELECTION, a.length - i);
            Comparable temp = a[i];
            a[i] = a[min];
            a[min] = temp;
        }
    }
    @SuppressWarnings("unchecked")
    public static void Insertion(Comparable[] a){
        int N = a.length;
        for (int i = 1; i < N; i++){
            int j;
            for (j = i; j > 0 && a[j].compareTo(a[j-1]) < 0; j--){
                Comparable temp = a[j];
                a[j] = a[j-1];
                a[j-1] = temp;
            }
            draw(a, i, j, Ex2_1_17_1.SortType.INSERTION, a.length - i);
        }
    }
    public static void draw(Comparable[] a, int ith, int jth, Ex2_1_17_1.SortType sortType, int row){
        try {
            Thread.sleep(1000);
        }catch (Exception e){
            e.printStackTrace();
        }
        //StdDraw.clear();
        for (int i = 0; i < a.length; i++){
            switch (sortType){
                case SELECTION:
                    if (i == jth){
                        StdDraw.setPenColor(Color.red);
                    }else if (i == ith){
                        StdDraw.setPenColor(Color.black);
                    }else{
                        StdDraw.setPenColor(Color.gray);
                    }
                    break;
                case INSERTION:
                    if (i == jth){
                        StdDraw.setPenColor(Color.red);
                    }else if (i > ith){
                        StdDraw.setPenColor(Color.gray);
                    }else {
                        StdDraw.setPenColor(Color.black);
                    }
            }
            double barHalfWidth = .08;
            double barHalfHeight = Double.parseDouble(String.valueOf(a[i])) / 2;
            StdDraw.filledRectangle(((double) i), barHalfHeight + row, barHalfWidth, barHalfHeight);
        }
    }

    public static void main(String[] args) {
        Comparable[] array = new Comparable[20];
        for (int i = 0; i < 20; i++){
            double value = StdRandom.uniform(0, 20);
            array[i] = value;
        }
        StdDraw.setCanvasSize(30*array.length, 90);
        StdDraw.setXscale(-.5, array.length / 3 + 1);
        StdDraw.setYscale(.5, array.length + 1.7);
        Selection(array);
        StdOut.print("Starting Insertion sort trace");
        Insertion(array);
    }
}
