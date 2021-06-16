package Chapter1_4AnalysisOfAlgorithms;

import edu.princeton.cs.algs4.Stack;

/*
*   Memory requirements on a 32-bit machine. Give the memory requirements for Integer, Date, Counter, int[], double[], double[][],
*   String, Node, and Stack(linked-list representation) for a 32-bit machine. Assume that references are 4 bytes. object overhead is 8 bytes,
*   and padding is to a multiple of 4 bytes.
*
* */
public class Ex33_MemoryRequirements {
    /*
    *   Integer: object overhead: 8 + int x: 4 = 12 bytes
    *
    *   Date: object overhead: 8 + int day: 4 + int month: 4 + int year: 4 = 20 bytes
    *
    *   Counter: object overhead: 8 + String name reference: 4 + int count: 4 = 16 bytes
    *
    *   int[]: object overhead: 8 + int size: 4 + N int value: N * 4 = (12 + N * 4) bytes
    *
    *   double[]: object overhead: 8 + int size: 4 + N double value: N * 8 = (12 + N * 8) bytes
    *
    *   double[][]: object overhead: 8 + int size: 4 + M reference: M * 4 + M * (object overhead: 8 + int size: 4 + N double value: N * 8)
    *                               = 12 + M * 4 + M * (12 + N * 8)
    *                               = (16 + M * 16 + M * N * 8) bytes
    *
    *   String: object overhead: 8 + int offset: 4 + int count: 4 + int hash: 4 + N char value: N * 2 + char reference: 4 + char[] array:
    *           object overhead: 8 + int size: 4
    *                               = (36 + N * 2) bytes
    *
    *   Node: object overhead: 8 + Item reference: 4 + next reference: 4 = 16 bytes
    *
    *   Stack: object overhead: 8 + int size: 4 + N * (nested non-static inner Node class: overhead: 8 + extra overhead: 4 + Item reference: 4 +
                                next reference: 4) + first reference: 4
                                = (16 + N * 20) bytes

    *
    * */

}
