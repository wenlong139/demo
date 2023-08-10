package juc;

/**
 * @Author qinwenlong
 * @Date 2021/11/18
 **/
public class ProducerAndConsumer {

    public static int count = 0;

    public static final Object producerMonitor = new Object();
    public final Object consumerMonitor = new Object();

    public static void increase() {
        synchronized (producerMonitor) {
            if (count < 20) {
                count++;
                System.out.println("生产==" + count);
                producerMonitor.notifyAll();
            } else {
                try {
                    System.out.println("商品满了 生产者停止");
                    producerMonitor.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void decrease() {
        synchronized (producerMonitor) {
            if (count > 0) {
                count--;
                System.out.println("消费商品===:" + count);
                producerMonitor.notifyAll();
            } else {
                try {
                    System.out.println("没有商品了,消费者停止");
                    producerMonitor.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        ProducerAndConsumer producerAndConsumer = new ProducerAndConsumer();
        Thread producerThread = new Thread(() -> {
            while (true) {
                System.out.println("生产线程号:" + Thread.currentThread());
                increase();
            }
        });
        new Thread(() -> {
            while (true) {
                System.out.println("生产线程号:" + Thread.currentThread());
                increase();
            }
        }).start();

        Thread consumerThread = new Thread(() -> {
            while (true) {
                System.out.println("消费线程号:" + Thread.currentThread());
                decrease();
            }
        });

        producerThread.start();
        consumerThread.start();
    }
}
