package lessons.lesson_12;

import org.junit.Test;
import lessons.lesson_6.Client;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.assertEquals;

public class BlockQueueTest {

    @Test
    public void queue() throws InterruptedException {
        final BlockQueue<Client> queue = new BlockQueue<>();
        final List<Customer> customers = Arrays.asList(new Customer(queue), new Customer(queue));
        for (Customer customer : customers) {
            customer.start();
        }
        Producer producer = new Producer(
                queue,
                Arrays.asList(
                        new Client("1", "1"), new Client("2", "2"),
                        new Client("3", "3"), new Client("4", "4")
                )
        );
        producer.start();
        producer.join();
        Thread.sleep(101);
        int count = 0;
        for (Customer customer : customers) {
            count += customer.size();
        }
        assertEquals(count, producer.size());
    }


    private static final class Producer extends Thread {
        private final BlockQueue<Client> queue;
        private final List<Client> store;

        public Producer(final BlockQueue<Client> queue, final List<Client> store) {
            super();
            this.queue = queue;
            this.store = store;
        }

        @Override
        public void run() {
            for (Client user : this.store) {
                this.queue.push(user);
            }
        }

        public int size() {
            return this.store.size();
        }
    }

    private static final class Customer extends Thread {
        private final BlockQueue<Client> queue;
        private final AtomicInteger counter = new AtomicInteger(0);

        public Customer(final BlockQueue<Client> queue) {
            super();
            this.queue = queue;
        }

        @Override
        public void run() {
            while (true) {
                System.out.println(
                        String.format("%s : %s", Thread.currentThread().getId(), this.queue.poll())
                );
                counter.incrementAndGet();
            }
        }

        public int size() {
            return this.counter.get();
        }
    }
}