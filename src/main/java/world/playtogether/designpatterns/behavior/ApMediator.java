package world.playtogether.designpatterns.behavior;

import java.util.Date;

/**
 * <project> algoPractice
 *
 * <p> 中介者模式
 * 提供了一个中介类，该类通常处理不同类之间的通信，并支持松耦合，使代码易于维护
 * 使用场景： 1、系统中对象之间存在比较复杂的引用关系，导致它们之间的依赖关系结构混乱而且难以复用该对象。 2、想通过一个中间类来封装多个类中的行为，而又不想生成太多的子类。
 *
 * @author penggs
 * @since 2020-10-25
 */
public class ApMediator {
    /**
     * 中介者
     */
    static class ChatRoom {
        public static void showMessage(User user, String message){
            System.out.println(new Date().toString()
                    + " [" + user.getName() +"] : " + message);
        }
    }

    static class User {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public User(String name){
            this.name  = name;
        }

        public void sendMessage(String message){
            ChatRoom.showMessage(this,message);
        }
    }

    public static void main(String[] args) {
        User tom = new User("Tom");
        User jim = new User("Jim");

        tom.sendMessage("Hi! Jim!");
        jim.sendMessage("Hello! Tom!");
    }
}