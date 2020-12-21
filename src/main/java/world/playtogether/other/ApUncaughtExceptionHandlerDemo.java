package world.playtogether.other;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * <project> algoPractice
 *
 * <p> 未捕获异常处理
 *
 * @author penggs
 * @since 2020-12-16
 */
public class ApUncaughtExceptionHandlerDemo {

    public static void main(String[] args) {
        // 所有线程设置默认的异常处理器
        //Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler());
        ApUncaughtExceptionHandlerDemo demo = new ApUncaughtExceptionHandlerDemo();
        demo.testThread();
        demo.testPoolExecute();
        demo.testPoolSubmit();
        demo.testPoolSubmitFutureGet();
        System.out.println("fin");
    }

    /**
     * Thread直接可设置未捕获异常处理器
     */
    void testThread() {
        System.out.println("====testThread====" );
        Thread thread = new Thread(new Task());
        // 单个线程设置未捕获异常处理器
        thread.setUncaughtExceptionHandler(new ExceptionHandler());
        thread.start();
    }

    /**
     * 线程池的需要在方法内设置未捕获异常处理器
     * 可以抛出异常，并被未捕获异常处理器处理
     */
    void testPoolExecute() {
        System.out.println("====testPoolExecute====" );
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new ThreadPoolTask());
        exec.shutdown();
    }

    /**
     * 不会抛异常，被未捕获异常处理器处理，因为被认为是认为返回状态的一部分
     */
    void testPoolSubmit() {
        System.out.println("====testPoolSubmit====" );
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.submit(new ThreadPoolTask());
        exec.shutdown();
    }

    /**
     * 通过Future.get()，会重新抛出被submit方法认为是任务返回状态（实际异常）的异常
     */
    void testPoolSubmitFutureGet() {
        System.out.println("====testPoolSubmitFutureGet====" );
        ExecutorService exec = Executors.newCachedThreadPool();
        Future<?> future = exec.submit(new Task());
        exec.shutdown();
        try
        {
            future.get();
        }
        catch (InterruptedException | ExecutionException e)
        {
            System.out.println("testPoolSubmitFutureGet catch Exception: "+e.getMessage());
        }

    }

    static class Task implements Runnable {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "--> " + 3/2);
            System.out.println(Thread.currentThread().getName() + "--> " + 3/0);
            System.out.println(Thread.currentThread().getName() + "--> " + 3/1);
        }
    }

    // 线程池内设置未捕获异常处理器
    static class ThreadPoolTask implements Runnable {
        @Override
        public void run()
        {
            Thread.currentThread().setUncaughtExceptionHandler(new ExceptionHandler());
            System.out.println(Thread.currentThread().getName() + "--> " + 3/2);
            System.out.println(Thread.currentThread().getName() + "--> " + 3/0);
            System.out.println(Thread.currentThread().getName() + "--> " + 3/1);
        }
    }

    static class ExceptionHandler implements Thread.UncaughtExceptionHandler {
        @Override
        public void uncaughtException(Thread t, Throwable e)
        {
            System.out.println(Thread.currentThread().getName() + "--> Exception: "+e.getMessage());
        }
    }
}