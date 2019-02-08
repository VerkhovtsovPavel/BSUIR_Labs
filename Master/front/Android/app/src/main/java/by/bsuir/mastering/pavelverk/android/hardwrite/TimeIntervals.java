package by.bsuir.mastering.pavelverk.android.hardwrite;

import java.util.Date;

public class TimeIntervals {

    private long lastTime = new Date().getTime();

    public long currentInterval() {
        long newTime = new Date().getTime();
        long interval = newTime - this.lastTime;
        this.lastTime = newTime;
        return interval;
    }
}
