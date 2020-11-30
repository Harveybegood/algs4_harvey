package Chapter2_1_ElementarySorts;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.Arrays;

/*
*   Corner cases. Write a client that runs sort() on difficult or pathological cases that might turn up in practical
*   applications. Example include arrays that are already in order,
*                                 arrays in reverse order,
*                                 arrays where all keys are the same,
*                                 arrays consisting of only two distinct values, and
*                                 arrays of size 0 and 1.
*
* */
@SuppressWarnings("unchecked")
public class Ex2_1_34 {
    private static int arrayLength = 10000;
    static Comparable[] array = new Comparable[arrayLength];
    public static Comparable[] arrayInOrder(){
        for (int i = 0; i < arrayLength; i++){
            array[i] = StdRandom.uniform(0, arrayLength);
        }
        Arrays.sort(array);
        return array;
    }
    public static void show(Comparable[] a){
        for (int i = 0; i < a.length; i++){
            StdOut.print(a[i] + " ");
        }
        StdOut.println();
    }
    public static double sort(Comparable[] a){
        Stopwatch timer = new Stopwatch();
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
        return timer.elapsedTime();
    }
    public static void generateArrayInOrder(){
        sort(arrayInOrder());
        show(array);
        StdOut.println("array in order." + " " + sort(array));
    }
    public static void generateArrayReverseOrder(){
        Comparable[] array = arrayInOrder();
        Comparable[] newArrayReverseOrder = new Comparable[arrayLength];
        for (int i = arrayLength - 1; i >= 0; i--){
            newArrayReverseOrder[i] = array[i];
        }
        sort(newArrayReverseOrder);
        show(newArrayReverseOrder);
        StdOut.println("array in reverse order" + " " + sort(array));
    }
    public static void generateArraySameKeys(){
        for (int i = 0; i < arrayLength; i++){
            array[i] = StdRandom.uniform(1,2);
        }
        sort(array);
        show(array);
        StdOut.println("array in same keys" + " " + sort(array));
    }
    public static void generateArrayTwoDistinctValues(){
        for (int i = 0; i < arrayLength; i++){
            array[i] = StdRandom.uniform(1, 3);
        }
        sort(array);
        show(array);
        StdOut.println("array in two distinct values" + " " + sort(array));
    }
    public static void generateArraySize0(){
        for (int i = 0; i <= 0; i++){
            array[i] = StdRandom.uniform();
        }
        sort(array);
        show(array);
        StdOut.println("array size 0" + " " + sort(array));
    }
    public static void generateArraySize1(){
        for (int i = 0; i < 1; i++){
            array[i] = StdRandom.uniform();
        }
        sort(array);
        show(array);
        StdOut.println("array size 1" + " " + sort(array));
    }

    public static void main(String[] args) {
        generateArrayInOrder();
        generateArrayReverseOrder();
        generateArraySameKeys();
        generateArrayTwoDistinctValues();
        generateArraySize0();
        generateArraySize1();
    }
/*  static int[] a = {};
  static int[] b = {1};
  public static double sort(int[] a){
      Stopwatch timer = new Stopwatch();
      int N = a.length;
      for (int i = 0; i < N; i++){
          int min = i;
          for (int j = i + 1; j < N; j++){
              if (a[j] < a[min]){
                  min = j;
              }
          }
          int temp = a[i];
          a[i] = a[min];
          a[min] = temp;
      }
      return timer.elapsedTime();
  }

    public static void main(String[] args) {
        StdOut.printf("Time cost: %.7f\n", + sort(a));
        StdOut.printf("Time cost: %.7f\n", + sort(b));
    }*/
}
