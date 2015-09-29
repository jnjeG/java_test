package org.cjj.concurrent;


import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Examples from Internet.
 * It decribe the producer comsumer design pattern using wait() notifyAll() method.
 *
 * @author chenjunjie
 */
public class ProducerCosumerTest2 {
    public static void main(String[] args) {
        EventStorage storage = new EventStorage();
        Producer producer = new Producer(storage);
        Thread thread1 = new Thread(producer);

        Consumer consumer = new Consumer(storage);
        Thread thread2 = new Thread(consumer);

        thread2.start();
        thread1.start();
    }
}

class EventStorage {

    private int maxSize;

    private List<Date> storage;

    public EventStorage() {
        maxSize = 10;
        storage = new LinkedList<Date>();
    }

    public synchronized void set() {
        while (storage.size() == maxSize) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        storage.add(new Date());
        System.out.printf("Set: %d", storage.size());
        notifyAll();
    }

    public synchronized void get() {
        while (storage.size() == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        System.out.printf("Get: %d: %s", storage.size(), ((LinkedList<?>) storage).poll());
        notifyAll();
    }

}


class Producer implements Runnable {

    private EventStorage storge;

    public Producer(EventStorage storage) {
        this.storge = storage;
    }

    public void run() {
        for (int i = 0; i < 100; i++) {
            storge.set();
        }
    }
}


class Consumer implements Runnable {

    private EventStorage storage;

    public Consumer(EventStorage storage) {
        this.storage = storage;
    }

    public void run() {
        for (int i = 0; i < 100; i++) {
            storage.get();
        }
    }
}

