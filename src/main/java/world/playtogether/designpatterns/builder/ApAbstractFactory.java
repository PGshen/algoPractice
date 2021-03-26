package world.playtogether.designpatterns.builder;

/**
 * <project> algoPractice
 *
 * <p> 抽象工厂模式
 * 当涉及到产品族的时候，就需要引入抽象工厂模式了
 * 从同一工厂产生的对象属于同一系列
 *
 * 抽象工厂的问题也是显而易见的，比如我们要加个显示器，就需要修改所有的工厂，给所有的工厂都加上制造显示器的方法。这有点违反了对修改关闭，对扩展开放这个设计原则。
 *
 * @author penggs
 * @since 2020-10-25
 */
public class ApAbstractFactory {

    interface ComputerFactory {
        Cpu makeCpu();
        MainBoard makeMainBoard();
    }

    public static class IntelComputerFactory implements ComputerFactory {

        @Override
        public Cpu makeCpu() {
            return new IntelCpu();
        }

        @Override
        public MainBoard makeMainBoard() {
            return new IntelMainBoard();
        }
    }

    public static class AmdComputerFactory implements ComputerFactory {

        @Override
        public Cpu makeCpu() {
            return new AmdCpu();
        }

        @Override
        public MainBoard makeMainBoard() {
            return new AmdMainBoard();
        }
    }

    static class Computer {
        Cpu cpu;
        MainBoard mainBoard;

        public Computer(Cpu cpu, MainBoard mainBoard) {
            this.cpu = cpu;
            this.mainBoard = mainBoard;
        }

        public String display() {
            return cpu.displayName() + "-" + mainBoard.displayName();
        }
    }

    static class Cpu {
        String name;
        String displayName() {
            return name;
        }
    }

    static class IntelCpu extends Cpu {
        public IntelCpu() {
            name = "IntelCpu";
        }
    }

    static class AmdCpu extends Cpu{
        public AmdCpu() {
            name = "AmdCpu";
        }
    }

    static class MainBoard {
        String name;
        String displayName () {
            return name;
        }
    }

    static class IntelMainBoard extends MainBoard{
        public IntelMainBoard() {
            name = "IntelMainBoard";
        }
    }

    static class AmdMainBoard extends MainBoard{
        public AmdMainBoard() {
            name = "AmdMainBoard";
        }
    }

    public static void main(String[] args) {
        ComputerFactory computerFactory = new IntelComputerFactory();
        Cpu cpu = computerFactory.makeCpu();
        MainBoard mainBoard = new AmdComputerFactory().makeMainBoard();
        Computer computer = new Computer(cpu, mainBoard);
        System.out.println(computer.display());
    }
}