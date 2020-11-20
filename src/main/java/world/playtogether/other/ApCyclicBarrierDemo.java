package world.playtogether.other;

import java.util.concurrent.CyclicBarrier;

/**
 * <project> algoPractice
 *
 * <p> CyclicBarrier样例
 *
 * @author penggs
 * @since 2020-11-20
 */
public class ApCyclicBarrierDemo {

    static class TaskThread extends Thread {
        CyclicBarrier barrier;

        public TaskThread(CyclicBarrier barrier) {
            this.barrier = barrier;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
                System.out.println(getName() + " reach A");
                barrier.await();
                System.out.println(getName() + " pass A");

                Thread.sleep(2000);
                System.out.println(getName() + " reach B");
                barrier.await();
                System.out.println(getName() + " pass B");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        int threadNum = 5;
        CyclicBarrier barrier = new CyclicBarrier(threadNum, () -> System.out.println(Thread.currentThread().getName() + "reach barrier...."));

        for (int i = 0; i < threadNum; i++) {
            new TaskThread(barrier).start();
        }
    }

}