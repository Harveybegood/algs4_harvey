package Chapter2_1_ElementarySorts;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;


/*
*   Animation. Add code to Insertion and Selection to make them draw the array contents as vertical bars like the visual
*   traces in this section, redrawing the bars after each pass, to produce an animated effect, ending in a "sorted" picture
*   where the bars appear in order of their height. Hint: Use a client like the one in the text that generates random Double
*   value, insert calls to show() as appropriate in the sort code, and implement a show() method that clears the canvas
*   and draw the bars.
*
* */
public class Ex2_1_17_1 {
    public enum SortType{
        SELECTION, INSERTION,SHELL
    }
    public static void sortOfSelections(Comparable[] a){
        int N = a.length;
        for (int i = 0; i < N; i++){
            int min = i;
            for (int j = i + 1; j < N; j++){
                if (less_exch(j, min)) {
                    min = j;
                }
            }
            draw(a, i, min, -1, SortType.SELECTION);
            exch(a, i, min);
        }
    }
    public static void sortOfInsertions(Comparable[] a){
        int N = a.length;
        for (int i = 1; i < N; i++){
            int j;
            for (j = i; j > 0 && less_exch(a[j], a[j-1]); j--){
                exch(a, i, j);
            }
            draw(a, i, j, -1, SortType.INSERTION);
        }
    }
    @SuppressWarnings("unchecked")
    public static void sortOfShell(Comparable[] a){
        int incrementSequence = 1;
        while (incrementSequence * 3 + 1 < a.length){
            incrementSequence *= 3;
            incrementSequence++;
        }
        while (incrementSequence > 0){
            for (int i = incrementSequence; i < a.length; i++){
                int j;
                for (j = i; j >= incrementSequence && a[j].compareTo(a[j - incrementSequence]) < 0; j -= incrementSequence){
                    Comparable temp = a[j];
                    a[j] = a[j - incrementSequence];
                    a[j - incrementSequence] = temp;
                }
                draw(a, i, j, incrementSequence, SortType.SHELL);
            }
            incrementSequence /= 3;
        }
    }
    @SuppressWarnings("unchecked")
    public static boolean less_exch(Comparable v, Comparable w){
        return v.compareTo(w) < 0;
    }
    public static void exch(Comparable[] a, int i, int j){
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private static void draw(Comparable[] a, int ith, int jth, int shellSortIncrent, SortType sortType){
        try{
            Thread.sleep(2000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        StdDraw.clear();
        for (int i = 0; i < a.length; i++){
            switch (sortType){
                case SELECTION:
                    //StdDraw.setPenColor(StdDraw.BOOK_RED);
                    if (i == jth){
                        StdDraw.setPenColor(StdDraw.BOOK_RED);
                    }else if (i < ith){
                        StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
                    }else {
                        StdDraw.setPenColor(StdDraw.BLACK);
                    }
                    break;
                case INSERTION:
                    if (i == jth && jth == 0){
                        StdDraw.setPenColor(StdDraw.BOOK_RED);
                    }else if (i > ith || i < jth){
                        StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
                    }else {
                        StdDraw.setPenColor(StdDraw.BLACK);
                    }
                    break;
                case SHELL:
                    if (i == jth){
                        StdDraw.setPenColor(StdDraw.BOOK_RED);
                    }else if (i > ith || i < jth){
                        StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
                    }else if ((i % shellSortIncrent) == (jth % shellSortIncrent)){
                        StdDraw.setPenColor(StdDraw.BLACK);
                    }else {
                        StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
                    }
                    break;
            }
            double barHalfWidth = .08;
            double barHalfHeight = Double.parseDouble(String.valueOf(a[i]))/2;
            StdDraw.filledRectangle(((double) i) / 3, barHalfHeight + .7, barHalfWidth, barHalfHeight);
        }
    }

    public static void main(String[] args) {
        int arraySize = 20;
        Comparable[] array = new Comparable[arraySize];
        for (int i = 0; i < array.length; i++){
            double value = StdRandom.uniform();
            array[i] = value;
        }
        StdDraw.setCanvasSize(30*(arraySize+3), 90);
        StdDraw.setXscale(-.5, arraySize/3 + 1);
        StdDraw.setYscale(0, 3);
        //sortOfSelections(array);
        //sortOfInsertions(array);
        sortOfShell(array);
    }
}
