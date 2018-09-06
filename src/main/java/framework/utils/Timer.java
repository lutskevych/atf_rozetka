package framework.utils;


public class Timer {
    private long startTime;
    private long stopTime;
    private double elapsedTime;

    public Timer startTimer(){
        startTime = System.nanoTime();
        return this;
    }

    public Timer stopTimer(){
        stopTime = System.nanoTime();
        return this;
    }

    public double getElapsedTime() {
        return elapsedTime = (stopTime - startTime) / 1000000000.0;
    }

    public void printElapsedTime() {
        printElapsedTime("");
    }

    public void printElapsedTime(String message) {
        StringBuilder sb = new StringBuilder()
                .append(">>>ELAPSED_TIME: ").append(message).append(" = ").append(getElapsedTime()).append(" seconds");
        System.out.println(sb.toString());
    }

    public Timer resetTimer() {
        startTime = 0;
        stopTime = 0;
        return this;
    }
}
