package world.playtogether.designpatterns.structure;

/**
 * <project> algoPractice
 *
 * <p> 门面模式
 * 客户端不再需要关注实例化时应该使用哪个实现类，直接调用门面提供的方法就可以
 *
 * @author penggs
 * @since 2020-10-25
 */
public class ApFacade {
    interface Shape {
        void draw();
    }

    static class Circle implements Shape {
        @Override
        public void draw() {
            System.out.println("Circle.draw()");
        }
    }

    static class Rectangle implements Shape {
        @Override
        public void draw() {
            System.out.println("Rectangle.draw()");
        }
    }

    /**
     * 门面
     */
    static class ShapeMaker {
        private Shape circle;
        private Shape rectangle;

        public ShapeMaker() {
            circle = new Circle();
            rectangle = new Rectangle();
        }

        public void drawCircle() {
            circle.draw();
        }

        public void drawRectangle() {
            rectangle.draw();
        }
    }

    public static void main(String[] args) {
        // 不需要关心实例化哪个对象
        ShapeMaker shapeMaker = new ShapeMaker();
        shapeMaker.drawCircle();
        shapeMaker.drawRectangle();
    }
}