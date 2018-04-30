package ru.sbt.jschool.session7;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.concurrent.TimeUnit.MINUTES;

/**
 * @author NIzhikov
 */
public class Main {
    public static final int CONSUMER_CNT = 4;

    public static void main(String[] args) {
        JobsStore store = new JobsStore();

        Set<Integer> doneJobs = new HashSet<>();

        Thread producer = new Thread(new Producer(store));

        producer.start();

        List<Thread> consumers = new ArrayList<>();

        for (int i = 0; i < CONSUMER_CNT; i++) {
            Thread consumer = new Thread(new Consumer(store, doneJobs));

            consumer.start();

            consumers.add(consumer);
        }

        try {
            MINUTES.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
