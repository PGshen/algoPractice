package world.playtogether.designpatterns.structure;

/**
 * <project> algoPractice
 *
 * <p> 代理模式
 * 代理模式说白了就是做 “方法包装” 或做 “方法增强”。
 * 在面向切面编程中，其实就是动态代理的过程。比如 Spring 中，我们自己不定义代理类，但是 Spring 会帮我们动态来定义代理，然后把我们定义在 @Before、@After、@Around 中的代码逻辑动态添加到代理中。
 *
 * Spring 中实现动态代理有两种，一种是如果我们的类定义了接口，那么采用 JDK 的动态代理;另一种是我们自己没有定义接口的，Spring 会采用 CGLIB 进行动态代理，它是一个 jar 包
 *
 * @author penggs
 * @since 2020-10-25
 */
public class ApProxy {
    /**
     * 接口
     */
    public interface LogService {
        void info(String msg);
        void error(String msg);
    }

    /**
     * 实现类
     */
    public static class LogServiceImpl implements LogService {

        @Override
        public void info(String msg) {
            System.out.println("[INFO]: " + msg);
        }

        @Override
        public void error(String msg) {
            System.out.println("[ERROR]: " + msg);
        }
    }

    /**
     * 代理类
     */
    public static class LogServiceProxy implements LogService {
        private LogService logService = new LogServiceImpl();

        @Override
        public void info(String msg) {
            System.out.println("before enhance");
            logService.info(msg);
            System.out.println("after enhance");
        }

        @Override
        public void error(String msg) {
            System.out.println("before enhance");
            logService.error(msg);
            System.out.println("after enhance");
        }
    }

    public static void main(String[] args) {
        LogService logService = new LogServiceProxy();
        logService.info("Test Proxy");
        logService.error("Test Proxy");
    }
}