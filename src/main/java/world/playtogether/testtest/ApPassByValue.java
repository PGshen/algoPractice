package world.playtogether.testtest;

/**
 * <project> algoPractice
 *
 * <p> 值传递
 *
 * @author penggs
 * @since 2020-09-27
 */
public class ApPassByValue {
    static class Student {
        String name;

        Student(String name) {
            this.name = name;
        }
    }

    /**
     * 传递的是对象的引用的拷贝（也是传值，只不过值是对象的引用），所以交换并不能生效
     * @param s1
     * @param s2
     */
    public static void swap(Student s1, Student s2) {
        Student tmp = s1;
        s1 = s2;
        s2 = tmp;
    }

    /**
     * 传递的是对象的引用的拷贝，形参和实参指向的对象是相同的，所以修改会生效
     * 数据同理
     * @param s1
     * @param s2
     */
    public static void change(Student s1, Student s2) {
        s1.name = "Tom";
        s2.name = "Bob";
    }

    /**
     * 基本类型传递是值的拷贝，因此交换也不会生效
     * @param a
     * @param b
     */
    public static void swap(int a, int b) {
        int tmp = a;
        a = b;
        b = a;
    }

    /**
     * 综上，Java方法只有传值没有传引用
     */

    public static void main(String[] args) {
        Student s1 = new Student("Bob");
        Student s2 = new Student("Tom");
        swap(s1, s2);
        System.out.println(s1.name + "," + s2.name);
        change(s1, s2);
        System.out.println(s1.name + "," + s2.name);
        int a = 1, b = 2;
        swap(a, b);
        System.out.println(a + "," + b);
    }
}