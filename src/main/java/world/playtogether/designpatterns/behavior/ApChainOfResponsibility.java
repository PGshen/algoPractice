package world.playtogether.designpatterns.behavior;

/**
 * <project> algoPractice
 *
 * <p> 责任链模式
 * 避免请求发送者与接收者耦合在一起，让多个对象都有可能接收请求，将这些对象连接成一条链，并且沿着这条链传递请求，直到有对象处理它为止。
 *
 * @author penggs
 * @since 2020-10-25
 */
public class ApChainOfResponsibility {
    static class Context {
        boolean isNewUser;
        String location;

        public boolean isNewUser() {
            return isNewUser;
        }

        public void setNewUser(boolean newUser) {
            isNewUser = newUser;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }
    }

    /**
     * 责任链-规则流程
     */
    static abstract class RuleHandler {
        // 后继节点
        protected RuleHandler successor;

        public abstract void apply(Context context);

        public void setSuccessor(RuleHandler successor) {
            this.successor = successor;
        }

        public RuleHandler getSuccessor() {
            return successor;
        }
    }

    /**
     * 以下为具体责任流程
     */
    static class NewUserRuleHandler extends RuleHandler {
        public void apply(Context context) {
            //boolean allowed = activityService.isSupportedLocation(context);
            boolean allowed = context.isNewUser;
            if (allowed) {
                // 如果有后继节点的话，传递下去
                if (this.getSuccessor() != null) {
                    this.getSuccessor().apply(context);
                }
            } else {
                throw new RuntimeException("该活动仅限新用户参与");
            }
        }
    }

    static class LocationRuleHandler extends RuleHandler {
        public void apply(Context context) {
            //boolean allowed = activityService.isSupportedLocation(context.getLocation);
            boolean allowed = "Guangzhou".equals(context.getLocation());
            if (allowed) {
                if (this.getSuccessor() != null) {
                    this.getSuccessor().apply(context);
                }
            } else {
                throw new RuntimeException("非常抱歉，您所在的地区无法参与本次活动");
            }
        }
    }

    static class LimitRuleHandler extends RuleHandler {
        public void apply(Context context) {
            //int remainedTimes = activityService.queryRemainedTimes(context); // 查询剩余奖品
            int remainedTimes = 1;
            if (remainedTimes > 0) {
                if (this.getSuccessor() != null) {
                    this.getSuccessor().apply(context);
                }
            } else {
                throw new RuntimeException("您来得太晚了，奖品被领完了");
            }
        }
    }

    public static void main(String[] args) {
        RuleHandler newUserHandler = new NewUserRuleHandler();
        RuleHandler locationHandler = new LocationRuleHandler();
        RuleHandler limitHandler = new LimitRuleHandler();

        // 假设本次活动仅校验地区和奖品数量，不校验新老用户
        //newUserHandler.setSuccessor(locationHandler);
        locationHandler.setSuccessor(limitHandler);

        Context context = new Context();
        //context.setLocation("Guangzhou");
        context.setLocation("Shenzhen");
        context.setNewUser(true);
        try {
            locationHandler.apply(context);
            System.out.println("领奖成功");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

}