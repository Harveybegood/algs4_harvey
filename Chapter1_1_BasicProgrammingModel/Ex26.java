package Chapter1_1_BasicProgrammingModel;

import edu.princeton.cs.algs4.StdOut;

/*
*   Sorting three numbers. Suppose that the variables a,b,c, and t are all of the same numerical primitive type.
*   Show that the following code puts a,b, and c in ascending order:
*
* */
public class Ex26 {

    // all of the same numerical primitive type
    // global variables
    private static int a = 4, b = 20, c = 2, t = 0;
    public static void main(String[] args) {
        // local variable
        //int a = 4, b = 10, c = 0, t = 0;
        StdOut.println("a = " + a + "  b = " + b + "  c = " + c);
        sorting3Numbers();
        // swap a and b
  /*      if (a > b){
            t = a;
            a = b;
            b = t;
            // then the order is b a
        }
        // swap a and c
        if (a > c){
            t = a;
            a = c;
            c = t;
            // c a
        }
        // swap b and c
        if (b > c){
            t = b;
            b = c;
            c = t;
            // c b
        }*/
        StdOut.println("a = " + a + "  b = " + b + "  c = " + c);
    }
    private static void sorting3Numbers(){

        // swap a and b
        if (a > b){
            t = a;
            a = b;
            b = t;
            // then the order is b a
        }
        // swap a and c
        if (a > c){
            t = a;
            a = c;
            c = t;
            // c a
        }
        // swap b and c
        if (b > c){
            t = b;
            b = c;
            c = t;
            // c b
        }
    }
}
