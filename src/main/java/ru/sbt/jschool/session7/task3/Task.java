package ru.sbt.jschool.session7.task3;

public class Task extends Thread {
    int coounter;
    int cyclicPeriod;

    public Task(int cyclicPeriod) {
        this(0, cyclicPeriod);
    }

    public Task(int coounter, int cyclicPeriod) {
        super("Поток " + (coounter % cyclicPeriod + 1));
        this.coounter = coounter;
        this.cyclicPeriod = cyclicPeriod;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
        if (coounter % cyclicPeriod == cyclicPeriod - 1)
            System.out.println();
        new Task(coounter + 1, cyclicPeriod).start();
    }

    public static void main(String[] args) {
        new Task(7).start();
    }
}
