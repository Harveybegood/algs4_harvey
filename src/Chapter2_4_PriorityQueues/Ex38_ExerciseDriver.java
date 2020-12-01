package Chapter2_4_PriorityQueues;
/*
*   Exercise driver. Write an exercise drive client program that uses the methods in our priority-queue interface of
*   Algorithm 2.6 on difficult or pathological cases that might turn up in practical applications. Simple examples include
*   keys that are already in order
*   keys in reverse order
*   all keys the same,
*   sequence of keys having only two distinct values.
*
* */
@SuppressWarnings("unchecked")
public class Ex38_ExerciseDriver {
    private static void test(MaxPQ<Integer> maxPQ, Comparable[] keys){
        for (int i = 0; i < keys.length; i++){
            maxPQ.insert((int) keys[i]);
        }
        while (maxPQ.size() > 0){
            maxPQ.delMax();
        }
    }
    public static void main(String[] args) {
        //Ex38_ExerciseDriver ex38_exerciseDriver = new Ex38_ExerciseDriver();
        // keys that are already in order
        int length = 100;
        Comparable[] arrayInOrder = new Comparable[length];
        for (int i = 0; i < length; i++){
            arrayInOrder[i] = i;
        }
        test(new MaxPQ<>(length), arrayInOrder);
        // keys in reverse order
        for (int i = 0; i < length; i++){
            arrayInOrder[i] = --length;
        }
        test(new MaxPQ<>(length), arrayInOrder);
        // all keys the same
        for (int i = 0; i < length; i++){
            arrayInOrder[i] = 1;
        }
        test(new MaxPQ<>(length), arrayInOrder);
        // sequence of keys having only two distinct values
        for (int i = 0; i < length; i++){
            if(i < length / 2){
                arrayInOrder[i] = 0;
            }{
                arrayInOrder[i] = 1;
            }
        }
        test(new MaxPQ<>(length), arrayInOrder);
    }
}
