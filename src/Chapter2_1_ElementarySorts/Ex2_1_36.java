package Chapter2_1_ElementarySorts;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

/*
*   Nonuniform data. Write a client that generates test data that is not uniform, including the following:
*       Half the data is 0s, half 1s
*       Half the data is 0s, half the remainder is 1s, half the remainder is 2s, and so forth.
*       Half the data is 0s, half random int values.
*   Develop and test hypotheses about the effect of such input on the performance of the algorithms in this section.
*
* */
@SuppressWarnings("unchecked")
public class Ex2_1_36 {
    public enum NonuniformData{
        Half0Half1, Half0HalfRemainder1WithIncrement, Half0HalfRandom
    }
    public static void sort(Comparable[] a){
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
    public static double time(Comparable[] a, NonuniformData nonuniformData){
        Stopwatch timer = new Stopwatch();
        if (nonuniformData == NonuniformData.Half0Half1){
            sort(a);
        }/*else if (nonuniformData == NonuniformData.Half0HalfRemainder1WithIncrement){
            sort(a);
        }*/else if (nonuniformData == NonuniformData.Half0HalfRandom){
            sort(a);
        }
        return timer.elapsedTime();
    }
    public static double timeNonuniformInput(NonuniformData nonuniformData, int arrayLength){
        double total = 0.0;
        Comparable[] array = new Comparable[arrayLength];
        for (int i = 0; i < arrayLength; i++){
            if (nonuniformData == NonuniformData.Half0Half1){
                if (i <= arrayLength/2 - 1){
                    array[i] = StdRandom.uniform(0,1);
                }else {
                    array[i] = StdRandom.uniform(1,2);
                }
            }
            /*else if (nonuniformData == NonuniformData.Half0HalfRemainder1WithIncrement){
                int newArrayLength = arrayLength / 2 - 1;
                int input = 0;
                for (int increasing = 0; increasing < newArrayLength; increasing++){
                    array[increasing] = StdRandom.uniform(input, input + 1);
                    newArrayLength *= .5;
                    input += 1;
                }
            }*/else if (nonuniformData == NonuniformData.Half0HalfRandom){
                if (i <= arrayLength/2 -1){
                    array[i] = StdRandom.uniform(0, 1);
                }else {
                    array[i] = StdRandom.uniform(0, 100000);
                }
            }
        }
        total += time(array, nonuniformData);
        return total;
    }

    public static void main(String[] args) {
        int arrayLength = 1000;
        double t1 = 0.0;
        double t3 = 0.0;
        for (int experiment = 0; experiment < 10; experiment++){

            for (int i = 0; i < arrayLength; i++){
                t1 = timeNonuniformInput(NonuniformData.Half0Half1, arrayLength);
                //double t2 = timeNonuniformInput(NonuniformData.Half0HalfRemainder1WithIncrement, arrayLength);
                t3 = timeNonuniformInput(NonuniformData.Half0HalfRandom, arrayLength);
            }
            StdOut.printf("%d, N: %d, Half0Half1: %.2f, Half0HalfRandom: %.2f\n",
                    experiment, arrayLength, t1, t3);
            arrayLength *= 2;
        }
    }
}
