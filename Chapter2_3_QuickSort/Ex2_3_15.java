package Chapter2_3_QuickSort;
/*
*   Nuts and bolts. (G.J.E Rawlins)You have a mixed pile of N nuts and N bolts and need to quickly find the corresponding
*   pairs of nuts and bolts. Each nut matches exactly one bolt, and each bolt matches exactly one nut. By fitting a nut
*   and bolt together, you can see which is bigger, but it is not possible to directly compare two nuts or two bolts.
*   Give an efficient method for solving the problem.
*
* */
public class Ex2_3_15 {
    /*
    *   Put nuts and bolts in two separated piles,
    *   Nuts lines like: N5, N1, N3, N7, N6, N4, N2, N0, N9, N8
    *   Bolts lines like: B5, B3, B9, B6, B4, B8, B0, B7, B2, B1
    *
    *   Firstly, select N5 as pivot, then take this N5 to compare each of Bolts, which make Bolts be two piles, one of
    *   which will bigger than N5, like B6, B7, B8, B9.
    *   the second pile less than N 5, like B0, B1, B2, B3, B4.
    *   and we already match N5 and B5.
    *
    *   then we select B5 as pivot, then take this B3 to compare each of Nuts, which make Buts be two piles,
    *   bigger than B3: N4, N6, N7. N8. N9
    *   smaller than B3: B0, B1, B2
    *   again, we already match N3 and B3
    *
    *   the way that we partition each subarray to smaller subarray.
    *
    *
    *
    * */
}
