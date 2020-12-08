package Test;

import edu.princeton.cs.algs4.StdOut;

public class splitWithColon {
    public static void main(String[] args) {
        String[] strings = {
                "job0", "09:00:59", "19:11:10",
                "job1", "09:01:10", "19:33:13",
                "job2", "09:03:13", "19:10:11",
                "job3", "09:10:11", "19:50:25",
                "job4", "09:10:25", "19:24:25",
                "job5", "09:14:25", "19:29:32",
                "job6", "09:19:32", "19:39:46",
                "job7", "09:19:46", "19:51:05",
                "job8", "09:21:05", "19:32:43",
                "job9", "09:22:43", "19:42:54",
        };
        String[][] jobTable = new String[10][3];
        int k = 0;
        for (int i = 0; i < 10; i++){
            for (int j = 0; j < 3; j++){
                jobTable[i][j] = strings[k++];
                StdOut.print(jobTable[i][j] + " ");
            }
            StdOut.println();
        }
        int[] s = new int[3];
        for (int i = 0; i < 3; i++){

        }
    }
}
