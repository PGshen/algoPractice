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
                int sleetTime = new Random().nextInt(1000);
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

    public static void main(String[] args) {
        int count = 20;
        CountDownLatch latch = new CountDownLatch(count);
        ExecutorService service = Executors.newFixedThreadPool(10);

        try {
            for (int i = 0; i < count; i++) {
                service.submit(new Worker(latch));
            }
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            service.shutdown();
        }
        System.out.println(sum);
    }
}