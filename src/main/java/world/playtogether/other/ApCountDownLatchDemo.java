package world.playtogether.other;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <project> algoPractice
 *
 * <p> CountDownLatch样例
 *
 * @author penggs
 * @since 2020-11-20
 */
public class ApCountDownLatchDemo {
    private static AtomicInteger sum = new AtomicInteger(0);

    static ExecutorService service = Executors.newFixedThreadPool(30);
    /**
     * 内部类
     */
    private static class Worker implements Runnable {
        private CountDownLatch latch;

        public Worker(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void run() {
            try {
                int sleetTime = new Random().nextInt(10000);
                System.out.println(Thread.currentThread().getName() + ": Sleep " + sleetTime + " millisecond");
                Thread.sleep(sleetTime);
                sum.getAndAdd(1);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                latch.countDown(); //无论 正确 或 异常，都必须 countDown，否则 main线程 会被 countDown.await() 一直挂住
            }

        }
    }

    public void test(String taskName) {
        int count = 20;
        CountDownLatch latch = new CountDownLatch(count);
        try {
            for (int i = 0; i < count; i++) {
                service.submit(new Worker(latch));
            }
            latch.await();
            System.out.println(taskName + " finished!!!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ApCountDownLatchDemo demo = new ApCountDownLatchDemo();
        demo.test("Task1");
        System.out.println("-----1");
        demo.test("Task2");
        System.out.println("-----2");
    }
}