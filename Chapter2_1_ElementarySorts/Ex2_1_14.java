package Chapter2_1_ElementarySorts;

import edu.princeton.cs.algs4.StdOut;

import static Tool.ArrayGenerator.intsVrgWithEachAmount;

/*
*   Dequeue sort. Explain how you would sort a deck of cards, with restriction that the only allowed operations are to
*   look at the values of the top two cards, to exchange the top two cards, and to move the top card to the bottom
*   of the deck.
*
* */
public class Ex2_1_14 {
    static class wrapper{
        int top, topNext;
        wrapper(int to, int topN){
            top = to;
            topNext = topN;
        }
        boolean isReversedOrder(){return top > topNext;}
        public String toString(){return String.format("{%d %d}", top, topNext);}
    }
    static class Queue{
        private int[] items = new int[2];
        private int head, tail, size;
        boolean isEmpty(){return  size == 0;}
        int size(){return size;}
        Queue(){}
        Queue(int[] org){
            for (int i : org){
                enqueue(i);
            }
        }
        void reSize(int newSize){
            int[] newItems = new int[newSize];
            int cur = head, k = 0;
            do {
                newItems[k++] = items[cur];
                cur = (cur+1) % items.length;
            }while (cur != tail);
            items = newItems;
            head = 0;
            tail = size;
        }
        void enqueue(int item){
            if (size == items.length){
                reSize(2 * size);
            }
            size++;
            items[tail] = item;
            tail = (tail + 1) % items.length;
        }
        int dequeue(){
            if (size == 0){
                throw new RuntimeException("dequeue from a empty queue");
            }
            --size;
            int del = items[head++];
            if (head == items.length) head = 0;
            if (size > 0 && size == items.length / 4){
                reSize(items.length / 2);
            }
            return del;
        }
        boolean hasReversedPair(){
            int cur = head, pre = head;
            while (true){
                cur = (cur + 1) % items.length;
                if (cur == tail) return false;
                if (items[cur] < items[pre])return true;
                pre = cur;
            }
        }
        void print(){
            int cur = head;
            do{
                StdOut.print(items[cur] + " ");
                cur = (cur + 1) % items.length;
            }while (cur != tail);
            StdOut.println();
        }
        public String toString(){
            StringBuilder stringBuilder = new StringBuilder();
            for (int i : items){
                stringBuilder.append(i + "");
            }
            return stringBuilder.toString();
        }
        wrapper exchangeTopTwo(){
            if (size < 2){
                throw new RuntimeException("queue items les than required");
            }
            int t = items[head];
            int nextIndex = (head + 1) % items.length;
            items[head] = items[nextIndex];
            items[nextIndex] = t;
            return new wrapper(items[head], items[nextIndex]);
        }
        wrapper peekTopTwo(){
            if (size < 2){
                throw new RuntimeException("Queue items less than required");
            }
            return new wrapper(items[head], items[(head+1) % items.length]);
        }
        void makeHeadEnqueueAgain(){
            enqueue(dequeue());
        }
    }
    public static void dequeueSort(Queue queue){
        int i = queue.size();
        while (queue.hasReversedPair()){
            while (--i > 0){
                if (queue.peekTopTwo().isReversedOrder()){
                    queue.exchangeTopTwo();
                }
                queue.makeHeadEnqueueAgain();
            }
            queue.makeHeadEnqueueAgain();
            i = queue.size();
        }
    }

    public static void main(String[] args) {
        int[] array = intsVrgWithEachAmount(13, 1,2,3,4);
        Queue queue= new Queue(array);
        StdOut.println("=====================before sorting===================");
        queue.print();
        dequeueSort(queue);
        StdOut.println("======================after sorting====================");
        queue.print();
    }
}
