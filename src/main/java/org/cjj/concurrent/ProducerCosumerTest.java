package cjj.concurrent;

        import java.util.Random;


/**
 * I write a producer comsumer design pattern using wait() notifyAll() method.
 * @author chenjunjie
 *
 */
public class ProducerCosumerTest {
    public static void main(String[] args) {
        BufferArea buffer = new BufferArea(10);
        for (int i = 0; i < 2000; i++) {
            Producer pro = new Producer(buffer);
            new Thread(pro).start();
            Comsumer com = new Comsumer(buffer);
            new Thread(com).start();
        }
    }


    static class Comsumer implements Runnable {
        private BufferArea buffer;

        public Comsumer(BufferArea buffer) {
            super();
            this.buffer = buffer;
        }

        @Override
        public void run() {
            try {
                buffer.poll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    static class Producer implements Runnable {
        private BufferArea buffer;

        public Producer(BufferArea buffer) {
            super();
            this.buffer = buffer;
        }



        @Override
        public void run() {
            try {
                buffer.put();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    static class BufferArea {
        private int[] buffer;
        private int headIndex;
        private int eleCounter;

        public BufferArea(int bufferSize) {
            super();
            this.buffer = new int[bufferSize];
            headIndex = -1;
            eleCounter = 0;
        }

        public synchronized int getHead() {
            if (headIndex == -1) {
                return -1;
            } else {
                int oldHeadIndex = headIndex;
                headIndex = (headIndex + 1) % buffer.length;
                eleCounter--;
                return buffer[oldHeadIndex];
            }
        }


        public synchronized void addEle(int item) {
            int tailIndex = -1;
            if (headIndex == -1) {
                headIndex = 0;
                tailIndex = 0;
            } else {
                tailIndex = (headIndex + eleCounter) % buffer.length;
            }
            buffer[tailIndex] = item;

            eleCounter++;
        }


        public synchronized boolean isFull() {
            return eleCounter == buffer.length ? true : false;
        }

        public synchronized boolean isEmpty() {
            return eleCounter == 0 ? true : false;
        }


        public synchronized void put() throws InterruptedException {
            while (isFull()) {
                wait();
            }
            Random ran = new Random();
            int item = ran.nextInt(1000);
            addEle(item);
            System.out.println("Produce:" + item);
            Thread.sleep((long) ((new Random().nextDouble() * 1000) / 1));
            notifyAll();
        }

        public synchronized void poll() throws InterruptedException {
            while (isEmpty()) {
                wait();
            }
            int headItem = getHead();
            System.out.println("Comsume:" + headItem);
            Thread.sleep((long) ((new Random().nextDouble() * 1000) / 1));
            notifyAll();
        }
    }
}







