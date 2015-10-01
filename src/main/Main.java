package main;

import java.util.Timer;
import java.util.TimerTask;

import other.Processor;
import other.TimePeriodGenerator;

public class Main {
    private static Processor[] querySystems;
    private static TimePeriodGenerator taskInterval;
    private static int countOfQuerySystems = 3;
    private static long counter;
    private static double intensityOfService = 3.5;
    private static double intensityOfTasks = 12;
    private static int longwork = 96;
//TODO Rewrite using cycle, remove timers
    public static void main(String[] args) {
        long workTime = longwork * 500;
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Generated task> " + counter);
                System.out.println("Processed task> " + Processor.getProcessedTask());
                
                System.out.println("Absolutely bandwidth> " + (double) Processor.getProcessedTask()
                        / longwork + " task/hours");
                double absolutelyBandwidth = (double) Processor.getProcessedTask()/longwork;
                System.out.println("Average queue length> "
                        + (float) Processor.getCountTasksInQueue() / Processor.getProcessedTask());
                //System.out.println("Average time in queue> "+(float) Processor.getCountTasksInQueue() / Processor.getProcessedTask() / absolutelyBandwidth+" hours");
                System.exit(1);
            }
        }, workTime);
        taskInterval = new TimePeriodGenerator(intensityOfTasks);
        createQuerySystems();
        int nextTaskInterval = taskInterval.getInterval();
        startWork(nextTaskInterval);
    }

    private static void createQuerySystems() {
        querySystems = new Processor[countOfQuerySystems];
        for (int i = 0; i < countOfQuerySystems; i++) {
            querySystems[i] = new Processor(intensityOfService);
        }
    }

    private static void startWork(int nextTaskInterval) {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                querySystems[(int) (counter % countOfQuerySystems)].setTask(counter);
                int nextTaskInterval = taskInterval.getInterval();
                startWork(nextTaskInterval);
                counter++;
            }
        }, nextTaskInterval);

    }

}
