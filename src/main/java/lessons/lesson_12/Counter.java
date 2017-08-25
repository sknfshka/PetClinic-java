package lessons.lesson_12;

import java.util.concurrent.atomic.AtomicInteger;

public class Counter {
    private AtomicInteger amount = new AtomicInteger(0);

    public int increase() {
        return amount.incrementAndGet();
    }
}