package Chapter1_4AnalysisOfAlgorithms;
/*
*   Analyze ThreeSum under a cost model that counts arithmetic operations(and comparisons)involving the input numbers.
*
*       count the array accesses, the number of times an array entry is accessed, for read or write.
*
*
*       public class ThreeSum
        {
            public static int count(int[] a)
            {
                int N = a.length;
                int cnt = 0;

                for (int i = 0; i < N; i++ )

                  for (int j = i+1; j < N; j++ )

                      for (int k = j+1; k < N; k++ )

                          if (a[i] + a[j] + a[k] == 0)

                              cnt++;

                 return cnt;
              }
                public static void main(String[] args)
                {
                    int[] a = In.readInts(args[0]);
                    StdOut.println(count(a));
                }

        }
*
* */
public class Ex07_CostModelOfThreeSum {

/*
*           the number of comparison of arithmetic operations of "(a[i] + a[j] + a[k] == 0)"
*
*           i = 0, j = 1; k = 2;                                    i = 1, j = 2; k = 3;                i = 2, j = 3; k = 4;
*
*                         k = 3;                                                  k = 4;
*
*                         k = 4;                                                  .....
*                         .....             N - 2;                                .....         N-3;
*                         .....
*                         .....                                                   k = N-2;
*                         k = N-2;
*                                                                                 k = N-1;
*                         k = N-1;
*
*
*           i = 0, j = 2; k = 3;
*
*                         k = 4;
*
*                         k = 5;
*                         .....             N - 3;
*                         .....
*                         k = N-2;
*
*                         k = N-1;
*                .................................              .................................
*                .................................              .................................
*
*           i = 0, j = N-3; k = N-2;
*                                              2                   i = 1, j = N-3; k = M-2;     2
*                           k = N-1;                                               k = N-1;
*
*           i = 0, j = N-2; k = N-1;           1                   i = 1. j = N-2; k = N-1;     1
*
*                the number of K: 1 + 2 + ... + (N-3) + (N-2)      1 + 2 + ... + (N-4) + (N-3)           1 + 2 + ... +(N-5) + (N-4)
*                                   = (N-2)(1 + N - 2)/2            = (N-3)(1 + N - 3)/2                =(N-4)(1 + N - 4)
*                                   = (N^2 - 3N + 2)/2              = (N^2 - 5N + 6)/2                  =(N^2 - 7N + 12)/2
*
*                             .......................................................................................................
*                             .......................................................................................................
*
*                                  i = N - 4, j = N - 3; k = N - 2;
*                                                                       2
*                                                        k = N - 1;
*
*                                             J = N - 2; K = N - 1;     1
*
*                                             1 + 2
*
*
*
*                                  i = N - 3, j = N - 2; k = N -1;  1
*
*                                             1
*
*
*
*                                  i = N - 2, j = N - 1; K = N;     => 0
*
*                                             0
*
*           Then, (N-1) times of i,
*
*                 first item of ((N^2) * (N-2)) /2 = (N^3 - 2N^2)/2
*
*                 second item of
 *                               last item will be: An = a1 + (N-3) * (2N) = -3N + 2n^2 - 4N =2N^2 - 9N;
 *                               (-3N - 5N - 7N - 9N -...2N^2 - 7N) = > -(3N + 2N^2 - 9N)(N-2)/2 = -(2N^2-6N)(N-2)/2
 *                                                                                             = -(2N^3-6N^2-4N^2+12N)/2
 *                                                                                             = -(2N^3-10N^2+12N)/2
 *                                                                                             = (-2N^3+10N^2-12N)/2
*
*                 third item of (2 + 6 + 12 + 20 + 30 + ... + )
*                                  4   6    8    10
*                                    2    2    2
*
*                                2   2 * 3   2 * 4  2 * 5
*
*                                a1 = a1 = 2;
*                                a2 = a1 + a1 * 2 = 2 + 2 * 2 = 6;
*                                a3 = a1 + a1 * 2 + a1 * 3 = 6 + 2 * 3 = 12;
*                                a4 = a1 + a1 * 2 + a1 * 3 + a1 * 4 = 12 + 2 * 4 = 20
*                                .....................................................
*                                a(N-3) = a1 + a1(2 + 3 + 4 + 5 + ... + (N-3))         = a1 * (N-3)(N-2)/2
*                                a(N-2) = a1 + a1(2 + 3 + 4 + 5 + ... + (N-3) + (N-2))
*
*                                = a1(1 + 2 + 3 + 4 + 5 + ... + (N-2)) = a1 * (N-2)(N-1)/2
*
*                                (1 + 1 ^ 2) + (2 + 2 ^ 2) + (3 + 3 ^ 2) + (4 + 4 ^ 2) + ... + ((N-2) + (N-2) ^ 2))
*
*                                = 1 + 2 + 3 + (N-2) + 1^2 + 2^2 + 3^2 + (N-2)^2
*
*                                = (N-2)(N-1)/2 + (N-2)(N-1)(2N-1)/6 = (3N^2-9N+6)/6 + (2N^3-7N^2+7N-2)/6
*
*                                = (2N^3-4N^2-2N+4)/6
*
*                              (3N^3 - 6N^2)/6 + (-6N^3+30N^-36N)/6 + (2N^3-4N^2-2N+4)/6
*
*
* */

}
