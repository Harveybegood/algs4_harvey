package Chapter1_4AnalysisOfAlgorithms;
/*
*   Give a formula to predict the running time of a program for a problem of size N when doubling experiments have shown
*   that the doubling factor 2^b and the running time for problems of size N0 is T.
*
* */
public class Ex09_FormulaToPredict {
    /*
    *           2^b
    *       N0 --- T
    *
    *       2N0 --- T * 2^b
    *
    *       4N0 --- T * 2^b * 2^b = T * 2^2b
    *
    *       8N0 --- T * 2^b * 2^b * 2^b = T * 2^3b
    *
    *       16N0 --- T * 2^4b  = T * 2^4b = T * 16^b
    *
    *       Then, we have
    *
    *       N  --- T * (N/N0)^b
    *
    *
    * */
}
