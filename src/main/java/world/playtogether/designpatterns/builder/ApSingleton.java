package world.playtogether.designpatterns.builder;

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
    /**
     * 饱汉模式
     */
    public static class Singleton1 {
        // 使⽤ volatile 可以禁⽌ JVM 的指令重排，保证在多线程环境下也能正常运⾏。不需要先实例化出来,调用getUniqueInstance才实例化
        private volatile static Singleton1 uniqueInstance;
        // 封锁new Singleton1()方式创建实例
        private Singleton1() {
        }

        public static Singleton1 getUniqueInstance() {
            //先判断对象是否已经实例过，没有实例化过才进⼊加锁代码
            if (uniqueInstance == null) {
                //类对象加锁
                synchronized (Singleton1.class) {
                    if (uniqueInstance == null) {
                        uniqueInstance = new Singleton1();
                    }
                }
            }
            return uniqueInstance;
        }
    }

    /**
     * 饿汉模式
     */
    public static class Singleton2 {
        private Singleton2() {}

        // 创建私有静态实例，意味着这个类第一次使用的时候就会进行创建
        private static final Singleton2 uniqueInstance = new Singleton2();

        public static Singleton2 getUniqueInstance() {
            return uniqueInstance;
        }
    }

    /**
     * 嵌套类模式
     */
    public static class Singleton3 {
        private Singleton3() {}

        private static class Holder {
            private static final Singleton3 uniqueInstance = new Singleton3();
        }

        public static Singleton3 getUniqueInstance() {
            return Holder.uniqueInstance;
        }
    }
}