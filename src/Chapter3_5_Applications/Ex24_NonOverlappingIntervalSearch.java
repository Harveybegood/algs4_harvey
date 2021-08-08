package Chapter3_5_Applications;

import edu.princeton.cs.algs4.SparseVector;
import edu.princeton.cs.algs4.StdOut;


/*
*   Non-overlapping interval search. Given a list of non-overlapping intervals of items, write a function that takes
*   an item as argument and determines in which, if any, interval that item lies. For example, if the items are integers
*   and the intervals are 1643-2033, 5532-7643, 8999-10332, 5666653-5669321, then the query point 9122 lies in the
*   third interval and 8122 lies in no interval.
*
* */
public class Ex24_NonOverlappingIntervalSearch {

    private SparseVector[] rows;

    public Ex24_NonOverlappingIntervalSearch(){
        rows = new SparseVector[4];
        for (int i = 0; i < 4; i++){
            int d = intervals[i][1] - intervals[i][0] + 1;
            rows[i] = new SparseVector(d);
        }
    }

   private int[][] intervals = {
            {1643, 2033},
            {5532, 7643},
            {8999, 10332},
            {5666653, 5669321}
    };

    // put each value of every interval to vector
    public void buildVector(){
        for (int i = 0; i < 4; i++){
            int j = 0;
            int k = 0;
            for (int val = intervals[i][j]; val <= intervals[i][j + 1]; val++){
                rows[i].put(k, val);
                k++;
            }
        }
    }
    public void determineInterval(int query){
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < rows[i].dimension(); j++){
                if (rows[i].get(j) == query){
                    StdOut.println("The integer number " + query + " lies in " + i + " interval");
                    return;
                }
            }
        }
        StdOut.println("The integer number " + query + " not in any interval");
    }

    public static void main(String[] args) {
        Ex24_NonOverlappingIntervalSearch nonOverlappingIntervalSearch = new Ex24_NonOverlappingIntervalSearch();
        nonOverlappingIntervalSearch.buildVector();
        nonOverlappingIntervalSearch.determineInterval(2000);
        nonOverlappingIntervalSearch.determineInterval(8000);
        nonOverlappingIntervalSearch.determineInterval(5668000);
    }
}

/*
*   The integer number 2000 lies in 0 interval
    The integer number 8000 not in any interval
    The integer number 5668000 lies in 3 interval
*
*
* */
