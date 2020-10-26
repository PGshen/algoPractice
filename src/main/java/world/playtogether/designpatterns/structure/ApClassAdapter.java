package world.playtogether.designpatterns.structure;

/**
 * <project> algoPractice
 *
 * <p> 类适配器
 *
 * @author penggs
 * @since 2020-10-25
 */
public class ApClassAdapter {
    interface Cook{
        void run();
    }

    interface Bird{
        void run();
        void fly();
    }

    static class WildCock implements Cook {
        @Override
        public void run() {
            System.out.println("鸡在跑。。。");
        }
    }

    /**
     * 适配，借用cook的方法,但因为继承关系以写死，所以不能动态的
     */
    static class CookAdapter extends WildCock implements Bird {

        // run方法已通过WildCook实现

        // 自己实现
        @Override
        public void fly() {
            System.out.println("鸟在飞。。。");
        }
    }

    public static void main(String[] args) {
        // 这里可以动态传入不同的对象
        Bird bird = new CookAdapter();
        bird.run();
        bird.fly();
    }
}