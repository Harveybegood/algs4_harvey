package Chapter2_5_Applications;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/*
*   Scheduling. Write a program SPT.java that reads job names and processing times from standard input and prints a
*   schedule that minimizes average completion time using the shortest processing time first rule. as described on
*   page 349.
*
*       beginning here with the use of sorting in a classic OR problem known as scheduling. Suppose that
        we have N jobs to complete, where job j requires tj seconds of processing time. We need
        to complete all of the jobs but want to maximize customer satisfaction by minimizing
        the average completion time of the jobs. The shortest processing time first rule, where we
        schedule the jobs in increasing order of processing time, is known to accomplish this
        goal. Therefore we can sort the jobs by processing time or put them on a minimum oriented priority queue.
* */
public class Ex12_Scheduling {
    // develop a class to read job names and processing times
    public static class Job implements Comparable<Job>{
        private String name;
        private Double time;
        public Job(String name, double time){
            this.name = name;
            this.time = time;
        }
        public String toString(){
            return name + " " + time;
        }

        @Override
        public int compareTo(Job o) {

            if (this.time > o.time){
                return 1;
            }
            else if (this.time < o.time){
                return -1;
            }
            else {
                return 0;
            }
        }
    }
    public static void main(String[] args) {
        int n = StdIn.readInt();
        Job[] jobs = new Job[n];
        for (int i = 0; i < n; i++){
            Double time = StdIn.readDouble();
            String name = StdIn.readString();
            jobs[i] = new Job(name, time);
        }
        Arrays.sort(jobs);
        for (int i = 0; i < n; i++){
            StdOut.println(jobs[i]);
        }
    }
}
