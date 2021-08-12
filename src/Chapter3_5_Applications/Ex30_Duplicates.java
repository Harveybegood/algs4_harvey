package Chapter3_5_Applications;

import Chapter2_5_Applications.Ex31_Duplicates;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

/*
*   Duplicates(revisited). Redo Exercise 2.5.31 using the DeDup filter given on page 490. Compare the running times of the two approaches.
*   Then use DeDup to run the experiments for N = 10^7, 10^8, and 10^9, repeat the experiments for random long values and discuss
*   the results.
*
* */
public class Ex30_Duplicates {
    Ex31_Duplicates duplicates;
    DeDup deDup;
    public Ex30_Duplicates(){
        duplicates = new Ex31_Duplicates();
        deDup = new DeDup();
    }
    public void compareRunningTime(){
        StdOut.printf("%15s %18s\n", "WithSymbolTable", "withoutSymbolTable");
        Stopwatch timer1 = new Stopwatch();
        duplicates.experiment();
        StdOut.printf("%15.2f", timer1.elapsedTime());
        Stopwatch timer2 = new Stopwatch();
        deDup.experiment();
        StdOut.printf("%18.2f\n", timer2.elapsedTime());

    }
    public void runExperiments(){
        for (int t = 0; t < 10; t++){
            int count1 = deDup.theNumberOfDuplicates(deDup.generateValues((int)Math.pow(10,7), (int)Math.pow(10, 3.5)));
            StdOut.printf("%6d %6.2f %6.2f\n", count1, count1 * 1.0/(int)Math.pow(10, 7), 1 - Math.pow(Math.E, -2));
            int count2 = deDup.theNumberOfDuplicates(deDup.generateValues((int)Math.pow(10,7), (int)Math.pow(10, 7)));
            StdOut.printf("%6d %6.2f %6.2f\n", count2, count2 * 1.0/(int)Math.pow(10, 7), 1 - Math.pow(Math.E, -1));
            int count3 = deDup.theNumberOfDuplicates(deDup.generateValues((int)Math.pow(10,7), (int)Math.pow(10, 14)));
            StdOut.printf("%6d %6.2f %6.2f\n", count3, count3 * 1.0/(int)Math.pow(10, 7), 1 - Math.pow(Math.E, -1/2));

            int count4 = deDup.theNumberOfDuplicates(deDup.generateValues((int)Math.pow(10, 8), (int)Math.pow(10, 4)));
            StdOut.printf("%6d %6.2f %6.2f\n", count4, count4 * 1.0/(int)Math.pow(10, 8), 1 - Math.pow(Math.E, -2));
            int count5 = deDup.theNumberOfDuplicates(deDup.generateValues((int)Math.pow(10,8), (int)Math.pow(10, 8)));
            StdOut.printf("%6d %6.2f %6.2f\n", count5, count5 * 1.0/(int)Math.pow(10, 8), 1 - Math.pow(Math.E, -1));
            int count6 = deDup.theNumberOfDuplicates(deDup.generateValues((int)Math.pow(10,8), (int)Math.pow(10, 16)));
            StdOut.printf("%6d %6.2f %6.2f\n", count6, count6 * 1.0/(int)Math.pow(10, 8), 1 - Math.pow(Math.E, -1/2));

            int count7 = deDup.theNumberOfDuplicates(deDup.generateValues((int)Math.pow(10,9), (int)Math.pow(10, 4.5)));
            StdOut.printf("%6d %6.2f %6.2f\n", count7, count7 * 1.0/(int)Math.pow(10, 9), 1 - Math.pow(Math.E, -2));
            int count8 = deDup.theNumberOfDuplicates(deDup.generateValues((int)Math.pow(10,9), (int)Math.pow(10, 9)));
            StdOut.printf("%6d %6.2f %6.2f\n", count8, count8 * 1.0/(int)Math.pow(10, 9), 1 - Math.pow(Math.E, -1));
            int count9 = deDup.theNumberOfDuplicates(deDup.generateValues((int)Math.pow(10,9), (int)Math.pow(10, 18)));
            StdOut.printf("%6d %6.2f %6.2f\n", count9, count9 * 1.0/(int)Math.pow(10, 9), 1 - Math.pow(Math.E, -1/2));
            StdOut.println();
            StdOut.println();
        }
    }
    public static void main(String[] args) {
        Ex30_Duplicates duplicates = new Ex30_Duplicates();
        duplicates.compareRunningTime();
        StdOut.println();
        StdOut.println();
        duplicates.runExperiments();
    }
}
