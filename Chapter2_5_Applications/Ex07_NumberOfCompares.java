package Chapter2_5_Applications;
/*
*   About how many compares are required, on the average, to find the smallest of N items using select().
* */
public class Ex7_NumberOfCompares {
    /*
    *   Proof: An analysis similar to, but significantly more complex than, the proof of
        Proposition K for quicksort leads to the result that the average number of compares
        is ~ 2N  2k ln(N/k)  2(N  k) ln(N/(N  k)), which is linear for any
        allowed value of k. For example, this formula says that finding the median (k =
        N/2) requires ~ (2  2 ln 2)N compares, on the average. Note that the worst case
        is quadratic but randomization protects against that possibility, as with quicksort.
    *
    *
    * */
}

