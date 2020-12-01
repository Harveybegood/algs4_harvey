package Chapter1_3_Bags_Queues_Stacks;

import edu.princeton.cs.algs4.StdOut;



public class test {

    public static void main(String[] args) {
        for (int i = 1; i < 10; i++){
           for (int j = 1; j < 10; j++){
               StdOut.print(j + " * " + i + " ");
               if (j >= i){
                   StdOut.println();
                   break;
               }
           }
        }
    }
}
