package Chapter1_2_DataAbstraction;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.awt.*;

/*
*   Develop a class VisualCounter that allows both increment and decrement operations. Take two arguments N and max
*   in the constructor, where N specifies the maximum number of operations and max specifies the maximum absolute
*   value for the counter. As a side effect, create a plot showing the value of the counter each time its tally
*   changes.
*
* */
public class Ex10 {
    public class visualCounter{
        /*private int N;
        private int max;*/
        private int count = 0;
        public visualCounter(int N, int max){
           /* this.max = max;
            this.N = N;*/
            StdDraw.setXscale(0, N);
            StdDraw.setYscale(0, max);
            StdDraw.setPenRadius(.005);
        }
        public void increment(){
            count++;
        }
        public void decrement(){
            count--;
        }
        public int tally(){
            return count;
        }
  /*      public void plot(){
            visualCounter counter =  new visualCounter(N, max);
            for (int i = 0; i < N && count < max; i++){
                    if (StdRandom.bernoulli(.3)){
                        counter.increment();
                    }
                    else {
                        counter.decrement();
                    }
                    StdDraw.setPenColor(Color.BLUE);
                    StdDraw.point(i, counter.tally());
            }
        }*/
    }

    public static void main(String[] args) {
        int N = 100;
        int max = 100;
        Ex10 ex10 = new Ex10();
        visualCounter visualCounter = ex10.new visualCounter(N, max);
        for (int i = 0; i < N && Math.abs(visualCounter.tally()) < max; i++){
            if (StdRandom.bernoulli(.6)){
                visualCounter.increment();
            }
            else {
                visualCounter.decrement();
            }
            StdDraw.setPenColor(Color.BLUE);
            StdDraw.point(i, visualCounter.tally());
            //StdOut.println(i + " " + visualCounter.tally());
        }
        StdOut.println(visualCounter.tally());
    }
}
