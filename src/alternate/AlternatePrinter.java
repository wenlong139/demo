package alternate;

import javafx.print.Printer;
import lombok.SneakyThrows;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * A B C三个线程交替打印1-100
 *
 * @Author qinwenlong
 * @Date 2023/8/10
 **/
public class AlternatePrinter {
    AtomicInteger a = new AtomicInteger(0);
    static int num = 1;
    static final Object lock = new Object();

    static  class Printer implements Runnable {
        public Printer(int mod) {
            this.mod = mod;
        }

        int mod;

        @SneakyThrows
        @Override
        public void run() {
            while (true) {
                synchronized (lock) {
                    if (num > 100) {
                        break;
                    }
                    if (num % 3 == mod) {
                        System.out.println(Thread.currentThread().getName() + ":" + num);
                        num++;
                        try {
                            lock.notifyAll();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        lock.wait();
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread A = new Thread(new Printer(1));
        A.setName("A");
        Thread B = new Thread(new Printer(2));
        B.setName("B");
        Thread C = new Thread(new Printer(0));
        C.setName("C");
        A.start();
        B.start();
        C.start();
    }

}
