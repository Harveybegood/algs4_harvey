package Chapter1_3_BagsQueuesStacks;

import edu.princeton.cs.algs4.StdOut;

/*
*   Ring buffer. A ring or circular queue, is a FIFO data structure of a fixed size N. It is useful for transferring data
*   between asynchronous processes or for storing log files. When the buffer is empty, the consumer waits until data is
*   deposited; when the buffer is full, the producer waits to deposit data. Develop an API for a RingBuffer and an
*   implementation that uses an array representation(with circular wrap-around).
*
* */
@SuppressWarnings("unchecked")
public class Ex39_RingBuffer<Item> {
    private int n;
    private int i;
    private Item[] items;
    public Ex39_RingBuffer(int cap){
        items = (Item[]) new Object[cap];
        n = 0;
        i = 0;
    }
    public boolean isEmpty(){return n == 0;}
    public int size(){return n;}
    public void insert(Item item){
        items[n] = item;
        n++;
    }
    public Item delete(){
        Item item = items[i];
        i++;
        n--;
        return item;
    }
    public void ringBufferImplement(Item item){
        // buffer is empty
        if (n == 0){
            for (int i = 0; i < items.length; i++){
                insert(item);
            }

        }

        // buffer is full
        else {
            for (int i = 0; i < items.length; i++){
                delete();
            }
        }

    }

    public static void main(String[] args) {
        Ex39_RingBuffer<String> ringBuffer = new Ex39_RingBuffer<>(5);
        ringBuffer.ringBufferImplement("a");
        StdOut.println("Buffer is 5: " + ringBuffer.size());
        ringBuffer.ringBufferImplement("a");
        StdOut.println("Buffer is 0: " + ringBuffer.size());
    }
}
