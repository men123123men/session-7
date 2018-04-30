package ru.sbt.jschool.session7.task4;

public class IntegerMutable {
    private volatile int value;

    public IntegerMutable(int value) {
        this.value = value;
    }

    public void increment() {
        value++;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }
}
