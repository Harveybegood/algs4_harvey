package Chapter2_5_Applications;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
*   Load balancing. Write a program LPT.java that takes an integer M as a command-line argument, reads job names and
*   processing times from standard input and prints a schedule assigning the jobs to M processors that approximately
*   minimizes the time when the last job completes using the longest processing time first rule, as described on page 349.
*
*       consider to create a text which serves purpose as the standard input file.
*
*
* */
public class Ex13_LoadBalancing{
    private static class Job implements Comparable<Job> {
        private String jobName;
        private int processingTime;
        public Job(String job, int time){
            this.jobName = job;
            this.processingTime = time;
        }
        public String toString(){
            return jobName + " " + processingTime;
        }
        public int compareTo(Job that){
            if (this.processingTime > that.processingTime){return -1;}
            else if (this.processingTime < that.processingTime){return 1;}
            return 0;
        }
    }
    private static class Processor implements Comparable<Processor>{
        private String processorName;
        private int processingTimeOfAssignedJob;
        private List<Job> jobAssigned;
        public Processor(String name){
            this.processorName = name;
            this.processingTimeOfAssignedJob = 0;
            jobAssigned = new ArrayList<>();
        }
        public void assignJob(Job job){
            jobAssigned.add(job);
        }
        public String toString(){
            return processorName;
        }
        public int compareTo(Processor that){
            if (this.processingTimeOfAssignedJob > that.processingTimeOfAssignedJob){return 1;}
            else if (this.processingTimeOfAssignedJob < that.processingTimeOfAssignedJob){return -1;}
            else {return 0;}
        }
    }
    public static void LPT(int m){
        String[] input = StdIn.readAllLines();
        Job[] jobs = new Job[input.length];
        int index = 0;
        for (String jobLine : input){
            String[] allJobs = jobLine.split(" ");
            String name = allJobs[0];
            int time = Integer.parseInt(allJobs[1]);
            jobs[index++] = new Job(name, time);
        }
        Arrays.sort(jobs);
        MinPQ<Processor> pq = new MinPQ<>();
        for (int i = 0; i < m; i++){
            Processor processor = new Processor("ProcessName " + i);
            pq.insert(processor);
        }
        loadBalancingAndPrintSchedule(jobs, pq);
    }
    public static void loadBalancingAndPrintSchedule(Job[] jobs, MinPQ<Processor> pq){
        for (int i = 0; i < jobs.length; i++){
            Processor nextAvailable = pq.delMin();
            nextAvailable.assignJob(jobs[i]);
            nextAvailable.processingTimeOfAssignedJob += jobs[i].processingTime;
            StdOut.println(jobs[i].jobName + " assigned to " + nextAvailable);
            pq.insert(nextAvailable);
        }
    }
    public static void main(String[] args) {
        int m = Integer.parseInt(args[0]);
        LPT(m);
    }
}
