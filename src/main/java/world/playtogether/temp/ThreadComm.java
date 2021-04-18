package world.playtogether.temp;

import java.util.concurrent.TimeUnit;

/**
 * <project> algoPractice
 *
 * <p>
 *
 * @author penggs
 * @since 2021-04-14 14:16
 */
public class ThreadComm extends Thread {
    //volatile修饰，保证线程可见性，这里不需要保证原子性，因为run有if判断
    private volatile static int state = 0;
    private static String name = "ABC";
    private int type;
    ThreadComm(int type){
        this.type = type;
    }
    @Override
    public void run() {
        for (int i = 0; i <10 ; ) {
            // 通过条件保证三个线程按顺序增加state
            if (state % 3 == type) {
                System.out.print(name.charAt(type));
                state++;
                i++;
            }
        }
    }
    public static void main(String[] args) {
        new ThreadComm(0).start();
        new ThreadComm(1).start();
        new ThreadComm(2).start();
    }
}