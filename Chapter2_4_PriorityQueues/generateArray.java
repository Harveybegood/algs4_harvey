package Chapter2_4_PriorityQueues;

import edu.princeton.cs.algs4.StdOut;

public class generateArray {
    private static int[] array = new int[50];
    public String toString(){
        return  array + " ";
    }
    public static void main(String[] args) {
        for (int i = 0; i < 50; i++){
            array[i] = i;
        }
        StdOut.println(array);
    }
}
