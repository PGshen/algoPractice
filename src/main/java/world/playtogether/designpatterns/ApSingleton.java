package world.playtogether.designpatterns;

/**
 * <project> algoPractice
 *
 * <p> 单例模式
 * 双重锁校验
 *
 * @author penggs
 * @since 2020-10-14
 */
public class ApSingleton {
    // 使⽤ volatile 可以禁⽌ JVM 的指令重排，保证在多线程环境下也能正常运⾏。
    private volatile static ApSingleton uniqueInstance;

    private ApSingleton() {
    }

    public static ApSingleton getUniqueInstance() {
        //先判断对象是否已经实例过，没有实例化过才进⼊加锁代码
        if (uniqueInstance == null) {
            //类对象加锁
            synchronized (ApSingleton.class) {
                if (uniqueInstance == null) {
                    uniqueInstance = new ApSingleton();
                }
            }
        }
        return uniqueInstance;
    }
}