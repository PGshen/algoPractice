package world.playtogether.busi.annotation;

/**
 * <project> algoPractice
 *
 * <p>
 *
 * @author penggs
 * @since 2021-04-18 11:57
 */
public class Test {

    @GetMethodInfo
    public String hello(String arg) {
        return "Hello, " + arg + " !";
    }


    public static void main(String[] args) {
        new Test().hello("PP");
    }
}