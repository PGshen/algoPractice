package world.playtogether.aop.factory;

import world.playtogether.aop.annotation.MyTransactional;
import world.playtogether.aop.utils.ConnectionUtils;
import world.playtogether.aop.utils.TransactionManager;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * <project> algoPractice
 *
 * <p>
 *
 * @author penggs
 * @since 2021-02-27
 */
public class BeanFactory {
    public Object getBean(String name) throws Exception {
        //得到目标类的Class对象
        Class<?> clazz = Class.forName(name);
        //得到目标对象
        Object bean = clazz.newInstance();
        //得到目标类上的@MyTransactional注解
        MyTransactional myTransactional = clazz.getAnnotation(MyTransactional.class);
        //如果打了@MyTransactional注解，返回代理对象，否则返回目标对象
        if (null != myTransactional) {
            TransactionManager txManager = new TransactionManager();
            txManager.setConnectionUtils(new ConnectionUtils());
            //装配通知和目标对象
            Object proxyBean = getProxy(bean, txManager);
            //返回代理对象
            return proxyBean;
        }
        //返回目标对象
        return bean;
    }

    public Object getProxy(final Object target, final TransactionManager txManager) {
        Object proxy = Proxy.newProxyInstance(
                target.getClass().getClassLoader(),/*1.类加载器*/
                target.getClass().getInterfaces(), /*2.目标对象实现的接口*/
                new InvocationHandler() {/*3.InvocationHandler*/
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        try {
                            //1.开启事务
                            txManager.beginTransaction();
                            //2.执行操作
                            Object retVal = method.invoke(target, args);
                            //3.提交事务
                            txManager.commit();
                            //4.返回结果
                            return retVal;
                        } catch (Exception e) {
                            //5.回滚事务
                            txManager.rollback();
                            throw new RuntimeException(e);
                        } finally {
                            //6.释放连接
                            txManager.release();
                        }

                    }
                }
        );
        return proxy;
    }

    public void test(Object target) {
        Object proxy = Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("Proxy");
                        return method.invoke(target, args);
                    }
                }
        );
    }
}