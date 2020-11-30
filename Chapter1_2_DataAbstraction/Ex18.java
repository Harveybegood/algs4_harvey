package Chapter1_2_DataAbstraction;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/*
*   Variance for accumulator. Validate that the following code, which adds the methods var() and stddve() to
*   Accumulator, computes both the mean and variance of the numbers presented as arguments to addDataValue();
*
*   This implementation is less susceptible to roundoff error than the straightforward implementation based on
*   saving the sum of squares of the numbers.
*
* */
public class Ex18 {
    public class betterAccumulator{
        private double m;
        private double s;
        private int N;
        public void addDataValue(double x){
            N++;
            s = s + 1.0 * (N-1)/N * (x-m) * (x-m);
            m = m + (x-m)/N;
        }
        public double mean(){
            return m;
        }
        public double var(){
            return s/(N-1);
        }
        public double stddev(){
            return Math.sqrt(this.var());
        }
        public String toString(){
            return "Mean (" + N + " values): "
                    + String.format("%7.5f", mean())
                    + " Var: "
                    + String.format("%7.5f", var())
                    + " stddev: "
                    + String.format("%7.5f", stddev())
                    ;
        }
    }

    public class Accumulator
    {
        private double total;
        private int N;
        public void addDataValue(double val)
        {
            N++;
            total += val;
        }
        public double mean()
        { return total/N; }
        public String toString()
        { return "Mean (" + N + " values): "
                + String.format("%7.5f", mean());
        }
    }

    public static void main(String[] args) {
        Ex18 ex18 = new Ex18();
        betterAccumulator betterAccumulator = ex18.new betterAccumulator();
        Accumulator accumulator = ex18.new Accumulator();
        for (int i = 0; i < 1000; i++){
            betterAccumulator.addDataValue(StdRandom.uniform());
            accumulator.addDataValue(StdRandom.uniform());
        }
        StdOut.println(betterAccumulator);
        StdOut.println(accumulator);
    }
}
