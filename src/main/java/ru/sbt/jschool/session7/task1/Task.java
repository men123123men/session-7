package ru.sbt.jschool.session7.task1;

public class Task extends Thread {
    int counter;

    public static void main(String[] args) {
        new Task().start();
    }

    public Task() {
        this(1);
    }

    public Task(int counter) {
        super(Integer.toString(counter));
        this.counter = counter;
    }

    @Override
    public void run() {
        if (counter > 50)
            return;
        Thread nextTask = new Task(counter + 1);
        nextTask.start();
        waitFor(nextTask);
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "Hello from Thread-" + Thread.currentThread().getName();
    }

    public void waitFor(Thread threadForWaiting) {
        try {
            threadForWaiting.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
