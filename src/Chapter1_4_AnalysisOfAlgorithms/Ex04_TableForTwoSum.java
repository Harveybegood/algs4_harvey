package Chapter1_4AnalysisOfAlgorithms;
/*
*   Develop a table like the one on page 181 for TwoSum.
*
* */
public class Ex04_TableForTwoSum {
    public static int count(int[] a){
        int N = a.length;
        int cnt = 0;
        for (int i = 0; i < N; i++){
            for (int j = i + 1; j < N; j++){
                if (a[i] + a[j] == 0){
                    cnt++;
                }
            }
        }
        return cnt;
    }


    /*
    *
    *
    *
    *
    *       statement block      time in seconds       frequency                        total time
    *
    *        int N = a.length;                          -----> A = 1
             int cnt = 0;                               -----> A = 1
             for (int i = 0; i < N; i++){               -----> B = N

               for (int j = i + 1; j < N; j++){         -----> C = N(a1 + aN)/2 ~~ N^2/2

                  if (a[i] + a[j] == 0){                -----> D = N(a1 + aN)/2 ~~ N^2/2

                        cnt++;                          -----> x

                    }
                }
            }
            return cnt;                                 ------> A = 1
    *
    *
    *
    *
    *
    *
    *
    *
    *
    *
    *
    *
    *
    *
    * */
}
