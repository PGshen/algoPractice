package world.playtogether.busi.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <project> algoPractice
 *
 * <p> 自定义注解获取方法信息
 *
 * @author penggs
 * @since 2021-04-18 11:45
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface GetMethodInfo {
}
