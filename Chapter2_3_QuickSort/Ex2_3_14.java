package Chapter2_3_QuickSort;
/*
*   Prove that when running quicksort on an array with N distinct items, the probability of comparing the ith and jth largest
*   items is 2(j - i). Then use this result to prove Proposition K.
*
* */
public class Ex2_3_14 {
    /*
    *   The ith and jth elements will be compared if either of them is selected as pivot.
    *   In an array of size (j - i + 1), the probability of choosing either the ith or the jth element as the pivot is
    *   2 / (j - i + 1). Therefore, when running quicksort on an array with N distinct items, the probability of comparing
    *   the ith and the jth smallest items is 2 / (j - i + 1).
    *
    *   Proposition K says that quicksort uses ~ 2NlnN compares (and one-sixth that many exchanges) on the average to sort
    *   an array of length N with distinct keys.
    *
    *   Consider the pivot element as k.
    *   1: when k > both ith and jth elements: in this case a[i] and a[j] are not compared to each other in this recursive
    *   call, but both are passed on to the first recursive call(so they might be compared in the future).
    *   2: when k < both ith and jth elements: similarly, in this case a[i] and a[j] are not compared to each other,
    *   but both are passed to the second recursive call.
    *
    *   As we have seen, the probability Pr of comparing the ith and jth elements is:
    *   Pr[Cij = 1] = 2 / (j - i + 1)
    *   Combining both 1 and 2 statements yields:
    *   C = 2 * SUM(from i = 1 to N) * SUM(from j = i + 1 to N) * 1 / (j - i + 1)
    *   Note that for each fixed i, the inner sum is:
    *   1/2 + 1/3 + 1/4 + ... + 1/(n - i + 1) <= SUM(from k = 2 to N) * 1/k
    *   we can upper bound the right-hand side by the area under the curve f(x) = 1/x. In other words:
    *   SUM(from k = 2 to N)*1/k <= LIM(from 1 to N)*dx/x = ln*x|(from 1 to N) = lnN
    *   putting it all together, we have
    *
    *       C = 2 * SUM(from i = 1 to N) * SUM(from j = i + 1 to N) * 1 / (j - i + 1) <= 2N*SUM(from 2 to N) * 1/k <= 2N ln N
    *
    *
    * */
}
