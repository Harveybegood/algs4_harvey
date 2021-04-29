package Chapter3_4_HashTables;
/*
*   Suppose that a linear-probing table of size 10^6 is half full, with occupied positions chosen at random. Estimate the
*   probability that all positions with indices divisible by 100 are occupied.
*
* */
public class Ex16_EstimateProbability {
    /*
    *   Since all table positions are equally likely to be the hash value of the next key to be inserted(under the uniform
    *   hashing assumption).
    *   And we can tell the total number of indices divisible by 100 are 10000 by calculating (10^6) % 100.
    *   And then, the table is half full.
    *   We can conclude the probability as: (10000 / 2) / 10^6 = 0.005
    *
    * */
}
