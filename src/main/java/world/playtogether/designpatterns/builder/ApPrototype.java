package world.playtogether.designpatterns.builder;

import java.util.Hashtable;

/**
 * <project> algoPractice
 *
 * <p> 原型模式
 * 用原型实例指定创建对象的种类，并且通过拷贝这些原型创建新的对象
 *
 * java 的克隆是浅克隆，碰到对象引用的时候，克隆出来的对象和原对象中的引用将指向同一个对象。
 * 通常实现深克隆的方法是将对象进行序列化，然后再进行反序列化
 *
 * @author penggs
 * @since 2020-10-25
 */
public class ApPrototype {
    public static abstract class Shape implements Cloneable {
        private String id;
        protected String type;

        abstract void draw();

        public String getType(){
            return type;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Object clone() {
            Object clone = null;
            try {
                clone = super.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            return clone;
        }

        @Override
        public String toString() {
            return "Shape{" +
                    "id='" + id + '\'' +
                    ", type='" + type + '\'' +
                    ", hashcode='" + hashCode() + '\'' +
                    '}';
        }
    }

    public static class Rectangle extends Shape {
        public Rectangle() {
            type = "Rectangle";
        }
        @Override
        void draw() {
            System.out.println("Draw Rectangle");
        }
    }

    public static class Circle extends Shape {
        public Circle() {
            type = "Circle";
        }
        @Override
        void draw() {
            System.out.println("Draw Circle");
        }
    }

    public static class ShapeCache {
        private static Hashtable<String, Shape> shapeMap
                = new Hashtable<String, Shape>();

        public static Shape getShape(String shapeId) {
            Shape cachedShape = shapeMap.get(shapeId);
            return (Shape) cachedShape.clone();
        }

        // 对每种形状都运行数据库查询，并创建该形状
        public static void loadCache() {
            Circle circle = new Circle();
            circle.setId("1");
            shapeMap.put(circle.getId(),circle);
            Rectangle rectangle = new Rectangle();
            rectangle.setId("2");
            shapeMap.put(rectangle.getId(),rectangle);
        }
    }

    public static void main(String[] args) {
        ShapeCache.loadCache();
        // 每次都返回的是原型的克隆
        System.out.println(ShapeCache.getShape("1"));
        System.out.println(ShapeCache.getShape("1"));
        System.out.println(ShapeCache.getShape("2"));
        System.out.println(ShapeCache.getShape("2"));
    }
}