package Chapter1_4AnalysisOfAlgorithms;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/*
*   Modify ThreeSum to work properly even when the int values are so large that adding two of them might cause overflow.
*
* */
public class Ex02_ThreeSumOverflow {

    public static int count(int[] a){
        int n = a.length;
        int cnt = 0;
        for (int i = 0; i < n; i++){
            for (int j = i+1; j < n; j++){
                for (int k = j+1; k < n; k++){
                    if (a[i] > 0 && a[j] > 0 && a[k] > 0){
                        continue;
                    }
                    else if (a[i] > 0 && a[j] > 0){
                        if (a[i] + a[k] + a[j] == 0){
                            cnt++;
                        }
                    }
                    else if (a[i] > 0 && a[k] > 0){
                        if (a[i] + a[j] + a[k] == 0){
                            cnt++;
                        }
                    }
                    else if (a[j] > 0 && a[k] > 0){
                        if (a[i] + a[j] + a[k] == 0){
                            cnt++;
                        }
                    }
                    else {
                        if (a[i] + a[j] + a[k] == 0){
                            cnt++;
                        }
                    }
                }
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        //int[] a = StdIn.readAllInts();
        int[] b = {1,2,3,4,5,6,7,-7,-6,-5,-4,-3,-2,1,0};
        StdOut.println(count(b));
    }
}
