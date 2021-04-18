package world.playtogether.busi.annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.util.Arrays;

/**
 * <project> algoPractice
 *
 * <p>
 *
 * @author penggs
 * @since 2021-04-18 11:47
 */
@Aspect
public class GetMethodInfoAspect {

    @Pointcut("@annotation(world.playtogether.busi.annotation.GetMethodInfo)")
    public void pointCut() {
    }

    @Around(value = "pointCut()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("Method Args: ");
        Arrays.asList(joinPoint.getArgs()).forEach(System.out::println);
        Object ret = joinPoint.proceed();
        System.out.println("Method Return: " + ret);
        return ret;
    }
}