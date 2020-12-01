package Chapter1_3_Bags_Queues_Stacks;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.StringJoiner;

/*
*   Ring buffer. A ring buffer, or circular queue, is a FIFO data structure of a fixed size N. It is useful
*   for transferring data between asynchronous processes or for storing log files. When the buffer is empty,
*   the consumer waits until data is deposited; when the buffer is full, the producer waits to deposit data.
*   Develop an API for a RingBuffer and an implementation that uses an array representation
*   (with circular wrap-around).
*
* */
@SuppressWarnings("unchecked")
public class Ex39<Item> implements Iterable<Item> {
    private Item[] RingBuffer;
    private int size;
    private int first;
    private int last;
    private Queue<Item> producerBuffer;
    private int dataCountToBeConsumed;
    private Ex39(int cap){
        RingBuffer = (Item[]) new Object[cap];
        size = 0;
        first = -1;
        last = -1;
        producerBuffer = new Queue<>();
        dataCountToBeConsumed = 0;
    }

    public boolean isEmpty(){return size == 0;}
    public int size(){return size;}

    public void produce(Item item){
        if (dataCountToBeConsumed > 0){
            consumeData(item);
            dataCountToBeConsumed--;
        }else {
            if (isEmpty()){
                RingBuffer[size] = item;
                first = 0;
                last = 0;
                size++;
            }else {
                if (size < RingBuffer.length){
                    if (last == RingBuffer.length - 1)
                        {last = 0;} // wrap around
                    else
                        {last++;}
                    RingBuffer[last] = item;
                    size++;
                }else {
                    producerBuffer.enqueue(item);
                }
            }
        }
    }
    private void consumeData(Item item){StdOut.print("Data consumed: " + item);}

    public Item consume(){
        if (isEmpty()){
            dataCountToBeConsumed++;
            return null;
        }
        Item item = RingBuffer[first];
        RingBuffer[first] = null; // avoid loitering
        if (first == RingBuffer.length - 1){first = 0;} // wrap around
        else {first++;}
        size--;
        if (!producerBuffer.isEmpty()){produce(producerBuffer.dequeue());}
        return item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new RingBufferIterator();
    }
    private class RingBufferIterator implements Iterator<Item>{
        private int current = first;
        private int count = 0;
        public boolean hasNext(){return count < size;}
        public Item next(){
            Item item = RingBuffer[current];
            if (current == RingBuffer.length - 1){
                current = 0; //wrap around
            }else {current++;}
            count++;
            return item;
        }
    }

    public static void main(String[] args) {
        Ex39<Integer> ringBuffer = new Ex39<>(10);
        for (int i = 0; i < 4; i++){
            ringBuffer.produce(i);
        }
        Integer item1 = ringBuffer.consume();
        if (item1 != null){StdOut.println("Consumed " + item1);}
        Integer item2 = ringBuffer.consume();
        if (item2 != null){StdOut.println("Consumed " + item2);}
        ringBuffer.produce(4);
        ringBuffer.produce(5);
        StringJoiner ringBufferItems = new StringJoiner(" ");
        for (int item : ringBuffer){
            ringBufferItems.add(String.valueOf(item));
        }
        StdOut.println("ring buffer items stocked: " + ringBufferItems.toString());
    }
}
