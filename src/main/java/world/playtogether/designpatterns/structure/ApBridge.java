package world.playtogether.designpatterns.structure;

/**
 * <project> algoPractice
 *
 * <p> 桥梁模式
 * 理解桥梁模式，其实就是理解代码抽象和解耦
 *
 * @author penggs
 * @since 2020-10-25
 */
public class ApBridge {
    // 作为桥梁
    interface DrawAPI {
        void draw(int radius, int x, int y);
    }

    public static class RedPen implements DrawAPI {
        @Override
        public void draw(int radius, int x, int y) {
            System.out.println("用红色笔画图，radius:" + radius + ", x:" + x + ", y:" + y);
        }
    }

    public static class GreenPen implements DrawAPI {
        @Override
        public void draw(int radius, int x, int y) {
            System.out.println("用绿色笔画图，radius:" + radius + ", x:" + x + ", y:" + y);
        }
    }

    public static abstract class Shape {
        // 此处使用
        protected DrawAPI drawAPI;
        protected Shape(DrawAPI drawAPI) {
            this.drawAPI = drawAPI;
        }
        public abstract void draw();
    }

    // 圆形
    public static class Circle extends Shape {
        private int radius;
        public Circle(int radius, DrawAPI drawAPI) {
            super(drawAPI);
            this.radius = radius;
        }
        public void draw() {
            drawAPI.draw(radius, 0, 0);
        }
    }
    // 长方形
    public static class Rectangle extends Shape {
        private int x;
        private int y;
        public Rectangle(int x, int y, DrawAPI drawAPI) {
            super(drawAPI);
            this.x = x;
            this.y = y;
        }
        public void draw() {
            drawAPI.draw(0, x, y);
        }
    }

    public static void main(String[] args) {
        // 通过桥梁模式抽象，将DrawAPI 与 Shape 解耦
        Shape greenCircle = new Circle(10, new GreenPen());
        Shape redRectangle = new Rectangle(4, 8, new RedPen());
        greenCircle.draw();
        redRectangle.draw();
    }
}