package Chapter2_3_QuickSort;
/*
*   Chebyshev's inequality says that the probability that a random variable is more than k standard deviations away from
*   the mean is less than 1/k^2. For N = 1million, use Chebyshev's inequality to bound the probability that the number of
*   compares used by quicksort is more than 100 billion(.1N^2).
*
* */
public class Ex2_3_10 {


    /*
    *   Pr(|X - µ| >= kσ) <= 1 / k^2
    *   //the probability that a random variable X is more than k standard deviations away from the mean is less than
    *     1 / k ^ 2
    *   Pr(|X - µ| >= kσ) >= 1 - 1 / k^2
    *   //the probability that a random variable x is more than k standard deviation away from the mean is more than
    *   1 - 1 / k ^ 2
    *   N = 1 million,
    *
    *   µ: mean
    *   σ: standard variance
    *   as known, the standard deviation for the ~2NlnN number of compares used by quick sort is .65N
    *   so suppose that the N = 1 million, the probability that the number of compares used by quick sort is more than
    *   100 billion is:
    *
    *           Pr(|X - ~2NlnN| >= 10^11) <= 1/k^2
    *           100000000000
    *           0.65Nk = 10^11 ==> k = 10^7 / 65
    *           1 / k ^ 2 = 0.00000000004225
    *
    *           100 000 000 000 = .1N^2
    *
    * */

}
