package world.playtogether.other;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * <project> algoPractice
 *
 * <p> ForkJoinPool使用
 * 来源：https://blog.csdn.net/niyuelin1990/article/details/78658251
 *
 * @author penggs
 * @since 2021-04-18 10:11
 */
public class ApForkJoinPoolDemo {
    public static class SumTask extends RecursiveTask<Integer> {

        private static final int THRESHOLD = 2;
        private int start;
        private int end;

        public SumTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Integer compute() {
            int sum = 0;
            boolean canCompute = (end - start) <= THRESHOLD;
            if (canCompute) {
                for (int i = start; i <= end; i++)
                    sum += i;
            } else {
                //如果任务大于阀值，就分裂成两个子任务计算
                int mid = (start + end) / 2;
                SumTask leftTask = new SumTask(start, mid);
                SumTask rightTask = new SumTask(mid + 1, end);

                //执行子任务
                leftTask.fork();
                rightTask.fork();

                //等待子任务执行完，并得到结果
                int leftResult = leftTask.join();
                int rightResult = rightTask.join();

                sum = leftResult + rightResult;
            }

            return sum;
        }
    }

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        SumTask sumTask = new SumTask(1, 10);
        Future<Integer> future = forkJoinPool.submit(sumTask);
        try {
            System.out.println(future.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}