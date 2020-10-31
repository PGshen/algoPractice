package world.playtogether.designpatterns.behavior;

/**
 * <project> algoPractice
 *
 * <p> 状态模式
 *
 * @author penggs
 * @since 2020-10-25
 */
public class ApState {
    interface State {
        public void doAction(Context context);
    }

    static class DeductState implements State {
        public void doAction(Context context) {
            System.out.println("商品卖出，准备减库存");
            context.setState(this);
            //... 执行减库存的具体操作
        }

        public String toString() {
            return "Deduct State";
        }
    }

    static class RevertState implements State {
        public void doAction(Context context) {
            System.out.println("给此商品补库存");
            context.setState(this);
            //... 执行加库存的具体操作
        }

        public String toString() {
            return "Revert State";
        }
    }

    static class Context {
        private State state;
        private String name;
        public Context(String name) {
            this.name = name;
        }

        public void setState(State state) {
            this.state = state;
        }
        public State getState() {
            return this.state;
        }
    }

    public static void main(String[] args) {
        Context context = new Context("iPhone X");

        State revertState = new RevertState();
        revertState.doAction(context);
        System.out.println(context.getState());

        State deductState = new DeductState();
        deductState.doAction(context);
        System.out.println(context.getState());
    }
}