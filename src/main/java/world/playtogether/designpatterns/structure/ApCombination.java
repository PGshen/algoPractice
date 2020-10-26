package world.playtogether.designpatterns.structure;

import java.util.ArrayList;
import java.util.List;

/**
 * <project> algoPractice
 *
 * <p> 组合模式
 * 组合模式用于表示具有层次结构的数据，使得我们对单个对象和组合对象的访问具有一致性
 *
 * @author penggs
 * @since 2020-10-25
 */
public class ApCombination {
    static class Dept {
        private Integer id;
        private String name;
        private List<Dept> subDept;

        public Dept(Integer id, String name) {
            this.id = id;
            this.name = name;
            this.subDept = new ArrayList<>();
        }

        public void add(Dept dept) {
            subDept.add(dept);
        }

        public void remove(Dept dept) {
            subDept.remove(dept);
        }

        public List<Dept> getSubDept() {
            return subDept;
        }

        @Override
        public String toString() {
            return "Dept{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}