package Chapter1_1_BasicProgrammingModel;

import edu.princeton.cs.algs4.StdOut;

/*
*   Binomial distribution. Estimate the number of recursive calls that would be used by the code
*
*   to compute binomial(100, 50, 0.25). Develop a better implementation that is based on saving computed values
*   in an array.
*
*   Knowledge: Binomial distribution,
*   to take 100 times of trials under the a 25% of success probability to generate 50 times of success
*
* */
public class Ex27 {
    //static int count = 0;
    static int count1 = 0;
    public static void main(String[] args) {
        /*binomial(10, 5, 0.25);
        StdOut.println(count);*/
        enhancedBinomia1(100, 50, 0.25);
        StdOut.println(count1);
    }
    // Binomial distribution
 /*   public static double binomial(int N, int k, double p){
        count++;
        if (N == 0 || (k < 0))
            return 1.0;
        return (1.0 - p) * binomial(N-1, k, p) + p*binomial(N-1, k-1, p);
    }*/

    // Enhanced Binomial distribution
    public static void enhancedBinomia1(int N, int k, double p){
        double[][] array = new double[N+1][k+1];
        for (int i = 0; i < array.length; i++){
            for (int j = 0; j < array[0].length; j++){
                array[i][j] = -2;
            }
        }
        StdOut.print(enhancedBinomial(array, N, k, p));
        StdOut.println();
    }
    public static double enhancedBinomial(double[][] array, int N, int k, double p){
        count1++;
        //StdOut.println(count1);
        if ((N == 0) && (k == 0)) return 1.0;
        if ((N < 0) || (k < 0)) return 0.0;
        if (array[N][k] == -2){
            array[N][k] = (1 - p)*enhancedBinomial(array, N - 1, k, p) + p*enhancedBinomial(array, N- 1, k - 1, p);
        }
        return array[N][k];
    }
}



/*
*
*                        (1.0 - p) * binomial(N-1, k, p)         +         p * binomial(N-1, k-1, p)
*                        (1.0-0.25)*binomial(4-1,1,0.25)        +         0.25*binomial(4-1,1-1,0.25)
*                                                                                N=4, K=1, p=0.25
*                                   (0.75)*binomial(3, 1, 0.25)                           +                                  0.25*binomial(3, 0, 0.25)
*
*                         .75*binomial(2,1,.25)          +                     .25*binomial(2,0,.25)               +             (.75)*binomial(2,0,.25)        +       .25*binomial(2,-1,.25)
*
*                (.75)*b(1,1.25)         +        .25*b(1,0,.25)         +          .75*b(1,0,.25)      +      .25b(1,-1,.25)     +      (.75)*b(1,0,.25)     +     (.25)*b(1,-1,.25)    +     1
*
        .75*b(0,1,.25) + .25*b(0,0,.25) + .75*b(0,0,.25) + .25*b(0,-1,.25) + .75*b(0,0,.025) + .25*b(0,-1,.25)  +      1       +    .75*b(0,0,.25) + .25*b(0,-1,.25)    +     1    +      1

*
* */