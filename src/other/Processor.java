package other;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;

public class Processor {
    private Queue<Object> taskQueue;
    private TimePeriodGenerator procesedTimeGenerator;
    private boolean isFree;
    private static int processedTasks;
    private static int taskInQueue;

    public Processor(double intensity) {
        this.taskQueue = new LinkedList<Object>();
        this.procesedTimeGenerator = new TimePeriodGenerator(intensity);
        this.isFree = true;
    }

    public void setTask(Object task) {
        if (isFree) {
            int timeToProcess = procesedTimeGenerator.getInterval();
            //Refactor
            new Timer().schedule(new TimerTask() { 
                @Override
                public void run() {
                    //System.out.println("Task processed "+taskQueue.size());
                    isFree = true;
                    processedTasks++;
                    taskInQueue+=taskQueue.size();
                    if(taskQueue.size()>0){
                        setTask(taskQueue.poll());
                    }
                }
            }, timeToProcess);
            isFree = false;
        }else{
            taskQueue.add(task);
        }
    }
    public static int getProcessedTask(){
        return processedTasks;
    }
    
    public static int getCountTasksInQueue(){
        return  taskInQueue;
    }
}
