package other;

import java.util.LinkedList;
import java.util.Queue;

public class Processor {
    private Queue<Object> taskQueue;
    private TimePeriodGenerator procesedTimeGenerator;
    private boolean isFree;
    private int timeToProcess;
    private static int timeTaskInQueue;
    private static int timeTaskInSystem;
    private static int processedTasks;
    private static int taskInQueue;
    private static int taskInSystem;

    public Processor(double intensity) {
        this.taskQueue = new LinkedList<Object>();
        this.procesedTimeGenerator = new TimePeriodGenerator(intensity);
        this.isFree = true;
    }

    public void setTask(Object task) {
        if (isFree) {
            timeToProcess = procesedTimeGenerator.getInterval();
            timeTaskInSystem += timeToProcess;
            isFree = false;
        } else {
            taskQueue.add(task);
        }
    }

    public static int getProcessedTask() {
        return processedTasks;
    }

    public static int getCountTasksInQueue() {
        return taskInQueue;
    }
    
    public static int getCountTasksInSystem() {
        return taskInSystem;
    }

    public static float getAverageTimeInQueue() {
        return (float) timeTaskInQueue / processedTasks / 3600;
    }

    public static float getAverageTimeInSystem() {
        return (float) timeTaskInSystem / processedTasks / 3600;
    }

    public void decrimentTimer() {
        taskInQueue += taskQueue.size();
        taskInSystem += taskQueue.size();
        if (!isFree) {
            taskInSystem += 1;
        }
        timeTaskInQueue += taskQueue.size();
        timeTaskInSystem += taskQueue.size();
        if (!isFree && timeToProcess == 0) {
            isFree = true;
            processedTasks++;
            if (taskQueue.size() > 0) {
                setTask(taskQueue.poll());
                timeToProcess = procesedTimeGenerator.getInterval();
            }
        } else if (timeToProcess == 0) {

        } else {
            timeToProcess--;
        }

    }
}
