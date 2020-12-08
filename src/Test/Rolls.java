package Test;

import edu.princeton.cs.algs4.Counter;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Rolls {
   /* static class Counter{
        private int head;
        private int tail;
        public Counter(){

        }
        public void increment(){

        }
    }*/
   public static void main(String[] args) {
       int T = Integer.parseInt(args[0]);
       int sides = 6;
       Counter[] rolls = new Counter[sides+1];
       for (int i = 0; i <= sides; i++){
           rolls[i] = new Counter(i + "'s");
           StdOut.println(rolls[i]);
       }
       for (int t = 0; t < T; t++){
           int result = StdRandom.uniform(1,sides+1);
           rolls[result].increment();
       }
       for (int i = 1; i <= sides; i++){
           StdOut.println(rolls[i]);
       }
   }

}
