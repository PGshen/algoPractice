package world.playtogether.designpatterns.builder;

/**
 * <project> algoPractice
 *
 * <p> 简单工厂模式
 * 一个工厂类 XxxFactory，里面有一个静态方法，根据我们不同的参数，返回不同的派生自同一个父类（或实现同一接口）的实例对象
 *
 * @author penggs
 * @since 2020-10-25
 */
public class ApSimpleFactory {

    public static Computer getComputer(String name) {
        if (name.equals("Apple")) {
            return new AppleComputer();
        } else if (name.equals("Huawei")) {
            return new HuaweiComputer();
        } else {
            return null;
        }
    }


    abstract static class Computer {
        String name;

        abstract String displayName();
    }

    static class AppleComputer extends Computer {
        public AppleComputer() {
            name = "Apple";
        }

        @Override
        String displayName() {
            return name;
        }
    }

    static class HuaweiComputer extends Computer {
        public HuaweiComputer() {
            name = "Huawei";
        }

        @Override
        String displayName() {
            return name;
        }
    }

    public static void main(String[] args) {
        Computer vendor = ApSimpleFactory.getComputer("Huawei");
        assert vendor != null;
        System.out.println(vendor.displayName());
    }
}