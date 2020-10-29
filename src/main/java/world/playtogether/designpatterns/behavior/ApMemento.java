package world.playtogether.designpatterns.behavior;

import java.util.ArrayList;
import java.util.List;

/**
 * <project> algoPractice
 *
 * <p> 备忘录模式
 * 在不破坏封装性的前提下，捕获一个对象的内部状态，并在该对象之外保存这个状态
 * 优点： 1、给用户提供了一种可以恢复状态的机制，可以使用户能够比较方便地回到某个历史的状态。 2、实现了信息的封装，使得用户不需要关心状态的保存细节。
 * 使用场景： 1、需要保存/恢复数据的相关状态场景。 2、提供一个可回滚的操作。
 *
 * @author penggs
 * @since 2020-10-25
 */
public class ApMemento {
    /**
     * 包含了要被恢复的对象的状态
     */
    static class Memento {
        private String state;

        public Memento(String state){
            this.state = state;
        }

        public String getState(){
            return state;
        }
    }

    /**
     * 创建并在 Memento 对象中存储状态
     */
    static class Originator {
        private String state;

        public void setState(String state){
            this.state = state;
        }

        public String getState(){
            return state;
        }

        // 返回当前状态封装
        public Memento saveStateToMemento(){
            return new Memento(state);
        }

        // 设置指定状态为当前状态
        public void getStateFromMemento(Memento Memento){
            state = Memento.getState();
        }
    }

    /**
     * 负责从 Memento 中恢复对象的状态
     */
    static class CareTaker {
        private List<Memento> mementoList = new ArrayList<Memento>();

        public void add(Memento state){
            mementoList.add(state);
        }

        public Memento get(int index){
            return mementoList.get(index);
        }
    }

    public static void main(String[] args) {
        Originator originator = new Originator();
        CareTaker careTaker = new CareTaker();
        originator.setState("State #1");
        originator.setState("State #2");
        // 归档记录
        careTaker.add(originator.saveStateToMemento());
        originator.setState("State #3");
        // 归档记录
        careTaker.add(originator.saveStateToMemento());
        originator.setState("State #4");

        // 返回当前状态
        System.out.println("Current State: " + originator.getState());
        // 回归到指定状态
        originator.getStateFromMemento(careTaker.get(0));
        System.out.println("First saved State: " + originator.getState());
    }
}