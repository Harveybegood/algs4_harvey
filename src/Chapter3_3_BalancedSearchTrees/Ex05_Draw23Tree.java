package Chapter3_3_BalancedSearchTrees;
/*
*   The figure at right shows all the structurally different 2-3 trees with N keys, for N from 1 up to 6 (ignore the order
*   of the subtree). Draw all the structurally different trees for N = 7, 8, 9, and 10.
*
* */
public class Ex05_Draw23Tree {
    /*
    *       N = 6,
    *                   (  )
    *
    *               ()  ()  (  )
    *
    *       N =7,
    *                   (  )                      ()
    *
    *               (  ) () (  )            ()          ()
    *
    *                                   ()      ()   ()     ()
    *
    *
    *       N = 8,
    *                   (  )                        ()
    *
    *              (  ) (  ) (  )             ()          ()
    *
    *                                   ( )       ()   ()     ()
    *
    *
    *       N = 9,
    *                    ()                         ()                        ()
    *
    *              ()          ()               ( )     ()              ()          ()
    *
    *          (  )  (  )   ()    ()        () ()  ()  () ()        ( )    ()   ()      ( )
    *
    *
    *       N = 10,
    *                   ()                      ()
    *
    *              ()        ()             ( )     ()
    *
    *          (  ) (  )   (  ) ()      () () ( )  () ()
    *
    *
    *
    *       The principle: a new node to be inserted will be comparing until null link from root, then satisfy two conditions
    *       : order and balance
    *
    *
    *
    *
    * */
}
