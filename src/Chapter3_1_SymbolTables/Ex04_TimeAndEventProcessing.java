package Chapter3_1_SymbolTables;

import edu.princeton.cs.algs4.StdOut;

/*   keys    values
*   09:00:00 Chicago
    09:00:03 Phoenix
    09:00:13 Houston
    09:00:59 Chicago
    09:01:10 Houston
    09:03:13 Chicago
    09:10:11 Seattle
    09:10:25 Seattle
    09:14:25 Phoenix
    09:19:32 Chicago
    09:19:46 Chicago
    09:21:05 Chicago
    09:22:43 Seattle
    09:22:54 Seattle
    09:25:52 Chicago
    09:35:21 Chicago
    09:36:14 Seattle
    09:37:44 Phoenix

    Develop Time and Event ADTs that allow processing of data as in the example illustrated on page 367.
*
* */
@SuppressWarnings("unchecked")
public class Ex04_TimeAndEventProcessing {
    public static class Time implements Comparable<Time>{
        private double hour;
        private double minute;
        private double second;
        public Time(double hour, double minute, double second){
            this.hour = hour;
            this.minute = minute;
            this.second = second;
        }
        public String toString(){
            return hour + " : " + minute + " : " + second;
        }
        public int compareTo(Time time){
            if (this.hour < time.hour) return -1;
            else if (this.hour > time.hour) return 1;
            else if (this.minute < time.minute) return -1;
            else if (this.minute > time.minute) return 1;
            else if (this.second < time.second) return -1;
            else if (this.second > time.second) return 1;
            else return 0;
        }
    }
    public static class Event{
        private String address;
        public Event(String address){
            this.address = address;
        }
        public String toString(){
            return address;
        }
    }
    private int N;
    private Time[] times;
    private Event[] events;
    public Ex04_TimeAndEventProcessing(int cap){
        times = (Time[]) new Comparable[cap];
        events = (Event[]) new Object[cap];
    }
    public void resize(int newSize){
        Time[] auxT = (Time[]) new Comparable[newSize];
        Event[] auxE = (Event[]) new Object[newSize];
        for (int i = 0; i < N; i++){
            auxT[i] = times[i];
            auxE[i] = events[i];
        }
        times = auxT;
        events = auxE;
    }
    public boolean isEmpty(){return N == 0;}
    public int size(){return N;}
    public int rank(Time time){
        int lo = 0;
        int hi = N - 1;
        while (lo <= hi){
            int mid = lo + (hi - lo)/2;
            int cmp = time.compareTo(times[mid]);
            if (cmp < 0){return hi = mid - 1;}
            else if (cmp > 0){return lo = mid + 1;}
            else return mid;
        }
        return lo;
    }
    public void put(Time time, Event event){
        int i = rank(time);
        if (N == this.size() / 2){
            resize(N * 2);
        }
        if (i < N && time.compareTo(times[i]) == 0){
            events[i] = event;
            return;
        }
        for (int j = N; j > i; j--){
            times[j] = times[j-1];
            events[j] = events[j-1];
        }
        times[i] = time;
        events[i] = event;
        N++;
    }
    public Event get(Time time){
        int i = rank(time);
        if (N == this.size() / 4){
            resize(N / 2);
        }
        if (time.compareTo(times[i]) == 0){
            return events[i];
        }
        else {
            return null;
        }
    }
   /* public Iterable<Time> times(){
        Queue<Time> times = new Queue<>();
        for (int i = 0; i < times.size(); i++){
            times.enqueue(times[i]);
        }
    }*/
    public static void main(String[] args) {
        //Ex04_TimeAndEventProcessing<Time, Event> searchST = new Ex04_TimeAndEventProcessing(2);
        binarySearchST<Time, Event> searchST = new binarySearchST(6);
        searchST.put(new Time(9, 10, 1), new Event("Chicago"));
        searchST.put(new Time(9, 22, 1), new Event("Phoenix"));
        searchST.put(new Time(9, 34, 2), new Event("Houston"));
        searchST.put(new Time(9, 3, 1), new Event("Seattle"));
        searchST.put(new Time(9, 2, 1), new Event("Chicago"));
        searchST.put(new Time(9, 10, 11), new Event("Houston"));

        /*for (int i = 0; i < searchST.size(); i++){
            StdOut.println(searchST.get(searchST.times[i]) + " " + searchST.events[i]);
        }*/
        for (Time time : searchST.keys()){
            StdOut.println(time + " " + searchST.get(time));
        }
    }
}
