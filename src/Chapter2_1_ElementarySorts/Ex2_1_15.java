package Chapter2_1_ElementarySorts;

/*
*   Expensive exchange. A clerk at a shipping company is charged with the task of rearranging a number of large crates
*   in order of the time they are to be shipped out. Thus, the cost of compares is very low(just look at the labels)
*   relative to the cost exchanges(move the crates). THe warehouse is nearly full - there is extra space sufficient to
*   hold any one of the crates, but not two. What sorting method should the clerk use?
*
* */
public class Ex2_1_15 {
    private static long select = 0;
    @SuppressWarnings("unchecked")
    public static void selection(Comparable[] a){
        int N = a.length;
        for (int i = 0; i < N; i++){
            int min = i;
            for (int j = i + 1; j < N; j++){
                if (a[j].compareTo(a[min]) < 0){
                    min = j;
                }
            }
            select++;
            Comparable temp = a[min];
            a[min] = a[i];
            a[i] = temp;
        }
    }
    private static long insert = 0;
    @SuppressWarnings("unchecked")
    public static void insertion(Comparable[] a){
        int N = a.length;
        for (int i = 1; i < N; i++){
            for (int j = i; j > 0 && a[j].compareTo(a[j-1]) < 0; j--){
                insert++;
                Comparable temp = a[j];
                a[j] = a[j-1];
                a[j-1] = temp;
            }
        }
    }
    private static long shell = 0;
    @SuppressWarnings("unchecked")
    public static void shellSort(Comparable[] a){
        int N = a.length;
        int h = 1;
        while (h < N / 3){
            h = h * 3 + 1;
        }
        while (h >= 1){
            for (int i = h; i < N; i++){
                for (int j = i; j >= h && a[j].compareTo(a[j-1]) < 0; j -= h){
                    shell++;
                    Comparable temp = a[j];
                    a[j] = a[j-1];
                    a[j-1] = temp;
                }
            }
            h =  h / 3;
        }
    }

    public static void main(String[] args) {

    }
}
