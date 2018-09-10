package framework.utils;

import org.apache.log4j.Logger;

public class Timer {
    private long startTime;
    private double elapsedTime;
    public Logger logger = Logger.getLogger(Timer.class);

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
        logger.info(sb.toString());
    }
}
