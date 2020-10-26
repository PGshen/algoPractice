package world.playtogether.designpatterns.structure;

/**
 * <project> algoPractice
 *
 * <p> 默认适配器
 *
 * @author penggs
 * @since 2020-10-25
 */
public class ApDefaultAdapter {
    /**
     * 有很多方法的接口
     */
    interface ManyMethodIntf {
        void method1();
        void method2();
        void method3();
        void method4();
        void method5();
    }

    /**
     * 用一个只有空实现的类作为适配器
     */
    public static class ManyMethodAdapter implements ManyMethodIntf {
        @Override
        public void method1() {}

        @Override
        public void method2() {}

        @Override
        public void method3() {}

        @Override
        public void method4() {}

        @Override
        public void method5() {}
    }

    /**
     * 具体实现，只需要实现我们需要的方法即可，避免实现接口要实现所有的方法
     */
    public static class ManyMethodImpl extends ManyMethodAdapter {
        @Override
        public void method1() {
            System.out.println("Implement method1");
        }
    }

    public static void main(String[] args) {
        ManyMethodImpl manyMethod = new ManyMethodImpl();
        manyMethod.method1();
    }
}