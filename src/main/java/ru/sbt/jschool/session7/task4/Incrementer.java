package ru.sbt.jschool.session7.task4;

import java.util.concurrent.CountDownLatch;

public class Incrementer implements Runnable {
    private final IntegerMutable valueForIncrementing;
    private CountDownLatch latch;
    private int countOfIncrements;

    public Incrementer(IntegerMutable valueForIncrementing, int countOfIncrements, CountDownLatch latch) {
        this.valueForIncrementing = valueForIncrementing;
        this.latch = latch;
        this.countOfIncrements = countOfIncrements;
    }

    @Override
    public void run() {
        latch.countDown();
//        System.out.printf("Запустился      %s Теперь счетчик потоков = %d%n", Thread.currentThread(), latch.getCount());

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        System.out.printf("Начал работу    %s %n", Thread.currentThread());
        for (int i = 0; i < countOfIncrements; i++) {
//            if (i%countOfIncrements/10==0)
//                System.out.printf("%-20s %d %n", Thread.currentThread().getName(),valueForIncrementing.getValue());
            incrementValue();
        }
//        System.out.printf("Закончил работу %s %n", Thread.currentThread());
    }

    private void incrementValue() {
        synchronized (valueForIncrementing) {
            valueForIncrementing.increment();
            Thread.yield();
        }
    }
}
