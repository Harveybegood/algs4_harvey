package Chapter1_4_AnalysisOfAlgorithms;

public class myStopwatch {
    private final long start;
    //private double end = 0.0;
    /*public myStopwatch(){
    }*/
    public myStopwatch(){
        start = System.currentTimeMillis();
    }
    public double elapseTime(){
        long now = System.currentTimeMillis();
        return (now - start) / 1000.0;
    }
}
