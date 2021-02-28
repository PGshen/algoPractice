package world.playtogether.aop;

import world.playtogether.aop.factory.BeanFactory;
import world.playtogether.aop.service.UserService;

/**
 * <project> algoPractice
 *
 * <p> AOP简单实现事务切面支持
 *     代码来源：https://zhuanlan.zhihu.com/p/63126398
 *
 * @author penggs
 * @since 2021-02-27
 */
public class AopTest {
    public static void main(String[] args) {
        BeanFactory beanFactory = new BeanFactory();
        try {
            Object bean = beanFactory.getBean("world.playtogether.aop.service.UserServiceImpl");
            System.out.println(bean.getClass().getName());
            UserService userService = (UserService) bean;
            userService.getUser();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}