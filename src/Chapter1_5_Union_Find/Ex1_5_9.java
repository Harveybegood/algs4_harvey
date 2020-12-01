package Chapter1_5_Union_Find;
/*
*   Draw the tree corresponding to the id[] array depicted at right. Can this be the result of running weighted
*   quick-union? Explain why this is impossible or give a sequence of operations that results in this way.
*
* */
public class Ex1_5_9 {
    /*
    *   Original data distribution:
    *   i   0 1 2 3 4 5 6 7 8 9
    * id[i] 0 1 2 3 4 5 6 7 8 9
    *
    *   Final data distribution:
    *   i   0 1 2 3 4 5 6 7 8 9
    * id[i] 1 1 3 1 5 6 1 3 4 5
    *
    *
    * */

    /*
    *       Tree map:
    *
    *          1
    *
    *     0    3     6
    *
    *        2   7     5
    *
    *                4   9
    *
    *              8
    *
    * */

    /*
    *   One of weighted quick-union's traits is small tree connected to big tree.
    *   according to tree, at first, sz[1] = 4 (sz[1]itself + sz[0] + sz[3] + sz[6]).
    *
    *   we assume that sz[6] = 2 - > sz[5] + sz[6], then unfortunately, sz[5] = 3 -> sz[5] + sz[4] + sz[9].
    *   then come to a conclusion that weighted quick-union not applicable due to the size of sz[5] > sz[6].
    *
    *   the other of weighed quick-union's traits is that the depth of any node in a forest for N sites is at most lg N.
    *
    *   From the tree above, we see the depth is 5. then come to lg 10 -> 2^x = 10 -> < 4.
    *
    * */
}
