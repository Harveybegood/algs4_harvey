package Chapter2_5_Applications;

import edu.princeton.cs.algs4.StdOut;

/*
*   Idle time. Suppose that a parallel machine processes N jobs. Write a program that given the list of job start and
*   finish times, finds the largest interval where the machine is idle and the largest interval where the machine
*   is not idle.
*
* */
public class Ex20_IdleTime {
    public static void main(String[] args) {
        //String[] strings =  StdIn.readString().split("\\s+");
  /*      String[] strings = {
                 "09:00:59", "19:11:10",
                 "09:01:10", "19:33:13",
                 "09:03:13", "19:10:11",
                 "09:10:11", "19:50:25",
                 "09:10:25", "19:24:25",
                 "09:14:25", "19:29:32",
                 "09:19:32", "19:39:46",
                 "09:19:46", "19:51:05",
                 "09:21:05", "19:32:43",
                 "09:22:43", "19:42:54",
        };*/
        Ex20_IdleTime ex20_idleTime = new Ex20_IdleTime();
        DataForIdleTime dataForIdleTime0 = ex20_idleTime.new DataForIdleTime(9, 0, 59);
        DataForIdleTime dataForIdleTime1 = ex20_idleTime.new DataForIdleTime(9, 11, 10);
        DataForIdleTime dataForIdleTime2 = ex20_idleTime.new DataForIdleTime(9, 16, 10);
        DataForIdleTime dataForIdleTime3 = ex20_idleTime.new DataForIdleTime(9, 20, 59);
        DataForIdleTime dataForIdleTime4 = ex20_idleTime.new DataForIdleTime(9, 30, 59);
        DataForIdleTime dataForIdleTime5 = ex20_idleTime.new DataForIdleTime(9, 50, 59);
        DataForIdleTime dataForIdleTime6 = ex20_idleTime.new DataForIdleTime(10, 10, 59);
        DataForIdleTime dataForIdleTime7 = ex20_idleTime.new DataForIdleTime(11, 0, 59);
        DataForIdleTime dataForIdleTime8 = ex20_idleTime.new DataForIdleTime(11, 10, 59);
        DataForIdleTime dataForIdleTime9 = ex20_idleTime.new DataForIdleTime(11, 30, 59);
        DataForIdleTime dataForIdleTime = ex20_idleTime.new DataForIdleTime();
        DataForIdleTime dataForNotIdleTime = ex20_idleTime.new DataForIdleTime();
        int MaxIdleTime1 = dataForIdleTime.differenceOfTime(dataForIdleTime1, dataForIdleTime0);
        int MaxIdleTime2 = dataForIdleTime.differenceOfTime(dataForIdleTime3, dataForIdleTime2);
        int MaxIdleTime3 = dataForIdleTime.differenceOfTime(dataForIdleTime5, dataForIdleTime4);
        int MaxIdleTime4 = dataForIdleTime.differenceOfTime(dataForIdleTime7, dataForIdleTime6);
        int MaxIdleTime5 = dataForIdleTime.differenceOfTime(dataForIdleTime9, dataForIdleTime8);
        int MaxNotIdleTime1 = dataForNotIdleTime.differenceOfTime(dataForIdleTime2, dataForIdleTime1);
        int MaxNotIdleTime2 = dataForNotIdleTime.differenceOfTime(dataForIdleTime4, dataForIdleTime3);
        int MaxNotIdleTime3 = dataForNotIdleTime.differenceOfTime(dataForIdleTime6, dataForIdleTime5);
        int MaxNotIdleTime4 = dataForNotIdleTime.differenceOfTime(dataForIdleTime8, dataForIdleTime7);
        StdOut.println(MaxIdleTime1 + " " + MaxIdleTime2 + " " + MaxIdleTime3 + " " + MaxIdleTime4 + " " + MaxIdleTime5);
        StdOut.println(MaxNotIdleTime1 + " " + MaxNotIdleTime2 + " " + MaxNotIdleTime3 + " " + MaxNotIdleTime4);
    }
/*
    private int idleTime(String[][] a){
        if (a[0][1].compareTo(a[0][2]) < 0){
            //StdOut.println("Okay");
            return 1;
        }
        return 0;
        //String[] s = a[0][1].split(":");
    }*/

    public  class DataForIdleTime{
        private int hour;
        private int min;
        private int second;
        public DataForIdleTime(int hour, int min, int second){
            this.hour = hour;
            this.min = min;
            this.second = second;
        }
        public DataForIdleTime(){}
        public int getHour(){
            return hour;
        }
        public int getMinute(){
            return min;
        }
        public int getSecond(){
            return second;
        }
        public String toString(){
            return hour + ":" + min + ":" + second;
        }
        private int differenceOfTime(DataForIdleTime t1, DataForIdleTime t2){
            int hour;
            int minute;
            int second;
            hour = t1.getHour() - t2.getHour();
            minute = t1.getMinute() - t2.getMinute();
            second = t1.getSecond() - t2.getSecond();
            return hour * 60 * 60 + minute * 60 + second;
        }
    }
}

