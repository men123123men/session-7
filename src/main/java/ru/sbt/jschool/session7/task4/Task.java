package ru.sbt.jschool.session7.task4;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Task {
    private static final int COUNT_OF_THREADS = 10;
    private static final int COUNT_OF_ONE_THREAD_INCREMENTS = 10;

    public static void main(String[] args) throws InterruptedException {
        System.out.printf("%nБудет запущенно %d потоков.%n" +
                        "Каждый из которых вызовет IntegerMutable.increment() по %d раз.%n%n",
                COUNT_OF_THREADS, COUNT_OF_ONE_THREAD_INCREMENTS);

        ExecutorService service = Executors.newFixedThreadPool(COUNT_OF_THREADS);
        IntegerMutable sharedInteger = new IntegerMutable(0);
        CountDownLatch latch = new CountDownLatch(COUNT_OF_THREADS);

        for (int i = 0; i < COUNT_OF_THREADS; i++)
            service.execute(new Incrementer(sharedInteger, COUNT_OF_ONE_THREAD_INCREMENTS, latch));

        service.shutdown();
        service.awaitTermination(1, TimeUnit.HOURS);  //while (!service.isTerminated());

        System.out.printf("%nЗначение общего счетчика всех инкрементаторов в конце = %d.%n", sharedInteger.getValue());
        assert sharedInteger.getValue() == COUNT_OF_THREADS * COUNT_OF_ONE_THREAD_INCREMENTS;
        System.out.println("Конец выполнения главного потока.");
    }
}
