package Chapter3_5_Applications;


import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdOut;

/*
*   Registrar scheduling. The registrar at a prominent northeastern University recently scheduled an instructor to teach
*   two different classes at the same exact time. Help the registrar prevent future mistakes by describing a method to
*   check for such conflicts. For simplicity, assume all classes run for 50 minutes starting at 9:00, 10:00, 11:00,
*   1:00, 2:00 and 3:00.
*
* */
@SuppressWarnings("unchecked")
public class Ex25_RegistrarScheduling {
    private ST<Integer, String> st;

    public Ex25_RegistrarScheduling(){
        st = new ST<>();
    }
    public void put(int i, String val){
        if (st.contains(i)){
            StdOut.println("The schedule for " + st.get(i) + " is already occupied");
            return;
        }
        st.put(i, val);
        StdOut.println("It is available for the time interval " + st.get(i) + " for scheduling");
    }

    public static void main(String[] args) {
        Ex25_RegistrarScheduling registrarScheduling = new Ex25_RegistrarScheduling();
        registrarScheduling.put(0, "9:00");
        registrarScheduling.put(1, "10.00");
        registrarScheduling.put(2, "11.00");
        registrarScheduling.put(3, "1.00");
        registrarScheduling.put(1, "10.00");
        registrarScheduling.put(4, "2.00");
        registrarScheduling.put(5, "3.00");
        registrarScheduling.put(5, "3.00");
    }
}
/*
*           It is available for the time interval 9:00 for scheduling
            It is available for the time interval 10.00 for scheduling
            It is available for the time interval 11.00 for scheduling
            It is available for the time interval 1.00 for scheduling
            The schedule for 10.00 is already occupied
            It is available for the time interval 2.00 for scheduling
            It is available for the time interval 3.00 for scheduling
            The schedule for 3.00 is already occupied
*
*
* */