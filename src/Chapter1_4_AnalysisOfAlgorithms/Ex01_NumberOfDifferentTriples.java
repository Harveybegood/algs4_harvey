package Chapter1_4AnalysisOfAlgorithms;
/*
*   Show that the number of different triples that can be chosen from N items is precisely N(N-1)(N-2)/6. Hint:
*   Use mathematical induction
*
* */
public class Ex01_NumberOfDifferentTriples {
    /*
    *
    *
    *       if N = 3, N(N-1)(N-2)/6 = 1
    *
    *       from k items; assume that it holds k(k-1)(k-2)/6  = p(k)
    *
    *       from k + 1 items:  (k+1)k(k-1)/6 = p(k+1) => p(k+1) = p(k) + new triples that contains n + 1 new items
    *
    *       => k(k-1)(k-2)/6 + k!/2!(k-2)!
    *
    *       => k(k-1)(k-2)/6 + k(k-1)/2
    *
    *       => k(k-1)(k-2)/6 + 3k(k-1)/6
    *
    *       => (k(k-1)(k-2) + 3k(k-1))/6
    *
    *       => k(k-1)(k+1)/6
    *
    *
    *
    *
    *
    *
    *
    * */
}
