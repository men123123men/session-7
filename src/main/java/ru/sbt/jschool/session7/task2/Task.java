package ru.sbt.jschool.session7.task2;

public class Task extends Thread {
    boolean aBoolean;

    public Task() {
        this(true);
    }

    public Task(boolean aBoolean) {
        super("Поток " + (aBoolean ? 1 : 2));
        this.aBoolean = aBoolean;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
        Thread newTask = new Task(!aBoolean);
        newTask.start();
    }

    public static void main(String[] args) {
        new Task().start();
    }
}
