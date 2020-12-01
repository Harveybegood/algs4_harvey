package Chapter2_1_ElementarySorts;

import edu.princeton.cs.algs4.StdDraw;

import java.awt.*;
import java.util.concurrent.TimeUnit;

/*
*   Animation. Add code to Insertion and Selection to make them draw the array contents as vertical bars like the
*   visual traces in this section, redrawing the bars after each pass, to produce an animation effect, ending in
*   a "sorted" picture where the bars appear in order of their height. Hint: Use a client like the one in the text
*   that generates random Double values, insert calls to show() as appropriate in the sort code, and
*   implement a show() method that clears the canvas and the bars.
*
* */
public class Ex2_1_17 {
    public static void selection(Comparable[] a){
        int N = a.length;
        for (int i = 0; i < N; i++){
            int min = i;
            for (int j = i; j < N; j++){
                if (less_exch.less(a[j], a[min]))
                    min = j;
            }
            less_exch.exch(a, i, min);
        }
    }
    public static void insertion(Comparable[] a){
        int N = a.length;
        for (int i = 0; i < N; i++){
            for (int j = i; j > 0 && less_exch.less(a[j], a[j-1]); j--){
                less_exch.exch(a, j, j-1);
            }
        }
    }
    public static void shell(Comparable[] a){
        int N = a.length;
        int h = 1;
        while (h < N / 3){
            h = h * 3 + 1;
        }
        while (h >= 1){
            for (int i = h; i < N; i++){
                for (int j = i; j >= h && less_exch.less(a[j], a[j-h]); j -= h){
                    less_exch.exch(a, j, j-h);
                }
            }
            h = h / 3;
        }
    }

    public static void show(Comparable[] a, boolean mark, int ith, int jth){
        //Comparable max;
        StdDraw.clear();
        StdDraw.setXscale(-1, a.length);
        StdDraw.setYscale(-.5, 2);
        for (int i = 0; i < a.length; i++){
            double x = i;
            double y = Double.parseDouble(String.valueOf(a[i]))/2.0;
            double barWidth = .08;
            double barHeight = Double.parseDouble(String.valueOf(a[i]))/2.0;
            if (mark){
                if (i == ith){
                    StdDraw.setPenColor(Color.gray);
                }else if (i == jth){
                    StdDraw.setPenColor(Color.black);
                }else {
                    StdDraw.setPenColor(Color.red);
                }
            }
            StdDraw.filledRectangle(x, y, barWidth, barHeight);
        }
        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

    }
}
