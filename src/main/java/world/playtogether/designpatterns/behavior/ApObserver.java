package world.playtogether.designpatterns.behavior;

import java.util.ArrayList;
import java.util.List;

/**
 * <project> algoPractice
 *
 * <p> 观察者模式
 * 观察者订阅自己关心的主题和主题有数据变化后通知观察者们。
 *
 * @author penggs
 * @since 2020-10-25
 */
public class ApObserver {
    static class Subject {
        // 保存观察者列表
        private List<Observer> observers = new ArrayList<Observer>();
        private int state;
        public int getState() {
            return state;
        }
        public void setState(int state) {
            this.state = state;
            // 数据已变更，通知观察者们
            notifyAllObservers();
        }
        // 注册观察者
        public void attach(Observer observer) {
            observers.add(observer);
        }
        // 通知观察者们
        public void notifyAllObservers() {
            for (Observer observer : observers) {
                observer.update();
            }
        }
    }

    /**
     * 观察者对象
     */
    static abstract class Observer {
        // 订阅的主题
        protected Subject subject;
        public abstract void update();
    }

    static class BinaryObserver extends Observer {
        // 在构造方法中进行订阅主题
        public BinaryObserver(Subject subject) {
            this.subject = subject;
            // 通常在构造方法中将 this 发布出去的操作一定要小心
            this.subject.attach(this);
        }
        // 该方法由主题类在数据变更的时候进行调用
        @Override
        public void update() {
            String result = Integer.toBinaryString(subject.getState());
            System.out.println("订阅的数据发生变化，新的数据处理为二进制值为：" + result);
        }
    }

    static class HexaObserver extends Observer {
        public HexaObserver(Subject subject) {
            this.subject = subject;
            this.subject.attach(this);
        }
        @Override
        public void update() {
            String result = Integer.toHexString(subject.getState()).toUpperCase();
            System.out.println("订阅的数据发生变化，新的数据处理为十六进制值为：" + result);
        }
    }

    public static void main(String[] args) {
        Subject sub = new Subject();
        new BinaryObserver(sub);
        new HexaObserver(sub);
        sub.setState(12);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sub.setState(19);
    }

}