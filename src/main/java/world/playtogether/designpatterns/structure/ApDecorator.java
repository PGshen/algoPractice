package world.playtogether.designpatterns.structure;

/**
 * <project> algoPractice
 *
 * <p> 装饰器模式
 *
 * 装饰，那么往往就是添加小功能这种，而且，我们要满足可以添加多个小功能。
 * 最简单的，代理模式就可以实现功能的增强，但是代理不容易实现多个功能的增强，当然你可以说用代理包装代理的多层包装方式，但是那样的话代码就复杂了
 *
 * @author penggs
 * @since 2020-10-25
 */
public class ApDecorator {
    /**
     * 抽象基类
     */
    public abstract static class Beverage {
        public abstract String getDesc();
        public abstract double cost();
    }

    /**
     * 以下是被装饰类
     */
    public static class BlackTea extends Beverage {
        @Override
        public String getDesc() {
            return "红茶";
        }

        @Override
        public double cost() {
            return 10;
        }
    }

    public static class GreenTea extends Beverage {
        @Override
        public String getDesc() {
            return "绿茶";
        }

        @Override
        public double cost() {
            return 12;
        }
    }

    /**
     * 装饰器基类
     */
    public static abstract class Condiment extends Beverage {
    }

    public static class Lemon extends Condiment {
        private Beverage beverage;

        public Lemon(Beverage beverage) {
            this.beverage = beverage;
        }

        @Override
        public String getDesc() {
            // 装饰操作写这儿
            return beverage.getDesc() + "，加柠檬";
        }

        @Override
        public double cost() {
            return beverage.cost() + 2;
        }
    }

    public static class Mango extends Condiment {
        private Beverage beverage;

        public Mango(Beverage beverage) {
            this.beverage = beverage;
        }

        @Override
        public String getDesc() {
            return beverage.getDesc() + "，加芒果";
        }

        @Override
        public double cost() {
            return beverage.cost() + 3;
        }
    }

    public static void main(String[] args) {
        Beverage beverage = new GreenTea();
        beverage = new Lemon(beverage);
        beverage = new Mango(beverage);
        System.out.println(beverage.getDesc() + " -> " + beverage.cost());
    }
}