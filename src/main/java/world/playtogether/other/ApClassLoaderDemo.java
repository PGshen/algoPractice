package world.playtogether.other;

/**
 * <project> algoPractice
 *
 * <p> Class.forName()和ClassLoader.loadClass()区别
 *
 * @author penggs
 * @since 2021-03-30
 */
public class ApClassLoaderDemo {

    public static void main(String[] args) throws ClassNotFoundException {
        ClassLoader classLoader= ApClassLoaderDemo.class.getClassLoader();
        //1、使用ClassLoader.loadClass()来加载类，不会执行初始化块
        //classLoader.loadClass("world.playtogether.other.ApDemo");

        //2、使用Class.forName()来加载类，默认会执行初始化块
        //Class.forName("world.playtogether.other.ApDemo");

        //3、使用Class.forName()来加载类，并指定ClassLoader，初始化时不执行静态块
        Class.forName("world.playtogether.other.ApDemo",true,classLoader);
    }
}