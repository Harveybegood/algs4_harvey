package Chapter1_2_DataAbstraction;


import edu.princeton.cs.algs4.StdOut;

/*
*   Add a method dayOfTheWeek() to SmartDate that returns a String value Monday, Tuesday, Wednesday, Thursday, Friday,
*   Saturday, or Sunday, giving the appropriate day of the week for the date. You may assume that the date is in the
*   21st century.
* */
public class Ex12 {
    public class SmartDate{
        //private final String firstDayOfTheFirstWeekOf2000 = "Saturday";
        private final String[] s = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        private final int[] daysOfLeap = {0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        private final int[] dayOfNonLeap = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        private String month;
        private int day;
        private int year;
        public SmartDate(String m, int d, int y){
            if (isValidDate(m, d, y)){
                throw new IllegalArgumentException("Illegal date input! ");
            }
            this.day = d;
            this.month = m;
            this.year = y;
        }
        public String getMonth(){
            return month;
        }
        public int getDay(){
            return day;
        }
        public int getYear(){
            return year;
        }
        public String toString(){
            return month + "/" + day + "/" + year;
        }
        public boolean isValidDate(String m, int d, int y){

            int i = 0;
            if (!(m.equals(s[i++]) && i < 12)){
                return false;
            }
            if (d < 1 || d > daysOfLeap[convertStringMToNumber(m)]){
                return false;
            }
            if (m.equals(s[1]) && d > 29 && isLeapYear(y)){
                return false;
            }
            return true;
        }
        public boolean isLeapYear(int y){
            if (y % 400 == 0) return true;
            if (y % 100 == 0) return false;
            return y % 4 == 0;
        }
        public int convertStringMToNumber(String m){
            int n = 0;
            for (int i = 0; i < 12; i++){
                if (m.equals(s[i]))
                    n = i + 1;
            }
            return n;
        }
        public String dayOfTheWeek(String m, int d, int y){
            int totalOfDays = 0;
            int DaysInLeap = 0;
            int DaysOutLeap = 0;
            int countOfDaysOfYearOnly;
            int countOfLeapYear = 0;
            for (int i = 2000; i <= y; i++){
                if (isLeapYear(i)){
                    countOfLeapYear++;
                }
            }
            countOfDaysOfYearOnly = countOfLeapYear * 366 + ((y + 1 - 2000) - countOfLeapYear) * 365;
            if (isLeapYear(y)){
                for (int i = 1; i < convertStringMToNumber(m); i++){
                    totalOfDays += daysOfLeap[i];
                }
                DaysInLeap = totalOfDays + d;
            }
            else {
                for (int i = 1; i < convertStringMToNumber(m); i++){
                    totalOfDays += dayOfNonLeap[i];
                }
                DaysOutLeap = totalOfDays + d;
            }
            totalOfDays = countOfDaysOfYearOnly - DaysInLeap - DaysOutLeap;
            for (int i = 1; i <= totalOfDays; i++){
                if (i % 7 == 1){
                    return "Saturday";
                }
                else if (i % 7 == 2){
                    return "Sunday";
                }
                else if (i % 7 == 3){
                    return "Monday";
                }
                else if (i % 7 == 4){
                    return "Tuesday";
                }
                else if (i % 7 == 5){
                    return "Wednesday";
                }
                else if (i % 7 == 6){
                    return "Thursday";
                }
                else if (i % 7 == 0){
                    return "Friday";
                }
            }
            return "Illegal day of week";
        }
    }

    public static void main(String[] args) {
        Ex12 ex12 = new Ex12();
        SmartDate smartDate = ex12.new SmartDate("Feb", 11, 2019);
        SmartDate smartDate1 = ex12.new SmartDate("Sep", 11, 2019);
        SmartDate smartDate2 = ex12.new SmartDate("September", 11, 2019);
        StdOut.println(smartDate);
        StdOut.println(smartDate1);
        StdOut.println(smartDate2);
        StdOut.println(smartDate.dayOfTheWeek("Feb", 13, 2019));
        StdOut.println(smartDate.dayOfTheWeek("Aug", 13, 2019));
        StdOut.println(smartDate.dayOfTheWeek("Aug", 14, 2019));
    }

}
