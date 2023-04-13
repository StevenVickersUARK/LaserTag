package edu.uark.csce3513.team18.lasertag;

import java.util.Timer;
import java.util.TimerTask;

public class CountdownTimer {
    private int count = 0;
    private Timer timer = new Timer();
    private boolean isRunning = false;

    public CountdownTimer(int count) {
        this.count = count;
    }

    public synchronized void start() {
        if (!isRunning) {
            timer.scheduleAtFixedRate(new TimerTask() {
                public void run() {
                    if (count > 0) {
                        count--;
                        System.out.println(count);
                    } else {
                        stop();
                    }
                }
            }, 0, 1000);
            isRunning = true;
        }
    }

    public synchronized void stop() {
        if (isRunning) {
            timer.cancel();
            isRunning = false;
        }
    }

    public int getCount() {
        return count;
    }
}
