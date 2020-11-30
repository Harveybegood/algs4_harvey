package Chapter1_5_Union_Find;

//import Chapter1_3_Bags_Queues_Stacks.bag.Ex1_3_34;


import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;


/*
*   Random grid generator. Write a program RandomGrid that takes an int value N from the command line, generates all
*   the connections in an N-by-N grid, puts them in random order, randomly orients them(so that p q and q p are equally
*   likely to occur), and prints the result to standard output. To randomly order the connections, use a RandomBag
*   (see exercise 1.3.34 on page 167). To encapsulate p and q in a single object, use the Connection nested class shown
*   below. Package your program as two static methods: generate(), which takes N as argument and returns an array of
*   connections, and main(), which takes N from the command line, calls generate(), and iterates through the returned
*   array to print the connections.
*
* */
public class Ex1_5_18 {
    static RandomBag<Connection> randomGridGenerator(int N) {
        RandomBag<Connection> rb = new RandomBag<Connection>();
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N - 1; j++)
                if (StdRandom.bernoulli(0.5))
                    rb.add(new Connection(i * N + j, i * N + j + 1));
                else
                    rb.add(new Connection(i * N + j + 1, i * N + j));

        for (int i = 0; i < N - 1; i++)
            for (int j = 0; j < N; j++)
                if (StdRandom.bernoulli(0.5))
                    rb.add(new Connection(i * N + j, i * N + N + j));
                else
                    rb.add(new Connection(i * N + N + j, i * N + j));
        return rb;
    }
    static class Connection {
        int p, q;
        Connection(int p, int q) {
            this.p = p;
            this.q = q;
        }
        public String toString() {  return String.format("{ %d, %d }", p, q); }
    }
    static class RandomBag<T> implements Iterable<T> {
        @SuppressWarnings("unchecked")
        private T[] items = (T[])new Object[1];
        private int size;
        RandomBag() {}
        boolean isEmpty() { return size == 0; }
        int size() { return size; }
        void resize(int newSize) {
            @SuppressWarnings("unchecked")
            T[] newItems = (T[])new Object[newSize];
            for (int i = 0; i < size; i++)
                newItems[i] = items[i];
            items = newItems;
        }
        void add(T item) {
            if (size == items.length)
                resize(size * 2);
            items[size++] = item;
        }
        public Iterator<T> iterator() {
            return new Iterator<T>() {
                private int index;
                @SuppressWarnings("unchecked")
                private T[] random = (T[])new Object[size];
                {
                    for (int i = 0; i < size; i++)
                        random[i] = items[i];
                    for (int i = 0; i < size; i++) {
                        int r = i + StdRandom.uniform(size - i);
                        T tmp = random[r];
                        random[r] = random[i];
                        random[i] = tmp;
                    }
                }
                public boolean hasNext() { return index < size; }
                public T next() { return random[index++]; }
            };
        }
    }
    public static void main(String[] args) {
        for (Connection c : randomGridGenerator(5))
            StdOut.println(c);
    }
}
