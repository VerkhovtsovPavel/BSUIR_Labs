package main;

import other.Processor;
import other.TimePeriodGenerator;

public class Main {
	private static Processor[] querySystems;
	private static TimePeriodGenerator taskInterval;
	private static int countOfQuerySystems = 3;
	private static long GeneratedTaskCounter;
	private static double intensityOfService = 4.5;
	private static double intensityOfTasks = 12;
	private static int workHours = 10000;

	public static void main(String[] args) {
		long workTime = workHours * 3600;

		taskInterval = new TimePeriodGenerator(intensityOfTasks);
		createQuerySystems();

		int nextTaskInterval = taskInterval.getInterval();
		startWork(nextTaskInterval, workTime);

		statictic(workTime);

	}

	private static void statictic(long workTime) {
		System.out.println("Work time> " +workTime/3600+" hours");
		System.out.println("Generated task> " + GeneratedTaskCounter);
		System.out.println("Processed task> " + Processor.getProcessedTask());

		System.out.println("Absolutely bandwidth> "
				+ (double) Processor.getProcessedTask() / workHours
				+ " task/hours");

		System.out.println("Average queue length> "
				+ (float) Processor.getCountTasksInQueue() / (workTime * countOfQuerySystems));
		System.out.println("Average time in queue> "
				+ Processor.getAverageTimeInQueue() + " hours");

	}

	private static void createQuerySystems() {
		querySystems = new Processor[countOfQuerySystems];
		for (int i = 0; i < countOfQuerySystems; i++) {
			querySystems[i] = new Processor(intensityOfService);
		}
	}

	private static void startWork(int nextTaskInterval, long workTime) {
		for (int i = 0; i < workTime; i++) {
			if (nextTaskInterval == 0) {
				querySystems[(int) (GeneratedTaskCounter % countOfQuerySystems)]
						.setTask(GeneratedTaskCounter);
				nextTaskInterval = taskInterval.getInterval();
				GeneratedTaskCounter++;
			}
			nextTaskInterval--;
			decrimentQuerySystem();

		}
	}

	private static void decrimentQuerySystem() {
		for (Processor proc : querySystems) {
			proc.decrimentTimer();
		}
	}

}
