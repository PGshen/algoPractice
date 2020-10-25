package world.playtogether.designpatterns.builder;

/**
 * <project> algoPractice
 *
 * <p> 工厂模式
 * 第一步，选取合适的工厂
 * 第二步基本上和简单工厂一样
 *
 * @author penggs
 * @since 2020-10-25
 */
public class ApFactory {

    public interface ComputerFactory {
        Computer makeComputer(String name);
    }

    public static class HuaweiComputerFactory implements ComputerFactory {
        @Override
        public Computer makeComputer(String name) {
            if (name.equals("A")) {
                return new HuaweiComputerA();
            } else if (name.equals("B")) {
                return new HuaweiComputerB();
            }
            return null;
        }
    }

    public static class AppleComputerFactory implements ComputerFactory {
        @Override
        public Computer makeComputer(String name) {
            if (name.equals("A")) {
                return new AppleComputerA();
            } else if (name.equals("B")) {
                return new AppleComputerB();
            }
            return null;
        }
    }


    static class Computer {
        String name;
        String displayName() {
            return name;
        }
    }

    static class HuaweiComputerA extends Computer {
        public HuaweiComputerA() {
            name = "HuaweiComputerA";
        }
    }
    static class HuaweiComputerB extends Computer {
        public HuaweiComputerB() {
            name = "HuaweiComputerB";
        }
    }
    static class AppleComputerA extends Computer {
        public AppleComputerA() {
            name = "AppleComputerA";
        }
    }
    static class AppleComputerB extends Computer {
        public AppleComputerB() {
            name = "AppleComputerB";
        }
    }

    public static void main(String[] args) {
        ComputerFactory computerFactory = new HuaweiComputerFactory();
        Computer computer = computerFactory.makeComputer("A");
        System.out.println(computer.displayName());
        computerFactory = new AppleComputerFactory();
        computer = computerFactory.makeComputer("B");
        System.out.println(computer.displayName());
    }
}