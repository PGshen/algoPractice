package world.playtogether.designpatterns.builder;

/**
 * <project> algoPractice
 *
 * <p> 建造者模式
 * Food food = new FoodBuilder().a().b().c().build();
 * 先 new 一个 Builder，然后可以链式地调用一堆方法，最后再调用一次 build() 方法，我们需要的对象就有了
 *
 * 适合属性很多，而且有些必填，有些选填的时候
 *
 * 使用lombok的@Builder注解可以达到下面的效果
 *
 * @author penggs
 * @since 2020-10-25
 */
public class ApBuilder {
    static class User {
        private String name;
        private String password;
        private int age;

        // 私有化
        private User(String name, String password, int age) {
            this.name = name;
            this.password = password;
            this.age = age;
        }

        public static UserBuilder builder() {
            return new UserBuilder();
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", password='" + password + '\'' +
                    ", age=" + age +
                    '}';
        }

        public static class UserBuilder {
            // 和 User 一模一样的一堆属性
            private String name;
            private String password;
            private int age;

            private UserBuilder() {}

            // 链式调用设置各个属性值，返回 this，即 UserBuilder
            public UserBuilder name(String name) {
                this.name = name;
                return this;
            }

            public UserBuilder password(String password) {
                this.password = password;
                return this;
            }

            public UserBuilder age(int age) {
                this.age = age;
                return this;
            }

            // build() 方法负责将 UserBuilder 中设置好的属性“复制”到 User 中。
            // 当然，可以在 “复制” 之前做点检验
            public User build() {
                if (name == null || password == null) {
                    throw new RuntimeException("用户名和密码必填");
                }
                if (age <= 0 || age >= 150) {
                    throw new RuntimeException("年龄不合法");
                }
                return new User(name, password, age);
            }
        }
    }

    public static void main(String[] args) {
        User user = User.builder().name("foo").password("ppps").age(12).build();
        System.out.println(user);
    }
}