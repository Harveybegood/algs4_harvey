package Chapter1_4AnalysisOfAlgorithms;
/*
*   Amortized analysis. Prove that, starting from an empty stack, the number of array accesses used by any sequence of M operations
*   in the resizing array implementation of Stack is proportional to M.
*
* */
@SuppressWarnings("unchecked")
public class Ex32_AmortizedAnalysis<Item> {
    private int N;
    private Item[] a = (Item[]) new Object[1];
    private void resize(int max){
        Item[] items = (Item[]) new Object[max];
        for (int i = 0; i < N; i++){
            items[i] = a[i];
        }
        a = items;
    }
    public void push(Item item){
        if (N == a.length){
            resize(2 * N);
        }
        a[N++] = item;
    }
    public Item pop(){
        Item item = a[N - 1];
        N--;
        if (N == a.length / 4){
            resize(a.length / 2);
        }
        return item;
    }

    /*
    *       Suppose the initial size of stack based on array is 1.
    *           Operations       -       array accesses
    *      push         1                            1
    *      resizing     0
    *
    *      resizing     (1)                           1
    *      push         2                             2
    *
    *      resizing     (2)                           2
    *      push         4                             4
    *
    *      resizing     (4)                           4
    *      push         8                             8
    *
    *      resizing     (8)                           8
    *      push         16                            16
    *
    *      The number of array access for resizing array is a power of 2
    *      The number of array access for pushing to stack is also a a power of 2
    *
    *      resizing     (M/2)                           M/2
    *      push          M                              M
    *
    *      So, the equation for operations equals to 1 + 2 + 4 + 8 + 16 + ... + M = 1 * (1 - 2^n) / (1-2) = 2^n - 1
    *      then, the equation for array accesses is
    *       1 + 2 + 4 + 8 + ... + M/2 = 1 * (1 - 2 ^(n-1)) / (1-2) + 1 + 2 + 4 + 8 + 16 + ... + M
    *       = 2^(n-1) -1 + 2^n - 1
    *       = 3/2
    *       The proportion is ~~3/2
    *
    * */
}
