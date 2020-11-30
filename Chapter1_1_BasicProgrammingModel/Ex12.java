package Chapter1_1_BasicProgrammingModel;

import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/*
*   What does the following code fragment print?
*
* */
public class Ex12 {
    //private static int[] a;
    public static void main(String[] args) {
        //String delimiter = " - ";
        StdOut.print(test1());
        StdOut.println();
        StdOut.print(Arrays.toString(test1()));
        StdOut.println();
        StdOut.print(test2());
        StdOut.println();
        StdOut.print(Arrays.toString(test2()));
        StdOut.println();
        test3();
    }
    public static int[] test1(){
        int[] a = new int[10];
        //a = new int[10];
        for (int i = 0; i < 10; i++){
            a[i] = 9 - i;
        }
        //StdOut.print(Arrays.toString(a));
        return a;
    }
    public static int[] test2(){
        int[] a = new int[10];
        //a = new int[10];
        for (int i = 0; i < 10; i++){
            // key point to a int type is that the default value is 0 after initialization
            a[i] = a[a[i]];
        }
        return a;
    }
    public static void test3(){
        for (int i = 0; i < 10; i++){
            System.out.println(i);
        }
    }
  /*  public String toString1(){
       int i = 0;
       int[] b = new int[10];
       while (i < 10){
           b[i] = a[i];
           i++;
       }
       return b[i] + " - ";
    }*/
}
