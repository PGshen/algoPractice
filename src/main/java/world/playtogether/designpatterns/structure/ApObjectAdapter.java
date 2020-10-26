package world.playtogether.designpatterns.structure;

/**
 * <project> algoPractice
 *
 * <p> 对象适配器
 * 代理模式做的是增强原方法的活；适配器做的是适配的活，将一个类包装为另一个类来使用
 *
 * @author penggs
 * @since 2020-10-25
 */
public class ApObjectAdapter {
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
     * 适配，借用cook的方法
     */
    static class CookAdapter implements Bird {

        Cook cook;

        public CookAdapter(Cook cook) {
            this.cook = cook;
        }

        // 借用cook的实现
        @Override
        public void run() {
            cook.run();
        }

        // 自己实现
        @Override
        public void fly() {
            System.out.println("鸟在飞。。。");
        }
    }

    public static void main(String[] args) {
        Cook cook = new WildCock();
        // 这里可以动态传入不同的对象，只要实现对应接口
        Bird bird = new CookAdapter(cook);
        bird.run();
        bird.fly();
    }

}