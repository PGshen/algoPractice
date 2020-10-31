package world.playtogether.designpatterns.behavior;

/**
 * <project> algoPractice
 *
 * <p> 模版方法模式
 * 模板方法只负责定义第一步应该要做什么，第二步应该做什么，第三步应该做什么，至于怎么做，由子类来实现。
 *
 * @author penggs
 * @since 2020-10-25
 */
public class ApTemplate {
    /**
     * 抽象模版
     */
    static abstract class AbstractTemplate {
        // 这就是模板方法
        public void templateMethod() {
            init();
            apply(); // 这个是重点
            end(); // 可以作为钩子方法
        }

        protected void init() {
            System.out.println("init()");
        }

        // 留给子类实现
        protected abstract void apply();

        protected void end() {
        }
    }

    /**
     * 实现模版
     */
    static class ConcreteTemplate extends AbstractTemplate {
        public void apply() {
            System.out.println("subclass.apply()");
        }

        public void end() {
            System.out.println("Hook method, We can modify it if necessary.");
        }
    }

    public static void main(String[] args) {
        AbstractTemplate template = new ConcreteTemplate();
        template.templateMethod();
    }

}