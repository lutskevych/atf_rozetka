package framework.utils;

public class Timer {
    private long startTime;
    private double elapsedTime;

    public Timer startTimer(){
        startTime = System.nanoTime();
        return this;
    }

    public double getElapsedTime() {
        elapsedTime = (System.nanoTime() - startTime) / 1000000000.0;
        return elapsedTime;
    }

    public void printElapsedTime(String message) {
        StringBuilder sb = new StringBuilder()
                .append("ELAPSED_TIME: ").append(message).append(" = ").append(getElapsedTime()).append(" seconds");
        System.out.println(sb.toString());
    }
}
