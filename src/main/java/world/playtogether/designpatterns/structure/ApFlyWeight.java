package world.playtogether.designpatterns.structure;

import java.util.HashMap;

/**
 * <project> algoPractice
 *
 * <p> 享元模式
 * 1. 何时使用
 * 系统中有大量对象时
 * 这些对象消耗大量内存时
 * 这些对象的状态大部分可以外部化时
 * 2. 方法
 * 用唯一标识码判断，如果在内存中有，则返回这个唯一标识码所标识的对象，用HashMap/HashTable存储
 * 3. 优点
 * 大大减少了对象的创建，降低了程序内存的占用，提高效率
 * 4. 应用实例
 * String常量池
 * 数据库连接池
 *
 * @author penggs
 * @since 2020-10-25
 */
public class ApFlyWeight {
    /**
     * 内部状态指对象共享出来的信息，存储在享元对象内部并且不会随环境的改变而改变；
     * 外部状态指对象得以依赖的一个标记，是随环境改变而改变的、不可共享的状态。
     */
    public static abstract class Flyweight {
        //内部状态
        public String intrinsic;
        //外部状态
        protected final String extrinsic;

        //要求享元角色必须接受外部状态
        public Flyweight(String extrinsic) {
            this.extrinsic = extrinsic;
        }

        //定义业务操作
        public abstract void operate(int extrinsic);

        public String getIntrinsic() {
            return intrinsic;
        }

        public void setIntrinsic(String intrinsic) {
            this.intrinsic = intrinsic;
        }
    }

    public static class ConcreteFlyweight extends Flyweight {
        //接受外部状态
        public ConcreteFlyweight(String extrinsic) {
            super(extrinsic);
        }

        //根据外部状态进行逻辑处理
        @Override
        public void operate(int xx) {
            System.out.println("具体Flyweight:" + xx);
        }
    }

    public static class UnsharedConcreteFlyweight extends Flyweight {
        public UnsharedConcreteFlyweight(String extrinsic) {
            super(extrinsic);
        }

        @Override
        public void operate(int xx) {
            System.out.println("不共享的具体Flyweight:" + xx);
        }
    }

    public static class FlyweightFactory {

        //定义一个池容器
        private static HashMap<String, Flyweight> pool = new HashMap<>();

        //享元工厂
        public static Flyweight getFlyweight(String extrinsic) {
            Flyweight flyweight = null;
            if(pool.containsKey(extrinsic)) {    //池中有该对象
                flyweight = pool.get(extrinsic);
                System.out.print("已有 " + extrinsic + " 直接从池中取---->");
            } else {
                //根据外部状态创建享元对象
                flyweight = new ConcreteFlyweight(extrinsic);
                //放入池中
                pool.put(extrinsic, flyweight);
                System.out.print("创建 " + extrinsic + " 并从池中取出---->");
            }
            return flyweight;
        }
    }

    public static void main(String[] args) {
        int xx = 22;

        Flyweight flyweightX = FlyweightFactory.getFlyweight("X");
        flyweightX.operate(++ xx);

        Flyweight flyweightY = FlyweightFactory.getFlyweight("Y");
        flyweightY.operate(++ xx);

        Flyweight flyweightReX = FlyweightFactory.getFlyweight("X");
        flyweightReX.operate(++ xx);
    }
}