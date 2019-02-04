package Chapter2_4_PriorityQueues;
/*
*   The largest item in a heap must appear in position 1, and the second largest must be in position 2, or position 3.
*   Give the list of position in a heap of size 31 where the kth largest(i) can appear, and (ii) cannot appear,
*   for k =2, 3, 4 (assuming the values to be distinct).
*
* */
public class Ex2_4_7 {
    /*
    *                                               31
*                                     30                             29
*                               28             27             26              25
    *                       24      23     22      21     20      19      18      17
    *                    16   15 14   13 12  11  10   9  8   7   6   5   4   3   2  1
    *
    *               based on the knowledge of binary tree and the above view(max heap), come to conclusion:
    *
    *               2nd largest item:
    *               Appear in such as these positions: 2, 3
    *               Cannot appear: self-evidence -> rest of positions expect for 2, 3
    *
    *               3rd largest items:
    *               Appear in such as these positions: 2, 3, 4, 5, 6, 7
    *               Cannot appear: rest of positions expected for 2, 3, 4, 5, 6, 7
    *
    *               4th largest items:
    *               Appear in such these positions: 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15
    *               Cannot appear: rest of positions.
    *
    *
    *
    *
    * */
}
